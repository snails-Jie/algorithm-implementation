package zhangjie.scheduled.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Service;
import zhangjie.scheduled.utils.ScheduleUtil;

import javax.annotation.Resource;

/**
 * @ClassName JobStopListener
 * @Description: ContextClosedEvent事件监听器
 * @author: zhangjie
 * @Date: 2020/10/18 11:26
 **/
@Service
@Slf4j
public class JobStopListener implements ApplicationListener {

    @Resource
    private ScheduleUtil scheduleUtil;

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        // 在spring bean容器销毁之前执行的事件，防止数据库连接池在任务终止前销毁
        if(event instanceof ContextClosedEvent){
            log.info("监听到容器关闭事件.....");
            scheduleUtil.stopJobs();
        }
    }
}
