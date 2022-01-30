package trees;

import java.util.*;

/**
 * lc 314 medium
 *
 * Analysis:
 * 简单的bfs应用，给每个节点打上标记就行
 *
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2021-04-07 16:03
 */
public class BinaryTreeVerticalOrderTraversal {

    int left = 0, right = 0;
    Map<Integer, List<Integer>> map = new HashMap<>();

    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (null == root) return ans;

        Queue<Node> q = new LinkedList<>();
        q.add(new Node(0, root));
        bfs(q);

        for ( ; left <= right; left++) {
            ans.add(map.get(left));
        }
        return ans;
    }

    void bfs(Queue<Node> q) {
        while (!q.isEmpty()) {
            int n = q.size();
            for (int i = 0; i < n; i++) {
                Node node = q.poll();

                List<Integer> list = map.getOrDefault(node.c, new ArrayList<>());
                list.add(node.node.val);
                map.put(node.c, list);

                if (null != node.node.left) {
                    left = Math.min(left, node.c-1);
                    q.add(new Node(node.c-1, node.node.left));
                }
                if (null != node.node.right) {
                    right = Math.max(right, node.c+1);
                    q.add(new Node(node.c+1, node.node.right));
                }
            }
        }
    }

    class Node {
        int c = 0;
        TreeNode node;
        public Node(int c, TreeNode node) {
            this.c = c;
            this.node = node;
        }
    }
}
