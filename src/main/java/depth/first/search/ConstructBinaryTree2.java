package depth.first.search;

import java.util.LinkedList;

/**
 * lc 105 medium
 *
 * Question Description:
 *  参见 lc 106
 *
 * Analysis:
 * AC 4ms faster than 37%，还可以再优化，就是确定根左右子树的地方
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2020-07-19 15:43
 */
public class ConstructBinaryTree2 {

    /*
        中序：左根右
        后序：左右根
        1.在后续中找到根，每个子树的根都是队尾
        2.在中序确定根的左右子树
        3.先递归计算右子树、再计算左子树
     */

    public static void main(String[] args) {
        ConstructBinaryTree2 tree = new ConstructBinaryTree2();
        int[] inOrder = {4,2,1,5,3,6};
        int[] postOrder = {4,2,5,6,3,1};
        TreeNode treeNode = tree.buildTree(inOrder, postOrder);
        System.out.println(treeNode);
    }

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        LinkedList<Integer> queue = new LinkedList<>();
        for (int i = 0; i < postorder.length; i++) {
            queue.add(postorder[i]);
        }
        return dfs(0, inorder.length, inorder, queue);
    }

    private TreeNode dfs(int l, int r, int[] inOrder, LinkedList<Integer> queue) {
        if (l >= r) return null;

        int root = queue.removeLast();

        int rightBound ;
        for (rightBound = l; rightBound < r; rightBound++) {
            if (inOrder[rightBound] == root) break;
        }

        TreeNode rootNode = new TreeNode(root);

        rootNode.right = dfs(rightBound + 1, r, inOrder, queue);
        rootNode.left = dfs(l, rightBound, inOrder, queue);
        return rootNode;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) { this.val = val; }
    }
}
