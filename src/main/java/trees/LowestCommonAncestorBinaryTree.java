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

    TreeNode ans = null;

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (null == root) return null;
        dAndC(root, p, q);
        return ans;
    }

    private int dAndC(TreeNode n, TreeNode p, TreeNode q) {
        int count = 0;
        if (n.val == p.val || n.val == q.val) count++;
        if (null != n.left) {
            count += dAndC(n.left, p, q);
        }
        // 搜完左子树可能已经得到祖先节点了
        if (null == ans && count == 2) ans = n;
        if (null == ans && null != n.right) {
            count += dAndC(n.right, p, q);
        }
        // 搜完右子树可能已经得到祖先节点了
        if (null == ans && count == 2) ans = n;
        return count;
    }
}
