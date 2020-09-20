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

      