package recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Joseph
 * @since 2020-01-26 12:42
 *
 * leetcode 95 medium
 *
 * Question Description:
 * Given an integer n, generate all structurally unique BST's (binary search trees) that store values 1 ... n.
 * Example:
 * Input: 3
 * Output:
 * [
 *   [1,null,3,2],
 *   [3,2,null,1],
 *   [3,1,null,null,2],
 *   [2,1,3],
 *   [1,null,2,null,3]
 * ]
 * Explanation:
 * The above output corresponds to the 5 unique BST's shown below:
 *    1         3     3      2      1
 *     \       /     /      / \      \
 *      3     2     1      1   3      2
 *     /     /       \                 \
 *    2     1         2                 3
 *
 * Analysis:
 *  递归分治，借助二叉树特性，可推出公式：
 *   f(l,r) 表示为以区间 l~r 之间的每一个 a[i] 为根结点的所有子树情况。
 *   f(l,r) = f(l,i-1) + f(i+1,r), {l <= i <= r}
 *   注意这不是递推公式
 *
 *  边界：
 *   f(l,r) = a[l], l == r
 *
 *  时间复杂度：O(n^2)
 *  空间复杂度：不知道
 */
public class UniqueBinarySearchTrees2 {

    int[] nodeValues ;


    /*public static void main(String[] args) {
        UniqueBinarySearchTrees2 trees2 = new UniqueBinarySearchTrees2();
        List<TreeNode> nodeList = trees2.generateTrees(3);
        for (TreeNode node : nodeList) {
            System.out.println(node);
        }
    }*/

    public List<TreeNode> generateTrees(int n) {
        if (n == 0) return new ArrayList<>();
        nodeValues = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            nodeValues[i] = i;
        }
        return recursiveProduceSubTree(1, n);
    }

    private List<TreeNode> recursiveProduceSubTree(int left, int right) {
        if (left > right) return null;
        if (left == right) {
            List<TreeNode> subTree = new ArrayList<>();
            TreeNode node = new TreeNode(nodeValues[left]);
            subTree.add(node);
            return subTree;
        }

        List<TreeNode> nodeList = new ArrayList<>();

        for (int i = left; i <= right; i++) {
            // 以 i 为根结点

            // 递归生成左子树
            List<TreeNode> leftTree = recursiveProduceSubTree(left, i - 1);
            // 递归生成右子树
            List<TreeNode> rightTree = recursiveProduceSubTree(i + 1, right);

            if (null == leftTree) {
                for (TreeNode rightChild : rightTree) {
                    TreeNode node = new TreeNode(nodeValues[i]);
                    node.right = rightChild;
                    nodeList.add(node);
                }
            }
            else if (null == rightTree) {
                for (TreeNode leftChild : leftTree) {
                    TreeNode node = new TreeNode(nodeValues[i]);
                    node.left = leftChild;
                    nodeList.add(node);
                }
            }
            else {
                // 左右子树都不空，需要组合左右子树的每一个情况
                for (TreeNode leftChild : leftTree) {
                    for (TreeNode rightChild : rightTree) {
                        TreeNode node = new TreeNode(nodeValues[i]);
                        node.left = leftChild;
                        node.right = rightChild;
                        nodeList.add(node);
                    }
                }
            }
        }

        return nodeList;
    }


    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
    }






}
