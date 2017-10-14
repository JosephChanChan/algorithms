package main.java.sort;

import java.util.Random;

/**
 * Created by Joseph on 2017/6/14.
 */
public class InsertSort {

//    static int[] arr = {5,4,6,1,3,8,7,9,2};
    static int[] arr = new int[100];

    public static void main(String[] args){

        Random random = new Random();
        for(int a=0; a<100; a++){
            arr[a] = random.nextInt(100);
        }

        /** 插入排序算法 */
        for(int i=0; i<arr.length-1; i++){
            for(int x=i+1; i<x; x--){
                if(arr[i]>arr[x]){
                    /** 做基本的换位操作 */
                    int temp = arr[i];
                    arr[i] = arr[x];
                    arr[x] = temp;
                    /** 插入排序算法有个特点是,每次排序后被排前的小的值,
                     需一直与其前面的值比较,直到数组的第一个元素 */
                    while(i>0 && arr[i]<arr[i-1]){
                        /** 如果i不是第一位元素,并且小于其前一位就进行换位操作 */
                        int p = arr[i-1];
                        arr[i-1] = arr[i];
                        arr[i] = p;
                        //i 要依次减一 依次与前位比较,直到为0
                        i--;
                    }
                }
                /** x--,结束本次循环 */
            }
        }

        boolean flag = true;
        int faild = -1;
        for(int k=0; k<arr.length; k++){
            if(k < arr.length-1){
                if(arr[k] > arr[k+1]){
                    flag = false;
                    faild = k;
                    break;
                }
            }
            System.out.print(arr[k]+"  ");
        }

        if(!flag)
            System.out.println("排序失败，关键位置:"+faild);
        else
            System.out.println("排序成功");

//        for(int k=0; k<arr.length; k++){
//            System.out.print(arr[k]+"  ");
//        }
    }
}
