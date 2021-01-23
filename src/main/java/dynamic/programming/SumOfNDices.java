package dynamic.programming;

import java.util.*;

/**
 * 剑指Offer 60 medium
 *
 * Analysis:
 *  这题一开始是懵的，思考了十几分钟用DFS做了，最后一个case TLE
 * 看了提示用DP写了一下过了，不过自己做完全想不到DP上去。
 *
 * f(i, j)为前i个骰子摇出j点的方案数
 * f(i, j) = f(i-1, j-6)+..+f(i-1, j-1)
 * f(0, j)=0, f(i, 0)=0, f(i, j)=0 && j < i or j > 6i
 * 1 <= i <= n
 * i <= j <= 6i
 *
 * 时间复杂度：DP O(n^2) DFS O(6^n)
 * 空间复杂度：DP O(n^2) DFS O(6n-n)
 *
 * @author Joseph
 * @since 2021-01-18 20:40
 */
public class SumOfNDices {

    public static void main(String[] args) {
        SumOfNDices test = new SumOfNDices();
        test.dicesProbability(2);
    }

    public double[] dicesProbability(int n) {
        return usingDP(n);
    }

    // AC 0ms
    public double[] usingDP(int n) {
        int[][] f = new int[n+1][6*n+1];
        // 1个骰子时必然摇出1~6点，可能数都是1
        f[1][1] = f[1][2] = f[1][3] = f[1][4] = f[1][5] = f[1][6] = 1;

        // 枚举n个骰子
        for (int i = 2; i <= n; i++) {
            // 枚举前i个骰子，可以摇出的点数的可能数
            for (int j = i; j <= 6*i; j++) {
                int c = 0;
                // 最后一个骰子可以摇出1~6点，上一个骰子则可以摇出 j-1~j-6点
                for (int k = 1; k < j && k < 7; k++) {
                    c += f[i-1][j-k];
                }
                f[i][j] = c;
            }
        }
        double base = Math.pow(6, n);
        double[] ans = new double[6*n-n+1];
        for (int s = n, i = 0; s <= 6*n; s++, i++) {
            ans[i] = f[n][s]/base;
        }
        return ans;
    }

    // dfs枚举所有s出现次数，除与总可能数 最后一个case超时
    Map<Integer, Integer> map = new HashMap<>();

    private double[] usingDFS(int n) {
        dfs(0, 0, n);
        int base = 6;
        for (int i = 2; i <= n; i++) {
            base *= 6;
        }
        double p = (double) base;
        double[] ans = new double[map.size()];
        Integer[] keys = map.keySet().toArray(new Integer[0]);
        Arrays.sort(keys);
        for (int j = 0; j < keys.length; j++) {
            ans[j] = map.get(keys[j]) / p;
        }
        return ans;
    }

    private void dfs(int sum, int d, int n) {
        if (d == n) {
            int c = map.getOrDefault(sum, 0);
            map.put(sum, c+1);
            return;
        }
        for (int i = 1; i <= 6; i++) {
            dfs(i + sum, d+1, n);
        }
    }
}
