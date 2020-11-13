package zhangjie.design.patterns.common;

/**
 * @ClassName MyInvoker
 * @Description: 调用者，持有命令者
 * @author: zhangjie
 * @Date: 2020/11/12 10:46
 **/
public class MyInvoker {
    private Command command;

    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public void invoke(){
        command.action();
    }
}
