package zhangjie.scheduled.shutdown;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;

/**
 * 关闭时执行destroy方法
 * 1. 可通过dao层恢复表中状态
 * @ClassName TestDisposableBean
 * @Description: TODO
 * @author: zhangjie
 * @Date: 2020/10/17 9:59
 **/
@Component
public class TestDisposableBean implements DisposableBean {
    @Override
    public void destroy() throws Exception {
        System.out.println("测试Bean 已销毁");
    }
}
