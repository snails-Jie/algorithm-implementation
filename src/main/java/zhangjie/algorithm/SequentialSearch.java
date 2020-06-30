package zhangjie.algorithm;


import java.util.Scanner;

/**
 * 顺序查找
 * 从头到尾，一个一个地比，找着相同的就成功，找不到就失败。很明显的缺点就是查找效率低
 * @Author zhangjie
 * @Date 2020/6/29 17:48
 **/
public class SequentialSearch {

     public static void main(String[] args) {
        int a[]={49,38,65,97,76,13,27,49,78,34,12,64,5,4,62,99,98,54,56,17,18,23,34,15,35,25,53,51};
        Scanner input=new Scanner(System.in);
        try{
            System.out.println("请输入要查询的数字：");
            int inputNumber=input.nextInt();
            for(int i=0;i<a.length;i++){
                if(a[i]==inputNumber){
                    System.out.println(inputNumber+"的位置为："+i);
                    break;
                }
                if(i==a.length-1)
                    System.out.println("No Result!");
        }
        }finally {
        input.close();
        }
    }
}
