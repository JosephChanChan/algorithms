package dynamic.programming;

/**
 * @author Joseph
 * @since 2020-05-02 00:19
 *
 * leetcode 152 medium
 *
 * Question Description:
 *  Given an integer array nums, find the contiguous subarray within an array (containing at least one number) which has the largest product.
 * Example 1:
 * Input: [2,3,-2,4]
 * Output: 6
 * Explanation: [2,3] has the largest product 6.
 * Example 2:
 * Input: [-2,0,-1]
 * Output: 0
 * Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
 *
 * Analysis:
 *  f(j)为以aj结尾的最大子串积
 * 对于整个序列来说肯定有一个最大的子串它的积是最大的，只需要记录下最大的f(j)。
 * 假设一个最大子串长度是1，那么它必然是某个aj
 * 如果一个最大子串长度>1，它的最后一步设为aj，aj前一步肯定是aj-1
 * 这个时候就得思考aj-1怎么转移到aj这个状态。
 * 如果aj>0，对于aj-1肯定是越大越好，因为aj-1*aj后使得整个以aj结尾的子串的积更大。
 * 如果aj<0，对于aj-1肯定是越小越好，因为以aj-1结尾的子串的积如果是越小的负数乘上aj后，
 * 可以使得以aj结尾的子串的积从负转正。
 * 如果aj==0，f(j)=0，因为必然包含aj。
 * 但是要处理一些特殊情况，例如对于aj是负数则对j-1期望越小越好，但是j-1的积只有正数怎么办？
 * 那只能取aj了。同理对于aj是正数而j-1的积只有负数时，只能取aj
 *
 * if aj > 0
 *     f(j)=max{positive(f(j-1))*aj, aj}
 * if aj < 0
 *     f(j)=max{negative(f(j-1))*aj, aj}
 * else
 *     f(j)=0
 *
 * positive(j)为取j结尾的子串的积中最大的积
 * negative(j)为取j结尾的子串的积中最小的积
 *
 * 边界：f(0)=0 f(1)=a1
 *
 *  时间复杂度：O(n)
 *  空间复杂度：O(n)可优化成O(1)
 */
public class MaxProductSubstring {

    public int maxProduct(int[] nums) {
        int max = 0;
        // 对于每个f(j)，f[j][0]是j结尾的子串中最小的积，f[j][1]是j结尾的子串中最大的积
        int[][] products = new int[nums.length][2];
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];

        products[0][0] = nums[0];
        products[0][1] = nums[0];

        max = Math.max(max, products[0][1]);

        for (int j = 1; j < nums.length; j++) {
            int aj = nums[j];
            if (aj > 0) {
                products[j][1] = Math.max(products[j-1][1] * aj, aj);
                products[j][0] = Math.min(products[j-1][0] * aj, aj);
            }
            else if (aj < 0) {
                products[j][1] = Math.max(products[j-1][0] * aj, aj);
                products[j][0] = Math.min(products[j-1][1] * aj, aj);
            }
            else {
                products[j][1] = products[j][0] = 0;
            }
            max = Math.max(max, products[j][1]);
        }
        return max;
    }

}
