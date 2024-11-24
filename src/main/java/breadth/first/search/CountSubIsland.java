package breadth.first.search;

import java.util.*;

/**
 * lc 1905 medium
 *
 * Analysis:
 * 时间复杂度：O(nm)
 * 空间复杂度：O(nm)
 *
 * @author Joseph
 * @since 2022/9/29
 */
public class CountSubIsland {

    /**
         bfs搜集岛屿，然后遍历每个岛屿的1判断是否都在grid1存在
         时间复杂度：O(n*m)，搜集岛屿时每个点最多被访问常数次

         这题其实用dfs做，时间复杂度和bfs一样，但是常数时间会更快，因为可以一边搜一边比较grid1
     */

    int[][] d = new int[][]{{1,0}, {-1,0}, {0,-1}, {0,1}};
    int[][] vis ;
    List<List<int[]>> islands = new LinkedList<>();

    public int countSubIslands(int[][] grid1, int[][] grid2) {
        int n = grid1.length;
        int m = grid1[0].length;

        vis = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid2[i][j] == 1 && vis[i][j] == 0) {
                    bfs(grid2, i, j);
                }
            }
        }
        int ans = 0;
        for (List<int[]> island : islands) {
            boolean allExists = true;
            for (int[] mt : island) {
                if (grid1[mt[0]][mt[1]] == 0) {
                    allExists = false;
                    break;
                }
            }
            if (allExists) {
                ans++;
            }
        }
        return ans;
    }

    void bfs(int[][] a, int i, int j) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{i, j});
        vis[i][j] = 1;
        List<int[]> island = new LinkedList<>();

        while (q.size() > 0) {
            int[] mt = q.poll();
            island.add(new int[]{mt[0], mt[1]});
            for (int k = 0; k < d.length; k++) {
                int row = mt[0] + d[k][0];
                int col = mt[1] + d[k][1];
                if (row >= 0 && row < a.length && col >= 0 && col < a[0].length && vis[row][col] == 0 && a[row][col] == 1) {
                    q.add(new int[]{row, col});
                    vis[row][col] = 1;
                }
            }
        }
        islands.add(island);
    }
}
