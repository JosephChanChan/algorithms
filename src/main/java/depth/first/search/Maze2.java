package depth.first.search;

/**
 * lc 505 medium
 *
 * Analysis:
 *
 * 时间复杂度：O(nm*max(n,m))
 * 空间复杂度：O(nm)
 *
 * @author Joseph
 * @since 2021-04-29 15:45
 */
public class Maze2 {

    int dx, dy, n, m ;
    int[][] a ;
    int[][] vis ;
    int[][] d = new int[][]{{-1,0}, {1,0}, {0,-1}, {0, 1}};

    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        /*
            和迷宫1很像，不过要尝试所有停顿点更新到终点的最短路。
            路径权值都是1，可用dfs
        */

        this.dy = destination[0];
        this.dx = destination[1];
        this.a = maze;
        this.n = a.length;
        this.m = a[0].length;
        this.vis = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) vis[i][j] = Integer.MAX_VALUE;
        }

        vis[start[0]][start[1]] = 0;
        dfs(start[0], start[1], 0);
        return vis[dy][dx] == Integer.MAX_VALUE ? -1 : vis[dy][dx];
    }

    void dfs(int i, int j, int cost) {
        if (i == dy && j == dx) {
            vis[dy][dx] = Math.min(vis[dy][dx], cost);
            return;
        }

        for (int k = 0; k < 4; k++) {
            int[] go = d[k];
            int r = i, c = j, dis = cost;
            while (true) {
                int y = r+go[0], x = c+go[1];
                if ((y < 0 || y == n || x < 0 || x == m || a[y][x] == 1)) {
                    if (vis[r][c] > dis) {
                        vis[r][c] = dis;
                        dfs(r, c, dis);
                    }
                    break;
                }
                dis++;
                r = y; c = x;
            }
        }
    }
}
