package dynamic.programming;

/**
 * lc 494 medium
 *
 * Analysis:
 *  变形的背包问题。数组元素还是存在两个决策，对ai是拿正数/负数，存在求和等于s的空间限制。
 * 所以可抽象为数组前i个元素求和，和等于j的方案数
 *
 * 时间复杂度：dp O(n*sum) 深搜 小于O(2^n)
 * 空间复杂度：dp O(n*sum) 深搜 O(n)
 *
 * @author Joseph
 * @since 2021-03-23 18:50
 */
public class TargetSum {

    int t, c = 0;
    int[][] f ;

    public int findTargetSumWays(int[] nums, int S) {
        return dp(nums, S);
    }
    int dp(int[] nums, int target) {
        /*
             f(i,j)为前i个元素拼出j的方案数
             f(i,j)=f(i-1, j-ai) + f(i-1, j+ai)
             需要枚举第二维从 -sum(i)~sum(i)

             边界：
             f(0,j)=0, a[0]!=j
             f(0,j)=1, a[0]==j
             f(0,-j)=1, -a[0]==-j
         */

        int n = nums.length;
        int sum = 0;
        for (int i = 0; i < n; i++) sum += nums[i];
        int offset = sum;

        if (sum < target) return 0;

        int[][] f = new int[n][(sum<<1)+1];
        // -3 -2 -1 0 1 2 3
        // 0  1  2  3 4 5 6
        f[0][nums[0]+offset] = 1;
        f[0][-nums[0]+offset] = 1;
        // 第一个元素是0，共有+0 -0 两种方案数
        if (nums[0] == 0) {
            f[0][nums[0]+offset] = 2;
        }

        for (int i = 1; i < n; i++) {
            for (int j = -sum; j <= sum; j++) {
                if (j+(-nums[i])+offset >= 0) {
                    f[i][j+offset] += f[i-1][j+(-nums[i])+offset];
                }
                if (j+nums[i]+offset < (sum<<1)+1) {
                    f[i][j+offset] += f[i-1][j+nums[i]+offset];
                }
                /*if (i == n-1) {
                    System.out.println("前i个拼"+j+"的方案数="+f[n-1][j+offset]);
                }*/
            }
        }
        return f[n-1][target+offset];
    }

    // AC 167ms faster than 40% 带一点剪枝功能的dfs，比暴力搜索要快一点
    int usingSuffixes(int[] nums, int S) {
        int n = nums.length;
        t = S;
        // 以每个i为终点累计的最大值和最小值，后缀和
        f = new int[n][2];
        f[n-1][0] = nums[n-1];
        f[n-1][1] = -nums[n-1];
        for (int j = n-2; j >= 0; j--) {
            f[j][0] = nums[j] + f[j+1][0];
            f[j][1] = -nums[j] + f[j+1][1];
        }

        dfs(0, nums, 0);
        return c;
    }
    void dfs(int i, int[] a, int sum) {
        if (i == a.length-1) {
            if ((sum + a[i]) == t) c++;
            if ((sum - a[i]) == t) c++;
            return;
        }

        // 如果当前ai是很大的正数，加上下一层能拿到的最小负数，还是超过目标和t，剪枝
        if ((sum + a[i] + f[i+1][1]) <= t) dfs(i+1, a, sum+a[i]);
        // 如果当前ai是很小的负数，加上下一层能拿到的最大正数，还是小于目标和t，剪枝
        if ((sum - a[i] + f[i+1][0]) >= t) dfs(i+1, a, sum-a[i]);
    }
}
