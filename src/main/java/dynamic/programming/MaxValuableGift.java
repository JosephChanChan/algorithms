package dynamic.programming;

/**
 * 剑指Offer 47 medium
 *
 * Analysis:
 *  跟Unique Paths一样的DP
 *
 * 时间复杂度：O(n*m)
 * 空间复杂度：O(m)
 *
 * @author Joseph
 * @since 2021-01-03 20:18
 */
public class MaxValuableGift {

    public int maxValue(int[][] grid) {
        /*
            f(i,j)=max{f(i-1,j), f(i,j-1)}
            边界：f(0,0)=a[0][0]
        */
        int n = grid.length;
        int m = grid[0].length;
        int[][] f = new int[2][m];
        f[0][0] = grid[0][0];

        int old = 0, now = 1;
        for (int r = 0; r < n; r++) {
            old = now;
            now = 1 - now;
            for (int c = 0; c < m; c++) {
                if (r == 0 && c > 0) {
                    f[now][c] = f[now][c-1] + grid[r][c];
                }
                if (c == 0 && r > 0) {
                    f[now][c] = f[old][c] + grid[r][c];
                }
                if (r > 0 && c > 0) {
                    f[now][c] = Math.max(f[old][c], f[now][c-1]) + grid[r][c];
                }
            }
        }
        return f[now][m-1];
    }

}
