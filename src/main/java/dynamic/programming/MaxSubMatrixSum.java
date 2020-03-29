package dynamic.programming;

import java.io.*;

/**
 * 一个M*N的矩阵，找到此矩阵的一个子矩阵，并且这个子矩阵的元素的和是最大的，输出这个最大的值。
 *  例如：3*3的矩阵：
 *  -1 3 -1
 *  2 -1 3
 *  -3 1 2
 *  和最大的子矩阵是：
 *  3 -1
 *  -1 3
 *  1 2
 *
 *  把每一列第i行到第j行之间的和求出来，形成一个数组c,于是一个第i行到第j行之间的最大子矩阵和对应于这个和数组c的最大子段和。
 *
 *  Created by Joseph on 2017/7/12.
 */
public class MaxSubMatrixSum {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in),1<<16);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out), 1 << 16);
        int m=0,n=0;
        long[][] arr = null;
        long[] c = null;
        String[] split = reader.readLine().split(" ");
        m = Integer.parseInt(split[0]);
        n = Integer.parseInt(split[1]);
        arr = new long[n][m];
        c = new long[m];
        for(int i=0; i<n; i++){
            split = reader.readLine().split(" ");
            for(int j=0; j<m; j++){
                arr[i][j] = Integer.parseInt(split[j]);
//                    System.out.print(arr[i][j]+" ");
            }
//                System.out.println();
        }

        long sum=0;
        int startRow=0, endRow=0,startCol=0,endCol=0;
        //1<=i<=j<=m 枚举所有i到j行
        for(int i=0; i<n; i++){
            for(int j=i; j<n; j++){
                //计算每一列第i行到j行的和
                for(int k=0; k<m; k++){
                    c[k] = i == j ? arr[i][k] : c[k]+arr[j][k];
                }

                long temp=0;
                //此时c数组中的元素是第i行到j行元素的和，可以代表i~j行的矩阵，算出c数组的最大子段和等于算出此时i~j行的最大子矩阵
                for(int l=0; l<c.length; l++){
                    if(temp>0){
                        temp += c[l];
                    }else{
                        temp = c[l];
                        startCol = l;
                    }
                    if(temp>sum){
                        sum = temp;
                        startRow = i;
                        endRow = j;
                        endCol = l;
                    }
                }
            }
        }

        writer.write(String.valueOf(sum));
        writer.flush();
    }
}