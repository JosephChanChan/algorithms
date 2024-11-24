package dynamic.programming;

/**
 * lc 361 medium
 *
 * Analysis:
 *  最朴素的做法是枚举每个0的格子，计算在此格子放炸弹，向4个方向能炸死的人数。
 * 但是在下一行同一列又要重新计算向上能炸死的人数，存在重复计算的子问题。
 * 分析到这里发现满足DP特征之一
 *
 * 预处理每个空格，记录当前空格向上/下/左/右能炸死的敌人数
 *
 * up[i][j] = 0, a[i][j]==W
 * up[i][j] = up[i-1][j]+a[i][j]==E ? 1 : 0
 *
 * 同理left right down
 *
 * 时间复杂度：O(nm)
 * 空间复杂度：O(nm)
 *
 * @author Joseph Chan
 * @since 2021-04-15
 */
public class BombEnemy {

    public int maxKilledEnemies(char[][] grid) {
        int n = grid.length, m = grid[0].length;

        int[][] up = new int[n][m];
        int[][] down = new int[n][m];
        int[][] left = new int[n][m];
        int[][] right = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 'W') continue;
                if (i == 0) {
                    up[i][j] = grid[i][j] == 'E' ? 1 : 0;
                    continue;
                }
                up[i][j] = up[i-1][j] + (grid[i][j] == 'E' ? 1 : 0);
            }
        }
        for (int i = n-1; i >= 0; i--) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 'W') continue;
                if (i == n-1) {
                    down[i][j] = grid[i][j] == 'E' ? 1 : 0;
                    continue;
                }
                down[i][j] = down[i+1][j] + (grid[i][j] == 'E' ? 1 : 0);
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 'W') continue;
                if (j == 0) {
                    left[i][j] = grid[i][j] == 'E' ? 1 : 0;
                    continue;
                }
                left[i][j] = left[i][j-1] + (grid[i][j] == 'E' ? 1 : 0);
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = m-1; j >= 0; j--) {
                if (grid[i][j] == 'W') continue;
                if (j == m-1) {
                    right[i][j] = grid[i][j] == 'E' ? 1 : 0;
                    continue;
                }
                right[i][j] = right[i][j+1] + (grid[i][j] == 'E' ? 1 : 0);
            }
        }

        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == '0') {
                    int f = up[i][j] + down[i][j] + left[i][j] + right[i][j];
                    max = Math.max(max, f);
                }
            }
        }
        return max;
    }

}
