package zhangjie.mapper.mybatis.extend;

import org.apache.ibatis.mapping.MappedStatement;
import tk.mybatis.mapper.mapperhelper.MapperHelper;
import tk.mybatis.mapper.mapperhelper.MapperTemplate;
import tk.mybatis.mapper.mapperhelper.SqlHelper;

/**
 *
 * @Author zhangjie
 * @Date 2020/9/6 19:57
 **/
public class MySelectProvider extends MapperTemplate {

    public MySelectProvider(Class<?> mapperClass, MapperHelper mapperHelper) {
        super(mapperClass, mapperHelper);
    }

    /**
     * 1. 其中 selectAll 方法名要和接口中定义的方法名一致。其次就是该方法的参数为 MappedStatement 类型
     * 查询全部结果
     * @param ms
     * @return
     */
    public String selectAll(MappedStatement ms) {
        //首先是获取了当前接口的实体类型
        final Class<?> entityClass = getEntityClass(ms);
        //修改返回值类型为实体类型
        setResultType(ms, entityClass);
        //拼接 XML 形式的 SQL
        StringBuilder sql = new StringBuilder();
        sql.append(SqlHelper.selectAllColumns(entityClass));
        sql.append(SqlHelper.fromTable(entityClass, tableName(entityClass)));
        sql.append(SqlHelper.orderByDefault(entityClass));
        return sql.toString();
    }
}
