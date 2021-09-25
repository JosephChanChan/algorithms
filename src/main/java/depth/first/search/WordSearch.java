package depth.first.search;

/**
 * lc 79 medium
 *
 * Analysis:
 *  顺着word每个字符dfs遍历，可以做一些剪枝，比如只有遇到当前word[i]字符才向下递归，避免基于无效字符搜索。
 *
 * 时间复杂度：下界 O(nm)
 * 空间复杂度：O(nm)
 *
 * @author Joseph
 * @since 2021-02-05 16:31
 */
public class WordSearch {

    int n, m ;
    boolean[][] vis ;
    int[][] d = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) {
        int a = 93, b = 73;
        System.out.println(Integer.toBinaryString(a));
        System.out.println(Integer.toBinaryString(b));
        System.out.println(a^b);
    }

    // 6ms AC faster than 86%
    public boolean exist(char[][] board, String word) {
        n = board.length;
        m = board[0].length;
        vis = new boolean[n][m];

        char[] c = word.toCharArray();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == c[0]) {
                    vis[i][j] = true;
                    if (c.length == 1) return true;
                    if (dfs(i, j, board, c, 1, 1)) return true;
                    vis[i][j] = false;
                }
            }
        }
        return false;
    }

    boolean dfs(int i, int j, char[][] board, char[] ch, int p, int l) {
        int r, c ;
        for (int k = 0; k < 4; k++) {
            r = i + d[k][0];
            c = j + d[k][1];
            if (r >= 0 && r < n && c >= 0 && c < m && !vis[r][c] && board[r][c] == ch[p]) {
                vis[r][c] = true;
                if (l+1 == ch.length) return true;
                if (dfs(r, c, board, ch, p+1, l+1)) return true;
                vis[r][c] = false;
            }
        }
        return false;
    }
}
