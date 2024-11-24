package dynamic.programming;

/**
 * lc 63 medium
 *
 * Analysis:
 *  正常情况下走到右下角的最后一步 i,j 根据题目意思必然从 [i-1, j] [i, j-1]走过来的。
 * 对于不正常的情况，例如上面或左边的坐标不可达，那么只能取到可达的坐标的方案数加起来。
 * 对于[i, j]坐标本身是障碍不可达它的方案数自然是0。
 *
 * f(i,j)从起点走到(i,j)格子的方案数
 * 显然上一步仍然是(i-1,j)和(i,j-1)，只不过上一步是障碍的话方案数是0
 * if (i,j)==1
 *     f(i,j)=0
 * else
 *     f(i,j)=f(i-1,j)+f(i,j-1)
 *
 * 边界
 * f(i,j)=f(i,j-1) i==0
 * f(i,j)=f(i-1,j) j==0
 *
 * 时间复杂度：O(n^2)
 * 空间复杂度：O(m)
 *
 * @author Joseph
 * @since 2020-05-02 10:54
 */
public class UniquePaths2 {


    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int n = obstacleGrid.length, m = obstacleGrid[0].length;
        int old = 0, now = 1;
        int[][] f = new int[2][m];
        f[now][0] = obstacleGrid[0][0] == 1 ? 0 : 1;

        for (int j = 1; j < m; j++) {
            f[now][j] = obstacleGrid[0][j] == 1 ? 0 : f[now][j-1];
        }

        for (int i = 1; i < n; i++) {
            old = 1-old; now = 1-now;
            for (int j = 0; j < m; j++) {
                if (j == 0) {
                    f[now][j] = obstacleGrid[i][j] == 1 ? 0 : f[old][j];
                    continue;
                }
                f[now][j] = obstacleGrid[i][j] == 1 ? 0 : f[now][j-1] + f[old][j];
            }
        }
        return f[now][m-1];
    }
}
