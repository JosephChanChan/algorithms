package greedy.algorithm;

import java.io.*;

/**
 *  n个人，已知每个人体重。独木舟承重固定，每只独木舟最多坐两个人，可以坐一个人或者两个人。
 *  显然要求总重量不超过独木舟承重，假设每个人体重也不超过独木舟承重，问最少需要几只独木舟？
 *
 *  采取的策略是:
 *      求最少独木舟可以把 n 个人运过去. 那么满足题目条件下, 使每条独木舟尽量多坐人.
 *      船承重 y 。 假设现在第 x 人, 他的体重是 h1，那么我们需要找体重 h2 <= y - h1 的人.
 *      这样把可以匹配的人都匹配完了, 剩下不可匹配的只能一个人坐一船.
 *      这种策略就是贪心策略.
 *
 * Input
 *    第一行包含两个正整数n (0<n<=10000)和m (0<m<=2000000000)，表示人数和独木舟的承重。
 *    接下来n行，每行一个正整数，表示每个人的体重。体重不超过1000000000，并且每个人的体重不超过m。
 * Output
 *    一行一个整数表示最少需要的独木舟数。
 *
 * Created by Joseph on 2017/7/10.
 */
public class DugoutCanone {

    public static void main(String[] args){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in),1<<16);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out), 1 << 16);
        int n = 0, shipHeavy = 0, count = 0;
        int[] arr = null;
        try {
            String[] split = reader.readLine().split(" ");
            n = Integer.parseInt(split[0]);
            shipHeavy = Integer.parseInt(split[1]);
            arr = new int[n];
            for(int i = 0; i < n; i++){
                int a = Integer.parseInt(reader.readLine());
                // 这里读入数据时, 做个小优化, 如果承重等于船, 直接count++
                if(a == shipHeavy){
                    count++;
                    continue;
                }
                arr[i] = a;
            }

            for(int j = 0; j < arr.length; j++){
                if(arr[j] < 1){
                    continue;
                }

                // y是匹配承重，查找尽量接近于y的重量
                int y = shipHeavy - arr[j];

                int index = -1, temp = 0;

                for(int k = j+1; k < arr.length; k++){
                    if (arr[k] < 1) {
                        continue;
                    }
                    // 假设该人体重刚好满足 y
                    if (arr[k] == y) {
                        index = k;
                        break;
                    }
                    // 假设该人比之前的 temp 要重 且 在 y 范围内, 这一个更好
                    else if (arr[k] > temp && arr[k] < y) {
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
