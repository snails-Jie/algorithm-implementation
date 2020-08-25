package zhangjie.basic.equals;

/**
 * @Author zhangjie
 * @Date 2020/7/6 17:47
 **/
public class MoneyEqual {
    int amount;
    String currencyCode;

    public MoneyEqual(int amount, String currencyCode) {
        this.amount = amount;
        this.currencyCode = currencyCode;
    }

    /**
     * 1. 重写equals（）方法，以便它不仅考虑对象身份，还考虑两个相关属性的值
     * 2. equals（）合约
     *   2.1 反身：一个对象必须等于自己
     *   2.2 对称：x.equals（y）必须返回与y.equals（x）相同的结果
     *   2.3 可传递的：如果x.equals（y）和y.equals（z）则也x.equals（z）
     *   2.4 一致的：仅当equals（）中包含的属性发生更改时，equals（）的值才应更改（不允许随机性）
     * 3. hashCode（）合同
     *   3.1 内部一致性： 仅当equals（）中的属性更改时，hashCode（）的值才可能更改
     *   3.2 等于一致性： 彼此相等的对象必须返回相同的hashCode
     *   3.3 冲突：不相等的对象可能具有相同的hashCode
     */
    @Override
    public boolean equals(Object o) {
        if (o == this){
            return true;
        }

        if (!(o instanceof MoneyEqual)) {
            return false;
        }

        MoneyEqual other = (MoneyEqual)o;
        boolean currencyCodeEquals = (this.currencyCode == null && other.currencyCode == null)
                || (this.currencyCode != null && this.currencyCode.equals(other.currencyCode));
        return this.amount == other.amount && currencyCodeEquals;
    }
}
