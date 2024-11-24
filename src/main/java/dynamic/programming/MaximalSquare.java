package dynamic.programming;

/**
 * lc 221 medium
 *
 * Analysis:
 *
 * 时间复杂度：dpArea O(n^2) dpValid O(n^3)
 * 空间复杂度：dpArea O(n^2) dpValid O(n^3)
 *
 * @author Joseph
 * @since 2021-03-26 15:11
 */
public class MaximalSquare {

    // O(n^3) AC 35ms
    public int dpValid(char[][] matrix) {
        /*
            首先要会枚举正方形的技巧。正方形可以定位一个右下角和边长来枚举。
            (i,j,k)为右下角i行j列边长k的正方形
            枚举得到一个正方形需要去检查这个区域内是否包含0
            对于每一个正方形都需要去检查，仔细想想这里面是存在重复子问题的。
            例如 (1,1,2) 这个正方形我们检查了一次发现不合法。
            枚举到(2,2,3) 这个正方形时又去检查了一遍在(1,1,2)已经检查过的区域。

            (1,1,2)是(2,2,3)的子问题，同样(1,2,2)和(2,1,2)是(2,2,3)子问题
            可以记录子问题的状态加速求解

            f(i,j,k)为右下角(i,j)坐标边长k的区域内是否包含0，不包含为true
            f(i,j,k)=f(i-1,j-1,k-1)&&f(i-1,j,k-1)&&f(i,j-1,k-1)&&matrix[i][j]=='1'

            边界：
            f(i,j,1)=m[i][j]==1
        */

        int n = matrix.length, m = matrix[0].length;
        boolean[][][] f = new boolean[n][m][Math.min(n, m)+1];

        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                f[i][j][1] = matrix[i][j] == '1';
                if (f[i][j][1]) max = 1;
            }
        }

        int len = Math.min(n, m);
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                for (int k = 2; k <= len; k++) {
                    if ((k-1) > i || (k-1) > j) break;
                    if (matrix[i][j] == '1' && f[i-1][j-1][k-1] && f[i-1][j][k-1] && f[i][j-1][k-1]) {
                        max = Math.max(max, k*k);
                        f[i][j][k] = true;
                    }
                }
            }
        }
        return max;
    }

    // O(n^2) AC 6ms
    public int dpArea(char[][] matrix) {
        /*
            方法一提交后居然很慢O(n^3)，AC 35ms faster than 5%...
            看题解答案是O(n^2)，枚举边长的一维被省略了

            那么我们直接记录 f(i,j)为右下角(i,j)的最大正方型面积的边长
            (i,j)点的最大面积的边长受限于左上、上边、左边的最大面积的边长
            其实就是看左上、上边、左边的最大正方形能延伸多长

            f(i,j)=min(f(i-1,j-1), f(i-1,j), f(i,j-1))+1

            边界：
            f(i,j)=m[i][j]==1 ? 1 : 0
        */

        int n = matrix.length, m = matrix[0].length;
        int[][] f = new int[n][m];

        int max = 0;
        for (int j = 0; j < m; j++) {
            f[0][j] = matrix[0][j] == '1' ? 1 : 0;
            if (f[0][j] == 1) max = 1;
        }
        for (int i = 0; i < n; i++) {
            f[i][0] = matrix[i][0] == '1' ? 1 : 0;
            if (f[i][0] == 1) max = 1;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (matrix[i][j] == '1') {
                    if (max == 0) max = 1;
                    int k = Math.min(f[i-1][j-1], Math.min(f[i-1][j], f[i][j-1]));
                    k += 1;
                    max = Math.max(max, k*k);
                    f[i][j] = k;
                }
            }
        }
        return max;
    }
}
