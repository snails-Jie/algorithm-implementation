package zhangjie.async.springboot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.concurrent.FailureCallback;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.util.concurrent.SuccessCallback;
import zhangjie.aysn.springboot.Application;
import zhangjie.aysn.springboot.service.DemoService;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @Author zhangjie
 * @Date 2020/8/25 21:58
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class DemoServiceTest {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private DemoService demoService;

    /**
     * 1. 两个方法都按顺序执行，执行时间15秒
     * 2. 都在主线程执行
     */
    @Test
    public void task01() {
        long now = System.currentTimeMillis();
        logger.info("[task01][开始执行]");

        demoService.execute01();
        demoService.execute02();

        logger.info("[task01][结束执行，消耗时长 {} 毫秒]", System.currentTimeMillis() - now);
    }

    /**
     * 1. 在execute01Async() 和 execute01Async()上，添加@Async实现异步调用
     * 2. DemoService 的两个方法，异步执行，所以主线程只消耗 39毫秒左右。注意，实际这两个方法，并没有执行完成
     * 3. DemoService 的两个方法，都在异步线程池中执行
     */
    @Test
    public void task02() {
        long now = System.currentTimeMillis();
        logger.info("[task02][开始执行]");

        demoService.execute01Async();
        demoService.execute02Async();

        logger.info("[task02][结束执行，消耗时长 {} 毫秒]", System.currentTimeMillis() - now);
    }

    /**
     * 1. 在这里两个异步方法中，添加了AsyncResult.forValue(this.execute02());,返回带有执行结果的Future对象
     * 2. 异步调用上述的两个方法,并阻塞线程等待异步调用返回结果
     *    2.1 Future对象的get()方法，效果：阻塞线程等待返回结果
     * 3. 两个异步调用方法，分别由线程池 task-1和task-2 同时执行。
     *   3.1 因为主线程阻塞等待执行结果 ，执行时间10秒，当同时有多个异步调用，线程阻塞等待，执行时间由消耗最长的异步调用逻辑所决定
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void task03() throws ExecutionException, InterruptedException {
        long now = System.currentTimeMillis();
        logger.info("[task03][开始执行]");

        // 执行任务
        Future<Integer> execute01Result = demoService.execute01AsyncWithFuture();
        Future<Integer> execute02Result = demoService.execute02AsyncWithFuture();
        // 阻塞等待结果
        execute01Result.get();
        execute02Result.get();

        logger.info("[task03][结束执行，消耗时长 {} 毫秒]", System.currentTimeMillis() - now);
    }

    /**
     * 1. 增加 #execute02() 的异步调用，并返回 ListenableFuture 对象
     * 2. 异步调用方法，在塞等待执行完成的同时，添加相应的回调 Callback 方法
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void task04() throws ExecutionException, InterruptedException {
        long now = System.currentTimeMillis();
        logger.info("[task04][开始执行]");

        // <1> 执行任务
        ListenableFuture<Integer> execute01Result = demoService.execute01AsyncWithListenableFuture();
        logger.info("[task04][execute01Result 的类型是：({})]",execute01Result.getClass().getSimpleName());
        execute01Result.addCallback(new SuccessCallback<Integer>() { // <2.1> 增加成功的回调

            @Override
            public void onSuccess(Integer result) {
                logger.info("[onSuccess][result: {}]", result);
            }

        }, new FailureCallback() { // <2.1> 增加失败的回调

            @Override
            public void onFailure(Throwable ex) {
                logger.info("[onFailure][发生异常]", ex);
            }

        });
        execute01Result.addCallback(new ListenableFutureCallback<Integer>() { // <2.2> 增加成功和失败的统一回调

            @Override
            public void onSuccess(Integer result) {
                logger.info("[onSuccess][result: {}]", result);
            }

            @Override
            public void onFailure(Throwable ex) {
                logger.info("[onFailure][发生异常]", ex);
            }

        });
        // <3> 阻塞等待结果
        execute01Result.get();

        logger.info("[task04][结束执行，消耗时长 {} 毫秒]", System.currentTimeMillis() - now);
    }




}
