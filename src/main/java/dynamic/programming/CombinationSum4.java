package dynamic.programming;

/**
 * lc 377 medium
 *
 * Analysis:
 * 组合和问题系列，1，2，3都是深搜问题，到4就是求方案数，DP问题。
 * 深搜会TLE。
 *
 * 这题其实也是典型的背包问题，每个元素可选多次，并且每个物品的选择没有顺序性。
 * 即[1,2,3] t=4
 * 可以选 1+1+2，也可以选1+2+1
 * 注意到：方法1中，第一个物品和第二个物品选了1，第三个物品选2
 * 方法2中，第一个物品选1，第二个物品选2，第三个物品又倒回去选1
 *
 * 这种是物品的选择没有顺序性，谁都可以当最后一个物品。
 * 所以最后一步考虑的不是第n个物品拿不拿，而是最后一个物品是谁？
 * 如果最后一个物品是a[i]，则前面需要拼出 j-a[i] 的重量
 *
 * 时间复杂度：O(target*n)
 * 空间复杂度：O(target)
 *
 * @author Joseph
 * @since 2021-04-12 16:16
 */
public class CombinationSum4 {

    public int combinationSum4(int[] nums, int target) {
        if (target == 0 || nums.length == 0) return 0;

        return dp(nums, target);
    }

    int dp(int[] nums, int t) {
        /*
            f(j)利用数组元素有多少种方式拼出j
            f(j)={f(j-a[i]) | 1<=i<=n && a[i]<=j }
            边界：
            f(0)=1
        */
        int n = nums.length;
        int[] f = new int[t+1];
        f[0] = 1;

        for (int j = 1; j <= t; j++) {
            for (int i = 0; i < n; i++) {
                if (nums[i] <= j) {
                    f[j] += f[j-nums[i]];
                }
            }
        }
        return f[t];
    }
}
