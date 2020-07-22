package depth.first.search;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * leetcode 113 medium
 *
 * Question Description:
 *  参见 lc 113
 *
 * Analysis:
 * 题目要求从root到leaf的路径，这样做很简单，就遍历完所有路径和就行。
 * 如果把题目改造下，不一定从root到leaf，而是任意一个节点到任意一个子树节点会更有意思
 *
 * 时间复杂度：O(n)
 * 空间复杂度：最坏O(n) 最好O(logN)
 *
 * @author Joseph
 * @since 2020-07-22 21:08
 */
public class PathSum2 {

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<Integer> list = new ArrayList<>(64);
        List<List<Integer>> table = new LinkedList<>();
        if (null == root) return table;

        dfs(root, sum, 0, list, table);
        return table;
    }

    private void dfs(TreeNode node, int sum, int accumulate,
                     List<Integer> list, List<List<Integer>> table) {
        if (null == node.left && null == node.right) {
            if (sum == node.val + accumulate) {
                list.add(node.val);
                List<Integer> result = new ArrayList<>(list);
                table.add(result);
                backTracking(list);
                return;
            }
        }

        if (null != node.left) {
            list.add(node.val);
            dfs(node.left, sum, node.val + accumulate, list, table);
            backTracking(list);
        }
        if (null != node.right) {
            list.add(node.val);
            dfs(node.right, sum, node.val + accumulate, list, table);
            backTracking(list);
        }

    }

    private void backTracking(List<Integer> list) {
        // back tracking
        list.remove(list.size()-1);
    }


    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
