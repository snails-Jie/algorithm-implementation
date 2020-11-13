package zhangjie.design.patterns.common;

/**
 * @ClassName CommandImplA
 * @Description: 命令实现A，持有接收者对象
 * @author: zhangjie
 * @Date: 2020/11/12 10:37
 **/
public class CommandImplA implements Command {

    private MyReceiver myReceiver;

    public CommandImplA(MyReceiver myReceiver) {
        this.myReceiver = myReceiver;
    }

    @Override
    public void action() {
        myReceiver.executeA();
    }
}
