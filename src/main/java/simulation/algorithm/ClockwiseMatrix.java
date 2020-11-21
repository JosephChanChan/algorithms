package simulation.algorithm;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 剑指Offer 29 medium
 *
 * Analysis:
 *  这次模拟的不好，下次再刷一遍
 *
 *  时间复杂度：O(nm)
 *  空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2020-11-21 22:01
 */
public class ClockwiseMatrix {

    /*
        边界参数：
            矩阵1个元素。一行元素，一列元素。
    */

    int n, m, visCount ;
    int[] ans ;
    boolean[][] vis ;
    Queue<Integer[]> q = new LinkedList<>();

    public int[] spiralOrder(int[][] matrix) {
        n = matrix.length;
        if (n == 0) return new int[0];
        m = matrix[0].length;
        if (n == 1 && m == 1) return new int[]{matrix[0][0]};

        ans = new int[n * m];
        vis = new boolean[n][m];
        visCount = n * m;

        q.add(new Integer[]{0, 1});
        q.add(new Integer[]{1, 0});
        q.add(new Integer[]{0, -1});
        q.add(new Integer[]{-1, 0});

        int count = 0;
        int i = 0, j = 0;

        while (visCount > 0) {
            Integer[] d = loopDir();
            while (true) {
                if (vis[i][j]) {
                    i += d[0]; j += d[1];
                }
                visCount--;
                vis[i][j] = true;
                ans[count++] = matrix[i][j];
                if (invalid(i, j, d)) break;
                i += d[0]; j += d[1];
            }
        }
        return ans;
    }

    private boolean invalid(int i, int j, Integer[] d) {
        int r = i + d[0];
        int c = j + d[1];
        return r < 0 || r == n || c < 0 || c == m || vis[r][c];
    }

    private Integer[] loopDir() {
        Integer[] d = q.poll();
        q.offer(d);
        return d;
    }
}
