package zhangjie.basic.string;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @Author zhangjie
 * @Date 2020/7/7 22:10
 **/
public class StringDemo {

    /**
     * 1. 字符串对象属于引用数据类型
     *   1.1 st1和st2两个字符串对象，指向的都是同一个地址
     */
    @Test
    public void equal(){
        String st1 = "abc";
        String st2 = "abc";
        assertThat(st1 == st2).isTrue();
        assertThat(st1.equals(st2)).isTrue();
    }

    /**
     *  由于st1指向的是堆内存的地址，st2看到“abc”已经在常量池存在，就不会再新建，所以st2指向了常量池的内存地址
     */
    @Test
    public void newString(){
        String st1 = new String("abc");
        String st2 = "abc";
        assertThat(st1 == st2).isFalse();
        assertThat(st1.equals(st2)).isTrue();
    }

    /**
     *1. “a”,”b”,”c”三个本来就是字符串常量，进行+符号拼接之后变成了“abc”，“abc”本身就是字符串常量（Java中有常量优化机制）
     *   -->常量池立马会创建一个“abc”的字符串常量对象
     *2. 在进行st2=”abc”,这个时候，常量池存在“abc”，所以不再创建
     */
    @Test
    public void append(){
        String st1 = "a" + "b" + "c";
        String st2 = "abc";
        assertThat(st1 == st2).isTrue();
        assertThat(st1.equals(st2)).isTrue();
    }


    /**
     * 1. 如果 String st3 = "ab" + "c"; 则st2 == st3为true
     * 2. “+”号操作最终得到是一个拼接的新的字符串
     *    --> 通过JDK API介绍得知拼接由StringBuilder或者StringBuffer的append方法实现拼接，然后调用toString。最后把字符串对象的地址赋值给变量
     */
    @Test
    public void appendEqual(){
        String st1 = "ab";
        String st2 = "abc";
        String st3 = st1 + "c";

        assertThat(st2 == st3).isFalse();
        assertThat(st2.equals(st3)).isTrue();
    }
}
