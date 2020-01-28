package trees;

/**
 * @author Joseph
 * @since 2020-01-27 19:38
 *
 * leetcode 98 medium
 *
 * Question Description:
 *  Given a binary tree, determine if it is a valid binary search tree (BST).
 * Assume a BST is defined as follows:
 * The left subtree of a node contains only nodes with keys less than the node's key.
 * The right subtree of a node contains only nodes with keys greater than the node's key.
 * Both the left and right subtrees must also be binary search trees.
 * Example 1:
 *     2
 *    / \
 *   1   3
 * Input: [2,1,3]
 * Output: true
 * Example 2:
 *     5
 *    / \
 *   1   4
 *      / \
 *     3   6
 * Input: [5,1,4,null,null,3,6]
 * Output: false
 * Explanation: The root node's value is 5 but its right child's value is 4.
 *
 * Analysis:
 *  分治递归。
 *  1、divide 以当前节点为根，左右子树划分。
 *  2、conquer 对当前节点的左右子树深搜判断是否合法，合法的话继续以左右子节点为根做深搜。
 *  3、combine 合并左右子树结果做判断。
 *
 *  leetcode 上有更好的解法，时间复杂度是 O(n)
 *
 *  时间复杂度：O(N^2)
 *  空间复杂度：O(1)
 */
public class ValidBinarySearchTree {


    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        TreeNode one = new TreeNode(1);
        TreeNode four = new TreeNode(4);
        TreeNode three = new TreeNode(3);
        TreeNode six = new TreeNode(6);
        root.left = one;
        one.right = four;
        four.left = three;
        root.right = six;

        ValidBinarySearchTree valid = new ValidBinarySearchTree();
        boolean validBST = valid.isValidBST(root);
        System.out.println(validBST);
    }

    public boolean isValidBST(TreeNode root) {
        return recursivelyCheckNode(root);
    }

    private boolean recursivelyCheckNode(TreeNode node) {
        if (null == node) return true;

        boolean leftValid = true,
                rightValid = true;

        if (null != node.left) {
            // 深搜左子树
            leftValid = depthCheck(node, node.left, true);
        }
        if (leftValid && null != node.right) {
            // 深搜右子树
            rightValid = depthCheck(node, node.right, false);
        }

        if (leftValid && rightValid) {
            // 遍历下一个左节点
            if (null != node.left) {
                leftValid = recursivelyCheckNode(node.left);
            }
            // 遍历下一个右节点
            if (null != node.right) {
                rightValid = recursivelyCheckNode(node.right);
            }
            return leftValid && rightValid;
        }

        return false;
    }

    private boolean depthCheck(TreeNode ancestor, TreeNode node, boolean leftSide) {
        if (null == node) return true;
        boolean valid = leftSide ? leftCheck(ancestor, node) : rightCheck(ancestor, node);
        if (!valid) return false;
        // 深搜左边
        valid = depthCheck(ancestor, node.left, leftSide);
        if (!valid) return false;
        // 深搜右边
        return depthCheck(ancestor, node.right, leftSide);
    }

    private boolean leftCheck(TreeNode ancestor, TreeNode node) {
        return ancestor.val > node.val;
    }

    private boolean rightCheck(TreeNode ancestor, TreeNode node) {
        return ancestor.val < node.val;
    }



    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

}
