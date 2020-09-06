package zhangjie.mapper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @Author zhangjie
 * @Date 2020/9/6 12:55
 **/
@SpringBootApplication
@MapperScan(basePackages = "zhangjie.mapper.mybatis.mapper")
public class MapperApplication {
    public static void main(String[] args) {
        SpringApplication.run(MapperApplication.class, args);
    }
}
