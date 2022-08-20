package graphs;

import trees.TreeNode;

import java.util.*;

/**
 * lc 863 medium
 *
 * Analysis:
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2022/7/27
 */
public class AllNodesDistanceKInTree {

    List<Integer> ans = new LinkedList<>();
    Map<Integer, LinkedList<Integer>> g = new HashMap<>();

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        if (null == root) {
            return ans;
        }
        dfs(root, null);
        bfs(target.val, k);
        return ans;
    }

    void bfs(int t, int k) {
        Set<Integer> vis = new HashSet<>();
        LinkedList<Integer> q = new LinkedList<>();
        q.add(t);
        vis.add(t);
        int d = -1;

        while (!q.isEmpty()) {
            d++;
            if (d > k) {
                // 最远遍历到k就行，题目求k的节点
                break;
            }
            int c = q.size();
            for (int i = 0; i < c; i++) {
                // 访问邻节点
                int p = q.removeFirst();
                if (d == k) {
                    ans.add(p);
                }
                // 拓展邻节点的邻节点
                LinkedList<Integer> list = g.get(p);
                for (Integer node : list) {
                    if (!vis.contains(node)) {
                        q.add(node);
                        vis.add(node);
                    }
                }
            }
        }
    }

    void dfs(TreeNode node, TreeNode parent) {
        LinkedList<Integer> q = g.getOrDefault(node.val, new LinkedList<>());
        if (null != parent) {
            q.add(parent.val);
        }
        if (null != node.left) {
            q.add(node.left.val);
            dfs(node.left, node);
        }
        if (null != node.right) {
            q.add(node.right.val);
            dfs(node.right, node);
        }
        g.put(node.val, q);
    }
}
