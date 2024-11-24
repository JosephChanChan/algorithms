package dynamic.programming;

/**
 * lint code 476 medium
 *
 * Analysis:
 * f(i,j)合并i~j区间石子的最小代价
 * 最后一步一定是两堆石子合并。那么最后剩下的是哪两堆石子？
 * 根据题目知道，对于i~j的石子，最后可以是：
 * (i)&(i+1~j)
 * (i~i+1)&(i+2~j)
 * ...
 * (i~j-2)&(j-1~j)
 * (i~j-1)&(j)
 * 对于每一个长度的区间，要枚举最后是哪两堆子区间合并使得代价最小
 *
 * f(i,j)=min{f(i,k)+f(k+1,j) | i<=k<=j} + sum(i,j)
 *
 * 边界：
 * f(i,j)=a[i]+a[j], i+1 == j
 *
 * 时间复杂度：O(n^3)
 * 空间复杂度：O(n^2)
 *
 * @author Joseph
 * @since 2021-04-16 13:46
 */
public class MergeStones {

    public int stoneGame(int[] A) {
        int n = A.length;

        if (n < 2) return 0;

        int[] preSum = new int[n];
        preSum[0] = A[0];

        for (int i = 1; i < n; i++) preSum[i] += preSum[i-1] + A[i];

        int[][] f = new int[n][n];

        for (int i = 0; i <= n-2; i++) {
            int j = i+1;
            f[i][j] = A[i]+A[j];
        }

        for (int len = 3; len <= n; len++) {
            for (int i = 0; i <= n-len; i++) {
                int j = i+len-1;
                f[i][j] = Integer.MAX_VALUE;
                for (int k = i; k < j; k++) {
                    f[i][j] = Math.min(f[i][j], f[i][k]+f[k+1][j]+(i == 0 ? preSum[j] : preSum[j]-preSum[i-1]));
                }
            }
        }
        return f[0][n-1];
    }
}
