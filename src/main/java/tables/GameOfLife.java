package tables;

/**
 * lc 289 medium
 *
 * Analysis:
 * 时间复杂度：O(nm)
 * 空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2022/12/29
 */
public class GameOfLife {

    /*
        原地置换算法，难点在于某个格子被更新后，在计算和它相邻格子时无法判断它原来是什么值。
        做标记的方法，0->1记为2，1->0记为3
        本质思想是，用中间状态记录过去的状态，以及未来的状态，一个中间状态代表一种 过去状态->未来状态 的映射
     */

    int[][] d = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};

    public void gameOfLife(int[][] board) {
        int n = board.length, m = board[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int c = 0;
                for (int k = 0; k < d.length; k++) {
                    int y = i + d[k][0];
                    int x = j + d[k][1];
                    if (y >= 0 && y < n && x >= 0 && x < m) {
                        c += (board[y][x] == 1 || board[y][x] == 3) ? 1 : 0;
                    }
                }
                if (board[i][j] == 0 && c == 3) {
                    board[i][j] = 2;
                }
                else if (board[i][j] == 1 && (c < 2 || c > 3)) {
                    board[i][j] = 3;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                //System.out.print(board[i][j]+" ");
                if (board[i][j] == 2) {
                    board[i][j] = 1;
                }
                else if (board[i][j] == 3) {
                    board[i][j] = 0;
                }
            }
            //System.out.println();
        }
    }
}
