package depth.first.search;

/**
 * lc 490 medium
 *
 * Analysis:
 * dfs尝试搜索每个停顿点的4个方向。
 * 这题有个需要注意的地方，在每个停顿点为起点搜索4个方向滚动时，如果搜索完毕从此停顿点往4个方向滚动不能到达终点，
 * 不需要回溯这个停顿点，即标记此点已搜索过，不用擦去标记。
 *
 * 一开始我搜索完一个停顿点，擦去了标记，导致过到30case会超时。
 *
 * 时间复杂度：O(nm)
 * 空间复杂度：O(nm)
 *
 * @author Joseph
 * @since 2021-04-19 16:11
 */
public class Maze {

    int d1, d2 ;
    int[][] a ;
    boolean[][] vis ;
    int[][] d = new int[][]{{-1,0}, {1,0}, {0,-1}, {0,1}};

    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        this.d1 = destination[0];
        this.d2 = destination[1];

        this.a = maze;
        int n = maze.length, m = maze[0].length;
        this.vis = new boolean[n][m];

        for (int k = 0; k < 4; k++) {
            if (dfs(start[0], start[1])) return true;
        }
        return false;
    }

    boolean dfs(int i, int j) {
        if (i == d1 && j == d2) return true;

        vis[i][j] = true;
        // 当前停顿点继续滚动4个方向
        for (int k = 0; k < 4; k++) {
            int y = i, x = j, r, c ;
            while (true) {
                r = y + d[k][0];
                c = x + d[k][1];
                if (r < 0 || r == a.length) break;
                if (c < 0 || c == a[0].length) break;
                if (a[r][c] == 1) break;
                y = r; x = c;
            }
            if (!vis[y][x] && dfs(y, x)) return true;
        }
        return false;
    }
}
