package zhangjie.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author zhangjie
 * @Date 2020/6/29 18:16
 * 二分搜索
 * 1. 二分搜索只对有序数组有效
 * 2. 搜索过程
 *   2.1 从数组的中间元素开始，如果中间元素正好是要查找的元素，则搜索过程结束；
 *   2.2 如果某一特定元素大于或者小于中间元素，则在数组大于或小于中间元素的那一半中查找,而且跟开始一样从中间元素开始比较
 *   -->这种搜索算法每一次比较都使搜索范围缩小一半
 * 3.二分查找算法在情况下的复杂度是对数时间，进行 O(\log n)次比较操作
 **/
public class binarySearch {
    public static void main(String[] args) {
        List<Integer> list=new ArrayList<Integer>();
        //往list加入逐渐增大1-10000的所有偶数，作为实验数组，很明显，他是有序的！
        for(int i=0;i<10000;i+=2){
            //这里当然也可用数组
            list.add(i);
        }
        int low=0;
        int high=list.size();
        int key=3334;
        while(low<=high){
            int mid=(low+high)/2;
            if(key==list.get(mid)){
                System.out.println("此数值在list中的位置为："+mid);
                break;
            }
            if(key>list.get(mid)){
                //当小于时，是low指针向后移动，high指针不变
                low=mid+1;
            }
            if(key<list.get(mid)){
                //当大于时，是high指针向前移动，low指针不变
                high=mid-1;
            }

        }
        if(low>high){
            System.out.println("没有查到结果！");
        }
    }
}
