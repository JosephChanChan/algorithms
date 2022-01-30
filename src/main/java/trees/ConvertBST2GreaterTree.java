package trees;

/**
 * lc 538 medium
 *
 * Analysis:
 *  仔细观察所谓的累加树就会发现，由于是BST树且每个节点值唯一，
 * 对于每个节点来说，求和树中大于等于它的节点值，只需要去搜索它的右子树。
 * 并且有一个累加过程，当前节点的累加值是它的后继节点的基础值。
 * 遍历过程符合 右根左
 *
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2021-03-24 15:26
 */
public class ConvertBST2GreaterTree {

    public TreeNode convertBST(TreeNode root) {
        dfs(root, 0);
        return root;
    }

    int dfs(TreeNode node, int sum) {
        if (null == node) return sum;

        sum = dfs(node.right, sum);
        node.val = sum + node.val;
        sum = dfs(node.left, node.val);

        return sum;
    }
}
