package depth.first.search;

import java.util.*;

/**
 * lc 1443 medium
 *
 * Analysis:
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2022/9/21
 */
public class MinTimeCollectAllApples {

    /**
         先将边集转成邻接表
         对树有苹果的分支节点染色 O(n)
         dfs遍历计数
     */
    boolean[] vis ;
    List<Boolean> hasApple ;
    Set<Integer> colorNodes = new HashSet<>();
    Map<Integer, List<Integer>> g = new HashMap<>();

    public int minTime(int n, int[][] edges, List<Boolean> hasApple) {
        this.vis = new boolean[n];
        this.hasApple = hasApple;
        for (int i = 0; i < edges.length; i++) {
            List<Integer> q = g.getOrDefault(edges[i][0], new LinkedList<>());
            q.add(edges[i][1]);
            g.put(edges[i][0], q);
            List<Integer> q1 = g.getOrDefault(edges[i][1], new LinkedList<>());
            q1.add(edges[i][0]);
            g.put(edges[i][1], q1);
        }
        markColor(0);
        this.vis = new boolean[n];
        int[] ans = new int[]{-1};
        vis[0] = true;
        collect(0, ans);
        return ans[0];
    }

    void collect(int node, int[] path) {
        path[0]++;
        // node节点的子分支有苹果
        List<Integer> q = g.getOrDefault(node, new LinkedList<>());
        for (Integer child : q) {
            if (!vis[child] && colorNodes.contains(child)) {
                vis[child] = true;
                collect(child, path);
                path[0]++;
            }
        }
    }

    int markColor(int node) {
        List<Integer> q = g.get(node);
        if (null == q) {
            return 0;
        }
        int c = 0;
        vis[node] = true;
        for (Integer child : q) {
            if (vis[child]) {
                continue;
            }
            if (hasApple.get(child)) {
                c++;
                colorNodes.add(child);
            }
            c += markColor(child);
        }
        if (c > 0) {
            colorNodes.add(node);
        }
        return c;
    }
}
