package zhangjie.spring.aop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


/**
 * 1. 每个信息都打印一行，在高并发请求下确实会出现请求之间打印日志串行的问题
 *  --> 每个信息都打印一行，在高并发请求下确实会出现请求之间打印日志串行的问题
 *    --> 因此构造一个对象(参考：RequestInfo)
 * @ClassName RequestLogAspect
 * @Description: 日志切面
 * @author: zhangjie
 * @Date: 2020/11/5 22:02
 **/
//@Aspect
//@Component
@Slf4j
public class RequestLogAspect {

    @Pointcut("execution(* zhangjie.spring.aop.controller..*(..))")
    public void requestServer() {
    }

    @Before("requestServer()")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes)
                RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        log.info("===============================Start========================");
        log.info("IP                 : {}", request.getRemoteAddr());
        log.info("URL                : {}", request.getRequestURL().toString());
        log.info("HTTP Method        : {}", request.getMethod());
        log.info("Class Method       : {}.{}", joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName());
    }

    @Around("requestServer()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        log.info("Request Params     : {}", getRequestParams(proceedingJoinPoint));
        log.info("Result               : {}", result);
        log.info("Time Cost            : {} ms", System.currentTimeMillis() - start);

        return result;
    }

    @After("requestServer()")
    public void doAfter(JoinPoint joinPoint) {
        log.info("===============================End========================");
    }

    /**
     * 获取入参
     * @param proceedingJoinPoint
     *
     * @return
     * */
    private Map<String, Object> getRequestParams(ProceedingJoinPoint proceedingJoinPoint) {
        Map<String, Object> requestParams = new HashMap<>();

        //参数名
        String[] paramNames =
                ((MethodSignature)proceedingJoinPoint.getSignature()).getParameterNames();
        //参数值
        Object[] paramValues = proceedingJoinPoint.getArgs();

        for (int i = 0; i < paramNames.length; i++) {
            Object value = paramValues[i];

            //如果是文件对象
            if (value instanceof MultipartFile) {
                MultipartFile file = (MultipartFile) value;
                value = file.getOriginalFilename();  //获取文件名
            }

            requestParams.put(paramNames[i], value);
        }

        return requestParams;
    }

}
