package trees;

/**
 * lc 783 easy
 *
 * Analysis:
 *
 * 时间复杂度：O(n)
 * 空间复杂度：最坏O(n)
 *
 * @author Joseph
 * @since 2021-04-30 20:57
 */
public class MinDistanceInBST {

    public int minDiffInBST(TreeNode root) {
        return inOrderTraversal(root);
    }

    TreeNode prev = null;
    int min = Integer.MAX_VALUE;

    // 升序遍历BST
    public int inOrderTraversal(TreeNode root) {
        if (null == root) return 0;

        dfs(root);
        return min;
    }

    void dfs(TreeNode node) {
        if (null != node.left) {
            dfs(node.left);
        }
        if (null == prev) {
            prev = node;
        }
        else {
            min = Math.min(min, node.val - prev.val);
            prev = node;
        }
        if (null != node.right) {
            dfs(node.right);
        }
    }

    int usingDAndC(TreeNode node) {
        /*
            分治主要是借鉴BST特性。左子树化为有序数组，右子树化为有序数组。
            left nodes <= 节点 <= right nodes
         */
        int[] a = dAndC(node);
        for (int i = 0; i < a.length-1; i++) {
            min = Math.min(min, Math.abs(a[i]-a[i+1]));
        }
        return min;
    }

    int[] dAndC(TreeNode node) {
        if (null == node) return new int[0];

        int[] l = dAndC(node.left);
        int[] r = dAndC(node.right);
        int[] ordered = new int[l.length + r.length + 1];

        int i = 0;
        for (int j = 0; j < l.length; j++) ordered[i++] = l[j];
        ordered[i++] = node.val;
        for (int j = 0; j < r.length; j++) ordered[i++] = r[j];
        return ordered;
    }
}
