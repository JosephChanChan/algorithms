package main.java.trees;

import java.util.Random;

public class BinarySearch {

    static int[] arr = new int[50];

    public static void main(String[] args){
//        int[] a = {0,1,2,3,4,5,6,7,8,9};
//        System.out.println(getKey(0,a));
        Random random = new Random();
        for(int i=0; i<50; i++){
            arr[i] = random.nextInt(50);
        }

        int temp;
        for(int i=0; i<arr.length-1; i++){
            for(int j=0; j<arr.length-i-1; j++){
                if(arr[j]>arr[j+1]){
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }

        Integer key = getKey(15, arr);
        System.out.println(key);

        for(int k=0; k<arr.length; k++){
            System.out.print(arr[k]+" ");
        }

        boolean b = arr[key] == 6;
        if(b){
            System.out.println("key下标在arr数组中元素是 "+arr[key]);
        }
    }

    public static Integer getKey(int key,int[] a){
        int min=0,max=a.length-1,mid=max/2;
        while(min<=max){
            mid = (max-min)/2+min;
            if(key>a[mid]){
                min = mid+1;
            }else if(key<a[mid]){
                max = mid-1;
            }else{
                return mid;
            }
        }
        System.out.println("该数组没有此key!  "+key);
        return null;
    }
}
