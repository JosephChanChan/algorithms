package graphs;

import java.util.*;

/**
 * lc 1034 medium
 *
 * Analysis:
 * 时间复杂度：O(nm)
 * 空间复杂度：O(nm)
 *
 * @author Joseph
 * @since 2022/7/27
 */
public class ColoringBorder {

    /**
         bfs搜索起点的所有连通分量的边界，然后着色
         1.搜索拓展按照相同连通分量
         2.访问节点判断是否边界
     */

    int[][] ans ;
    int[][] d = {{-1,0}, {1,0}, {0,-1}, {0,1}};

    public int[][] colorBorder(int[][] grid, int row, int col, int color) {
        int n = grid.length;
        int m = grid[0].length;
        ans = new int[n][m];

        boolean[][] f = new boolean[n][m];
        LinkedList<int[]> q = new LinkedList<>();
        q.add(new int[]{row, col});
        f[row][col] = true;

        while (!q.isEmpty()) {
            int[] node = q.removeFirst();
            ans[node[0]][node[1]] = grid[node[0]][node[1]];
            int nodeColor = ans[node[0]][node[1]];
            // 看看此点是不是连通分量的边界
            if (isBorder(node, grid)) {
                ans[node[0]][node[1]] = color;
            }
            // 扩展此点的连通分量
            for (int i = 0; i < d.length; i++) {
                int r = node[0] + d[i][0];
                int c = node[1] + d[i][1];
                if ((r >= 0 && r < n && c >= 0 && c < m) && !f[r][c] && grid[r][c] == nodeColor) {
                    q.add(new int[]{r, c});
                    f[r][c] = true;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!f[i][j]) {
                    ans[i][j] = grid[i][j];
                }
            }
        }
        return ans;
    }

    boolean isBorder(int[] node, int[][] a) {
        int n = ans.length;
        int m = ans[0].length;
        if (node[0] == 0 || node[0] == n-1) {
            return true;
        }
        if (node[1] == 0 || node[1] == m-1) {
            return true;
        }
        int nodeColor = a[node[0]][node[1]];
        for (int i = 0; i < d.length; i++) {
            int r = node[0] + d[i][0];
            int c = node[1] + d[i][1];
            if (a[r][c] != nodeColor) {
                return true;
            }
        }
        return false;
    }
}
