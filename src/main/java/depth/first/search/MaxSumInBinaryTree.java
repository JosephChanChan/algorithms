package depth.first.search;

/**
 * lc 124 hard
 *  左右根的后序遍历树
 * 设定max是树中最大路径和。
 *
 * max有可能出现在
 * 1.单独的节点上，
 * 2.左子树最大路径和+当前节点，
 * 3.右子树最大路径和+当前节点，
 * 4.左子树最大路径和+当前节点+右子树最大路径和
 *
 * 但是返回当前子树的最大路径和时，只能选择
 * 1.当前节点值
 * 2.节点+左子树最大路径和
 * 3.节点+左子树最大路径和
 * 因为碍于“路径”这个概念的限定，从父节点访问当前子树时，父节点的路径只能经当前节点去左子树或者右子树。
 *
 * Analysis：
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2021-03-05 21:49
 */
public class MaxSumInBinaryTree {


    // 成员变量的基本类型有默认值，局部变量就不会有
    int max ;

    public int maxPathSum(TreeNode root) {
        max = root.val;
        dfs(root);
        return max;
    }

    int dfs(TreeNode node) {
        int m = node.val, left = 0, right = 0;
        max = Math.max(m, max);
        if (null != node.left) {
            left = dfs(node.left);
            max = Math.max(left, max);
            max = Math.max(left + m, max);
            if (left > 0) m += left;
        }
        if (null != node.right) {
            right = dfs(node.right);
            max = Math.max(right, max);
            max = Math.max(right + m, max);
            if (right > 0) m += right;
        }
        max = Math.max(m, max);
        if (Math.max(left, right) > 0) {
            return node.val + Math.max(left, right);
        }
        return node.val;
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
