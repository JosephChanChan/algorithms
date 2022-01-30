package trees;

/**
 * @author Joseph
 * @since 2019-09-22 14:34
 *
 * lc 104 easy
 *
 * Question Description:
 *  Given a binary tree, find its maximum depth.
 * The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
 * Note: A leaf is a node with no children.
 * Example:
 * Given binary tree [3,9,20,null,null,15,7],
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * return its depth = 3.
 *
 * Analysis:
 *  想要求的最深度，只能遍历，前中后序遍历都可以，每递归1次就记录一层深度。
 *
 * 时间复杂度:O(n)
 * 空间复杂度:O(1)
 */
public class MaxDepth4Tree {

    private Integer maxDepth = 0;
    private Integer depth = 0;

    public static void main(String[] args) {
        MaxDepth4Tree maxDepth4Tree = new MaxDepth4Tree();
        int depth = maxDepth4Tree.maxDepth(null);
        System.out.println(depth);
    }

    public int maxDepth2(TreeNode treeNode) {
        // 计算每一个节点的左子树和右子树深度加上本层的节点
        if (null == treeNode) return 0;
        // 当2个递归函数结束返回来时，左右子树的深度都已得知，加上本层的深度1再返回上一层以供上一层的计算。
        return Math.max(maxDepth2(treeNode.left), maxDepth2(treeNode.right)) + 1;
    }

    // 自己的解法，稍比上面的解法慢1ms，时间复杂度都是一样的O(n)
    public int maxDepth(TreeNode root) {
        if (null == root) return maxDepth;
        maxDepth = 1;
        depth = 1;
        calcDepth(root);
        return maxDepth;
    }

    private void calcDepth(TreeNode treeNode) {
        if (null != treeNode.left) {
            compareDepth();
            calcDepth(treeNode.left);
            depth--;
        }

        // 遍历到根结点 什么都不做

        if (null != treeNode.right) {
            compareDepth();
            calcDepth(treeNode.right);
            depth--;
        }
    }

    private void compareDepth() {
        if (++depth > maxDepth) {
            maxDepth = depth;
        }
    }








    private class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }


}
