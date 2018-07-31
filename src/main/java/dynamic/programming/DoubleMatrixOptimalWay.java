package dynamic.programming;

import java.io.*;

/**
 * 一个M*N矩阵中有不同的正整数，经过这个格子，就能获得相应价值的奖励，先从左上走到右下，再从右下走到左上。
 *     第1遍时只能向下和向右走，第2遍时只能向上和向左走。两次如果经过同一个格子，则该格子的奖励只计算一次，求能够获得的最大价值。
 *     例如：3 * 3的方格。
 *      1 3 3
 *      2 1 3
 *      2 2 1
 *     能够获得的最大价值为：17。1 -> 3 -> 3 -> 3 -> 1 -> 2 -> 2 -> 2 -> 1。其中起点和终点的奖励只计算1次。
 *
 *      没招了么？其实我们可以“两个人一起”dp（让两个人同时走）。
 *
 *      用dp[x1][y1][x2][y2]表示第一个人在(x1,y1) 并且第二个人在(x2,y2)时的最大值。
 *
 *      我们有初值dp[1][1][1][1] = a[1][1]， 求的是dp[m][n][m][n]。
 *
 *      问题来了： 每个人走一步，状态转移是什么？
 *
 *      dp[x1][y1][x2][y2] = max{dp[x1’][y1’][x2’][y2’]} + a[x1][y1] + a[x2][y2]
 *      其中(x1’,y1’)是(x1,y1)的邻居，(x2’,y2’)是(x2,y2)的邻居。
 *
 *      事实上，因为我们有这个等式提示我们其实只要用3维就可以表示这个矩阵，因为 y2 = x1 + y1 – x2所以那一维可以用走多少步表示出来。
 *
 *      设dp[i][j][k]表示两人走i步，第一个人到达第i行，第二个人到达第k行时的最大价值。
 *      dp[i][j][k]=max(dp[i-1][j][k],dp[i-1][j-1][k],dp[i-1][j][k-1],dp[i-1][j-1][k-1])+M[x1][y1]+M[x2][y2] (x1!=x2)
 *
 *      然而这个dp并没有体现出走到相同格子，数字仅计算一次的要求。
 *      那么我们加上这个条件：
 *         如果x1 = x2，dp[i][j][k] = max(dp[i-1][j][k],dp[i-1][j-1][k],dp[i-1][j][k-1],dp[i-1][j-1][k-1])+M[x1][y1]
 *
 * Created by Joseph on 2017/10/20.
 */
public class DoubleMatrixOptimalWay {

    public static void main(String[] args){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in),1<<16);
        int n = 0,m = 0;
        int[][] arr = null;
        /*
            vals[step][x1][x2]
            存储每一步中 x1和x2 每一列的情况。例如：第5步时，x1在第2列，x2在第3列时获得的最大价值。
            即 x1在 5-2 第3行，第2列，x2在 5-3 第2行，第3列。这2个获得的最大价值
            如果列数大于步数不计算
        */
        int[][][] vals = null;
        try {
            String[] split = reader.readLine().split(" ");
            n = Integer.parseInt(split[1]); //行
            m = Integer.parseInt(split[0]); //列

            arr = new int[n][m];
            String[] col = null;
            for(int i=0; i<n; i++){
                col = reader.readLine().split(" ");
                for(int j=0; j<col.length; j++){
                    arr[i][j] = Integer.parseInt(col[j]);
                }
            }

            //一共n+m-2步，枚举x1,x2处于m列的情况
            vals = new int[n+m-1][m][m];

            for(int s=0; s<n+m-1; s++){       //走s步时
                for(int j=0; j<m; j++){     //x1走s步后，在第j列时
                    for(int k=0; k<m; k++){ //x2走s步后，在第k列时
                        //如果x1或x2处于的列数大于步数不计算
                        if(j > s || k > s){
                            break;
                        } else {
                            int y1 = s-j;
                            int y2 = s-k;
                            //如果x1或x2在走了s步后，计算出的所在行超出原有行，情形不成立，不计算
                            if(y1 >= n || y2 >= n){
                                continue;
                            }
                            //x1和x2所在的行，列都在合法范围内
                            /*
                                x1 != x2   dp[i][j][k]=max(dp[i-1][j][k],dp[i-1][j-1][k],dp[i-1][j][k-1],dp[i-1][j-1][k-1])+M[x1][y1]+M[x2][y2]
                                x1 = x2  dp[i][j][k] = max(dp[i-1][j][k],dp[i-1][j-1][k],dp[i-1][j][k-1],dp[i-1][j-1][k-1])+M[x1][y1]
                                max()中意思是，此时x1和x2在的位置，各自能到达x1、x2的所有位置最大价值的位置情况。
                                如：
                                    i-1,j,k         i-1,j-1,k       i-1,j,k-1       i-1,j-1,k-1
                                       x2'               x2'
                                    x1'x2                x2       (x1'x2')x2           x2'x2
                                    x1              x1'x1             x1            x1'x1
                             */
                            int val;
                            if(j == k){
                                val = arr[y1][j];
                            }else {
                                int v1 = arr[y1][j];
                                int v2 = arr[y2][k];
                                val = v1 + v2;
                            }

                            //dp[i-1][j][k]
                            int a = calcNeighborNode(vals, s - 1, j, k);
                            //dp[i-1][j-1][k]
                            int b = calcNeighborNode(vals, s - 1, j - 1, k);
                            //dp[i-1][j][k-1]
                            int c = calcNeighborNode(vals, s - 1, j, k - 1);
                            //dp[i-1][j-1][k-1]
                            int d = calcNeighborNode(vals, s - 1, j - 1, k - 1);

                            int f = maxInt(a, b, c, d);
                            vals[s][j][k] = f + val;
                        }
                    }
                }
            }

            System.out.println(vals[n+m-2][m-1][m-1]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int calcNeighborNode(int[][][] vals,int s,int j,int k){
        if(s < 0 || j < 0 || k < 0){
            return 0;
        }
        if(s < j || s < k){
            return 0;
        }
        return vals[s][j][k];
    }

    private static int maxInt(int a,int b,int c,int d){
        int f = a > b ? a : b;
        f = f > c ? f : c;
        return f > d ? f : d;
    }

}
