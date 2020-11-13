package zhangjie.design.patterns.common;

/**
 * 1. Command模式的根本目的在于将“行为请求者”与“行为实现者”解耦
 *   1.1 将行为抽象为对象
 * 2.通过使用Compmosite模式，可以将多个命令封装为一个“复合命令”MacroCommand
 * @ClassName CommandTest
 * @Description: 命令模式测试类
 * @author: zhangjie
 * @Date: 2020/11/12 10:47
 **/
public class CommandTest {
    public static void main(String[] args) {
        MyInvoker myInvoker = new MyInvoker();
        MyReceiver myReceiver = new MyReceiver();
        myInvoker.setCommand(new CommandImplA(myReceiver));
        myInvoker.invoke();
        myInvoker.setCommand(new CommandImplB(myReceiver));
        myInvoker.invoke();
        myInvoker.setCommand(new CommandImplC(myReceiver));
        myInvoker.invoke();
    }
}
