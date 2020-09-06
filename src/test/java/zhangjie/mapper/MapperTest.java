package zhangjie.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mybatis.mapper.entity.Example;
import zhangjie.mapper.mybatis.mapper.SelectAllMapper;
import zhangjie.mapper.mybatis.mapper.TFilmStatisticMapper;
import zhangjie.mapper.mybatis.model.TFilmStatistic;

import javax.annotation.Resource;

/**
 * @Author zhangjie
 * @Date 2020/9/6 18:45
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MapperApplication.class)
public class MapperTest {

    @Resource
    private TFilmStatisticMapper mapper;

    @Test
    public void testDeleteByExample(){
        Example example = new Example(TFilmStatistic.class);
        example.createCriteria().andEqualTo("filmId",1);
        example.setDistinct(true);
        TFilmStatistic statistic = mapper.selectOneByExample(example);
        System.out.println(statistic.getFilmId());
    }

}
