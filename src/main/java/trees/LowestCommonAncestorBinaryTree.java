package trees;

/**
 * leetcode 236 medium
 *
 * Analysis:
 * 分治，左右子树是否存在p/q，以及自己是否p/q，再合并左右子树的结果判断就行。
 *
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2020-08-24 21:56
 */
public class LowestCommonAncestorBinaryTree {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == p || root == q) return root;
        Result r = divide(root, p, q);
        return r.lca;
    }

    // 计算以node下是否存在p q的lca
    private Result divide(TreeNode node, TreeNode p, TreeNode q) {
        if (null == node) return new Result(null, false, false);

        // divide
        Result lr = divide(node.left, p, q);
        // 加了左子树的判断剪枝右子树，总体时间上并不会更快，还是 faster than 68%
        // if (null != lr.lca) return lr;
        // if (node == p && lr.qPrecent || node == q && lr.pPrecent) return new Result(node, true, true);

        Result rr = divide(node.right, p, q);

        // merge
        if (null != lr.lca) return lr;
        if (null != rr.lca) return rr;
        if ((lr.pPrecent && rr.qPrecent) || (lr.qPrecent && rr.pPrecent)) {
            return new Result(node, true, true);
        }
        if (node == p && (lr.qPrecent || rr.qPrecent) ||
                node == q && (lr.pPrecent || rr.pPrecent)) {
            return new Result(node, true, true);
        }
        return new Result(null,
                node == p || lr.pPrecent || rr.pPrecent,
                node == q || lr.qPrecent || rr.qPrecent);
    }

    class Result {
        TreeNode lca;
        boolean pPrecent;
        boolean qPrecent;

        public Result(TreeNode lca, boolean p, boolean q) {
            this.lca = lca;
            this.pPrecent = p;
            this.qPrecent = q;
        }
    }

}
