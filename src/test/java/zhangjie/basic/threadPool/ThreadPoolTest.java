package zhangjie.basic.threadPool;

import org.junit.Test;

import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @Author zhangjie
 * @Date 2020/7/6 18:04
 **/
public class ThreadPoolTest {

    // Maximum number of threads in thread pool
    static final int MAX_T = 3;

    /**
     * 1.创建一个要执行的任务（可运行对象）
     * 2.使用执行程序创建执行程序池
     * 3.将任务传递给执行程序池
     * 4.关闭执行程序池
     */
    @Test
    public void test(){
        // creates five tasks
        Runnable r1 = new Task("task 1");
        Runnable r2 = new Task("task 2");
        Runnable r3 = new Task("task 3");
        Runnable r4 = new Task("task 4");
        Runnable r5 = new Task("task 5");

        // creates a thread pool with MAX_T no. of
        // threads as the fixed pool size(Step 2)
        ExecutorService pool = Executors.newFixedThreadPool(MAX_T);

        // passes the Task objects to the pool to execute (Step 3)
        pool.execute(r1);
        pool.execute(r2);
        pool.execute(r3);
        pool.execute(r4);
        pool.execute(r5);

        // pool shutdown ( Step 4)
        pool.shutdown();
    }

    @Test
    public void testLinkeBlockingQueue(){
        SynchronousQueue queue = new SynchronousQueue();
        TaskTest task1 = new TaskTest("zhangjie");
        TaskTest task2 = new TaskTest("zhangjie");
        assertThat(task1.equals(task2)).isTrue();

        queue.offer(task1);
        queue.offer(task2);

        assertThat(queue.size()).isEqualTo(1);
    }

    class TaskTest{
        private String name;

        public TaskTest(String name) {
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            TaskTest taskTest = (TaskTest) o;
            return Objects.equals(name, taskTest.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }
    }
}
