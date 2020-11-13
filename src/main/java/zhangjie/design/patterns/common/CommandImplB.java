package zhangjie.design.patterns.common;

/**
 * @ClassName CommandImplB
 * @Description: 命令实现B
 * @author: zhangjie
 * @Date: 2020/11/12 10:41
 **/
public class CommandImplB implements Command {

    private MyReceiver myReceiver;

    public CommandImplB(MyReceiver myReceiver) {
        this.myReceiver = myReceiver;
    }

    @Override
    public void action() {
        this.myReceiver.executeB();
    }
}
