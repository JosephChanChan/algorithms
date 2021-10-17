package breadth.first.search;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * lc 695 medium
 *
 * Analysis:
 *  BFS一直需要关注的地方在于，访问到一个点之后什么时候将它标记为visited
 * 可能需要在访问到点时就记录，也可能需要从队列拿出来时再记录。
 *
 * 如果从队列拿出来再记录，就需要注意，该点可能会被其他点访问到。也就是有个关系，A->B，A->C，C->B。
 * 遍历到A点时，A->B，把B入队，A->C，把C入队。此时遍历到C点时，C->B，由于B在先前没被标记，所以再次把B入队。
 * B在队中会被访问2次。
 *
 * 像Dijkstra算法需要把点从队列拿出来时再记录，此点已被访问。因为需要遍历所有可以访问到此点的路径，更新此点的最小路径和。
 *
 * 时间复杂度：O(n*m)
 * 空间复杂度：O(n*m)
 *
 * @author Joseph
 * @since 2021-07-05 17:26
 */
public class MaxAreaOfIsland {

    int n, m, max ;
    boolean[][] vis ;
    Deque<int[]> que = new ArrayDeque<>();

    public int maxAreaOfIsland(int[][] grid) {
        this.n = grid.length;
        this.m = grid[0].length;
        this.max = 0;
        this.vis = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1 && !vis[i][j]) {
                    bfs(i, j, grid);
                }
            }
        }
        return max;
    }

    void bfs(int i, int j, int[][] a) {
        int area = 0;
        que.add(new int[]{i, j});
        vis[i][j] = true;
        while (!que.isEmpty()) {
            area++;
            int[] node = que.poll();
            if (node[0]-1 >= 0 && a[node[0]-1][node[1]] == 1 && !vis[node[0]-1][node[1]]) {
                vis[node[0]-1][node[1]] = true;
                que.add(new int[]{node[0]-1, node[1]});
            }
            if (node[0]+1 < n && a[node[0]+1][node[1]] == 1 && !vis[node[0]+1][node[1]]) {
                vis[node[0]+1][node[1]] = true;
                que.add(new int[]{node[0]+1, node[1]});
            }
            if (node[1]-1 >= 0 && a[node[0]][node[1]-1] == 1 && !vis[node[0]][node[1]-1]) {
                vis[node[0]][node[1]-1] = true;
                que.add(new int[]{node[0], node[1]-1});
            }
            if (node[1]+1 < m && a[node[0]][node[1]+1] == 1 && !vis[node[0]][node[1]+1]) {
                vis[node[0]][node[1]+1] = true;
                que.add(new int[]{node[0], node[1]+1});
            }
        }
        max = Math.max(max, area);
    }
}
