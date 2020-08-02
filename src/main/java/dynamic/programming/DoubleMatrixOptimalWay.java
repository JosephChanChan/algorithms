package dynamic.programming;

/**
 * Question Description:
 *  一个M*N矩阵中有不同的正整数，经过这个格子，就能获得相应价值的奖励，先从左上走到右下，再从右下走到左上。
 * 第1遍时只能向下和向右走，第2遍时只能向上和向左走。两次如果经过同一个格子，则该格子的奖励只计算一次，求能够获得的最大价值。
 * 例如：3 * 3的方格。
 *   1 3 3
 *   2 1 3
 *   2 2 1
 * 1 > 3 > 3 > 3 > 1 > 2 > 2 > 2 > 1 去掉起点和终点的重复计算最大收益为17
 *
 * Analysis:
 * 和UniquePaths一样的做法，不过是走多一次，把第一遍的走法记录下来格子全部记为0，再从左上走一遍到右下。
 *
 * 时间复杂度：O(n^2)
 * 空间复杂度：O(n^2)
 *
 * @author Joseph
 * @since 2020-07-31
 *
 */
public class DoubleMatrixOptimalWay {

    /*
        f(i,j) = max{f(i-1,j), f(i,j-1)} + a[i][j]
     */

    public static void main(String[] args) {
        int[][] matrix = {{1,3,3}, {2,1,3}, {2,2,1}};
        DoubleMatrixOptimalWay test = new DoubleMatrixOptimalWay();
        int firstPrice = test.go(matrix);
        int secondPrice = test.go(matrix);
        System.out.println(firstPrice + secondPrice);
    }

    private int go(int[][] matrix) {
        int i, j, n = matrix.length, m = matrix[0].length;

        int price = 0;
        int[][] temp = new int[n][m];
        for (i = 0; i < n; i++) {
            for (j = 0; j < m; j++) {
                temp[i][j] = matrix[i][j];
            }
        }

        for (i = 1; i < n; i++) {
            temp[i][0] += temp[i-1][0];
        }
        for (j = 1; j < m; j++) {
            temp[0][j] += temp[0][j-1];
        }

        for (i = 1; i < n; i++) {
            for (j = 1; j < m; j++) {
                temp[i][j] += Math.max(temp[i-1][j], temp[i][j-1]);
            }
        }

        price += temp[n-1][m-1];

        // back track
        i = n-1; j = m-1;
        matrix[i][j] = 0;
        while (!(i == 0 && j == 0)) {
            if (i == 0) {
                matrix[i][j-1] = 0;
                j--;
                continue;
            }
            if (j == 0) {
                matrix[i-1][j] = 0;
                i--;
                continue;
            }
            if (temp[i-1][j] > temp[i][j-1]) {
                matrix[i-1][j] = 0;
                i--;
            }
            else {
                matrix[i][j-1] = 0;
                j--;
            }
        }
        return price;
    }

}
