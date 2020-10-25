package breadth.first.search;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 剑指Offer 13 medium
 *
 * Analysis:
 *  bfs从0,0开始扩散，搜所有小于k的坐标。有优化的地方在搜索方向只往下/右搜索。
 *  (i, j)之所以会被搜索到，就是从上/左过来的，所以上/左肯定已经被搜索过了。
 *  其实4个方向一起搜，差别也不大。时间复杂度都是O(nm)级别的，因为有全局vis记录访问状态，
 *  即使4个方向搜，每个格子也还是只被访问一次。
 *
 * 时间复杂度：O(nm)
 * 空间复杂度：O(nm)
 *
 * @author Joseph
 * @since 2020-10-25 11:04
 */
public class RobotWalkGrid {

    int count = 0;
    boolean[][] vis ;
    int[][] dir = {{0, 1}, {1, 0}};
    Queue<Integer[]> q = new LinkedList<>();

    public int movingCount(int m, int n, int k) {
        vis = new boolean[m][n];
        vis[0][0] = true;
        q.add(new Integer[]{0, 0});

        while (!q.isEmpty()) {
            Integer[] p = q.poll();
            int i = p[1], j = p[0];
            count++;
            for (int d = 0; d < 2; d++) {
                int x = j + dir[d][0];
                int y = i + dir[d][1];
                if (y >= 0 && y < m && x >= 0 && x < n && !vis[y][x] && canEnter(y, x, k)) {
                    vis[y][x] = true;
                    q.add(new Integer[]{x, y});
                }
            }
        }
        return count;
    }

    private boolean canEnter(int i, int j, int k) {
        int sum = 0;
        while (i > 0) {
            sum += i % 10;
            i = i / 10;
        }
        while (j > 0) {
            sum += j % 10;
            j = j / 10;
        }
        return sum <= k;
    }
}
