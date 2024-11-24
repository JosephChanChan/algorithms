package dynamic.programming;

/**
 * lc 2419 medium
 *
 * Analysis:
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2022/9/26
 */
public class LongestSubarrayWithMaxBitwiseAND {

    /**
         f(i,0)为以Ai结尾的前i个元素做与运算的最大值
         f(i,1)为以Ai结尾的前i个元素做与运算的最大值的长度
         f(i,0)=max{f(i-1,0) & Ai, Ai}
     */

    public int longestSubarray(int[] nums) {
        int n = nums.length;
        int[][] f = new int[n][2];
        f[0][0] = nums[0];
        f[0][1] = 1;

        for (int i = 1; i < n; i++) {
            int a = nums[i];
            int b = (f[i-1][0] & nums[i]);
            if (b >= a) {
                f[i][1] = f[i-1][1] + 1;
                f[i][0] = b;
            }
            else {
                f[i][0] = a;
                f[i][1] = 1;
            }
        }
        int max = 0, maxLen = 0;
        for (int i = 0; i < n; i++) {
            if (max < f[i][0]) {
                max = f[i][0];
                maxLen = f[i][1];
            }
            if (max == f[i][0] && maxLen < f[i][1]) {
                maxLen = f[i][1];
            }
        }
        return maxLen;
    }
}
