package trees;

/**
 * lc 543 easy
 *
 * Analysis:
 *  树中任意两个距离最远的节点就是最大直径。
 * 题目给出提示，最大直径不一定经过根节点。
 * 每个节点的左树的最深距离+右树最深距离都可能构成最大直径
 *
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2021-03-24 16:53
 */
public class DiameterOfTree {

    int max = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        /*
            分治每一个节点。计算节点时看左右子树的最大深度和是否>max，更新。
         */
        if (null == root) return 0;
        dAndC(root);
        return max;
    }

    int dAndC(TreeNode node) {
        int left = 0, right = 0;
        if (null != node.left) {
            left = dAndC(node.left) + 1;
        }
        if (null != node.right) {
            right = dAndC(node.right) + 1;
        }
        max = Math.max(max, left + right);
        return Math.max(left, right);
    }
}
