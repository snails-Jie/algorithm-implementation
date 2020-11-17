package zhangjie.object.apache;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

/**
 * @ClassName MyPoolableObjectFactory
 * @Description: 创建工厂类
 * @author: zhangjie
 * @Date: 2020/11/17 21:43
 **/
public class MyPoolableObjectFactory extends BasePooledObjectFactory<Resource> {
    /**
     * 创建一个对象实例
     * @return
     * @throws Exception
     */
    @Override
    public Resource create() throws Exception {
        return new Resource();
    }

    /**
     * 包裹创建的对象实例，返回一个pooledobject
     * @param obj
     * @return
     */
    @Override
    public PooledObject<Resource> wrap(Resource obj) {
        return new DefaultPooledObject<Resource>(obj);
    }


}
