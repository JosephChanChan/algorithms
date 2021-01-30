package depth.first.search;

/**
 * 剑指Offer 12 medium & leetcode 79
 *
 * Analysis:
 *  标准回溯解法
 *
 * 时间复杂度：大于O(nm)
 * 空间复杂度：O(nm)
 *
 * @author Joseph
 * @since 2020-10-25 11:13
 */
public class MatrixRoad {

    int r, c ;
    char[] ca ;
    boolean[][] vis ;
    int[][] dir = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

    public boolean exist(char[][] board, String word) {
        r = board.length;
        c = board[0].length;
        ca = word.toCharArray();
        vis = new boolean[r][c];

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                vis[i][j] = true;
                if (board[i][j] == ca[0] && backSearch(i, j, 1, board)) return true;
                vis[i][j] = false;
            }
        }
        return false;
    }

    private boolean backSearch(int i, int j, int next, char[][] board) {
        if (next == ca.length) return true;

        for (int d = 0; d < 4; d++) {
            int x = dir[d][0] + j;
            int y = dir[d][1] + i;
            if (x >= 0 && x < c && y >= 0 && y < r && !vis[y][x] && board[y][x] == ca[next]) {
                vis[y][x] = true;
                if (backSearch(y, x, next + 1, board)) return true;
                vis[y][x] = false;
            }
        }
        return false;
    }
}
