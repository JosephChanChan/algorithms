package trees;

/**
 * 剑指Offer 28 easy
 *
 * Analysis:
 *  本层的节点可以用p & q枚举下一层的每一个节点，每一次对比都是在比较p.l和q.r，p.r和q.l。
 *  所以不会有遗漏。
 *
 *  时间复杂度：O(n)
 *  空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2020-11-21 16:19
 */
public class SymmetricTree {

    public boolean isSymmetric(TreeNode root) {
        /*
            边界参数：
                some node has no subtree or has only one
            错误参数：
                root is null
        */
        if (null == root) return true;
        if (null == root.left && null == root.right) return true;
        if (null == root.left || null == root.right) return false;
        if (root.left.val != root.right.val) return false;
        return dAndC(root.left, root.right);
    }

    private boolean dAndC(TreeNode p, TreeNode q) {
        if (null == p && null == q) return true;
        if (null == p || null == q) return false;
        if (p.val != q.val) return false;
        return dAndC(p.left, q.right) && dAndC(p.right, q.left);
    }
}
