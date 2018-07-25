package dynamic.programming;

import java.io.*;

/**
 * Created by Joseph on 2017/8/17.
 *  给定一个数列，从中删掉任意若干项剩余的序列叫做它的一个子序列，求它的最长的子序列，满足子序列中的元素是单调递增的。
 *  例如给定序列{1,6,3,5,4},答案是3，因为{1,3,4}和{1,3,5}就是长度最长的两个单增子序列。
 *  假设 l(i) 是序列中前i位最长的单增子序列长度。
 *  那么这个最长单增子序列中，ai的前一位aj，必然小于ai，aj < ai且 j < i。
 *  l(i) = l(j) + 1。
 *  根据条件要求最长的单增子序列，要使 l(i)最大，则求最大的l(j)，所以 l(i) = max{l(j)}+1
 *
 */
public class LongestIncreasingSubsequence {

    public static void main(String[] args){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in),1<<16);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out), 1 << 16);
        int count=0,n=0,maxL=0,f=0;
        int[] arr = null;
        int[][] val = null;
        try {
            n = Integer.parseInt(reader.readLine());
            arr = new int[n];
            for(int i=0; i<n; i++){
                arr[i] = Integer.parseInt(reader.readLine());
            }

            val = new int[n][2];
            val[0][0] = 1;      //第0列记录第i位的最长单增子序列的长度
            val[0][1] = -1;     //第1列记录第i位的最长单增子序列中前一位aj的位置，可以借此得到具体的最长单增子序列

            //l(i) = max{l(j)}+1 边界i=0 l(i)=1
            for(int i=1; i<n; i++){
                int max = 0,aj = -1;
                for(int j=0; j<i; j++){
                    if(val[j][0] > max && arr[j] < arr[i]){
                        max = val[j][0];
                        aj = j;
                    }
                }
                val[i][0] = max+1;
                val[i][1] = aj;
                if(val[i][0] > maxL){
                    maxL = val[i][0];
                    f = i;
                }
            }

            int k = 0,v = f;
//            System.out.print(arr[f]+" ");
//            while ((k=val[f][1]) != -1){
//                System.out.print(arr[k]+" ");
//                f = k;
//            }

            count = val[v][0];
            writer.write(count+"\r\n");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}