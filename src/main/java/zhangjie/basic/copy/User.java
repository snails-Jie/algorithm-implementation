package zhangjie.basic.copy;

import java.io.Serializable;

/**
 * @Author zhangjie
 * @Date 2020/7/6 16:46
 **/
public class User implements Cloneable, Serializable {
    private String firstName;
    private String lastName;
    private Address address;

    public User(String firstName, String lastName, Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
    }

    /**
     * 复制构造函数
     * @param that
     */
    public User(User that) {
        this(that.getFirstName(), that.getLastName(), new Address(that.getAddress()));
    }

    @Override
    public Object clone() {
        User user = null;
        try {
            user = (User) super.clone();
        } catch (CloneNotSupportedException e) {
            user = new User(
                    this.getFirstName(), this.getLastName(), this.getAddress());
        }
        user.address = (Address) this.address.clone();
        return user;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
