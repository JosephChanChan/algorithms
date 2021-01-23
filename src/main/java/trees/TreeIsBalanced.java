package trees;

/**
 * 剑指Offer 55 easy
 *
 * Analysis:
 *  分治计算每个节点的深度。16min AC
 *
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2021-01-16 18:18
 */
public class TreeIsBalanced {

    boolean balanced = true;

    public boolean isBalanced(TreeNode root) {
        if (null == root) return true;
        dAndC(root, 1);
        return balanced;
    }

    private int dAndC(TreeNode node, int d) {
        if (null == node.left && null == node.right) return d;
        int l = d, r = d;
        if (null != node.left) l = dAndC(node.left, d+1);
        if (!balanced) return 0;
        if (null != node.right) r = dAndC(node.right, d+1);
        if (!balanced) return 0;
        if (Math.abs(l - r) > 1) balanced = false;
        return Math.max(l, r);
    }
}
