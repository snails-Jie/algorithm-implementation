package zhangjie.aysn.springboot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.Future;


/**
 * 1. 定义 execute01和 execute02方法，分别模拟sleep 10秒和5秒
 * 2. 同时在方法中，使用logger打印日志，方便我们看到每个方法的执行时间，和执行的线程
 * @Author zhangjie
 * @Date 2020/8/25 21:56
 **/
@Service
public class DemoService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    public Integer execute01() {
        logger.info("[execute01]");
        sleep(10);
        return 1;
    }

    public Integer execute02() {
        logger.info("[execute02]");
        sleep(5);
        return 2;
    }

    private static void sleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Async
    public Integer execute01Async() {
        return this.execute01();
    }

    @Async
    public Integer execute02Async() {
        return this.execute02();
    }

    @Async
    public Future<Integer> execute01AsyncWithFuture() {
        return AsyncResult.forValue(this.execute01());
    }

    @Async
    public Future<Integer> execute02AsyncWithFuture() {
        return AsyncResult.forValue(this.execute02());
    }


    @Async
    public ListenableFuture<Integer> execute01AsyncWithListenableFuture() {
        try {
            //int i = 1 / 0;
            return AsyncResult.forValue(this.execute02());
        } catch (Throwable ex) {
            return AsyncResult.forExecutionException(ex);
        }
    }

}
