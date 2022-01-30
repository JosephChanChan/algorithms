package trees;

/**
 * lc 226 easy
 *
 * Analysis:
 *  dfs依次翻转左右子节点
 *
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2021-03-26 15:42
 */
public class InvertBinaryTree {

    public TreeNode invertTree(TreeNode root) {
        if (null == root) return null;
        dfs(root);
        return root;
    }

    void dfs(TreeNode node) {
        if (null == node.left && null == node.right) return;
        TreeNode n = node.left;
        node.left = node.right;
        node.right = n;
        if (null != node.left) dfs(node.left);
        if (null != node.right) dfs(node.right);
    }
}
