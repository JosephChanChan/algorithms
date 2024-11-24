package dynamic.programming;

/**
 * lc 62 medium
 *
 * Question Description:
 *  参见 lc 62
 *
 * Analysis:
 * 时间复杂度：O(n^2)
 * 空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2020-07-29 22:02
 */
public class UniquePaths {

    /*
        设第i行j列格子有f(i,j)中走法
        上一步只能从(i-1,j)和(i,j-1)走过来，这两个格子的走法的和就是走到(i,j)的走法
            f(i,j) = f(i-1,j) + f(i,j-1)
        边界：
            f(i,j)=0, i || j < 0
            f(0,0)=1
     */

    // 滚动数组
    int[][] a ;

    public int uniquePaths(int m, int n) {
        if (m == 0 || n == 0) return 0;
        if (m * n == 1) return 1;

        int i, j, old, now = 0 ;
        a = new int[2][n];
        for (i = 0; i < m; i++) {
            // swap old and now
            old = now;
            now = 1 - now;
            for (j = 0; j < n; j++) {
                if (i == 0 || j == 0) {
                    a[now][j] = 1; continue;
                }
                a[now][j] = a[old][j] + a[now][j-1];
            }
        }
        return a[now][m-1];
    }

}
