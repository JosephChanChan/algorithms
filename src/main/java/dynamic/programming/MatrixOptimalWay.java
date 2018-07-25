package dynamic.programming;

import java.io.*;

/**
 * Created by Joseph on 2017/7/13.
 *  给定一个m行n列的矩阵，矩阵每个元素是一个正整数，你现在在左上角（第一行第一列），
 *  你需要走到右下角（第m行，第n列），每次只能朝右或者下走到相邻的位置，不能走出矩阵。走过的数的总和作为你的得分，求最大的得分。
 *
 *  最优子结构: 假设点(x,y)是最优路径上的一点  f(x,y) 代表从起点到点(x,y)获得的最大数
 *  f(x, y) = max{ f(x – 1, y) , f(x, y – 1) } + A[x][y]
 *
 *  这道题的解法和贪心很像，不仔细思考会被绕进去。
 *  贪心算法解这道题，是每一步都只算当前的最优情况，动态规划算的是到达x,y点之前的全部情况，在之前的情况中选择最优的。
 */
public class MatrixOptimalWay {

    public static void main(String[] args){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in),1<<16);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out), 1 << 16);
        int sum = 0,n;
        int[][] arr = null;
        String nums = null;
        try {
            n = Integer.parseInt(reader.readLine());
            arr = new int[n][n];
            for(int i=0; i<n; i++){
                nums = reader.readLine();
                String[] split = nums.split(" ");
                for(int j=0; j<n; j++){
                    arr[i][j] = Integer.parseInt(split[j]);
                }
            }

            //根据状态转移方程 f(x, y) = max(f(x – 1, y) , f(x, y – 1) ) + A[x][y]
            for(int y=0; y<n; y++){
                for(int x=0; x<n; x++){
                    if(x==0 && y==0) continue;
                    else if(x==0){
                        arr[y][x] += arr[y-1][x];
                    }else if(y==0){
                        arr[y][x] += arr[y][x-1];
                    }else{
//                        arr[y][x] += arr[y][x-1] > arr[y-1][x] ? arr[y][x-1] : arr[y-1][x];
                        if(arr[y][x-1] > arr[y-1][x]){
                            arr[y][x] += arr[y][x-1];
                        }else{
                            arr[y][x] += arr[y-1][x];
                        }
                    }
                }
            }

            for(int y=0; y<n; y++){
                for(int x=0; x<n; x++){
                    System.out.print(arr[y][x]+" ");
                }
                System.out.println();
            }
            System.out.println();

            //贪心算法，选择已经出来的最优路径走
            int x=0,y=0;
            System.out.print(arr[y][x]+" ");
            while (x<n-1 || y<n-1){
                if(x+1>=n){
                    System.out.print(arr[y+1][x]+" ");
                    y+=1;
                }else if(y+1>=n){
                    System.out.print(arr[y][x+1]+" ");
                    x+=1;
                } else if(arr[y][x+1]>arr[y+1][x]){
                    System.out.print(arr[y][x+1]+" ");
                    x+=1;
                }else{
                    System.out.print(arr[y+1][x]+" ");
                    y+=1;
                }
            }
            System.out.println();

//            System.out.println("最优路径得出数量: "+arr[n-1][n-1]);
            System.out.println(arr[n-1][n-1]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}