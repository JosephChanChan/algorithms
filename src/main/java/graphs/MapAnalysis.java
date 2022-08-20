package graphs;

import java.util.*;

/**
 * lc 1162 medium
 *
 * Analysis:
 * 时间复杂度：O(n^2)
 * 空间复杂度：O(n^2)
 *
 * @author Joseph
 * @since 2022/7/27
 */
public class MapAnalysis {

    /**
         可以用朴素bfs求，求max{ 每个海洋点算离它最近的陆地点距离 }
         但是时间复杂度会n^4

         反过来从每个陆地点算离它的海洋点距离，然后不断更新海洋点被不同陆地点访问到的最短距离，时间也是n^4
         这里可以把所有陆地点加起来做超级源点开始扩展
     */

    int n, m ;
    int[][] a ;
    int[][] d = {{-1,0}, {1,0}, {0,-1}, {0,1}};

    public int maxDistance(int[][] grid) {
        n = grid.length;
        m = grid[0].length;
        a = new int[n][m];

        LinkedList<int[]> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    q.add(new int[]{i, j});
                }
            }
        }

        int dis = 0, ans = -1;
        while (!q.isEmpty()) {
            dis++;
            int count = q.size();
            for (int i = 0; i < count; i++) {
                int[] p = q.removeFirst();
                for (int j = 0; j < 4; j++) {
                    int r = p[0] + d[j][0];
                    int c = p[1] + d[j][1];
                    if ((r >= 0 && r < n && c >= 0 && c < m) && grid[r][c] == 0 && a[r][c] == 0) {
                        a[r][c] = dis;
                        q.add(new int[]{r, c});
                        ans = Math.max(ans, dis);
                    }
                }
            }
        }
        return ans;
    }

}
