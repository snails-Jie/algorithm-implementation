package zhangjie.scheduled.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName SheduleUtil
 * @Description: 定时任务关闭
 * @author: zhangjie
 * @Date: 2020/10/18 10:48
 **/
@Component
@Slf4j
public class ScheduleUtil {
    public static final int shutdownWaitTime = 5;

    @Resource
    private ScheduledExecutorService taskExecutor;

    public void stopJobs(){
        taskExecutor.shutdown();
        try {
            if(taskExecutor.awaitTermination(shutdownWaitTime, TimeUnit.SECONDS)){
                log.warn("Executor did not terminate in the specified time.");
                List<Runnable> droppedTasks =  taskExecutor.shutdownNow();
                log.error("Executor was abruptly shut down. " + droppedTasks.size() + " tasks will not be executed.");
            }
        } catch (Exception e) {
            log.error("stop service awaitTermination failed.", e);
            taskExecutor.shutdownNow();
        }
    }
}
