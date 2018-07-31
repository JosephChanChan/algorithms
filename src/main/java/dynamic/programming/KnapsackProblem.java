package dynamic.programming;

import java.io.*;

/**
 *  有n件物品，第i件物品(I = 1,2,3…n)的价值是vi, 重量是wi,我们有一个能承重为m的背包，我们选择一些物品放入背包，
 *  显然放入背包的总重量不超过m。我们要求选择物品的总价值最大，请问如何选择？这里我们假设所有出现的数都是正整数。
 *  状态：
 *      设 f(i,j)为选择前i件物品，重量不超过j时获得的最大价值
 *      对于第i件物品，如果不选，则 f(i,j) = f(i-1,j)
 *      如果选，并且j>=Wi f(i,j) = f(i-1,j-Wi) + Vi
 *  状态转移方程：
 *      求总价值最大， f(i,j) = max{ f(i-1,j) , f(i-1,j-Wi) + Vi}
 解释下该方程：
 *          对于第i件物品，如果不选的话，则将总资源j分配给前面i-1件物品，得到的价值。
 *          对于第i件物品，如果选的话，则对于前面i-1件物品，只有 （总资源-第i件物品所需的资源） 					  剩余资源分配给前i-1件物品，得到的价值。
 *          所以综合对于第i件物品，取还是不取，只需要比较总价值最大。
 *
 *  边界：
 *      n=0，f(0,j)=0
 *      j=0，f(n,0)=0
 *      n>1,j<Wi，f(n,j)=f(i-1,j)
 *      n>1,j>=Wi，f(i,j) = max{ f(i-1,j) , f(i-1,j-Wi) + Vi}
 *
 *  有2个维度，一个是物品n，一个是重量j，所以是一张表，纵轴是物品n，横轴是重量j，依次填表即可。
 *  时间复杂度 O(n*m)，空间复杂度 O(n*m)。
 *  优化，经过分析，当前状态只与上一个状态有关，i和i-1，其中i-1包含了 1~i-1的所有情况，只记录一行即可。
 *  空间复杂度优化成 O(m)。
 *  还有，对于第i件物品，如果j<Wi，则为不选情况，所以只需要枚举 j>=Wi情况即可。
 *
 *  Created by Joseph on 2017/8/18.
 */

public class KnapsackProblem {

    static int[][] WiVi = null;
    static int[][] val = null;
    public static void main(String[] args){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in),1<<16);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out), 1 << 16);
        int n=0,m=0;
        try {
            String[] split = reader.readLine().split(" ");
            n = Integer.parseInt(split[0]);
            m = Integer.parseInt(split[1]);
            WiVi = new int[n][2];
            val = new int[n][m];
            for(int i=0; i<n; i++){
                split = reader.readLine().split(" ");
                WiVi[i][0] = Integer.parseInt(split[0]);
                WiVi[i][1] = Integer.parseInt(split[1]);
            }

            /**
             *      n=0，f(0,j)=0
             *      j=0，f(n,0)=0
             *      n>1,j<Wi，f(n,j)=f(i-1,j)
             *      n>1,j>=Wi，f(i,j) = max{ f(i-1,j) , f(i-1,j-Wi) + Vi}
             */
            for(int i=0; i<n; i++){
                int Wi = WiVi[i][0];
                int Vi = WiVi[i][1];
                for(int j=1; j<=m; j++){
                    if(j<Wi){
                        //n>1,j<Wi，f(n,j)=f(i-1,j)
                        val[i][j-1] = getVi(i - 1, j);
                    }else {
                        //n>1,j>=Wi，f(i,j) = max{ f(i-1,j) , f(i-1,j-Wi) + Vi}
                        if(getVi(i-1,j) > (getVi(i-1,j-Wi)+Vi)){
                            val[i][j-1] = getVi(i-1,j);
                        }else{
                            val[i][j-1] = (getVi(i-1,j-Wi)+Vi);
                        }
                    }
                }
            }

//            for(int i=0; i<n; i++){
//                for(int j=0; j<m; j++){
//                    System.out.print(val[i][j]+" ");
//                }
//                System.out.println();
//            }


            writer.write(val[n-1][m-1]+"\r\n");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int getVi(int i,int j){
        j -= 1;
        if(i<0 || j<=0){
            return 0;
        }
        return val[i][j];
    }
}