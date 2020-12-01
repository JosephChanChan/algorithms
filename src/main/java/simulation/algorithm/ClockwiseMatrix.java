package simulation.algorithm;

/**
 * 剑指Offer 29 medium
 *
 * Analysis:
 *  37min AC。还得再刷
 *
 *  时间复杂度：O(nm)
 *  空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2020-11-21 22:01
 */
public class ClockwiseMatrix {

    /*
        边界参数：
            矩阵1个元素。一行元素，一列元素。
    */

    int r, c ;
    int visCount = 0, directions = 0;
    boolean[][] vis ;
    int[] ans ;
    int[][] d = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};


    public int[] spiralOrder(int[][] matrix) {
        r = matrix.length;
        if (r == 0) return new int[0];
        c = matrix[0].length;

        visCount = r * c;
        vis = new boolean[r][c];

        ans = new int[visCount];

        int i = 0, j = 0;
        for (int k = 0; k < visCount; ) {
            while (true) {
                ans[k++] = matrix[i][j];
                vis[i][j] = true;
                int row = i + d[directions][0];
                int column = j + d[directions][1];
                if (exceed(row, column) || vis[row][column]) {
                    directions = ++directions % 4;
                    i += d[directions][0];
                    j += d[directions][1];
                    break;
                }
                i = row; j = column;
            }
        }
        return ans;
    }

    private boolean exceed(int i, int j) {
        return i < 0 || i >= r || j < 0 || j >= c;
    }
}
