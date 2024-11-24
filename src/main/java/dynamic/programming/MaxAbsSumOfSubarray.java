package dynamic.programming;

/**
 * lc 1749 medium
 *
 * Analysis:
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2022/3/20
 */
public class MaxAbsSumOfSubarray {

    public int maxAbsoluteSum(int[] nums) {
        /**
             f(i,max)为以ai结尾的前i个数中和的最大值
             f(i,min)为以ai结尾的前i个数中和的最小值
             现有最优序列 a1 a2 ... ak，这个序列的和的绝对值最大
             ak是正数，则a1...ak-1的和肯定是正数
             ak是负数，则a1...ak-1的和肯定是负数
             从这里可推出，最优序列可能是一个很大的正数，或很小的一个负数
             所以对于每个以ai结尾的子数组和需要记录2个状态，到目前为止最大的数和最小的数
             f(i,max)=max{f(i-1,max)+ai, ai}
             f(i,min)=min{f(i-1,min)+ai, ai}
             答案是 max{abs(fmax), abs(fmin)}
         */
        int n = nums.length;

        int[][] f = new int[n][2];
        f[0][0] = f[0][1] = nums[0];
        int ans = Math.abs(f[0][0]);

        for (int i = 1; i < n; i++) {
            f[i][0] = Math.max(f[i-1][0]+nums[i], nums[i]);
            f[i][1] = Math.min(f[i-1][1]+nums[i], nums[i]);
            ans = Math.max(ans, Math.max(Math.abs(f[i][0]), Math.abs(f[i][1])));
        }
        return ans;
    }
}
