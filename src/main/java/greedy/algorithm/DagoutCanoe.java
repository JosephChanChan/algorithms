package main.java.greedy.algorithm;

import java.io.*;
import java.util.*;

/**
 * Created by Joseph on 2017/7/9.
 *
 * n个人，已知每个人体重。独木舟承重固定，每只独木舟最多坐两个人，可以坐一个人或者两个人。
 * 显然要求总重量不超过独木舟承重，假设每个人体重也不超过独木舟承重，问最少需要几只独木舟？
 * Input
    第一行包含两个正整数n (0<n<=10000)和m (0<m<=2000000000)，表示人数和独木舟的承重。
    接下来n行，每行一个正整数，表示每个人的体重。体重不超过1000000000，并且每个人的体重不超过m。
    Output
    一行一个整数表示最少需要的独木舟数。
 */
public class DagoutCanoe {

    public static void main(String[] args){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in),1<<16);
        PrintWriter writer = new PrintWriter(System.out);
        int p=0,shipHeavy,count=0;
        int[] arr = new int[p];
        Map<Integer,Integer> map = new TreeMap<>();
        try {
            String[] split = reader.readLine().split(" ");
            p = Integer.parseInt(split[0]);
            shipHeavy = Integer.parseInt(split[1]);



            writer.print(count);
            writer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

//    private int binarySearch(int[] arr,int key){
//        int temp=-1,min=0,max=arr.length-1,mid=0;
//        while (min <= max){
//            mid = (max-min) >> 1 + min;
//            if(arr[mid]==key){
//                //刚好中了，返回此下标
//                return mid;
//            }else if(arr[mid] > key){
//                max = mid-1;
//            }else if(arr[mid] < key){
//                min = mid+1;
//            }
//            if(arr[mid] > temp && arr[mid] <= )
//        }
//        return temp;
//    }

}
