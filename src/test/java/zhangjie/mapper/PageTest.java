package zhangjie.mapper;

import com.github.pagehelper.PageHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mybatis.mapper.entity.Example;
import zhangjie.mapper.mybatis.typehandler.User;
import zhangjie.mapper.mybatis.typehandler.UserMapper;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author zhangjie
 * @Date 2020/9/7 22:29
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MapperApplication.class)
public class PageTest {
    @Resource
    private UserMapper userMapper;

    @Test
    public void test(){
        PageHelper.startPage(2,2);
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("name","zz");
        example.setDistinct(true);
        List<User> user = userMapper.selectByExample(example);
        System.out.println(user);
    }
}
