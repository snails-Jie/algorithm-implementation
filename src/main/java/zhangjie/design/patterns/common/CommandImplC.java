package zhangjie.design.patterns.common;

/**
 * @ClassName CommandImplC
 * @Description: TODO
 * @author: zhangjie
 * @Date: 2020/11/12 10:42
 **/
public class CommandImplC implements Command {

    private MyReceiver myReceiver;

    public CommandImplC(MyReceiver myReceiver) {
        this.myReceiver = myReceiver;
    }

    @Override
    public void action() {
        this.myReceiver.executeC();
    }
}
