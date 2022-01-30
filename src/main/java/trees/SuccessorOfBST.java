package trees;

/**
 * lc 285 medium
 *
 * Analysis:
 * BST中序后继
 * 从root开始二分查找，结合BST特性和题目条件，树中无重复节点值
 * 考虑node p的大小关系，如果node < p，p的后继肯定在node右子树，因为 node < p < p's successor
 * node == p，找到p节点，根据后继定义，p的右子树存在，则后继在右子树中
 * node > p，当前node可能是p的后继，如果p没有右子树情况下，p的第一个比它大的父节点就是后继，搜索node左子树
 *
 * 时间复杂度：O(depth of tree)
 * 空间复杂度：树退化为链表时 O(n)
 *
 * @author Joseph
 * @since 2021-04-06 10:44
 */
public class SuccessorOfBST {

    TreeNode ans = null;

    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        return dfsWithBinarySearch(root, p);
    }

    TreeNode dfsWithBinarySearch(TreeNode node, TreeNode p) {
        if (null == node) return ans;

        if (node.val == p.val) {
            // p如果没有右子树，那可能是没后继的，要看p是不是某个子树的左节点
            if (null == p.right) return ans;
            // p的右子树没有左子树，那右子树就是后继
            if (null == p.right.left) return p.right;
            // 最深的左子树就是后继
            TreeNode successor = p.right.left;
            while (null != successor.left) {
                successor = successor.left;
            }
            return successor;
        }

        if (node.val < p.val) {
            return dfsWithBinarySearch(node.right, p);
        }
        if (node.val > p.val) {
            ans = node;
            return dfsWithBinarySearch(node.left, p);
        }
        return ans;
    }
}
