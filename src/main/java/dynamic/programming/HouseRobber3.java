package dynamic.programming;

/**
 * lc 337 medium
 *
 * Analysis：
 *  这题官方给的tag是DP，实现上是自顶向下的深搜。到底后会按照决策条件向上转移状态。
 *  在每一层的状态决策中都考虑了拿当前的子节点累计的值和不拿当前子节点累计的值，两种状态中选出最好的状态。
 *
 *  时间复杂度：O(N)
 *  空间复杂度：O(logN)
 *
 * @author Joseph
 * @since 2021-03-19 23:38
 */
public class HouseRobber3 {

    public int rob(TreeNode root) {
        Integer[] m = dfs(root);
        return Math.max(m[0], m[1]);
    }

    Integer[] dfs(TreeNode node) {
        if (null == node) return new Integer[]{0, 0};

        Integer[] m = new Integer[2];
        Integer[] c1 = dfs(node.left);
        Integer[] c2 = dfs(node.right);
        // 不拿当前节点对于每个子树就有两种决策
        // 拿左节点和不拿左节点的最大收益 和 拿右节点和不拿右节点的最大收益
        m[0] = Math.max(c1[0], c1[1]) + Math.max(c2[0], c2[1]);
        // 拿当前节点对于每个子树只有一种决策
        // 左节点和右节点都不能拿
        m[1] = c1[0] + c2[0] + node.val;
        return m;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;

        }
    }
}