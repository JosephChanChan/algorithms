package trees;

/**
 * 剑指Offer 27 easy
 *
 * Analysis:
 *  将左右子树交换，dfs这个过程。
 *
 *  时间复杂度：O(n)
 *  空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2020-11-21 15:28
 */
public class MirrorTree {

    public TreeNode mirrorTree(TreeNode root) {
        /*
            边界参数：
                left tree or right tree is null
                left and right is null
            错误参数：
                root is null
        */
        if (null == root) return null;
        dfs(root);
        return root;
    }

    private void dfs(TreeNode node) {
        TreeNode left = node.left;
        TreeNode right = node.right;
        node.left = right;
        node.right = left;
        if (null != node.left) dfs(node.left);
        if (null != node.right) dfs(node.right);
    }
}
