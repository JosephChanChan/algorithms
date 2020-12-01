package breadth.first.search;

import java.util.*;

/**
 * leetcode 103 medium & 剑指Offer 32
 *
 * Analysis:
 *  推荐双栈的解法，一次遍历真正的O(n)，并且思想和代码实现更简单。
 * 双栈：左栈 & 右栈
 * 左栈存 left -> right 的元素。遍历左栈时，从左到右添加子元素到右栈，此时最右子元素在右栈的Top。
 * 右栈存 right -> left 的元素。遍历右栈时，从右到左添加子元素到左栈，此时最左子元素在左栈的Top。
 *
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


    // ==================================== 第二种解法 双栈 O(n) 35min AC 2ms

    Stack<TreeNode> left = new Stack<>();// left -> right
    Stack<TreeNode> right = new Stack<>();// right -> left
    List<List<Integer>> ans = new LinkedList<>();

    public List<List<Integer>> levelOrder(TreeNode root) {
        if (null == root) return ans;
        left.add(root);
        bfs();
        return ans;
    }

    private void bfs() {
        if (left.isEmpty() && right.isEmpty()) return;
        List<Integer> list = new LinkedList<>();
        if (right.isEmpty()) {
            // left -> right this level
            while (!left.isEmpty()) {
                TreeNode node = left.pop();
                list.add(node.val);
                if (null != node.left) {
                    right.add(node.left);
                }
                if (null != node.right) {
                    right.add(node.right);
                }
            }
        }
        else {
            while (!right.isEmpty()) {
                TreeNode node = right.pop();
                list.add(node.val);
                if (null != node.right) {
                    left.add(node.right);
                }
                if (null != node.left) {
                    left.add(node.left);
                }
            }
        }
        if (!list.isEmpty()) ans.add(list);
        bfs();
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
