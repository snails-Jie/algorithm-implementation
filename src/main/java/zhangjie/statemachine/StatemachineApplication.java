package zhangjie.statemachine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.statemachine.config.EnableStateMachine;

/**
 * @ClassName StatemachineApplication
 * @Description: TODO
 * @author: zhangjie
 * @Date: 2020/11/15 10:33
 **/
@SpringBootApplication
@EnableStateMachine
public class StatemachineApplication {
    public static void main(String[] args) {
        SpringApplication.run(StatemachineApplication.class,args);
    }
}
