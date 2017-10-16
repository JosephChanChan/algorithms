package main.java.greedy.algorithm;

import java.io.*;

/**
 * Created by Joseph on 2017/7/10.
 *  n个人，已知每个人体重。独木舟承重固定，每只独木舟最多坐两个人，可以坐一个人或者两个人。
 *  显然要求总重量不超过独木舟承重，假设每个人体重也不超过独木舟承重，问最少需要几只独木舟？
 *
 *  Input
     第一行包含两个正整数n (0<n<=10000)和m (0<m<=2000000000)，表示人数和独木舟的承重。
     接下来n行，每行一个正整数，表示每个人的体重。体重不超过1000000000，并且每个人的体重不超过m。
    Output
     一行一个整数表示最少需要的独木舟数。
 */
public class DugoutCanone {

    public static void main(String[] args){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in),1<<16);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out), 1 << 16);
        int n = 0,shipHeavy=0,count = 0;
        int[] arr = null;
        try {
            String[] split = reader.readLine().split(" ");
            n = Integer.parseInt(split[0]);
            shipHeavy = Integer.parseInt(split[1]);
            arr = new int[n];
            for(int i=0; i<n; i++){
                int a = Integer.parseInt(reader.readLine());
                if(a==shipHeavy){
                    count++;
                    continue;
                }
                arr[i] = a;
            }

            for(int j=0; j<arr.length; j++){
                if(arr[j]<1)
                    continue;
                //y是匹配承重，查找尽量接近于y的重量
                int y = shipHeavy - arr[j];
                int index = -1,temp=0;
                for(int k=j+1; k<arr.length; k++){
                    if(arr[k]<1)
                        continue;
                    if(arr[k]==y){
                        index = k;
                        break;
                    }else if(arr[k]>temp && arr[k]<y){
                        temp = arr[k];
                        index = k;
                    }
                }

                if(index >= 0){
                    arr[index] = 0;
                }
                count++;
                arr[j] = 0;
            }

            writer.write(count+"\r\n");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
