## ![业务逻辑验证框架fluent-validator](https://github.com/neoremind/fluent-validator)
### FluentValidator特点
1. 一个校验器（Validator）只负责某个属性或者对象的校验，可以做到职责单一，易于维护，并且可复用<br/>
2. 使用注解方式验证<br/>
3.  回调给予你充分的自由度<br/>

### 上手
1. maven引入依赖<br/>
    ```
    <dependency>
        <groupId>com.baidu.unbiz</groupId>
        <artifactId>fluent-validator</artifactId>
        <version>1.0.5</version>
    </dependency>
    ```
2. 开发一个专职的Validator
    ```
    public class CarSeatCountValidator extends ValidatorHandler<Integer> implements Validator<Integer> {
     
        @Override
        public boolean validate(ValidatorContext context, Integer t) {
            if (t < 2) {
                context.addErrorMsg(String.format("Seat count is not valid, invalid value=%s", t));
                return false;
            }
            return true;
        }
    }
    ```
   2.1 实现Validator接口，泛型T规范这个校验器待验证的对象的类型<br/>
   2.2 继承ValidatorHandler可以避免实现一些默认的方法<br/>
   2.3 validate()方法第一个参数是整个校验过程的上下文，第二个参数是待验证对象<br/>
3. 验证<br/>
    ```
    Car car = getCar();
    Result ret = FluentValidator.checkAll()
                                .on(car.getLicensePlate(), new CarLicensePlateValidator())
                                .on(car.getManufacturer(), new CarManufacturerValidator())
                                .on(car.getSeatCount(), new CarSeatCountValidator())
                                .doValidate()
                                .result(toSimple());
    ```
   3.1 通过FluentValidator.checkAll()获取了一个FluentValidator实例<br/>
   3.2 紧接着调用了failFast()表示有错了立即返回<br/>
   3.3 惰性求值（Lazy valuation）:直到doValidate()验证才真正执行了<br/>
   3.4 直接使用默认提供的静态方法toSimple()来做一个回调函数传入result()方法<br/>
   
### 用法简介
#### Hibernate Validator集成
#### 注解验证
#### 分组
#### 级联对象图
#### Spring AOP集成
#### 国际化支持
   
 