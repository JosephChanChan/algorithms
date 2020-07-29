package depth.first.search;

/**
 * leetcode 130 medium
 *
 * Question Description:
 *  参见 lc 130
 *
 * Analysis:
 * 思路是DFS，就从每个边缘的O开始向上下左右四个方向延伸，记录下可以延伸到的地方。
 * 最后把延伸不到的O反转X即可。
 *
 * 时间复杂度：O(n^2)
 * 空间复杂度：O(n^2)
 *
 * @author Joseph
 * @since 2020-07-26 19:27
 */
public class SurroundedRegions {

    int n, m ;
    boolean[][] visited ;

    /* AC 1ms faster than 99% */
    public void solve(char[][] board) {
        if (board.length <= 1) return;
        if (board[0].length <= 1) return;

        n = board.length;
        m = board[0].length;
        visited = new boolean[n][m];

        // 从边缘O开始计算，将可以延伸到的O全部计算
        int i, j ;
        for (i = 0, j = 0; j < m; j++) {
            if (board[i][j] == 'O' && !visited[i][j]) {
                dfs(i, j, board);
            }
        }
        for (i = n-1, j = 0; j < m; j++) {
            if (board[i][j] == 'O' && !visited[i][j]) {
                dfs(i, j, board);
            }
        }
        for (i = 0, j = 0; i < n; i++) {
            if (board[i][j] == 'O' && !visited[i][j]) {
                dfs(i, j, board);
            }
        }
        for (i = 0, j = m-1; i < n; i++) {
            if (board[i][j] == 'O' && !visited[i][j]) {
                dfs(i, j, board);
            }
        }

        for (i = 0; i < n; i++) {
            for (j = 0; j < m; j++) {
                if (board[i][j] == 'O' && !visited[i][j]) {
                    board[i][j] = 'X';
                }
            }
        }
    }

    private void dfs(int i, int j, char[][] board) {
        if (i < 0 || i == n) return;
        if (j < 0 || j == m) return;
        if (visited[i][j]) return;

        if (board[i][j] == 'O') {
            visited[i][j] = true;
            // 往上走一格
            dfs(i-1, j, board);
            dfs(i+1, j, board);
            dfs(i, j-1, board);
            dfs(i, j+1, board);
        }
    }



}
