package zhangjie.scheduled.retry;

import org.springframework.remoting.RemoteAccessException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

/**
 * @ClassName RetryService
 * @Description: TODO
 * @author: zhangjie
 * @Date: 2020/10/16 22:57
 **/
@Service
public class RetryService {
    /**
     * @Retryable 标注此注解的方法在发生异常时会进行重试
     *  1. value：指定处理的异常类
     *  2. include：指定处理的异常类和value一样，默认为空，当exclude也为空时，默认所有异常
     *  3. exclude：指定异常不处理，默认空，当include也为空时，默认所有异常
     *  4. maxAttempts：最大重试次数。默认3次
     *  5.  backoff： 重试等待策略。默认使用@Backoff注解
     *      5.1 不设置参数时，默认使用FixedBackOffPolicy（指定等待时间），重试等待1000ms
     *      5.2 设置delay,使用FixedBackOffPolicy（指定等待时间），重试等待填写的时间
     *      5.3 multiplier即指定延迟倍数，比如delay=5000l,multiplier=2
     * @throws Exception
     */
    @Retryable(value = {RemoteAccessException.class},maxAttempts = 5,backoff = @Backoff(delay = 5000L,multiplier = 1))
    public void retryTest() throws Exception {
        System.out.println(LocalTime.now() + " do something...");
        throw new RemoteAccessException("RemoteAccessException....");
    }

    /**
     * 1. @Recover用于@Retryable重试失败后处理方法
     * 2. 此注解注释的方法参数一定要是@Retryable抛出的异常，否则无法识别
     * @param e
     */
    @Recover
    public void recover(RemoteAccessException e){
        System.out.println(e.getMessage());
        System.out.println("recover....");
    }
}
