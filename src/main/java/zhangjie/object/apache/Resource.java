package zhangjie.object.apache;

/**
 * @ClassName Resource
 * @Description: 需要池化的对象,缓存的对象
 * @author: zhangjie
 * @Date: 2020/11/17 21:40
 **/
public class Resource {
    private static int id;
    private int rid;

    public Resource(){
        synchronized (this){
            this.rid = id++;
        }
    }

    public int getRid(){
        return this.rid;
    }

    @Override
    public String toString() {
       return "id:" + this.rid;
    }
}
