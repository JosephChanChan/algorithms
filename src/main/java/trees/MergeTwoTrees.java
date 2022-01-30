package trees;

/**
 * lc 617 easy
 *
 * Analysis:
 *  根左右顺序遍历两颗树，选定一颗作为主树
 * 当子树有主树没有的节点，迁过来
 *
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2021-03-24 22:56
 */
public class MergeTwoTrees {

    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (null == root1 && null == root2) return null;
        if (null == root1) return root2;
        if (null == root2) return root1;
        dfs(root1, root2);
        return root1;
    }

    void dfs(TreeNode p, TreeNode q) {
        p.val += q.val;
        if (null != p.left && null != q.left) {
            dfs(p.left, q.left);
        }
        // 主树没有 而子树有，迁过来
        if (null == p.left && null != q.left) {
            p.left = q.left;
        }
        if (null != p.right && null != q.right) {
            dfs(p.right, q.right);
        }
        if (null == p.right && null != q.right) {
            p.right = q.right;
        }
    }
}
