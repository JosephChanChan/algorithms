package dynamic.programming;

/**
 * lc 55 medium
 *
 * Analysis：
 * 时间复杂度：贪心 O(n) DP O(n^2)
 * 空间复杂度：贪心 O(1) DP O(n)
 *
 * @author Joseph
 * @since 2021-03-02 21:40
 */
public class JumpGame {

    /*
        借鉴了题解的思路。题解是维护最远能到达的距离，这里是维护最远的那个0
        最远的那个0是最有可能成为跳不过的0，设为z
        只要z前面所有的正数从它们位置开始跳不过z，那终点就是不可达的
     */
    boolean greedy(int[] nums) {
        int z = -1;
        int n = nums.length;

        for (int i = n-2; i >= 0; i--) {
            // 记录最远的那个0
            if (nums[i] == 0) {
                if (z == -1) z = i;
                continue;
            }
            // 遇到正数都看看能不能跳过z，跳的过就找下一个最远的0
            if (nums[i] > z-i) z = -1;
        }
        return z == -1;
    }

    /*
        f(i)为是否能跳到第i格，true能，false不能
        f(i)= or{f(j) && a[j]>=i-j, j < i}
     */
    boolean dp(int[] nums) {
        boolean[] f = new boolean[nums.length];
        f[0] = true;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (f[j] && nums[j] >= i-j) {
                    f[i] = true; break;
                }
            }
        }
        return f[nums.length-1];
    }
}
