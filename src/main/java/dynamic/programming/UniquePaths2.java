package dynamic.programming;

/**
 * leetcode 63 medium
 *
 * Question Description:
 *  参见 leetcode 63
 *
 * Analysis:
 *  正常情况下走到右下角的最后一步 i,j 根据题目意思必然从 [i-1, j] [i, j-1]走过来的。
 * 对于不正常的情况，例如上面或左边的坐标不可达，那么只能取到可达的坐标的方案数加起来。
 * 对于[i, j]坐标本身是障碍不可达它的方案数自然是0。
 *
 * 设f(i,j)为从原点走到i行j列坐标的方案数。
 * if [i, j] == 1
 *     f(i,j)=0
 * if arrive(i-1,j) && arrive(i,j-1)
 *     f(i,j)=f(i,j-1)+f(i-1,j)
 * else if arrive(i-1,j)
 *     f(i,j)=f(i-1,j)
 * else if arrive(i,j-1)
 *     f(i,j)=f(i,j-1)
 *
 * arrive(i,j)代表坐标是否可达
 *
 * 其实也不用像上面那样判断那么多种情况，直接将不可达的坐标方案数置0，正常算就行
 * if [i,j] == 1
 *     f(i,j)=0
 * else
 *     f(i,j)=f(i,j-1)+f(i-1,j)
 *
 * 边界：f(0,0)=1，{f(i,j)=0 | i,j < 0}
 *
 *  时间复杂度：O(n^2)
 *  空间复杂度：O(n^2)可优化成O(1)
 *
 * @author Joseph
 * @since 2020-05-02 10:54
 */
public class UniquePaths2 {


    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int n = obstacleGrid.length, m = obstacleGrid[0].length;
        int[][] planCount = new int[n][m];
        if (obstacleGrid[0][0] == 1) {
            planCount[0][0] = 0;
        }
        else {
            planCount[0][0] = 1;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0 && j == 0) continue;
                if (obstacleGrid[i][j] == 1) {
                    planCount[i][j] = 0;
                    continue;
                }
                planCount[i][j] = getPrevious(i-1, j, planCount) + getPrevious(i, j-1, planCount);
            }
        }

        return planCount[n-1][m-1];
    }

    private int getPrevious(int i, int j, int[][] planCount) {
        if (i < 0 || j < 0) {
            return 0;
        }
        return planCount[i][j];
    }
}
