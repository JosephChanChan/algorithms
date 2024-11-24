package dynamic.programming;

/**
 * lc 300 medium
 *
 * Question Description:
 *  参见 lc 300
 *
 * Analysis:
 * 时间复杂度：O(n^2)
 * 空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2020-07-30
 */
public class LongestIncreasingSequence {

    /*
        坐标型DP。
        设f(i)为ai结尾的最长单增子序列的长度
        如果这个ai结尾的子序列只有ai一个元素则f(i)=1
        若不只ai，假设为 a1 a2 ... ai，那么 ai-1 < ai，所以只要找到ai前面小于ai的元素并且以它结尾最长的序列
            f(i) = max{f(j) | j < i && aj < ai} + 1
        边界：
            f(0)=0
            f(1)=1
     */

    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return 1;

        int max = 0, i, j ;
        int[] f = new int[nums.length];
        f[0] = 1;

        for (i = 1; i < nums.length; i++) {
            int subMax = 0;
            for (j = 0; j < i; j++) {
                if (nums[j] < nums[i] && f[j] > subMax) {
                    subMax = f[j];
                }
            }
            f[i] = subMax + 1;
            if (f[i] > max) max = f[i];
        }
        return max;
    }



}