package zhangjie.basic.copy;

import com.google.gson.Gson;
import org.apache.commons.lang3.SerializationUtils;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotEquals;


/**
 * @Author zhangjie
 * @Date 2020/7/6 16:47
 **/
public class CopyTest {
    @Test
    public void whenShallowCopying_thenObjectsShouldNotBeSame() {

        Address address = new Address("Downing St 10", "London", "England");
        User pm = new User("Prime", "Minister", address);

        //浅拷贝
        User shallowCopy = new User(
                pm.getFirstName(), pm.getLastName(), pm.getAddress());

        //pm！= shallowCopy意味着它们是不同的对象
        assertThat(shallowCopy)
                .isNotSameAs(pm);
    }


    /**
     * 浅拷贝问题在于当我们更改任何原始地址的属性时，这也会影响shallowCopy的地址
     * -->深拷贝解决此问题
     */
    @Test
    public void whenModifyingOriginalObject_ThenCopyShouldChange() {

        Address address = new Address("Downing St 10", "London", "England");
        User pm = new User("Prime", "Minister", address);

        User shallowCopy = new User(
                pm.getFirstName(), pm.getLastName(), pm.getAddress());

        address.setCountry("Great Britain");
        assertThat(shallowCopy.getAddress().getCountry())
                .isEqualTo(pm.getAddress().getCountry());
    }

    /**
     * 深拷贝
     * --> 复制构造函数,对引用属性进行新建操作（new Address）
     */
    @Test
    public void whenModifyingOriginalObject_thenCopyShouldNotChange() {
        Address address = new Address("Downing St 10", "London", "England");
        User pm = new User("Prime", "Minister", address);
        User deepCopy = new User(pm);

        address.setCountry("Great Britain");
        assertNotEquals(
                pm.getAddress().getCountry(),
                deepCopy.getAddress().getCountry());
    }

    /**
     * 深拷贝
     *  --> 重写clone方法
     *  super.clone（）调用返回对象的浅表副本，但是我们手动设置了可变字段的深表副本
     */
    @Test
    public void whenModifyingOriginalObject_thenCloneCopyShouldNotChange() {
        Address address = new Address("Downing St 10", "London", "England");
        User pm = new User("Prime", "Minister", address);
        User deepCopy = (User) pm.clone();

        address.setCountry("Great Britain");

        assertThat(deepCopy.getAddress().getCountry())
                .isNotEqualTo(pm.getAddress().getCountry());
    }


    /**
     * 1.深拷贝
     *   1.1 Apache Commons Lang具有SerializationUtils＃clone，当对象图中的所有类都实现Serializable接口时，它将执行深度复制
     *   1.2 如果该方法遇到无法序列化的类，则它将失败并抛出未经检查的SerializationException
     *       -->需要将Serializable接口添加到我们的类中
     */
    @Test
    public void whenModifyingOriginalObject_thenCommonsCloneShouldNotChange() {
        Address address = new Address("Downing St 10", "London", "England");
        User pm = new User("Prime", "Minister", address);
        User deepCopy = (User) SerializationUtils.clone(pm);

        address.setCountry("Great Britain");

        assertThat(deepCopy.getAddress().getCountry())
                .isNotEqualTo(pm.getAddress().getCountry());
    }

    /**
     * 序列化的另一种方法是使用JSON序列化
     *  -->与Apache Commons Lang不同，GSON不需要Serializable接口即可进行转换
     */
    @Test
    public void whenModifyingOriginalObject_thenGsonCloneShouldNotChange() {
        Address address = new Address("Downing St 10", "London", "England");
        User pm = new User("Prime", "Minister", address);
        Gson gson = new Gson();
        User deepCopy = gson.fromJson(gson.toJson(pm), User.class);

        address.setCountry("Great Britain");

        assertThat(deepCopy.getAddress().getCountry())
                .isNotEqualTo(pm.getAddress().getCountry());
    }




}
