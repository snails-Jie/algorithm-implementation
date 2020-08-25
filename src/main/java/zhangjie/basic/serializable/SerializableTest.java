package zhangjie.basic.serializable;

import java.io.*;

/**
 * @Author zhangjie
 * @Date 2020/7/7 18:59
 **/
public class SerializableTest {
    public static void main(String[] args) throws Exception {
//        serializeFlyPig();
        FlyPig flyPig = deserializeFlyPig();
        System.out.println(flyPig.toString());

    }

    /**
     * 序列化
     * 1.transient 修饰的属性不会被序列化的
     * 2.静态变量不被序列
     *  --> 验证步骤：（1）先序列化对象到文件中（执行serializeFlyPig方法）
     *               （2）修改对象静态变量的值，再进行反序列化
     *                   --> 发现静态变量的值没有被覆盖，所以得出此结论
     *
     */
    private static void serializeFlyPig() throws IOException {
        FlyPig flyPig = new FlyPig();
        flyPig.setColor("black");
        flyPig.setName("naruto");
        flyPig.setCar("0000");
        // ObjectOutputStream 对象输出流，将 flyPig 对象存储到E盘的 flyPig.txt 文件中，完成对 flyPig 对象的序列化操作
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("d:/flyPig.txt")));
        oos.writeObject(flyPig);
        System.out.println("FlyPig 对象序列化成功！");
        oos.close();
    }

    /**
     * 反序列化
     */
    private static FlyPig deserializeFlyPig() throws Exception {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("d:/flyPig.txt")));
        FlyPig person = (FlyPig) ois.readObject();
        System.out.println("FlyPig 对象反序列化成功！");
        return person;
    }
}
