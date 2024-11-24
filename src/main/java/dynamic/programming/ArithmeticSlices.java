package dynamic.programming;

/**
 * lc 413 medium
 *
 * @author Joseph
 * @since 2023/8/26
 */
public class ArithmeticSlices {

    /**
         f(i,j)为Ai~Aj是否为等差数列，并且是的话公差为多少
         f(i,j)=f(i,j-1) && Aj-Aj-1 == Ai~Aj-1的公差
         计数f(i,j)为true的个数

         边界：f(i,j)=0, j-i<3

         区间DP时间：O(n^2)，空间：O(n^2)
         空间超过了限制 gg
     */

    public int numberOfArithmeticSlices(int[] nums) {
        int n = nums.length;
        int[][][] f = new int[n][n][2];
        int ans = 0;
        for (int len = 3; len <= n; len++) {
            for (int i = 0, j = i+len-1; j < n; i++, j++) {
                if (len == 3) {
                    if (nums[j] - nums[j-1] == nums[j-1] - nums[i]) {
                        f[i][j][0] = 1;
                        f[i][j][1] = nums[j]-nums[j-1];
                        ans++;
                    }
                    continue;
                }
                if (f[i][j-1][0] == 1 && f[i][j-1][1] == nums[j]-nums[j-1]) {
                    f[i][j][0] = 1;
                    f[i][j][1] = nums[j]-nums[j-1];
                    ans++;
                }
            }
        }
        return ans;
    }

    /**
     * 坐标DP，观察区间DP发现当前状态之和上一个状态有关
     * f(i)为Ai结尾的等差数列个数
     * 如果f(i-1)有等差数列，那么不论Ai-1为结尾，它的所有等差数列加上Ai作为新结尾就会构成f(i-1)个新等差数列
     * f(i)=f(i-1)+1, Ai-Ai-1 == Ai-1-Ai-2
     *
     * 边界：f(i)=0, i<3
     * f(i)=1, Ai-Ai-1 == Ai-1-Ai-2
     *
     * 答案是sum(f(i))
     */
    public int numberOfArithmeticSlices2(int[] nums) {
        int n = nums.length;
        if (n < 3) {
            return 0;
        }
        int ans = 0;
        int[] f = new int[n];
        if (nums[2] - nums[1] == nums[1] - nums[0]) {
            f[2] = 1;
            ans++;
        }
        for (int i = 3; i < n; i++) {
            // Aj和前面的Ai的等差数列公差相同
            if (nums[i] - nums[i-1] == nums[i-1] - nums[i-2]) {
                f[i] = f[i-1]+1;
                ans += f[i];
            }
        }
        return ans;
    }

}
