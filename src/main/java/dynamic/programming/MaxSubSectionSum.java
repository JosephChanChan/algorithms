package dynamic.programming;

/**
 * Question Description:
 *  参见 leetcode 53
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
        int sum = Integer.MIN_VALUE, temp = 0;
        // 最大连续子段和开始和结束下标
        int start = 0, end = 0;
        for(int i = 0; i < nums.length; i++){
            if (temp > 0) {
                temp += nums[i];
            }
            else {
                temp = nums[i];
                start = i;
            }
            if (temp > sum) {
                sum = temp;
                end = i;
            }
        }
        return sum;
    }

}