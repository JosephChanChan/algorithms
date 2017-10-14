package main.java.simulation.algorithm;

import java.io.BufferedReader;
import java.io.*;

/**
 * Created by Joseph on 2017/6/13.
 *
 * 有编号1-n的n个格子，机器人从1号格子顺序向后走，一直走到n号格子，并需要从n号格子走出去。
 * 机器人有一个初始能量，每个格子对应一个整数A[i]，表示这个格子的能量值。如果A[i] > 0，
 * 机器人走到这个格子能够获取A[i]个能量，如果A[i] < 0，走到这个格子需要消耗相应的能量，如果机器人的能量 < 0，
 * 就无法继续前进了。问机器人最少需要有多少初始能量，才能完成整个旅程。
   例如：n = 5。{1，-2，-1，3，4} 最少需要2个初始能量，才能从1号走到5号格子。途中的能量变化如下3 1 0 3 7。

 */
public class RobotWalk {

    public static void main(String[] args){
        int num = 0;
        int[] arr = null;
        int index = 0;
        long diff = 0;
        long count = 0;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in),1<<16);
        PrintWriter printWriter = new PrintWriter(System.out);
        try {
            num = Integer.parseInt(reader.readLine());
            arr = new int[num];
            for(int i=0; i<num; i++){
                arr[i] = Integer.parseInt(reader.readLine());
                if(arr[i]<0){
                    index = i;
                }
            }

            for(int j=0; j<=index; j++){
                count += arr[j];
                if(count <0 && Math.abs(count) > diff){
                    diff = Math.abs(count);
                }
            }
            printWriter.print(diff);
            printWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
