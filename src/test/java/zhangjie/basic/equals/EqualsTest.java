package zhangjie.basic.equals;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @Author zhangjie
 * @Date 2020/7/6 17:43
 **/
public class EqualsTest {

    /**
     * Object类中equals（）的默认实现表示相等性与对象标识相同
     */
    @Test
    public void test(){
        Money income = new Money(55, "USD");
        Money expenses = new Money(55, "USD");
        boolean balanced = income.equals(expenses);
        assertThat(balanced).isFalse();
    }

    @Test
    public void testOverrideEquals(){
        MoneyEqual income = new MoneyEqual(55, "USD");
        MoneyEqual expenses = new MoneyEqual(55, "USD");
        boolean balanced = income.equals(expenses);
        assertThat(balanced).isTrue();
    }
}
