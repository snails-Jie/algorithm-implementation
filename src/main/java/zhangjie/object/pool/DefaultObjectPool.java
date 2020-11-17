package zhangjie.object.pool;

/**
 * @ClassName DefaultObjectPool
 * @Description: TODO
 * @author: zhangjie
 * @Date: 2020/11/17 18:58
 **/
public class DefaultObjectPool extends ObjectPool<String> {

    @Override
    public PooledObject<String> create() {
        return new PooledObject<String>(new String("" + 1));
    }
}
