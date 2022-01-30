package trees;

/**
 * lc 510 medium
 *
 * Analysis:
 * 一开始就给定要求的节点
 * node.right存在，则找右子树的最小节点
 * node.right不存在，向上找第一个比他大的父节点。
 * 由于不能比较值，只能分类讨论下第一个比p大的父节点是谁，
 * 1.如果p是父节点的左子树，则当前父节点就是后继
 * 2.如果p是父节点的右子树，继续向上，直到找到第一个满足条件1的父节点
 * 3.一直到根了，都没满足条件1，则无后继
 *
 * 时间复杂度：O(depth of tree)
 * 空间复杂度：O(depth of tree)
 *
 * @author Joseph
 * @since 2021-04-06 11:06
 */
public class SuccessOfBST2 {

    public Node inorderSuccessor(Node node) {
        if (null != node.right) {
            if (null == node.right.left) return node.right;
            Node successor = node.right.left;
            while (null != successor.left) {
                successor = successor.left;
            }
            return successor;
        }
        return dfsUp(node);
    }

    Node dfsUp(Node node) {
        if (null == node.parent) return null;

        Node parent = node.parent;
        if (parent.left == node) return parent;

        return dfsUp(parent);
    }

    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node parent;
    };
}
