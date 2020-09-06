### Spring Boot 集成
1. 基本上分为两大类<br/>
 1.1 基于 starter 的自动配置<br/>
 1.2 基于 @MapperScan 注解的手工配置<br/>

#### mapper-spring-boot-starter
1. 如果你没有使用 @MapperScan 注解，你就需要在你的接口上增加 @Mapper 注解，否则 MyBatis 无法判断扫描哪些接口<br/>
   1.1 第一种用法没有用 @MapperScan 注解，所以你需要在所有接口上增加 @Mapper 注解<br/>
2. 添加maven依赖
    ```
    <dependency>
      <groupId>tk.mybatis</groupId>
      <artifactId>mapper-spring-boot-starter</artifactId>
      <version>版本号</version>
    </dependency>
    ```
3.  对通用mapper进行配置<br/>
    ```
      yml格式配置
      mapper:
        mappers:
          - tk.mybatis.mapper.common.Mapper
          - tk.mybatis.mapper.common.Mapper2
        notEmpty: true
    
       properties配置
       mapper.mappers=tk.mybatis.mapper.common.Mapper,tk.mybatis.mapper.common.Mapper2
       mapper.notEmpty=true  
    ```
    3.1 在 Spring Boot 中使用的时候参数名不必和配置中的完全一致
    
### 对象关系映射
1. 通用 Mapper 使用 JPA 注解和自己提供的注解来实现对象关系映射<br/>
#### 简单示例
1. 一个 @Id 标记字段为主键<br/>
2. 数据库中的字段名和实体类的字段名是完全相同的，这中情况下实体和表可以直接映射<br/>
3. 只要配置 MyBatis 时能注册或者扫描到该接口，该接口提供的方法就都可以使用<br/>
    ```
    import tk.mybatis.mapper.common.Mapper;
    public interface CountryMapper extends Mapper<Country> {
    }
    ```
   3.1 该接口默认继承的方法如下:selectOne、select、selectAll、selectCount、selectByPrimaryKey等<br/>
4. 增加自己写的方法<br/>
  4.1 使用纯接口注解方式<br/>
  4.2 使用xml方式<br/>
#### 数据库映射
1. 通用 Mapper 中，默认情况下是将实体类字段按照驼峰转下划线形式的表名列名进行转换<br/>
  1.1 @NameStyle在类上进行配置，优先级高于对应的 style 全局配置<br/>
2. @Table(JPA) 注解配置 name 属性后，直接使用提供的表名，不再根据实体类名进行转换<br/>
3. @Column 注解（JPA）<br/>
   3.1  name 配置映射的列名<br/>
4. @ColumnType 注解（Mapper）<br/>
   4.1 jdbcType 用于设置特殊数据库类型时指定数据库中的 jdbcType<br/>
   4.2 typeHandler 用于设置特殊类型处理器，常见的是枚举<br/>
    ```
    @ColumnType(
            column = "countryname",
            jdbcType = JdbcType.VARCHAR,
            typeHandler = StringTypeHandler.class)
    private String  countryname;
    ```
5. @Transient 注解（JPA）:告诉通用 Mapper 这不是表中的字段<br/>
### 主键策略
1. 主键策略和数据库关系很大，有些数据库支持主键自增，而有些数据库只能通过序列来获得<br/>
2. 新增的@KeySql 注解用于替换 @GeneratedValue 注解
    ```
    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;
    或
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;
    ```
    2.1 生成对应生成的 XML 代码：
    ```
    <insert id="insert" useGeneratedKeys="true" keyProperty="id">
        insert into country (id, countryname, countrycode)
        values (#{id},#{countryname},#{countrycode})
    </insert>
    ```
3. 列表数据库名字后面对应的 SQL 是插入后取 id 的 SQL 语句
    ```
    //建议直接指定数据库
    @Id
    @KeySql(dialect = IdentityDialect.MYSQL)
    private Integer id;
    或：
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    ```
   3.1 对应的 XML 形式为：
   ```
    <insert id="insertAuthor">
        <selectKey keyProperty="id" resultType="int" order="AFTER">
          SELECT LAST_INSERT_ID()
        </selectKey>
        insert into country (id, countryname, countrycode)
        values (#{id},#{countryname},#{countrycode})
    </insert>
    ```
