package depth.first.search;

/**
 * lc 129 medium
 *
 * Question Description:
 *  参见 lc 129
 *
 * Analysis:
 * 时间复杂度：O(n)
 * 空间复杂度：最坏情况O(n) 最好情况O(logN)
 *
 * @author Joseph
 * @since 2020-07-26 18:36
 */
public class SumRoot2LeafNumbers {

    int sum = 0;

    public int sumNumbers(TreeNode root) {
        if (null == root) return 0;
        dfs(root, new StringBuilder());
        return sum;
}

    private void dfs(TreeNode node, StringBuilder builder) {
        builder.append(node.val);
        if (null == node.left && null == node.right) {
            sum += Integer.parseInt(builder.toString());
            return;
        }
        if (null != node.left) {
            dfs(node.left, builder);
            builder.deleteCharAt(builder.length() - 1);
        }
        if (null != node.right) {
            dfs(node.right, builder);
            builder.deleteCharAt(builder.length() - 1);
        }
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

}
