package trees;

/**
 * 剑指Offer 33 medium
 *
 * Analysis:
 *  35min
 *  解法一：常规思维，根据后序遍历特点，左右根，后续遍历最后元素是根，根据特点重建一颗二叉搜索树。
 *  再对BST进行后续遍历，边遍历边检查原后续遍历的元素是否匹配。
 *  时间复杂度：O(nlogn)
 *  空间复杂度：O(n)
 *
 *  解法二：分治。根据后续遍历特点，左右根，确定根后，前面元素应为两部分，小于根的左子树和大于根的右子树。
 *  设 l~k-1 是左子树，k~r-1 是右子树，r是根。
 *  如果有左子树肯定是先出现的，只需要确定左子树范围后，再检查右子树中是否出现小于根的则非法。
 *  如果该根的左右子树均合法，则继续分治左右子树。
 *
 *  因为每个节点最多会被检查一次，所以
 *  时间复杂度：O(n)
 *  空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2020-11-28 22:29
 */
public class VerifyPostOrder {

    public boolean verifyPostorder(int[] postorder) {
        if (postorder.length == 0) return true;
        if (postorder.length == 1) return true;

        return dAndC(postorder, 0, postorder.length-1);
    }

    private boolean dAndC(int[] post, int l, int r) {
        if (l >= r) return true;

        int root = post[r];
        int k = l;
        // 找到第一个大于根的就是右子树开头，l~k-1是左子树
        while (k < r && post[k] < root) k++;
        // k现在是右子树开头
        int m = k;
        while (k < r && post[k] > root) k++;
        if (k == r) {
            // 走到这里代表右子树均大于根，分治左右子树看是否合法
            return dAndC(post, l, m-1) && dAndC(post, m, r-1);
        }
        return false;
    }


    // ======================================== 解法一 常规思维

    int i = 0;
    boolean valid = true;
    TreeNode root = null;

    public boolean verifyPostorder2(int[] postorder) {
        if (postorder.length == 0) return true;
        if (postorder.length == 1) return true;

        int len = postorder.length;
        root = new TreeNode(postorder[len - 1]);
        for (int k = len - 2; k >= 0; k--) {
            buildBST(postorder[k], root);
        }
        check(postorder, root);
        return valid;
    }

    private void check(int[] post, TreeNode node) {
        if (null != node.left) {
            check(post, node.left);
        }
        if (valid && null != node.right) {
            check(post, node.right);
        }
        if (valid && post[i++] != node.val) {
            valid = false;
        }
    }

    private TreeNode buildBST(int val, TreeNode node) {
        if (null == node) {
            return new TreeNode(val);
        }
        if (val > node.val) {
            node.right = buildBST(val, node.right);
        }
        else {
            node.left = buildBST(val, node.left);
        }
        return node;
    }

    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {this.val = val;}
    }

}
