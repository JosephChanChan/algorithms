package main.java.sort;

import java.util.Random;

/**
 * Created by Joseph on 2017/6/14.
 */
public class SelectSort {

//    static int[] arr = {5,4,6,1,3,8,7,9,2};
    static int[] arr = new int[100];

    public static void main(String[] args){
        Random random = new Random();
        for(int a=0; a<100; a++){
            arr[a] = random.nextInt(100);
        }

        int index=-1,temp=-1;
        for(int i=0; i<arr.length; i++){
            index = i;
            for(int j=i; j<arr.length; j++){
                if(arr[j] < arr[index]){
                    index = j;
                }
            }
            temp = arr[i];
            arr[i] = arr[index];
            arr[index] = temp;
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
    }
}
