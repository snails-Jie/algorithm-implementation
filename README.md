### spring aop
#### [实现自定义Spring Aop注解](https://github.com/eugenp/tutorials)
参考资料：https://mp.weixin.qq.com/s/OE69mnycfujmPQwTwXHOPg
1. 添加maven依赖
    ```
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-aop</artifactId>
    </dependency>
    ```

2. 创建自定义注释
    ```
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface LogExecutionTime {
     
    }
    ```
   2.1 @Target告诉注解在哪里合适<br/>
   2.2 @Retention只是规定标注是否会提供给JVM在运行与否<br/>
   &nbsp; (1) 默认情况下不是，因此Spring AOP将无法看到注释。这就是为什么要重新配置它的原因<br/>

3.  创建Pointcut和Advice
    ```
    @Around("@annotation(LogExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        return joinPoint.proceed();
    }
    ```
    3.1 也可以使用@Pointcut定义切点
    ```
      @Pointcut("execution(* your_package.controller..*(..))")
      public void requestServer() {
      }  
    ```
    3.2 通知注解<br/>
        (1) @Before => 在切点之前执行代码 <br/>
        (2) @After => 在切点之后执行代码 <br/>
        (3) @AfterReturning => 切点返回内容后执行代码，可以对切点的返回值进行封装 <br/>
        (4) @AfterThrowing => 切点抛出异常后执行 <br/>
        (5) @Around => 环绕，在切点前后执行代码 <br/>

3.  完整切面代码
    ```
    @Aspect
    @Component
    public class ExampleAspect {
        @Pointcut("execution(* your_package.controller..*(..))")
        public void requestServer() {
        }

        @Before("requestServer()")
        public void doBefore(JoinPoint joinPoint) {
        }
    }
    ```
4.  关于traceId 跟踪定位，可以根据traceId跟踪整条调用链<br/>
    4.1 添加拦截器
    ```
        public class LogInterceptor implements HandlerInterceptor {
            private final static String TRACE_ID = "traceId";
        
            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
                String traceId = java.util.UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
                ThreadContext.put("traceId", traceId);
        
                return true;
            }
        
            @Override
            public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
                    throws Exception {
            }
        
            @Override
            public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
                    throws Exception {
                ThreadContext. remove(TRACE_ID);
            }
        }
    ```
    4.2 修改日志配置文件 在原来的日志格式中 添加traceId的占位符
    ```
        <property name="pattern">[TRACEID:%X{traceId}] %d{HH:mm:ss.SSS} %-5level %class{-1}.%M()/%L - %msg%xEx%n</property>
    ```     
     
 
