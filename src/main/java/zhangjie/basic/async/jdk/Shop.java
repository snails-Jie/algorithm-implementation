package zhangjie.basic.async.jdk;

import java.util.Random;

/**
 * @author zhangjie
 */
public class Shop {

    public double getPrice(String product){
        return calculatePrice(product);
    }

    private double calculatePrice(String product){
        delay();
        return new Random().nextDouble() * product.charAt(0) + product.charAt(1);
    }

    /**
     * 模拟延时操作
     */
    public static void delay(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
