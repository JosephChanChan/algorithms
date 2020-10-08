package trees;

import java.util.*;

/**
 * leetcode 105 & 剑指Offer 7 medium
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
 * 先序、中序的重建算法
 *  1、在先序中找到根。
 *  2、在中序中确定根的左子树、右子树
 *  3、递归左子树，返回左子树根
 *  4、递归右子树，返回右子树根
 *
 *  分治的好例题。根据先序、中序的重建算法。
 *  每次递归返回一颗l~r范围重建完毕的子树的根。
 *  在前序确定根后，l~root-1即左子树范围，root+1~r即右子树范围，分别递归计算返回左右子树的根。
 *
 *  给定一个l~r范围前序，如何确定这个范围的根？如果在前序中遍历哪个节点先出现则要O(n)时间，
 *  观察前序，每个元素其实都是某个范围的根，在每一个l~r范围都需要一个根，用队列记录每个元素，取根只需O(1)时间
 *
 *  时间复杂度：O(n)
 *  空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2020-03-28 16:48
 */
public class RestructureBinaryTree {

    Map<Integer, Integer> indexM ;
    Queue<Integer> q = new LinkedList<>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int n = preorder.length;
        if (n == 0) return null;
        if (n == 1) return new TreeNode(preorder[0]);

        for (int i = 0; i < preorder.length; i++) {
            q.add(preorder[i]);
        }

        indexM = new HashMap<>(n);
        for (int i = 0; i < n; i++) {
            indexM.put(inorder[i], i);
        }

        return doBuild(preorder, inorder, 0, n-1);
    }

    private TreeNode doBuild(int[] preorder, int[] inorder, int l, int r) {
        if (l > r) return null;
        if (l == r) {
            q.poll();
            return new TreeNode(inorder[l]);
        }

        int root = q.poll();
        int rx = indexM.get(root);

        TreeNode node = new TreeNode(root);

        node.left = doBuild(preorder, inorder, l, rx-1);
        node.right = doBuild(preorder, inorder, rx+1, r);
        return node;
    }
}
