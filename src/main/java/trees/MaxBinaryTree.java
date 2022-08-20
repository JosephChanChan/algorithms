package trees;

/**
 * lc 654 medium
 *
 * Analysis:
 * 时间复杂度：O(n^2)，在每个区间要花O(r-l)时间找最大，共有n个区间
 * 空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2022/8/20
 */
public class MaxBinaryTree {

    public TreeNode constructMaximumBinaryTree(int[] nums) {
        if (nums.length == 1) {
            return new TreeNode(nums[0]);
        }
        return dfs(0, nums.length-1, nums);
    }

    TreeNode dfs(int l, int r, int[] a) {
        if (l > r) {
            return null;
        }
        int max = -1, maxV = -1;
        for (int i = l; i <= r; i++) {
            if (maxV < a[i]) {
                max = i;
                maxV = a[i];
            }
        }
        TreeNode node = new TreeNode(maxV);
        node.left = dfs(l, max-1, a);
        node.right = dfs(max+1, r, a);
        return node;
    }
}
