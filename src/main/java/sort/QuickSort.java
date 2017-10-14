package main.java.sort;

import java.util.Random;

/**
 * Created by Joseph on 2017/6/10.
 */
public class QuickSort {

//    static int[] arr = {5,8,3,7,1,6,4,9,2};
    static int[] arr = new int[100];

    public static void main(String[] args){
        Random random = new Random();
        for(int j=0; j<100; j++){
            arr[j] = random.nextInt(100);
        }

        doQuickSort(arr,0,arr.length-1);

        boolean b = CheckSortedArr.checkAsc(arr);
        if(!b) System.out.println("排序有误!");
        else System.out.println("排序正确");

        for(int i : arr){
            System.out.print(i+" ");
        }
    }

    public static void doQuickSort(int[] arr,int left,int right){
        if(left >= right){
            return;
        }
        int base = arr[left];
        int i = quickSort(base, left, right);
        doQuickSort(arr,left,i-1);
        doQuickSort(arr,i+1,right);
    }

    public static int quickSort(int base,int left,int right){
        while (left < right){
            while (left < right && arr[right] >= base){
                right--;
            }
            arr[left] = arr[right];
            while (left < right && arr[left] <= base){
                left++;
            }
            arr[right] = arr[left];
        }
        arr[left] = base;
        return left;
    }
}
