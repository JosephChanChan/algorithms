package trees;

/**
 * 剑指Offer 55 easy
 *
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2021-01-16 17:59
 */
public class DepthOfTree {

    public int maxDepth(TreeNode root) {
        if (null == root) return 0;
        return dAndC(root, 1);
    }

    private int dAndC(TreeNode node, int d) {
        if (null == node.left && null == node.right) return d;
        int left = 0, right = 0;
        if (null != node.left) left = dAndC(node.left, d+1);
        if (null != node.right) right = dAndC(node.right, d+1);
        return Math.max(left, right);
    }

}
