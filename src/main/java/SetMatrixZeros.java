/**
 * lc 73 medium
 *
 * Analysis:
 *  非最优解法
 * 时间复杂度：O(nm)
 * 空间复杂度：O(n+m)
 *
 * @author Joseph
 * @since 2022/2/4 5:25 PM
 */
public class SetMatrixZeros {

    int[] rows ;
    int[] cols ;

    public void setZeroes(int[][] matrix) {
        int n = matrix.length, m = matrix[0].length;
        rows = new int[n];
        cols = new int[m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == 0) {
                    rows[i] = 1;
                    cols[j] = 1;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            if (rows[i] == 1) {
                for (int j = 0; j < m; j++) {
                    matrix[i][j] = 0;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            if (cols[i] == 1) {
                for (int j = 0; j < n; j++) {
                    matrix[j][i] = 0;
                }
            }
        }
    }
}
