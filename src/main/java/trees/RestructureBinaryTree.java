package trees;

import java.util.*;

/**
 * @author Joseph
 * @since 2020-03-28 16:48
 *
 * 剑指Offer 7
 *
 * Question Description:
 *  输入某二叉树的前序遍历和中序遍历的结果，请重建该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 * 例如，给出
 * 前序遍历 preorder = [3,9,20,15,7]
 * 中序遍历 inorder = [9,3,15,20,7]
 * 返回如下的二叉树：
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 限制：
 * 0 <= 节点个数 <= 5000
 *
 * Analysis:
 *  1、在先序中找到根。
 *  2、在中序中确定根的左子树、右子树
 *  3、递归左子树，返回左子树根
 *  4、递归右子树，返回右子树根
 *  算法思想很简单明了，微操作中有坑，在第2步找左右子树中会涉及重复节点和属于其它子树的节点，
 *  问题是如何排除这些错误节点？
 *  直接查找当前的子树集合，根和它的左右子树必定在当前的子树集合中，这样可以排除不属于当前根的节点。
 *
 *  时间复杂度：O(n)
 *  空间复杂度：O(n)
 */
public class RestructureBinaryTree {

    private Set<Integer> used = new HashSet<>(64);

    public static void main(String[] args) {
        /*
            [7,-10,-4,3,-1,2,-8,11]
            [-4,-10,3,-1,7,11,-8,2]
            [3,9,20,15,7]
            [9,3,15,20,7]
         */
        int[] p = {3,9,20,15,7};
        int[] i = {9,3,15,20,7};
        RestructureBinaryTree binaryTree = new RestructureBinaryTree();
        TreeNode root = binaryTree.buildTree(p, i);
        System.out.println(root);
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0)
            return null;
        if (preorder.length == 1)
            return new TreeNode(preorder[0]);

        List<Integer> inList = new ArrayList<>();
        for (int value : inorder) {
            inList.add(value);
        }

        TreeNode root = new TreeNode(preorder[0]);
        used.add(root.val);

        List<Integer> leftTree = leftTree(inList, root.val);
        List<Integer> rightTree = rightTree(inList, root.val);
        root.left = recursion(preorder, leftTree);
        root.right = recursion(preorder, rightTree);
        return root;
    }

    private TreeNode recursion(int[] preOrder, List<Integer> tree) {
        if (tree.size() == 0)
            return null;
        if (tree.size() == 1) {
            used.add(tree.get(0));
            return new TreeNode(tree.get(0));
        }

        Set<Integer> existsSet = new HashSet<>(tree.size());
        existsSet.addAll(tree);

        TreeNode root = null;
        int r = 0;
        for ( ; r < preOrder.length; r++) {
            if (used.contains(preOrder[r]))
                continue;
            if (existsSet.contains(preOrder[r])) {
                root = new TreeNode(preOrder[r]);
                used.add(root.val);
                break;
            }
        }
        List<Integer> leftTree = leftTree(tree, root.val);
        List<Integer> rightTree = rightTree(tree, root.val);

        root.left = recursion(preOrder, leftTree);
        root.right = recursion(preOrder, rightTree);
        return root;
    }

    private List<Integer> leftTree(List<Integer> inOrder, int root) {
        List<Integer> list = new ArrayList<>();
        for (int left = 0; ; left++) {
            if (inOrder.get(left) == root)
                break;
            if (used.contains(inOrder.get(left)))
                continue;
            list.add(inOrder.get(left));
        }
        return list;
    }

    private List<Integer> rightTree(List<Integer> inOrder, int root) {
        List<Integer> list = new ArrayList<>();
        int right = 0;
        for ( ; right < inOrder.size(); right++) {
            if (root == inOrder.get(right)) {
                break;
            }
        }
        for (right++; right < inOrder.size(); right++) {
            if (used.contains(inOrder.get(right)))
                continue;
            list.add(inOrder.get(right));
        }
        return list;
    }

}
