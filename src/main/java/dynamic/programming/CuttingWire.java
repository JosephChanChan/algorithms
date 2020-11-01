package dynamic.programming;

/**
 * 剑指Offer 14 medium & leetcode 343
 *
 * Analysis:
 *  f(n, m) 前n长度被分成m段的最大乘积
 *  假设最优策略中最后一段长度为j，剩下的n-j长度划分成若干段最大乘积 * j就是f(n, m)的最大乘积
 *  具体n距离划分多少段才可得到最大乘积，不知道，所以枚举m，1 <= m < n
 *  每段起点不知道，所以枚举j
 *
 *  f(n, m) = max{f(j, m-1) * j, 1<=j<n, m < n}
 *
 *  边界：f(0, m) = 0, f(n, 0) = n, f(n, m) = 0, m >= n
 *
 *  时间复杂度：O(n^3)
 *  空间复杂度：O(n^2)
 *
 * @author Joseph
 * @since 2020-11-01 18:50
 */
public class CuttingWire {

    public int cuttingRope(int n) {
        int[][] f = new int[n+1][n];

        f[0][0] = 0;

        int i, m, j ;
        for (i = 1; i < n; i++) {
            f[i][0] = i;
        }
        for (m = 1; m < n-1; m++) {
            f[0][m] = 0;
        }

        int totalMax = 0;
        for (i = 1; i <= n; i++) {
            for (m = 1; m < i; m++) {
                // 枚举第m段的起点j
                int max = -1;
                for (j = 1; j < i; j++) {
                    max = Math.max(f[i-j][m-1] * j, max);
                }
                f[i][m] = max;
                totalMax = Math.max(totalMax, max);
            }
        }
        return totalMax;
    }

}
