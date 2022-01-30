package trees;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Joseph
 * @since 2020-02-03 12:19
 *
 * lc 102 medium
 *
 * Question Description:
 *  Given a binary tree, return the level order traversal of its nodes' values.
 *  (ie, from left to right, level by level).
 * For example:
 * Given binary tree [3,9,20,null,null,15,7],
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * return its level order traversal as:
 * [
 *   [3],
 *   [9,20],
 *   [15,7]
 * ]
 *
 * Analysis:
 *  时间复杂度：递归与迭代 O(n)
 *  空间复杂度：递归与迭代 O(n)
 */
public class BinaryTreeLevelOrderTraversal {


    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<TreeNode>> nodeList = new ArrayList<>();
        List<List<Integer>> valueList = new ArrayList<>();

        if (null == root) return valueList;

        List<TreeNode> nList = new ArrayList<>();
        nList.add(root);

        List<Integer> vList = new ArrayList<>();
        vList.add(root.val);

        nodeList.add(nList);
        valueList.add(vList);

        iterativeTraversal(0, nodeList, valueList);
        return valueList;
    }


    /** 常规操作，递归实现树的层级遍历 AC 1ms */
    private void recursiveTraversal(int level,
                                    List<List<TreeNode>> nodeList,
                                    List<List<Integer>> valueList) {
        List<TreeNode> levelOrderList = nodeList.get(level);

        List<Integer> vList = new ArrayList<>();
        List<TreeNode> nList = new ArrayList<>();

        searchNode(levelOrderList, nList, vList);

        if (nList.size() > 0) {
            nodeList.add(nList);
            valueList.add(vList);
            recursiveTraversal(level + 1, nodeList, valueList);
        }
    }

    /** 迭代实现树的层级遍历 AC 1ms */
    private void iterativeTraversal(int level,
                                    List<List<TreeNode>> nodeList,
                                    List<List<Integer>> valueList) {
        List<TreeNode> nList;
        while (null != (nList = nodeList.get(level))) {
            List<TreeNode> nextLevel = new ArrayList<>();
            List<Integer> nextLevelValue = new ArrayList<>();

            searchNode(nList, nextLevel, nextLevelValue);

            if (nextLevel.size() > 0) {
                nodeList.add(nextLevel);
                valueList.add(nextLevelValue);
                level++;
            }
            else {
                break;
            }
        }
    }

    private void searchNode(List<TreeNode> nList,
                            List<TreeNode> nextLevel,
                            List<Integer> nextLevelValue) {
        for (TreeNode node : nList) {
            if (null != node.left) {
                nextLevel.add(node.left);
                nextLevelValue.add(node.left.val);
            }
            if (null != node.right) {
                nextLevel.add(node.right);
                nextLevelValue.add(node.right.val);
            }
        }
    }




}
