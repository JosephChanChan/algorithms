package main.java.sort;

import java.util.Random;

/**
 * Created by Joseph on 2017/6/10.
 */
public class QuickSort {

    static Integer[] arr = {5,8,3,7,1,6,4,9,2};

    public static void main(String[] args){
        /*Random random = new Random();
        for(int j=0; j<100; j++){
            arr[j] = random.nextInt(100);
        }

        doQuickSort(arr,0,arr.length-1);

        int flag = CheckSortedArr.checkAsc(arr);

        if(flag != -1) {
            System.out.println("排序有误!");
        }
        else {
            System.out.println("排序正确");
        }

        for(int i : arr){
            System.out.print(i+" ");
        }*/
        doQuickSort(arr,0, arr.length-1);

        int flag = CheckSortedArr.checkAsc(arr);
        if (flag != -1){
            System.out.println("数组排序有误! "+flag);
        }
        else {
            System.out.println("数组排序成功!");
        }
        for (Integer integer : arr){
            System.out.print(integer + " ");
        }
    }

    public static <T extends Comparable<? super T>> void doQuickSort(T[] arr,int left,int right){
        if(left >= right){
            return;
        }

        T base = arr[left];

        int i = quickSort(arr, base, left, right);

        doQuickSort(arr,left,i-1);
        doQuickSort(arr,i+1,right);
    }

    private static <T extends Comparable<? super T>> int quickSort(T[] arr, T base, int left, int right){
        while (left < right){
            while (left < right && arr[right].compareTo(base) >= 0){
                right--;
            }
            arr[left] = arr[right];
            while (left < right && arr[left].compareTo(base) <= 0){
                left++;
            }
            arr[right] = arr[left];
        }
        arr[left] = base;
        return left;
    }
}