4. 通过序列和任意 SQL 获取主键值<br/>
    4.1 和上面的区别：插入表之后才有 id 的值；是插入数据库前需要获取一个值作为主键<br/>
    ```
    @Id
    @KeySql(sql = "select SEQ_ID.nextval from dual", order = ORDER.BEFORE)
    private Integer id;
   或：
   @Id
   @GeneratedValue(
     strategy = GenerationType.IDENTITY,
     generator = "select SEQ_ID.nextval from dual")
   private Integer id;
    ```
   对应的xml配置
   ```
    <insert id="insertAuthor">
      <selectKey keyProperty="id" resultType="int" order="BEFORE">
        select SEQ_ID.nextval from dual
      </selectKey>
      insert into country (id, countryname, countrycode)
      values (#{id},#{countryname},#{countrycode})
    </insert>
    ```
### mappergenerator
1. 使用该插件可以很方便的生成实体类、Mapper接口以及对应的XML文件
2. 使用 Maven 执行MBG<br/>
  2.1 Maven 中的插件配置如下：<br/>
    ```
       <plugins>
         <plugin>
           <artifactId>maven-compiler-plugin</artifactId>
           <configuration>
             <source>${jdk.version}</source>
             <target>${jdk.version}</target>
           </configuration>
         </plugin>
         <plugin>
           <groupId>org.mybatis.generator</groupId>
           <artifactId>mybatis-generator-maven-plugin</artifactId>
           <version>1.3.6</version>
           <configuration>
             <configurationFile>
               ${basedir}/src/main/resources/generator/generatorConfig.xml
             </configurationFile>
             <overwrite>true</overwrite>
             <verbose>true</verbose>
           </configuration>
           <dependencies>
             <dependency>
               <groupId>mysql</groupId>
               <artifactId>mysql-connector-java</artifactId>
               <version>5.1.29</version>
             </dependency>
             <dependency>
               <groupId>tk.mybatis</groupId>
               <artifactId>mapper</artifactId>
               <version>4.0.0</version>
             </dependency>
           </dependencies>
         </plugin>
       </plugins>
    ```
       （1） 两个必须的依赖：JDBC 驱动和mapper插件
   2.2 配置文件： generatorConfig.xml
    ```
    <!DOCTYPE generatorConfiguration
            PUBLIC "-//mybatis.org//DTD MyBatis Generator Configuration 1.0//EN"
            "http://mybatis.org/dtd/mybatis-generator-config_1_0.dtd">
    
    <generatorConfiguration>
        <properties resource="config.properties"/>
    
        <context id="Mysql" targetRuntime="MyBatis3Simple" defaultModelType="flat">
            <property name="beginningDelimiter" value="`"/>
            <property name="endingDelimiter" value="`"/>
    
            <plugin type="tk.mybatis.mapper.generator.MapperPlugin">
                <property name="mappers" value="tk.mybatis.mapper.common.Mapper"/>
                <property name="caseSensitive" value="true"/>
            </plugin>
    
            <jdbcConnection driverClass="${jdbc.driverClass}"
                            connectionURL="${jdbc.url}"
                            userId="${jdbc.user}"
                            password="${jdbc.password}">
            </jdbcConnection>
    
            <javaModelGenerator targetPackage="com.isea533.mybatis.model" 
                                targetProject="src/main/java"/>
    
            <sqlMapGenerator targetPackage="mapper" 
                             targetProject="src/main/resources"/>
    
            <javaClientGenerator targetPackage="com.isea533.mybatis.mapper" 
                                 targetProject="src/main/java"
                                 type="XMLMAPPER"/>
    
            <table tableName="user_info">
                <generatedKey column="id" sqlStatement="JDBC"/>
            </table>
        </context>
    </generatorConfiguration>
    ```
        (1) 执行 mvn mybatis-generator:generate