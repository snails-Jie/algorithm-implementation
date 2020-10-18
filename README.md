### [解决多定时任务执行阻塞问题](https://blog.csdn.net/LYM0721/article/details/89499588)
1. 定时任务默认使用单线程<br>
  1.1 场景复现<br/>
    (1) 参考block目录下的ScheduledTask<br>
2. 解决方案<br/>
  2.1 使用@Async注解实现异步任务<br/>
    (1) 参考aync目录下的ScheduledTask <br/>
    (2) 启动类配合加上 @EnableAsync <br/>
  2.2 手动设置定时任务的线程池大小 <br/>
    (1) 设置参考configtask目录下的AppConfig
    (2) 定时任务使用block目录下的ScheduledTask <br/>
    
### 定时任务防重实现（一锁二发）
```$xslt
//1.锁定数据
String uuid=createUUID();//a73266fc0aa411eaae330221860e9b7e
upate t_order set batch_id=#{uuid} where status=‘0’ and batch_id is null and rownum<=200;
//2.取出本线程锁定的数据
select * from t_order where status=‘0’ and batch_id=#{uuid};
foreach i++{
    Send(i)；
    update t_order set status=#{status} where id=#{id} and status='0' and batch_id=#{uuid}
}
```


### 定时任务优雅停机
1. kill -9 和 kill -15 有什么区别？<br/>
    1.1 kill -9 pid 可以理解为操作系统从内核级别强行杀死某个进程 <br/>
    1.2 kill -15 pid （ctrl + c）则可以理解为发送一个通知，告知应用主动关闭 <br/>
2.  springboot优雅停机原理简易分析 <br/>
    2.1 容器初始化时，注册钩子函数：AbstractApplicationContext#registerShutdownHook <br/>
    2.2 JVM关闭时，会触发钩子函数,调用其doClose方法：AbstractApplicationContext#doClose <br/>
    2.3 关闭流程 <br/>
    (1) 发布ContextClosedEvent事件 <br/>
    (2) 销毁容器中所有的bean ：this.destroyBeans(); <br/>
    ```$xslt
        最终会调用DisposableBean#destroy
                                DefaultSingletonBeanRegistry#destroySingletons //先销毁依赖当前bean的其它bean，再销毁当前bean
                                    -->DefaultSingletonBeanRegistry#destroyBean(String beanName, @Nullable DisposableBean bean)
    ```
    （3） 关闭容器本身<br/>
3.  暴力停机的危害<br/>
    3.1 任务获取锁后还未释放锁就终止了，会导致资源被锁，无法再进行处理<br/>
        --> 正确做法：不再启动新的定时任务，并等待当前正在执行的所有定时任务执行完毕，然后才终止 <br/>
            --> 核心方法：获取对应的线程池，通过调用Executor的shutdown来通知线程池停止接收新的任务，并等待当前已经执行的任务执行完毕<br/>
4.  安全优雅地关闭定时任务<br/>
    4.1 手动指定定时任务的线程池(参考AppConfig#configureTasks) <br/>
    4.2 停机时调用起关闭函数 <br/>
        -->监听ContextClosedEvent事件，调用关闭函数(参考JobStopListener#onApplicationEvent) <br/>

### springboot 重试机制
1.  引包<br/>
    ```$xslt
    <!-- springboot 重试机制 -->
            <dependency>
                <groupId>org.springframework.retry</groupId>
                <artifactId>spring-retry</artifactId>
                <version>1.1.4.RELEASE</version>
            </dependency>
            <dependency>
                <groupId>org.aspectj</groupId>
                <artifactId>aspectjweaver</artifactId>
                <version>1.8.9</version>
            </dependency>
    ```
2.  在启动类添加@EnableRetry注解 <br/>
3.  将待重试的方法上添加@Retryable注解（参考RetryService#retryTest）<br/>



        
      

     
        