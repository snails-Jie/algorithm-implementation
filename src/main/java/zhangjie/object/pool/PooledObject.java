package zhangjie.object.pool;

/**
 * @ClassName PooledObject
 * @Description: 池化对象
 * @author: zhangjie
 * @Date: 2020/11/17 18:48
 **/
public class PooledObject<T> {
    /** 外界使用的对象 */
    private T objection = null;

    /** 此对象是否正在使用的标志，默认没有正在使用 */
    private boolean busy = false;

    /** 构造函数，池化对象 */
    public PooledObject(T objection){
        this.objection = objection;
    }
    /** 返回此对象中的对象 */
    public T getObject() {
        return objection;
    }

    /** 设置此对象的，对象 */
    public void setObject(T objection) {
        this.objection = objection;
    }

    /** 获得对象对象是否忙 */
    public boolean isBusy() {
        return busy;
    }

    /** 设置对象的对象正在忙 */
    public void setBusy(boolean busy) {
        this.busy = busy;
    }

}
