package zhangjie.object.pool;

/**
 * @ClassName CustomerObjectTest
 * @Description: TODO
 * @author: zhangjie
 * @Date: 2020/11/17 19:00
 **/
public class CustomerObjectTest {
    public static void main(String[] args) {
        ObjectPool<String> objPool = new DefaultObjectPool();
        objPool.createPool();
        String obj = objPool.getObject();
        objPool.returnObject(obj);
        objPool.closeObjectPool();
    }
}
