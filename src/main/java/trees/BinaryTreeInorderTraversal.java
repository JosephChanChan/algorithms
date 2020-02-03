package trees;

import java.util.*;

/**
 * @author Joseph
 * @since 2020-02-02 11:38
 *
 * leetcode 94 medium
 *
 * Question Description:
 *  Given a binary tree, return the inorder traversal of its nodes' values.
 * Example:
 * Input: [1,null,2,3]
 *    1
 *     \
 *      2
 *     /
 *    3
 * Output: [1,3,2]
 * Follow up: Recursive solution is trivial, could you do it iteratively?
 *
 * Analysis:
 *
 *  时间复杂度：递归 O(n) 迭代 O(n)
 *  空间复杂度：递归 O(n) 迭代 O(n)
 */
public class BinaryTreeInorderTraversal {

    private List<Integer> list = new ArrayList<>();

    private Stack<TreeNode> stack = new Stack<>();

    public static void main(String[] args) {
        BinaryTreeInorderTraversal inorderTraversal = new BinaryTreeInorderTraversal();
        TreeNode root = inorderTraversal.new TreeNode(1);
        TreeNode two = inorderTraversal.new TreeNode(2);
        TreeNode three = inorderTraversal.new TreeNode(3);

        root.right = two;
        two.left = three;

        inorderTraversal.inorderTraversal(root);
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        if (null == root) return list;
        iterativeInorderTraversal(root);
        return list;
    }

    /** 常规操作，递归实现中序遍历 */
    private void recursiveInorderTraversal(TreeNode node) {
        // 左根右
        if (null == node) return;

        recursiveInorderTraversal(node.left);
        list.add(node.val);
        recursiveInorderTraversal(node.right);
    }

    /** 迭代实现中序遍历（看着简单，但真的难想出来，抄自 leetcode ）*/
    private void iterativeInorderTraversal(TreeNode root) {
        TreeNode node = root;
        while (null != node || !stack.isEmpty()) {
            while (null != node) {
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            list.add(node.val);
            node = node.right;
        }
    }

    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }



}
