package dynamic.programming;

import java.io.*;

/**
 * Created by Joseph on 2017/8/8.
 *  给定两个字符串S和T，对于T我们允许三种操作：
 (1) 在任意位置添加任意字符
 (2) 删除存在的任意字符
 (3) 修改任意字符
 问最少操作多少次可以把字符串T变成S？
 例如： S＝  “ABCF”   T = “DBFG”
 那么我们可以
 (1) 把D改为A
 (2) 删掉G
 (3) 加入C
 所以答案是3。

 输入
 第1行：字符串a(a的长度 <= 1000)。
 第2行：字符串b(b的长度 <= 1000)。
 输出
 输出a和b的编辑距离
 输入示例
 kitten
 sitting
 输出示例
 3

 假如要把S字符串转化为T字符串
 子问题：    设 f(i,j)为字符串S前i位和字符串T前j位最小的编辑距离。
 如果S[i] == T[j] 则求最少的编辑距离 f(i,j) = f(i-1,j-1)
 如果S[i] != T[j]  有3种情况可做
 1，把T[j]加入到S[i]前面，这样T[j]==S[i+1] 就只需要计算 f(i,j-1)的编辑距离 + 1( 1为添加T[j]的操作)
 2，既然不等于，就删除S[i]，这样就只需要计算 f(i-1,j)的编辑距离 + 1( 1为删除S[i]的操作)
 3，把S[i]修改成T[j]即可，这样只需要计算 f(i-1,j-1)的编辑距离 + 1( 1为修改S[i]的操作)
 最优子结构： 对于S[i] != T[j] 选上面3种情况最少的情况。
 递推公式:   f(i,j) = min{ f(i-1,j-1)+same(i,j) , f(i,j-1)+1 , f(i-1,j)+1 }

 @see_http://blog.csdn.net/qq_34552886/article/details/72556242
 */
public class EditDistance {

    static char[] s = null,t=null;
    static int[][] arr = null;

    public static void main(String[] args){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in),1<<16);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out), 1 << 16);
        int i = 0,j = 0;
        try {
            String a = reader.readLine();
            s = a.toCharArray();
            String b = reader.readLine();
            t = b.toCharArray();
            arr = new int[s.length+1][t.length+1];

            for(; i<=s.length; i++){
                arr[i][0] = i;
            }

            for(; j<=t.length; j++){
                arr[0][j] = j;
            }

            for(i=0; i<s.length; i++){
                for(j=0; j<t.length; j++){
                    //f(i,j) = min{ f(i-1,j-1)+same(i,j) , f(i,j-1)+1 , f(i-1,j)+1 }
                    int val = chooseMin(i+1, j+1);
                    arr[i+1][j+1] = val;
                }
            }

//            for(i=0; i<s.length+1; i++){
//                for(j=0; j<t.length+1; j++){
//                    System.out.print(arr[i][j]+" ");
//                }
//                System.out.println();
//            }


            writer.write(arr[s.length][t.length]+"\r\n");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static int chooseMin(int i,int j){
        int val = 0;
        if(arr[i-1][j-1] + same(i-1,j-1) < arr[i][j-1] + 1){
            val = arr[i-1][j-1] + same(i-1,j-1);
        }else{
            val = arr[i][j-1] + 1;
        }

        if(val > arr[i-1][j]+1){
            val = arr[i-1][j]+1;
        }

        return val;
    }

    private static int same(int i,int j){
        return s[i] == t[j] ? 0 : 1;
    }
}