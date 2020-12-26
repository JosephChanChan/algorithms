package dynamic.programming;

/**
 * 剑指Offer 42 & leetcode 53
 *
 * Analysis:
 *    令f[i]表示以位置 i 为终点的所有子区间中和最大的一个(必须包含a[i])
 *  子问题:如i为终点的最大子区间包含了位置i-1,则以i-1为终点的最大子区间必然包括在其中
 *  如果f[i-1] >0, 那么显然f[i] = f[i-1] + a[i]，用之前最大的一个加上a[i]即可，因为a[i]必须包含
 *  如果f[i-1]<=0,那么f[i] = a[i] ,因为既然最大，前面的负数必然不能使你更大
 *  f(i) = max{f(i-1)+a[i],a[i]}
 *
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 */
public class MaxSubSectionSum {

    public int maxSubArray(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];

        int[] f = new int[nums.length];
        f[0] = nums[0];
        int max = f[0];
        for (int i = 1; i < f.length; i++) {
            f[i] = Math.max(f[i-1]+nums[i], nums[i]);
            if (f[i] > max) max = f[i];
        }
        return max;
    }
}