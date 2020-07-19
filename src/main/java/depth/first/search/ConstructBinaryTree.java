package depth.first.search;

import java.util.LinkedList;

/**
 * leetcode 105 medium
 *
 * Question Description:
 *  参见 lc 105
 *
 * Analysis:
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2020-07-19 15:43
 */
public class ConstructBinaryTree {

    /*
        先序：根左右
        中序：左根右
        1.在先序确定根r，一般第一个为r
        2.在中序r左边为左子树，右边为右子树
        3.递归计算左右子树
     */

    public static void main(String[] args) {
        ConstructBinaryTree tree = new ConstructBinaryTree();
        int[] preOrder = {1, 2, 4, 3, 5, 6};
        int[] inOrder = {4,2,1,5,3,6};
        TreeNode treeNode = tree.buildTree(preOrder, inOrder);
        System.out.println(treeNode);
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        LinkedList<Integer> queue = new LinkedList<>();
        for (int i = 0; i < preorder.length; i++) {
            queue.add(preorder[i]);
        }
        return dfs(0, preorder.length, inorder, queue);
    }

    private TreeNode dfs(int l, int r, int[] inOrder, LinkedList<Integer> queue) {
        if (l >= r) return null;

        int root = queue.removeFirst();

        // 这里确定左右子树边界的地方可以优化
        int rightBound ;
        for (rightBound = l; rightBound < r; rightBound++) {
            if (inOrder[rightBound] == root) break;
        }

        TreeNode rootNode = new TreeNode(root);

        rootNode.left = dfs(l, rightBound, inOrder, queue);
        rootNode.right = dfs(rightBound + 1, r, inOrder, queue);
        return rootNode;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
        }
    }
}
