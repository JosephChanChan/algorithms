package depth.first.search;

/**
 * lc 110 easy
 *
 * Question Description:
 *  参见 lc 110
 *
 * Analysis:
 * 时间复杂度：O(n)
 * 空间复杂度：如果树退化成链表则是 O(n)，最好的平衡树情况是 O(logN)
 *
 * @author Joseph
 * @since 2020-07-21 00:12
 */
public class HeightOfBST {


    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode r = new TreeNode(2);
        TreeNode r1 = new TreeNode(3);
        root.right = r;
        r.right = r1;
        HeightOfBST test = new HeightOfBST();
        boolean balanced = test.isBalanced(root);
        System.out.println(balanced);
    }

    public boolean isBalanced(TreeNode root) {
        /*
            计算每个节点的左右子树树高，当差超过1立即返回false.
            每个节点拿子树返回的较大的高加上自身高度1返回。
        */
        return dfs(root);
    }

    private boolean dfs(TreeNode node) {
        if (null == node) return true;

        if (dfs(node.left) && dfs(node.right)) {
            if (Math.abs(height(node.left) - height(node.right)) < 2) {
                node.val = Math.max(height(node.left), height(node.right)) + 1;
                return true;
            }
        }
        return false;
    }

    private int height(TreeNode node) {
        if (null == node) return 0;
        return node.val;
    }




    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

}
