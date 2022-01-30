package trees;

/**
 * lc 236 medium
 *
 * Analysis:
 * 分治，左右子树是否存在p/q，以及自己是否p/q，再合并左右子树的结果判断就行。
 * 二分，题目给出p&q一定存在，如果p&q不一定存在则不能二分，因为当 p<r<q 时不确定p or q是否存在左右子树中。
 * 对每个节点有4种情况，1、p&q都在左子树，2、p&q都在右子树，3、p or q是当前节点，4、p&q各在左右子树
 * 对于1和2可以二分，3和4直接返回当前节点
 *
 * 时间复杂度：因为BST可能退化成链表所以最坏 O(n)
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

    // 二分法
    void binaryDfs(TreeNode node, TreeNode p, TreeNode q) {
        if (node.val == p.val || node.val == q.val) {
            ans = node; return;
        }
        int min = Math.min(p.val, q.val);
        int max = Math.max(p.val, q.val);
        if (node.val > max) {
            binaryDfs(node.left, p, q);
        }
        if (node.val < min) {
            binaryDfs(node.right, p, q);
        }
        if (null == ans) ans = node;
    }
}
