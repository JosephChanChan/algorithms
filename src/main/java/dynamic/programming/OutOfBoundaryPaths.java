package dynamic.programming;

/**
 * lc 576 medium
 *
 * Analysis:
 *  (i,j)向上下左右可以走出界的路径数，如果知道上下左右可以走出界的路径数求和就是当前(i,j)的出界路径数。
 * 没有明确方向性，考虑记忆化搜索。
 * f(i,j,w)是在(i,j)点出发时继续走w步的出界数
 * f(i,j,w)=f(i-1,j,w-1)+f(i+1,j,w-1)+f(i,j-1,w-1),f(i,j+1,w-1)
 *
 * 时间复杂度：O(nmN)
 * 空间复杂度：O(nmN)
 *
 * @author Joseph
 * @since 2021-03-19 17:39
 */
public class OutOfBoundaryPaths {

    double mod = 1e9+7;
    int m, n ;
    int[][] d = {{1,0},{-1,0},{0,-1},{0,1}};
    int[][][] f ;

    public int findPaths(int m, int n, int N, int i, int j) {
        this.m = m;
        this.n = n;
        f = new int[m][n][N+1];

        for (int p = 0; p < m; p++) {
            for (int q = 0; q < n; q++) {
                for (int k = N; k > 0; k--) {
                    f[p][q][k] = -1;
                }
            }
        }
        return dfs(i, j, N);
    }

    int dfs(int i, int j, int N) {
        if (i < 0 || i == m || j < 0 || j == n) return 1;
        if (N == 0) return 0;
        if (f[i][j][N] > -1) return f[i][j][N];

        long cases = 0;
        for (int k = 0; k < d.length; k++) {
            int y = i + d[k][0];
            int x = j + d[k][1];
            cases += (int)(dfs(y, x, N-1)%mod);
        }
        f[i][j][N] = (int)(cases%mod);
        return f[i][j][N];
    }
}
