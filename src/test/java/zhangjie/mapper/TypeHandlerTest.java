package zhangjie.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mybatis.mapper.entity.Example;
import zhangjie.mapper.mybatis.typehandler.User;
import zhangjie.mapper.mybatis.typehandler.UserMapper;

import javax.annotation.Resource;

/**
 * @Author zhangjie
 * @Date 2020/9/7 21:33
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MapperApplication.class)
public class TypeHandlerTest {

    @Resource
    private UserMapper userMapper;

    @Test
    public void test(){
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("name","abel533");
        example.setDistinct(true);
        User user = userMapper.selectOneByExample(example);
        System.out.println(user);
    }
}
