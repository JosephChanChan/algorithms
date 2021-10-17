package breadth.first.search;

import java.util.*;

/**
 * lc 103 medium & 剑指Offer 32
 *
 * Analysis:
 *  推荐双栈的解法，一次遍历真正的O(n)，并且思想和代码实现更简单。
 * 双栈：左栈 & 右栈
 * 左栈存 left -> right 的元素。遍历左栈时，从左到右添加子元素到右栈，此时最右子元素在右栈的Top。
 * 右栈存 right -> left 的元素。遍历右栈时，从右到左添加子元素到左栈，此时最左子元素在左栈的Top。
 *
 * 面试忘记元素应该入哪个栈时，在纸上画两个栈，模拟入栈顺序就明白了
 * 本层元素的左右元素入栈顺序忘了，就看下一层元素出栈顺序要求是什么，本层和它相反
 * 下一层元素要求右边元素先出栈，本层就从左边开始入栈
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

    public ArrayList<ArrayList<Integer>> twoStack(TreeNode root) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList();

        if (null == root) return ans;

        // left
        Stack<TreeNode> a = new Stack();
        // right
        Stack<TreeNode> b = new Stack();

        b.add(root);
        bfs(a, b, ans);
        return ans;
    }

    void bfs(Stack<TreeNode> a, Stack<TreeNode> b, ArrayList<ArrayList<Integer>> ans) {
        if (a.size() > 0) {
            ArrayList<Integer> l = new ArrayList();
            while (!a.isEmpty()) {
                TreeNode n = a.pop();
                l.add(n.val);
                if (null != n.right) b.add(n.right);
                if (null != n.left) b.add(n.left);
            }
            ans.add(l);
        }
        else {
            ArrayList<Integer> l = new ArrayList();
            while (!b.isEmpty()) {
                TreeNode n = b.pop();
                l.add(n.val);
                if (null != n.left) a.add(n.left);
                if (null != n.right) a.add(n.right);
            }
            ans.add(l);
        }
        if (a.size() > 0 || b.size() > 0) {
            bfs(a, b, ans);
        }
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
