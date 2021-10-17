package breadth.first.search;

import java.util.LinkedList;
import java.util.Queue;

/**
 * lc 286 medium
 *
 * Analysis:
 * 多源点访问多终点最短路问题。转化为超级源点访问多终点问题。
 * 所有边长为1的最短路问题，可用bfs求解。
 * 边长不定最短路问题，需要用Dijkstra
 *
 * 时间复杂度：O(nm)
 * 空间复杂度：O(nm)
 *
 * @author Joseph
 * @since 2021-04-08 00:28
 */
public class WallsAndGates {

    int n, m ;
    Queue<Integer[]> q = new LinkedList<>();

    boolean[][] vis ;
    int[][] d = new int[][]{{-1,0}, {1, 0}, {0, -1}, {0, 1}};

    public void wallsAndGates(int[][] rooms) {
        n = rooms.length;
        m = rooms[0].length;

        vis = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (rooms[i][j] == 0) {
                    vis[i][j] = true;
                    q.add(new Integer[]{i, j});
                }
                else if (rooms[i][j] == -1) {
                    vis[i][j] = true;
                }
            }
        }

        bfs(q, rooms);
    }

    void bfs(Queue<Integer[]> q, int[][] a) {
        int step = 0;
        while (!q.isEmpty()) {
            int s = q.size();
            for (int i = 0; i < s; i++) {
                Integer[] coo = q.poll();
                a[coo[0]][coo[1]] = step;

                // expand
                for (int k = 0; k < 4; k++) {
                    int y = coo[0]+d[k][0];
                    int x = coo[1]+d[k][1];
                    if (y >= 0 && y < n && x >= 0 && x < m && !vis[y][x]) {
                        // 还没真正访问到此点就加标记，是因为防止其它点访问到它，造成它再被加入队列
                        // 也就是此点只需要被离它最近的点访问一次就好
                        vis[y][x] = true;
                        q.add(new Integer[]{y, x});
                    }
                }
            }
            step++;
        }
    }
}
