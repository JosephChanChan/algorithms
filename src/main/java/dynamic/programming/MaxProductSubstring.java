package dynamic.programming;

/**
 * lc 152 medium
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
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)可优化成O(1)
 *
 * @author Joseph
 * @since 2020-05-02 00:19
 */
public class MaxProductSubstring {

    public int maxProduct(int[] nums) {
        /*
             f(i,0)为以ai结尾的最大子数组乘积
             f(i,1)为以ai结尾的最小子数组乘积
             if ai > 0 期望i-1结尾的子数组乘积越大越好
             if ai < 0 期望i-1结尾的子数组乘积越小越好
             if ai = 0 则f(i,0)&f(i,1)=0
         */
        int n = nums.length;
        int[][] f = new int[n][2];
        f[0][0] = nums[0];
        f[0][1] = nums[0];
        int[] a = nums;

        int max = Math.max(Integer.MIN_VALUE, f[0][0]);
        for (int i = 1; i < n; i++) {
            if (a[i] == 0) {
                f[i][0] = 0;
                f[i][1] = 0;
            }
            else if (a[i] > 0) {
                f[i][0] = Math.max(f[i-1][0]*a[i], a[i]);
                f[i][1] = Math.min(f[i-1][1]*a[i], a[i]);
            }
            else {
                f[i][0] = Math.max(f[i-1][1]*a[i], a[i]);
                f[i][1] = Math.min(f[i-1][0]*a[i], a[i]);
            }
            max = Math.max(max, f[i][0]);
        }
        return max;
    }

}
