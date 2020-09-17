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