package graphs;

import java.util.*;

/**
 * lc 1765 medium
 *
 * Analysis:
 * 时间复杂度：O(nm)
 * 空间复杂度：O(nm)
 *
 * @author Joseph
 * @since 2022/7/28
 */
public class HighestPeekOfMap {

    /**
         按照规则，水高度必须是0，相邻格子高度差最多为1，则水域周围陆地格子高度必定为1，
         则挨着水域的陆地格子再往外扩散高度可以为2，按照这种规则容易理解为离水域越远的陆地高度越高，
         因为按照贪心思想就是这么样

         把所有水域收集起来同时bfs就行
     */

    int n , m;
    int[][] vis ;
    int[][] ans ;
    int[][] dis = {{-1,0}, {1,0}, {0,-1}, {0,1}};
    LinkedList<int[]> q = new LinkedList<>();

    public int[][] highestPeak(int[][] isWater) {
        n = isWater.length;
        m = isWater[0].length;

        vis = new int[n][m];
        ans = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (isWater[i][j] == 1) {
                    q.add(new int[]{i, j});
                    vis[i][j]++;
                }
            }
        }

        int d = -1;
        while (!q.isEmpty()) {
            d++;
            int c = q.size();
            for (int i = 0; i < c; i++) {
                int[] p = q.removeFirst();
                ans[p[0]][p[1]] = d;
                for (int j = 0; j < 4; j++) {
                    int row = p[0] + dis[j][0];
                    int col = p[1] + dis[j][1];
                    if ((row >= 0 && row < n && col >= 0 && col < m) &&
                            vis[row][col] == 0 && isWater[row][col] == 0) {
                        q.add(new int[]{row, col});
                        vis[row][col]++;
                    }
                }
            }
        }
        return ans;
    }
}
