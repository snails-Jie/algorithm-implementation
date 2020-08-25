package zhangjie.algorithm;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author zhangjie
 * @Date 2020/6/30 10:48
 **/
public class binarySearchTest {

    @Test
    public void test(){
        List<Integer> list=new ArrayList<Integer>();
        //往list加入逐渐增大1-10000的所有偶数，作为实验数组，很明显，他是有序的！
        for(int i=0;i<10000;i+=2){
            //这里当然也可用数组
            list.add(i);
        }

        int loc = Collections.binarySearch(list,1);
        System.out.println("所在位置为："+loc);
    }
}
