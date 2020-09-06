package zhangjie.mapper.mybatis.mapper;

import org.apache.ibatis.annotations.SelectProvider;
import tk.mybatis.mapper.annotation.RegisterMapper;
import zhangjie.mapper.mybatis.extend.MySelectProvider;

import java.util.List;

/**
 * @Author zhangjie
 * @Date 2020/9/6 19:52
 **/
@RegisterMapper
public interface SelectAllMapper<T> {

    /**
     * 1.其中 MySelectProvider 是你要实现的一个类，该类需要继承 MapperTemplate
     * 查询全部结果
     * @return
     */
    @SelectProvider(type = MySelectProvider.class, method = "dynamicSQL")
    List<T> selectAll();
}
