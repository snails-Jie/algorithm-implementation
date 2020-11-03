package zhangjie.scheduled.bug;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;

/**
 * @ClassName ScheduledExecutorTest
 * @Description: 问题重现
 * @author: zhangjie
 * @Date: 2020/10/19 10:22
 **/
public class ScheduledExecutorTest {

    private static LongAdder longAdder = new LongAdder();


    public static void main(String[] args) {

        ScheduledExecutorService scheduledExecutor = Executors.newSingleThreadScheduledExecutor();

        scheduledExecutor.scheduleAtFixedRate(ScheduledExecutorTest::doCatchTask,
                1, 1, TimeUnit.SECONDS);
    }

    /**
     * 遇到异常直接停止
     */
    public static void doTask() {
        int count = longAdder.intValue();
        longAdder.increment();

        System.out.println("定时任务开始执行 === " + count);

        // ① 下面这一段注释前和注释后的区别
        if (count == 3) {
            throw new RuntimeException("some runtime exception");
        }
    }

    public static void doCatchTask() {
        try{
            int count = longAdder.intValue();
            longAdder.increment();

            System.out.println("定时任务开始执行 === " + count);

            // ① 下面这一段注释前和注释后的区别
            if (count == 3) {
                throw new RuntimeException("some runtime exception");
            }
        }catch (Exception e){
            System.out.println("发生异常 === ");
        }

    }
}
