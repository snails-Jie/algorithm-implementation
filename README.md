### 参考资料：https://juejin.im/post/6844904183775133703
1. 使用进程内的队列或者线程池来实现异步调用的话，一定要尽可能的保证JVM进程的优雅关闭，保证他们在关闭前被执行完<br/>
2. 在 Spring Framework 的 Spring Task 模块，提供了 @Async 注解，可以添加在方法上，自动实现该方法的异步调用 <br/>
  2.1 在实现原理上，也是基于 Spring AOP 拦截，实现异步提交该操作到线程池中，达到异步调用的目的<br/>
  2.2  Spring Task 是 Spring Framework 的模块,所以在我们引入 spring-boot-web 依赖后，无需特别引入它<br/>
3. Spring Boot TaskExecutionAutoConfiguration 自动化配置类， 实现了Spring Task 自动配置，创建了ThreadPoolTaskExecutor基于线程池的任务执行器<br/>
  3.1 实际上ThreadPoolTaskExecutor就是ThreadPoolExecutor的分装，主要增加执行任务，并返回 ListenableFuture 对象功能<br/>
  3.2  Spring Task 调度任务的配置 ，对应TaskExecutionProperties配置类<br/>
4. spring.task.execution.shutdown配置关闭，是为了实现Spring Task的优雅关闭<br/>
  4.1 通过配置**await-termination: true**,实现在应用关闭时，等待异步任务执行完成。这样在应用关闭时，Spring 会等待 ThreadPoolTaskExecutor执行完任务，再销毁Bean<br/>
  4.2 应用关闭时，在某些业务场景下我们不可能让Spring一直等待，异步任务的完成。通过配置**await-termination-period: 60**,设置Spring最大等待时间，时间一到将不再等待异步任务完成<br/>
5.  等待异步调用完成 中，我们看到的 AsyncResult类 表示异步结果。返回结果分为两种情况<br/>
  5.1 执行成功时，调用AsyncResult#forValue(V value) 静态方法，返回成功的 ListenableFuture对象<br/>
  5.2 执行异常时，调用 AsyncResult#forExecutionException(Throwable ex) 静态方法，返回异常的 ListenableFuture 对象<br/>
6. AsyncResult 同时也实现了 ListenableFuture接口，提供异步执行结果回调处理<br/>
    ```
     public void addCallback(ListenableFutureCallback<? super V> callback) {
            this.addCallback(callback, callback);
        }
    
        public void addCallback(SuccessCallback<? super V> successCallback, FailureCallback failureCallback) {
            try {
                if (this.executionException != null) {
                    failureCallback.onFailure(exposedException(this.executionException));
                } else {
                    successCallback.onSuccess(this.value);
                }
            } catch (Throwable var4) {
            }
    
        }
    ```
  
