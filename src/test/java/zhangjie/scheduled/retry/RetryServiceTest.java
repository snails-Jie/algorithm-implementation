package zhangjie.scheduled.retry;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import zhangjie.scheduled.ScheduledApplication;

import javax.annotation.Resource;

/**
 * @ClassName RetryService
 * @Description: TODO
 * @author: zhangjie
 * @Date: 2020/10/16 22:57
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ScheduledApplication.class)
public class RetryServiceTest {

    @Resource
    private RetryService retryService;

    @Test
    public void test() throws Exception {
        retryService.retryTest();
    }
}
