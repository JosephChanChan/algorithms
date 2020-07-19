package depth.first.search;

/**
 * leetcode 108 easy
 *
 * Question Description；
 *  参见 lc 108
 *
 * Analysis:
 * 考虑平衡二叉树的性质 l <= root <= r，并且任意一个节点的左右子树高度差小于2.
 * 那么根据有序的数组性质，很容易想到中位数 am，|1~am-1 - am+1~an| 左右子数组元素的个数差应该是小于2的。
 * 递归计算左右子树，确保每个左右子树高度差小于2.
 *
 * 时间复杂度：O(n)
 * 空间复杂度：O(logN)
 *
 * @author Joseph
 * @since 2020-07-19 23:05
 */
public class ConvertSortedArray2BST {

    public static void main(String[] args) {
        int[] nums = {-10,-3,0,5,9};
        ConvertSortedArray2BST test = new ConvertSortedArray2BST();
        TreeNode treeNode = test.sortedArrayToBST(nums);
        System.out.println(treeNode);
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        return dfs(0, nums.length - 1, nums);
    }

    private TreeNode dfs(int l, int r, int[] nums) {
        if (l > r) return null;
        if (l == r) return new TreeNode(nums[l]);

        int m = (r + l) >> 1;
        TreeNode root = new TreeNode(nums[m]);
        root.left = dfs(l, m - 1, nums);
        root.right = dfs(m + 1, r, nums);
        return root;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) { this.val = val; }
    }
}
