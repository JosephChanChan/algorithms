package greedy.algorithm;

import java.util.Arrays;

/**
 * lc 406 medium
 *
 * Analysis:
 *  从最矮的开始思考，假设最矮的是i，i有ki个比他高的人排在i前面。所以如果把i放在ki+1后的位置，会有ki+1个比他高的人排在他前面，不行。
 * 如果把i放在 0~ki-1 的位置，排在他前面的人又少于ki个，不行。
 * 依次处理每一个最矮的i，对于每个i，从队列的左边往右数，空位置和比他高的计数，直到计数到负数，代表它前面累计了足够比它高的人，
 * 并且找到一个空位置让他插入。
 *
 * 时间复杂度：贪心 O(n^2) DFS涉及剪枝无法计算
 * 空间复杂度：贪心 O(n) DFS O(n)
 *
 * @author Joseph
 * @since 2021-03-21 21:08
 */
public class QueueReconstructionByHeight {



    public int[][] reconstructQueue(int[][] people) {
        return greedy(people);
    }

    int[][] greedy(int[][] p) {
        int n = p.length;
        boolean[] vis = new boolean[n];
        int[][] q = new int[n][2];
        for (int i = 0; i < n; i++) q[i][0] = -1;

        Arrays.sort(p, (o1, o2) -> {
            if (o1[0] == o2[0]) {
                return o1[1]-o2[1];
            }
            return o1[0]-o2[0];
        });

        for (int i = 0; i < n; i++) {
            int h = p[i][0];
            int d = p[i][1];
            for (int j = 0; j < n; j++) {
                if (!vis[j] || q[j][0] >= h) d--;
                if (d < 0) {
                    vis[j] = true;
                    q[j][0] = h;
                    q[j][1] = p[i][1];
                    break;
                }
            }
        }
        return q;
    }

    int[][] ans = null;
    // dfs TLE 16/36 cases
    public int[][] usingDFS(int[][] people) {
        int[][] q = new int[people.length][2];
        for (int i = 0; i < q.length; i++) q[i][0] = -1;

        boolean[] vis = new boolean[people.length];
        dfs(0, q, vis, people);
        return ans;
    }

    void dfs(int i, int[][] q, boolean[] vis, int[][] p) {
        if (i == p.length) {
            ans = q;
            return;
        }
        F1:
        for (int j = 0; j < p.length; j++) {
            if (vis[j]) continue;
            int h = p[j][0];
            int d = p[j][1];
            for (int k = 0; k < q.length; k++) {
                if (q[k][0] >= h) {
                    if (d == 0) continue F1;
                    d--;
                }
            }
            if (d == 0) {
                vis[j] = true;
                q[i][0] = h;
                q[i][1] = p[j][1];
                dfs(i+1, q, vis, p);
                if (null != ans) return;
                vis[j] = false;
                q[i][0] = -1;
                q[i][1] = -1;
            }
        }
    }
}
