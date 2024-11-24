package dynamic.programming;

/**
 * lc 416 medium
 *
 * Analysis:
 *  01背包问题。当对于数组的元素有两种决策选or不选时，并且要求选择的元素的代价不超过某种限制。
 * 例如选择元素的代价不超过背包的空间，这种模式就是01背包问题。
 *
 * 在这题可以抽象成数组中选择一些数字，使得和不超过sum/2
 *
 * 时间复杂度：O(n*target)
 * 空间复杂度：O(n*target)
 */
public class GroupPositiveInt {

    public boolean canPartition(int[] nums) {
        int t = 0;
        for (int i : nums) t += i;
        if (t % 2 > 0) return false;

        t = t >> 1;
        /*
            f(i,j)从前i个数中选一些数求和不超过j的情况下的最大值
            f(i,j)=max{f(i-1,j), f(i-1,j-w[i])+v[i]}
            边界：
            f(0,j)=0
            f(i,0)=0
        */
        int n = nums.length;
        int[][] f = new int[n+1][t+1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= t; j++) {
                if (nums[i-1] > j) {
                    f[i][j] = f[i-1][j];
                    continue;
                }
                f[i][j] = Math.max(f[i-1][j], f[i-1][j-nums[i-1]]+nums[i-1]);
            }
        }
        return f[n][t] == t;
    }
}