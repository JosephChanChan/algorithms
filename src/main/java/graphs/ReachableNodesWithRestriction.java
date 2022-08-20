package graphs;

import java.util.*;

/**
 * lc 2368 medium
 *
 * Analysis:
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2022/8/10
 */
public class ReachableNodesWithRestriction {

    Set<Integer> vis = new HashSet<>();
    LinkedList<Integer> q = new LinkedList<>();
    Map<Integer, LinkedList<Integer>> g = new HashMap<>();

    public int reachableNodes(int n, int[][] edges, int[] restricted) {
        for (int[] edge : edges) {
            LinkedList<Integer> l1 = g.getOrDefault(edge[0], new LinkedList<>());
            l1.add(edge[1]);
            LinkedList<Integer> l2 = g.getOrDefault(edge[1], new LinkedList<>());
            l2.add(edge[0]);
            g.put(edge[0], l1);
            g.put(edge[1], l2);
        }
        for (int node : restricted) {
            vis.add(node);
        }
        int ans = 0;
        q.add(0);
        vis.add(0);
        while (!q.isEmpty()) {
            ans++;
            int node = q.removeFirst();
            LinkedList<Integer> nes = g.get(node);
            for (Integer neNode : nes) {
                if (!vis.contains(neNode)) {
                    q.add(neNode);
                    vis.add(neNode);
                }
            }
        }
        return ans;
    }


}
