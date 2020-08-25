package zhangjie.basic.copy;

import java.io.Serializable;

/**
 * 标记接口Cloneable：指示这些类实际上是可克隆的
 * @Author zhangjie
 * @Date 2020/7/6 16:45
 **/
public class Address implements Cloneable, Serializable {
    private String street;
    private String city;
    private String country;

    public Address(String street, String city, String country) {
        this.street = street;
        this.city = city;
        this.country = country;
    }

    /**
     * 复制构造函数
     * @param that
     */
    public Address(Address that) {
        this(that.getStreet(), that.getCity(), that.getCountry());
    }

    /**
     * 重写克隆方法
     */
    @Override
    public Object clone(){
        try {
            return (Address) super.clone();
        } catch (CloneNotSupportedException e) {
            return new Address(this.street, this.getCity(), this.getCountry());
        }
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
