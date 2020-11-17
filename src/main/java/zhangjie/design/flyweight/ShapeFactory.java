package zhangjie.design.flyweight;

import java.util.HashMap;

/**
 * @ClassName ShapeFactory
 * @Description: 创建一个工厂，生成基于给定信息的实体类的对象
 * @author: zhangjie
 * @Date: 2020/11/17 21:33
 **/
public class ShapeFactory {

    private static final HashMap<String,Shape> circleMap = new HashMap<>();

    public static Shape getCircle(String color){
        Circle circle = (Circle) circleMap.get(color);
        if(circle == null) {
            circle = new Circle(color);
            circleMap.put(color, circle);
            System.out.println("Creating circle of color : " + color);
        }
        return circle;
    }
}
