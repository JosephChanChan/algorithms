package math;

/**
 * lc 1252 easy
 *
 * @author Joseph
 * @since 2022/7/12
 */
public class CellsWithOddValInMatrix {


    /*
        最优解，数学分析题
        单元格是奇数的必要条件是所在行和列的累加次数奇偶性相异
        也就是说统计出了有k行累加了奇数次，则由m-k行累加了偶数次
        k行中任意一行都可以和累加了偶数次的任意一列，组成一个累加奇数次的单元格
        设有j列累加了偶数次，则根据组合原理，有k*j个奇数值的单元格

        时间复杂度：O(indices+n+m)
        空间复杂度：O(n+m)
     */
    int[] rows2, cols2 ;

    public int oddCells3(int m, int n, int[][] indices) {
        rows2 = new int[m];
        cols2 = new int[n];
        for (int i = 0; i < indices.length; i++) {
            // 单元格的奇数取决于所在行和列被加了多少次，只用对indices的行和列计数
            int r = indices[i][0];
            int c = indices[i][1];
            rows2[r]++;
            cols2[c]++;
        }
        // 奇数的行可以和偶数的列组合成奇数的单元格
        int oddRows = 0, oddCols = 0;
        for (int i = 0; i < rows2.length; i++) {
            if ((rows2[i] & 1) == 1) oddRows++;
        }
        for (int i = 0; i < cols2.length; i++) {
            if ((cols2[i] & 1) == 1) oddCols++;
        }
        return oddRows * (n - oddCols) + oddCols * (m - oddRows);
    }

    /*
        优化的模拟，不需要真的去对行和列的每一个单元格做计数
        单元格的累加值取决于所在行和列被加了多少次，只用对indices的行和列计数
        时间复杂度：O(indices + n*m)
     */
    int[] rows, cols ;

    public int oddCells2(int m, int n, int[][] indices) {
        int odd = 0;
        rows = new int[m];
        cols = new int[n];
        for (int i = 0; i < indices.length; i++) {
            int r = indices[i][0];
            int c = indices[i][1];
            rows[r]++;
            cols[c]++;
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int sum = rows[i] + cols[j];
                if ((sum & 1) == 1) {
                    odd++;
                }
            }
        }
        return odd;
    }

    /*
        最暴力的做法，纯模拟
        时间复杂度：O(indices*(n+m))
        空间复杂度：O(n+m)
     */
    int odd = 0;
    int[][] a ;

    public int oddCells(int m, int n, int[][] indices) {
        a = new int[m][n];
        for (int i = 0; i < indices.length; i++) {
            calc(indices[i]);
        }
        return odd;
    }

    void calc(int[] p) {
        int n = a.length;
        int m = a[0].length;
        int row = p[0];
        int col = p[1];
        for (int i = 0; i < n; i++) {
            a[i][col]++;
            if ((a[i][col] & 1) == 1) odd++;
            else odd--;
        }
        for (int j = 0; j < m; j++) {
            a[row][j]++;
            if ((a[row][j] & 1) == 1) odd++;
            else odd--;
        }
    }
}
