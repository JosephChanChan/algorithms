package dynamic.programming;

/**
 * lc 279 medium
 *
 * Analysis:
 *  假设有k个完全平方数凑成n，a1...ak，最后一个ak，剩下的a1...ak-1也能凑出n-ak
 * 上一步就是n-ak，有多少个上一步取决于ak有多少个，ak是多少不知道，所以的枚举ak
 * ak是一个平方数设成j^2，j^2从1~n都有可能，所以j的范围 1<=j<=根号n
 *
 * f(n)拼出n的完全平方数最少数量
 * f(n)=min{f(n-j) | j是完全平方数 && j <= n} + 1
 *
 * 边界：
 * max是正无穷，表示拼不出n
 * f(0)=0
 * f(1)=1
 * f(2)=2
 *
 * 时间复杂度：O(n*根号n)
 * 空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2020-05-23 16:53
 */
public class PrefectSquares {

    int c = 0;

    public int numSquares(int n) {
        if (n == 1) return 1;

        return dp(n);
    }

    int dp(int n) {
        int max = Integer.MAX_VALUE;
        int[] f = new int[n+1];
        f[0] = 0;
        f[1] = 1;

        for (int i = 2; i <= n; i++) {
            f[i] = max;
            // 枚举j，t*t=j 只需要枚举t，保证t*t < n
            for (int t = 1; t*t <= i; t++) {
                int j = t*t;
                f[i] = Math.min(f[i], f[i-j]+1);
            }
        }
        return f[n];
    }

    // dfs思路是错的，其实这里的dfs结合了贪心，每次都选一个最大的平方数，但此贪心策略是错的。反例：12 会构造出[9,1,1,1]
    boolean dfs(int i, int sum, int k, int n) {
        if (sum == n) {
            c = k;
            return true;
        }

        for (int j = i; j >= 1; j--) {
            int v = (int) Math.sqrt(j);
            if (v * v != j) continue;

            if (sum + j > n) {
                j = j-((sum+j)-n)+1;
                continue;
            }

            if (dfs(j, sum+j, k+1, n)) return true;
        }
        return false;
    }
}
