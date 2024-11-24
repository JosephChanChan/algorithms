package dynamic.programming;

/**
 * lc 312 hard
 *
 * Analysis:
 *  最朴素的做法是dfs枚举所有排列，选出收益最大的排列
 * dfs通常是从最小的状态深搜到最大的状态。
 * 记忆化搜索从最大的状态开始思考，拆解大状态->小状态
 * 样例数据举例，最后打爆8，代表先打爆了[3,1,5]，[3,1,5]中再去枚举最后打爆谁
 * 继续分解可以发现递归树中存在相同状态的子问题
 *
 * f(i,j)是戳爆区间i~j内所有气球的最大收益
 * 枚举i~j区间内最后戳爆第k个气球，先戳爆其余气球的最大收益，选择最大的k就是i~j区间最大收益
 * f(i,j)=max{a[i-1]*a[k]*a[j+1]+f(i,k-1)+f(k+1,j)}
 *
 * 边界：
 * f(i,j)=a[i-1]*a[i]*a[i+1], i==j
 * f(i,j)=0, i<j, i<0 or j>n
 *
 * 时间复杂度：区间(0,0)￿~(n,n)都会被搜索一遍，期间每个(i,j)小区间都会枚举k，所以 O(n^3)
 * 空间复杂度：O(n^2)
 *
 * @author Joseph
 * @since 2021-03-27 22:55
 */
public class BurstBalloons {

    int n;
    int[] a;
    int[][] f;
    boolean[][] vis;

    public int maxCoins(int[] nums) {
        if (nums.length == 1) return nums[0];

        a = nums;
        n = a.length;
        f = new int[n][n];
        vis = new boolean[n][n];

        f[0][0] = a[0]*a[1];
        f[n-1][n-1] = a[n-2]*a[n-1];
        for (int i = 1; i < n-1; i++) {
            f[i][i] = a[i-1]*a[i]*a[i+1];
        }

        return dfs(0, n-1);
    }

    // 戳爆i~j的气球获得最大收益
    int dfs(int i, int j) {
        if (i < 0 || j >= n || i > j) return 0;
        if (vis[i][j]) return f[i][j];

        int max = 0;
        // 枚举i~j内最后打爆哪个气球能获得最大收益
        for (int k = i; k <= j; k++) {
            int v = (i-1 < 0 ? 1 : a[i-1]) * a[k] * (j+1 == n ? 1 : a[j+1]);
            // 打爆左边的气球能获得最大收益和右边的最大收益
            max = Math.max(max, dfs(i, k-1) + v + dfs(k+1, j));
        }
        vis[i][j] = true;
        f[i][j] = max;
        return max;
    }
}
