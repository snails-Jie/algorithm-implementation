package zhangjie.algorithm;

import org.junit.Test;

import java.util.TreeMap;

/**
 * @Author zhangjie
 * @Date 2020/7/5 8:35
 **/
public class RedBlackTreeTest {

    @Test
    public void testTreeMap(){
        TreeMap<String , Double> map =
                new TreeMap<String , Double>();
        map.put("ccc" , 89.0);
        map.put("aaa" , 80.0);
        map.put("zzz" , 80.0);
        map.put("bbb" , 89.0);
        System.out.println(map);
    }

    @Test
    public void test(){
        RedBlackTree<Integer , Integer> map =
                new RedBlackTree<Integer , Integer>();
        for (int i=0; i<100;i++){
            map.put(i, i);
        }
        System.out.println(map);
    }
}
