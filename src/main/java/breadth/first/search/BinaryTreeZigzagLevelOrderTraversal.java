package breadth.first.search;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * leetcode 103 medium
 *
 * Analysis:
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2020-09-09 21:51
 */
public class BinaryTreeZigzagLevelOrderTraversal {

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        /*
            left -> right   poll head from queue and add left child first to queue tail
            right -> left   poll tail from queue and add right child first to queue head
        */
        List<List<Integer>> ans = new ArrayList<>();
        LinkedList<TreeNode> q = new LinkedList<>();
        if (null == root) return ans;

        q.add(root);
        bfs(q, ans, false);
        return ans;
    }

    private void bfs(LinkedList<TreeNode> q, List<List<Integer>> ans, boolean left) {
        int size = q.size();
        if (size == 0) return;

        LinkedList<Integer> v = new LinkedList<>();
        if (left) {
            for (int i = 0; i < size; i++) {
                TreeNode n = q.removeFirst();
                v.addFirst(n.val);
                if (null != n.left) q.addLast(n.left);
                if (null != n.right) q.addLast(n.right);
            }
        }
        else {
            for (int i = 0; i < size; i++) {
                TreeNode n = q.removeLast();
                v.addFirst(n.val);
                if (null != n.right) q.addFirst(n.right);
                if (null != n.left) q.addFirst(n.left);
            }
        }
        ans.add(v);
        bfs(q, ans, !left);
    }

    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
    }
}
