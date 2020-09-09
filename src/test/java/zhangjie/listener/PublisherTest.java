package zhangjie.listener;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import zhangjie.listener.service.publisher.UserServiceImpl;

import javax.annotation.Resource;

/**
 * @ClassName PublisherTest
 * @Description: TODO
 * @author: zhangjie
 * @Date: 2020/9/9 15:37
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class PublisherTest {

    @Resource
    private UserServiceImpl userService;

    /** 无序 */
    @Test
    public void test(){
        userService.register("测试");
    }

}
