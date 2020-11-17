### [设计模式：状态模式](http://www.gxitsky.com/2019/10/20/designPatterns-10-State/)
1. 有状态对象：根据不同的状态做出不同的行为 <br/>
  1.1 状态：影响对象行为的一个或多个动态变化的属性 <br/>
#### 模式分析
1.  抽象状态类：一个抽象类来专门表示对象的状态 <br/>
    1.1 对象的具体状态都继承该类，在重写的方法实现自己状态的行为，包括各种状态迁移<br/>
2.  解决思想：把相关**判断逻辑**提取出来，放到一系列的状态类当中
#### 模式结构
1. 抽象状态（State）:抽象状态，用于封装环境对象(Context)中特定状态所对应的行为
2. 具体状态: 实现抽象状态类中的方法，方法里封装自己状态的行为
3. 环境(Context) -- 充当状态管理器角色 <br/>
    3.1 持有一个抽象状态类型的属性用于维护当前状态<br/>
    3.2 定义一个方法，在方法里将与状态相关的操作委托托给当前状态对象来处理<br/>
![状态模式](https://raw.githubusercontent.com/nicky-chen/pic_store/master/20190509163258.png)

### [Spring Statemachine](http://www.gxitsky.com/2019/12/17/springboot-app-52-spring-statemachine/)
1. 状态机可分为有限状态机和无限状态机<br/>
    1.1 有限状态机指拥有有限数量的状态<br/>
    1.2 无限状态机指拥无限数量的状态<br/>
2.  有限状态机是一种用来进行对象行为建模的工具 <br/>
    2.1 作用：描述对象在它的生命周期内所经历的状态序列，以及如何响应来自外界的各种事件 <br/>
3.  状态机4要素 <br/>
    3.1 现态:是指当前所处的状态 <br/>
    3.2 条件：又称 事件，当一个条件被满足，将会触发一个动作，或者执行一次状态的迁移<br/>
    3.3 动作：条件满足后执行的动作<br/>
    3.4 次态：条件满足后要迁往的新状态<br/>
4. 状态机的难点不在于编码，在于理清状态的迁移图<br/>

#### spring有限状态机学习笔记
![代码实现流程](https://oscimg.oschina.net/oscnet/389a2040da9cec8562afa8a1c191b7721d1.jpg)
1. Spring 状态机选择器不支持事件触发<br/>
  1.1 状态选择器：withChoice() <br/>
  1.2 使用事件触发情况下只能使用接口Guard进行辅助<br/>
  1.3 如果想实现check后业务处理 --> action实现
    ```
    .withChoice()
                        .source(ComplexFormStates.CHECK_CHOICE)
                        .first(ComplexFormStates.CONFIRM_FORM, new ComplexFormCheckChoiceGuard(),new ComplexFormChoiceAction())
                        .last(ComplexFormStates.DEAL_FORM,new ComplexFormChoiceAction())
                        .and()
    ```
2. 项目中需要考虑的问题<br/>
  2.1 多种流程怎么处理（例如，订单流程与运单流程、不同类型的运单处理流程）<br/>
  2.2 参数问题（例如传订单号以及入库信息怎么传）<br/>
  2.3 存储问题<br/>
    (1) 状态机如果有多个，需要时从哪取，暂时不需要放哪<br/>
3. 多个状态机的做法  --> builder <br/>
    3.1 machineId是状态机的配置类和事件实现类的关联<br/>
    ```
    builder.configureConfiguration()
                .withConfiguration()
                .machineId(MACHINEID)
                .beanFactory(beanFactory);
    
    @WithStateMachine(id="orderMachine")
    public class OrderEventConfig {
    @OnTransition(target = "UNPAID")
    public void create() {
       logger.info("---订单创建，待支付---");
    }
    
    ```
    3.2 在controller类里注入StateMachineBuilder<br/>
    (1) 状态机应该和业务对象一一对应<br/>
4. 传递参数的message <br/>
  4.1 spring通用消息组成：Header + Payload <br/>
  4.1 需要把状态值传递给业务方法，同样处理状态变化也需要业务数据<br/>
    ```
    // 触发RECEIVE事件
    Order order = new Order(orderId, "547568678", "广东省深圳市", "13435465465", "RECEIVE");
    Message<OrderEvents> message = MessageBuilder.withPayload(OrderEvents.RECEIVE).setHeader("order", order).build();
    stateMachine.sendEvent(message);
    ```
    (1) 如果需要传递多个数据对象，可以多次setHeader<br/>
  4.2 状态机处理类接收参数并处理<br/>
    ```
       @OnTransition(source = "WAITING_FOR_RECEIVE", target = "DONE")
        public void receive(Message<OrderEvents> message) {
            System.out.println("传递的参数：" + message.getHeaders().get("order"));
            logger.info("---用户已收货，订单完成---");
        }
    ```
5. 持久化<br/>
   5.1 持久化到本地内存<br/>
        (1) spring提供接口：StateMachinePersist规范管理map
       （2）StateMachinePersist保存的对象是StateMachineContext，不是StateMachine，需要配置一下
    ```
     @Component
     public class InMemoryStateMachinePersist implements StateMachinePersist<OrderStates, OrderEvents, String> {
     	private Map<String, StateMachineContext<OrderStates, OrderEvents>> map = new HashMap<String, StateMachineContext<OrderStates,OrderEvents>>();
     	@Override
     	public void write(StateMachineContext<OrderStates, OrderEvents> context, String contextObj) throws Exception {
     		map.put(contextObj, context);
     	}
     	@Override
     	public StateMachineContext<OrderStates, OrderEvents> read(String contextObj) throws Exception {
     		return map.get(contextObj);
     	}
     }
    ```
    ```
    @Configuration
    public class PersistConfig {
        @Autowired
        private InMemoryStateMachinePersist inMemoryStateMachinePersist;
        /**
         * 注入StateMachinePersister对象
         * @return
         */
        @Bean(name="orderMemoryPersister")
        public StateMachinePersister<OrderStates, OrderEvents, String> getPersister() {
            return new DefaultStateMachinePersister<>(inMemoryStateMachinePersist);
        }
    }
       注意：1. InMemoryStateMachinePersist 实现的接口是org.springframework.statemachine.StateMachinePersist
            2. getPersister方法返回的是org.springframework.statemachine.persist.StateMachinePersister接口
             2.1 StateMachinePersister是可以直接保存StateMachine对象的
    ```
    ```
    //持久化stateMachine
    orderMemorypersister.persist(stateMachine, order.getId());
    //取出状态机
    orderMemorypersister.restore(stateMachine, id);
    ```
   5.2 持久化到redis <br/>
        (1)原因：一般都是多台机分布式运行<br/>
6.  伪持久化和中间段的状态机<br/>
    6.1 状态机的主要作用：规范整个订单业务流程的状态和事件<br/>
     --> 只需知道订单的状态，然后伴随业务到下一个状态节点<br/>
    6.2 任意调节状态的才是我们需要的状态机<br/>
    ```
    @Component
    public class OrderStateMachinePersist implements StateMachinePersist<OrderStates, OrderEvents, Order> {
    
        @Override
        public void write(StateMachineContext<OrderStates, OrderEvents> context, Order contextObj) throws Exception {
            //这里不做任何持久化工作
        }
    
        @Override
        public StateMachineContext<OrderStates, OrderEvents> read(Order contextObj) throws Exception {
            StateMachineContext<OrderStates, OrderEvents> result = new DefaultStateMachineContext<OrderStates, OrderEvents>(OrderStates.valueOf(contextObj.getState()), 
                    null, null, null, null, "orderMachine");
            return result;
        }
    }
    ```
      



#### 示例
1. 添加依赖：spring-statemachine-starter <br/>
2. 状态机配置：参考项目中的StateMachineConfig类<br/>
    2.1 加上@EnableStateMachine注解<br/>
    2.2 继承EnumStateMachineConfigurerAdapter<States, Events>  <br/>
      （1）在配置状态机前，先定义状态枚举：States 以及事件枚举：Events <br/>
      （2）自动启动，添加监听器：重写configure(StateMachineConfigurationConfigurer config)方法<br/>
         --> 监听器参考：StateMachineListenerAdapter <br/>
      （3）状态配置：重写configure(StateMachineStateConfigurer states)方法<br/>
      （4）状态迁移：重写configure(StateMachineTransitionConfigurer transitions)方法<br/>

#### [状态机配置](https://docs.spring.io/spring-statemachine/docs/2.1.3.RELEASE/reference/#preface)
1. @EnableStateMachine 和 @EnableStateMachineFactory* 来启用状态机所需的一些基本功能<br/>
    1.1 @EnableStateMachine <br/>
      (1) 需要配置创建 Statemachine 实例时使用<br/>
      (2) 状态机会自动检测是否使用这些适配器类（EnumStateMachineConfigurerAdapter 或 StateMachineConfigurerAdapter），并相应地修改运行时配置逻辑<br/>
    1.2 @EnableStateMachineFactory:若需要配置创建 StateMachineFactory 实例时使用<br/>
2. 状态配置<br/>
    ```
    @Configuration
    @EnableStateMachine
    public class Config1Enums extends EnumStateMachineConfigurerAdapter<States, Events> {
    
        @Override
        public void configure(StateMachineStateConfigurer states) throws Exception {
            states
                .withStates()
                    .initial(States.S1)
                    .end(States.SF)
                    .states(EnumSet.allOf(States.class));
        }
    }
    ```
3. 迁移配置<br/>
    3.1 Spring Statemachine 支持三种不同类型的迁移：external，internal，local<br/>
    3.2 迁移由 信号（发送到状态机的事件）或定时器触发<br/>
    ```
    @Configuration
    @EnableStateMachine
    public class Config3
            extends EnumStateMachineConfigurerAdapter<States, Events> {
        @Override
        public void configure(StateMachineTransitionConfigurer transitions)
                throws Exception {
            transitions
                .withExternal()
                    .source(States.S1).target(States.S2)
                    .event(Events.E1)
                    .and()
                .withInternal()
                    .source(States.S2)
                    .event(Events.E2)
                    .and()
                .withLocal()
                    .source(States.S2).target(States.S3)
                    .event(Events.E3);
        }
    }
    ```

      