package trees;

/**
 * 剑指Offer 54 easy
 *
 * Analysis:
 *  根据二叉搜索树的性质，右根左遍历树节点，计数 10min AC
 *
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2021-01-16 17:52
 */
public class NthKTreeNode {

    int k ;
    TreeNode ans = null;

    public int kthLargest(TreeNode root, int k) {
        if (null == root) return 0;
        if (null == root.left && null == root.right) return root.val;
        this.k = k;
        travse(root);
        return ans.val;
    }

    private void travse(TreeNode node) {
        if (null != node.right) {
            travse(node.right);
        }
        if (--k == 0) ans = node;
        if (null == ans && null != node.left) {
            travse(node.left);
        }
    }
}
