### 参考：https://github.com/abel533/MyBatis-Spring-Boot

### 集成
1. 引入分页插件<br/>
    ```
    <dependency>
        <groupId>com.github.pagehelper</groupId>
        <artifactId>pagehelper</artifactId>
        <version>最新版本</version>
    </dependency>
    ```
2. 配置拦截器插件<br/>
   2.1 新版拦截器是 com.github.pagehelper.PageInterceptor <br/>
   2.2 com.github.pagehelper.PageHelper 现在是一个特殊的 dialect 实现类，是分页插件的默认实现类 <br/>
   ```
    1. 在 MyBatis 配置 xml 中配置拦截器插件
        <plugins>
            <!-- com.github.pagehelper为PageHelper类所在包名 -->
            <plugin interceptor="com.github.pagehelper.PageInterceptor">
                <!-- 使用下面的方式配置参数，后面会有所有的参数介绍 -->
                <property name="param1" value="value1"/>
            </plugin>
        </plugins>
    2. 在 Spring 配置文件中配置拦截器插件
        <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
          <!-- 注意其他配置 -->
          <property name="plugins">
            <array>
              <bean class="com.github.pagehelper.PageInterceptor">
                <property name="properties">
                  <!--使用下面的方式配置参数，一行配置一个 -->
                  <value>
                    params=value1
                  </value>
                </property>
              </bean>
            </array>
          </property>
        </bean>
    ```
3.  如何在代码中使用 <br/>
    3.1 分页插件支持以下几种调用方式 <br/>
    ```
        PageHelper.startPage(1, 10);
        List<User> list = userMapper.selectIf(1);
    ```
    3.2 注意事项 <br/>
        (1) 只有紧跟在PageHelper.startPage方法后的第一个Mybatis的查询（Select）方法会被分页 <br/>