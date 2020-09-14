package breadth.first.search;

import java.util.*;

/**
 * leetcode 417 medium
 *
 * Analysis:
 * 裸bfs在最坏情况下接近O((n*m)^2)，直接超时。后来想了想加了类似DP的思想记录下可以到达太平洋和大西洋的点，
 * 遍历到达这些点则起点明显也可达太平洋或大西洋。bfs无法记录下从原点到终点的路径，这使得加速不明显。
 *
 * 用dfs记录下可达两个海的路径上的每一点，原本以为记录的点越多搜索越快，结果时间和bfs一样。
 * 只能在400+ms的量级。应该是重复记录解路径上耗费了多余时间。
 *
 * 时间复杂度：小于O((n*m)^2)
 * 空间复杂度：O(n*m)
 *
 * @author Joseph
 * @since 2020-09-13 22:08
 */
public class PacificAtlanticWaterFlow {

    int r, c ;
    boolean[][] pacific ;
    boolean[][] atlantic ;
    boolean isPacific = true;

    public List<List<Integer>> pacificAtlantic(int[][] matrix) {
        List<List<Integer>> ans = new ArrayList<>();

        if (matrix.length == 0) return ans;

        r = matrix.length;
        c = matrix[0].length;

        pacific = new boolean[r][c];
        atlantic = new boolean[r][c];

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (canGoPacific(i, j, matrix) || bfs(i, j, matrix, true)) {
                    pacific[i][j] = true;
                    if (canGoAtlantic(i, j, matrix) || bfs(i, j, matrix, false)) {
                        atlantic[i][j] = true;
                        List<Integer> l = new ArrayList<>(2);
                        l.add(i); l.add(j);
                        ans.add(l);
                    }
                }
            }
        }
        return ans;
    }

    /* AC 403ms faster than 7% */
    private boolean bfs(int i, int j, int[][] m, boolean pacific) {
        Queue<Integer[]> q = new LinkedList<>();
        boolean[][] vis = new boolean[m.length][m[0].length];
        Integer[] p = new Integer[2];
        p[0] = i;
        p[1] = j;

        q.add(p);

        while (!q.isEmpty()) {
            p = q.poll();
            i = p[0];
            j = p[1];
            int h = m[i][j];
            vis[i][j] = true;
            if (pacific) {
                if (i == 0 || j == 0 || canGoPacific(i, j, m)) return true;
            }
            else {
                if (i == m.length-1 || j == m[0].length-1 || canGoAtlantic(i, j, m)) return true;
            }
            if (j > 0 && m[i][j-1] <= h && !vis[i][j-1]) q.add(new Integer[]{i, j-1});
            if (j < m[0].length - 1 && m[i][j+1] <= h && !vis[i][j+1]) q.add(new Integer[]{i, j+1});
            if (i > 0 && m[i-1][j] <= h && !vis[i-1][j]) q.add(new Integer[]{i-1, j});
            if (i < m.length - 1 && m[i+1][j] <= h && !vis[i+1][j]) q.add(new Integer[]{i+1, j});
        }
        return false;
    }

    /* AC 429ms faster than 7% */
    /*public List<List<Integer>> pacificAtlantic(int[][] matrix) {
        List<List<Integer>> ans = new ArrayList<>();

        if (matrix.length == 0) return ans;

        r = matrix.length;
        c = matrix[0].length;

        pacific = new boolean[r][c];
        atlantic = new boolean[r][c];

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                boolean[][] vis = new boolean[r][c];
                List<Integer[]> path = new LinkedList<>();
                if (dfs(i, j, matrix, vis, isPacific, path)) {
                    record(matrix, path, isPacific);
                    path = new LinkedList<>();
                    vis = new boolean[r][c];
                    if (dfs(i, j, matrix, vis, !isPacific, path)) {
                        record(matrix, path, !isPacific);
                        List<Integer> l = new ArrayList<>(2);
                        l.add(i); l.add(j);
                        ans.add(l);
                    }
                }
            }
        }
        return ans;
    }*/

    private boolean dfs(int i, int j, int[][] m, boolean[][] vis,
                        boolean isPacific, List<Integer[]> path) {
        vis[i][j] = true;
        path.add(new Integer[]{i, j});

        if (isPacific && canGoPacific(i, j, m)) return true;
        if (!isPacific && canGoAtlantic(i, j, m)) return true;

        int h = m[i][j];

        if (i > 0 && h >= m[i-1][j] && !vis[i-1][j] && dfs(i-1, j, m, vis, isPacific, path)) {
            return true;
        }
        if (i < r-1 && h >= m[i+1][j] && !vis[i+1][j] && dfs(i+1, j, m, vis, isPacific, path)) {
            return true;
        }
        if (j > 0 && h >= m[i][j-1] && !vis[i][j-1] && dfs(i, j-1, m, vis, isPacific, path)) {
            return true;
        }
        if (j < c-1 && h >= m[i][j+1] && !vis[i][j+1] && dfs(i, j+1, m, vis, isPacific, path)) {
            return true;
        }
        vis[i][j] = false;
        path.remove(path.size()-1);
        return false;
    }

    private void record(int[][] m, List<Integer[]> path, boolean isPacific) {
        for (Integer[] p : path) {
            int i = p[0];
            int j = p[1];
            if (isPacific) {
                pacific[i][j] = true;
            }
            else {
                atlantic[i][j] = true;
            }
        }
    }

    private boolean canGoPacific(int i, int j, int[][] m) {
        if (i == 0 || j == 0) return true;
        int h = m[i][j];
        if (h >= m[i][j-1] && pacific[i][j-1]) return true;
        if (h >= m[i-1][j] && pacific[i-1][j]) return true;
        if (j < c-1 && h >= m[i][j+1] && pacific[i][j+1]) return true;
        if (i < r-1 && h >= m[i+1][j] && pacific[i+1][j]) return true;
        return false;
    }

    private boolean canGoAtlantic(int i, int j, int[][] m) {
        if (i == r-1 || j == c-1) return true;
        int h = m[i][j];
        if (h >= m[i][j+1] && atlantic[i][j+1]) return true;
        if (h >= m[i+1][j] && atlantic[i+1][j]) return true;
        if (j < c-1 && h >= m[i][j+1] && atlantic[i][j+1]) return true;
        if (i < r-1 && h >= m[i+1][j] && atlantic[i+1][j]) return true;
        return false;
    }

}
