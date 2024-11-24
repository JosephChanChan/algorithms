package dynamic.programming;

/**
 * lc 64 medium
 *
 * Analysis：
 *  坐标型DP是最简单的DP了，这题和lc 62 63 剑指47是一个题型
 *
 * 时间复杂度：O(nm)
 * 空间复杂度：O(m)
 *
 * @author Joseph
 * @since 2021-03-03 22:34
 */
public class MinimumPathSum {

    public int minPathSum(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        int old = 0, now = 1;
        int[][] f = new int[2][m];
        f[now][0] = grid[0][0];

        for (int j = 1; j < m; j++) {
            f[now][j] = f[now][j-1] + grid[0][j];
        }

        for (int i = 1; i < n; i++) {
            old = 1-old; now = 1-now;
            for (int j = 0; j < m; j++) {
                if (j == 0) {
                    f[now][j] = f[old][j] + grid[i][j];
                    continue;
                }
                f[now][j] = Math.min(f[old][j], f[now][j-1]) + grid[i][j];
            }
        }
        return f[now][m-1];
    }
}
