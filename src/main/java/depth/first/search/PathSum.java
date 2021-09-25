package depth.first.search;

/**
 * lc 112 easy
 *
 * Question Description:
 *  参见 lc 112
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
public class PathSum {

    public boolean hasPathSum(TreeNode root, int sum) {
        return dfs(root, sum, 0);
    }

    private boolean dfs(TreeNode node, int sum, int accumulate) {
        if (null == node.left && null == node.right) {
            return sum == node.val + accumulate;
        }

        if (null != node.left) {
            if (dfs(node.left, sum, node.val + accumulate)) return true;
        }
        if (null != node.right) {
            return dfs(node.right, sum, node.val + accumulate);
        }
        return false;
    }




    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
