package tables;

/**
 * lc 311 medium
 *
 * Analysis:
 * 结果矩阵的行数来自第一个矩阵的行数，列数来自第二个矩阵的列数
 * 第n行m列交集位置结果，第一个矩阵n行位置分别 * 第二个矩阵m列，乘积求和
 *
 * 刚开始没思路看了下标签是hash，没想到hash怎么做。想到记录有值的行&列的做法，加速计算。
 * AC后看了题解，其实题解hash做法和这种做法思想差不多。
 *
 * 时间复杂度：O(n*m^2)
 * 空间复杂度：O(n+m)
 *
 * @author Joseph
 * @since 2021-04-09 11:15
 */
public class SparseMatrixMultiplication {

    boolean[] has1 ;
    boolean[] has2 ;

    // faster than 100%
    public int[][] multiply(int[][] mat1, int[][] mat2) {
        has1 = new boolean[mat1.length];
        has2 = new boolean[mat2[0].length];

        for (int i = 0; i < mat1.length; i++) {
            for (int j = 0; j < mat1[0].length; j++) {
                if (mat1[i][j] != 0) {
                    has1[i] = true; break;
                }
            }
        }

        for (int j = 0; j < mat2[0].length; j++) {
            for (int i = 0; i < mat2.length; i++) {
                if (mat2[i][j] != 0) {
                    has2[j] = true; break;
                }
            }
        }

        int[][] ans = new int[mat1.length][mat2[0].length];
        for (int i = 0; i < ans.length; i++) {
            for (int j = 0; j < ans[0].length; j++) {
                int sum = 0;
                if (has1[i] && has2[j]) {
                    for (int k = 0; k < mat1[0].length; k++) {
                        if (mat1[i][k] != 0 && mat2[k][j] != 0) {
                            sum += mat1[i][k] * mat2[k][j];
                        }
                    }
                }
                ans[i][j] = sum;
            }
        }
        return ans;
    }
}
