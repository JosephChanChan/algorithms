package depth.first.search;

/**
 * 剑指Offer 26 medium
 *
 * Analysis:
 *  DFS找到头，Divide & Conquer匹配子树结构
 *
 * 时间复杂度：最坏 O(n*logn)
 * 空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2020-11-21 14:33
 */
public class SameSubTree {

    public boolean isSubStructure(TreeNode A, TreeNode B) {
        /*
            边界参数：
                A节点数是1 or B节点数是1
                B节点数 > A节点数

            错误参数：
                A or B is null
        */
        if (null == B || null == A) return false;
        return searchHead(A, B);
    }

    private boolean searchHead(TreeNode A, TreeNode B) {
        if (null == A) return false;
        if (A.val == B.val) {
            // get a head, lets match left and right subtree
            if (dAndC(A, B)) return true;
        }
        if (searchHead(A.left, B)) return true;
        return searchHead(A.right, B);
    }

    private boolean dAndC(TreeNode a, TreeNode b) {
        if (null == b) return true;
        if (null == a) return false;

        if (a.val == b. val) {
            return dAndC(a.left, b.left) && dAndC(a.right, b.right);
        }
        return false;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
