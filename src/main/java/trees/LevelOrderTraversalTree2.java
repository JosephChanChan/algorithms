package trees;

import java.util.*;

/**
 * lc 107 medium
 *
 * Analysis:
 * bfs层级遍历，把节点分层级保存起来，最后logN时间从最底层收集节点返回
 *
 *
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2021-04-06 10:17
 */
public class LevelOrderTraversalTree2 {

    int depth = -1;
    Map<Integer, List<Integer>> leaves = new HashMap<>();

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        if (null == root) return new ArrayList<>();

        Deque<TreeNode> q = new ArrayDeque<>();
        q.add(root);

        while (!q.isEmpty()) {
            depth++;
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                List<Integer> list = leaves.getOrDefault(depth, new ArrayList<>());
                list.add(node.val);
                leaves.put(depth, list);
                if (null != node.left) q.add(node.left);
                if (null != node.right) q.add(node.right);
            }
        }
        List<List<Integer>> ans = new ArrayList<>(depth+1);
        for (int l = depth; l >= 0; l--) {
            ans.add(leaves.get(l));
        }
        return ans;
    }
}
