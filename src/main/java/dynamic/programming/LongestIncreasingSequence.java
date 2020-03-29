package dynamic.programming;

import java.io.*;

/**
 * 给定一个数列，从中删掉任意若干项剩余的序列叫做它的一个子序列，求它的最长的子序列，满足子序列中的元素是单调递增的。
 * 例如给定序列{1,6,3,5,4},答案是3，因为{1,3,4}和{1,3,5}就是长度最长的两个单增子序列。
 * 设定一个状态:
 * dp(i) 表示在原序列中以第i位结尾(包含第i位元素)的最长单增子序列的长度。
 * 根据最优子结构特性思考和前面那些元素的关系，找出状态转移方程。
 * dp(i) 肯定存在一个最长的单增子序列，假设是 A[]，长度为 n。那么 A1 ... An 中的最后一位 An
 * 肯定的是 n < i 且 An < Ai，因为要将 Ai 加入到 A[] 中去，作为递增的。
 * 要求 dp(i) 最长的单增子序列，那么是不是要求最长的 A[] ?
 * 所以要找到一个最长的 dp(n)，这样把 Ai 加到 dp(n) 的子序列中去，
 * 得到状态转移方程：
 * dp(i) = max{dp(n)} + 1，{n | n < i 且 An < Ai}
 *
 * 用方程求出原序列中每一位以它结尾的最长单增子序列后，再看哪一位结尾的元素的子序列是最长的就OK了。
 * 即看 max{dp(i)} {i | 1 ~ 原序列长度}
 *
 * Created by Joseph on 2017/8/17.
 */
public class LongestIncreasingSequence {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in), 1 << 16);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out), 1 << 16);
        int count, n, maxL = 0, f = 0;
        int[] arr ;
        int[][] val ;
        n = Integer.parseInt(reader.readLine());
        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(reader.readLine());
        }

        val = new int[n][2];
        val[0][0] = 1;      // 第0列记录第i位的最长单增子序列的长度
        val[0][1] = -1;     // 第1列记录第i位的最长单增子序列中前一位aj的位置，可以借此得到具体的最长单增子序列

        /*
            dp(i) = max{dp(n)} + 1 {n | n < i 且 An < Ai}
         */
        for (int i = 1; i < n; i++) {
            int max = 0, aj = -1;
            for (int j = 0; j < i; j++) {
                if (val[j][0] > max && arr[j] < arr[i]) {
                    max = val[j][0];
                    aj = j;
                }
            }
            val[i][0] = max + 1;
            val[i][1] = aj;
            if (val[i][0] > maxL) {
                maxL = val[i][0];
                f = i;
            }
        }

        int k = 0, v = f;
//            System.out.print(arr[f]+" ");
//            while ((k=val[f][1]) != -1){
//                System.out.print(arr[k]+" ");
//                f = k;
//            }

        count = val[v][0];
        writer.write(count + "\r\n");
        writer.flush();
    }
}