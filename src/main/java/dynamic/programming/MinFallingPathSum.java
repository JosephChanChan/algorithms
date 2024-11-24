package dynamic.programming;

/**
 * lc 931 medium
 *
 * Analysis：
 *  f(i,j)是i行j列的最小下降路径和
 * f(i,j)=min{f(i-1,j-1), f(i-1,j), f(i-1,j+1)}+a[i][j]
 * 边界：
 * f(i,j)=a[i][j], i==0
 *
 * 时间复杂度：O(n^2)
 * 空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2021-03-14 00:16
 */
public class MinFallingPathSum {

    public int minFallingPathSum(int[][] matrix) {
        int n = matrix.length;
        int[][] f = new int[2][n];

        int old = 0, now = 1, min = Integer.MAX_VALUE;
        for (int j = 0; j < n; j++) {
            f[now][j] = matrix[0][j];
            min = Math.min(min, f[now][j]);
        }

        if (n == 1) return min;

        min = Integer.MAX_VALUE;
        for (int i = 1; i < n; i++) {
            old = 1-old; now = 1-now;
            for (int j = 0; j < n; j++) {
                if (j == 0) {
                    f[now][j] = Math.min(f[old][j], f[old][j+1])+matrix[i][j];
                }
                else if (j == n-1) {
                    f[now][j] = Math.min(f[old][j], f[old][j-1])+matrix[i][j];
                }
                else {
                    f[now][j] = min(f[old][j-1], f[old][j], f[old][j+1])+matrix[i][j];
                }
                if (i == n-1) {
                    min = Math.min(min, f[now][j]);
                }
            }
        }
        return min;
    }

    int min(int a, int b, int c) {
        int min = Math.min(a, b);
        return Math.min(min, c);
    }
}
