package trees;

/**
 * lc 814 medium
 *
 * Analysis:
 *
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2022/7/21
 */
public class BinaryTreePruning {

    public TreeNode pruneTree(TreeNode root) {
        if (null == root) {
            return null;
        }
        int c = dAndC(root);
        if (c == 0) {
            return null;
        }
        return root;
    }

    int dAndC(TreeNode node) {
        int c = node.val == 1 ? 1 : 0;
        if (null != node.left) {
            int lc = dAndC(node.left);
            c += lc;
            if (lc == 0) {
                node.left = null;
            }
        }
        if (null != node.right) {
            int rc = dAndC(node.right);
            c += rc;
            if (rc == 0) {
                node.right = null;
            }
        }
        return c;
    }
}
