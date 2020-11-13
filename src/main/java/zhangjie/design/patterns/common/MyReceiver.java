package zhangjie.design.patterns.common;

/**
 * @ClassName MyReceiver
 * @Description: 接收者，主要负责执行事件
 * @author: zhangjie
 * @Date: 2020/11/12 10:38
 **/
public class MyReceiver {
    public  void executeA(){
        System.out.println("执行A事件");
    }

    public void executeB(){
        System.out.println("执行B事件");
    }

    public void executeC(){
        System.out.println("执行C事件");
    }
}
