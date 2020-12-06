package trees;

/**
 * 剑指Offer 36 medium & lc 426
 *
 * Analysis:
 *  既然中序遍历能得到排好序的链表，就在遍历过程用prev, cur 2个指针记录前节点和当前节点连接起来。
 *
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2020-12-06 17:26
 */
public class BST2DoublyList {

    Node p = null, head = null;

    public Node treeToDoublyList(Node root) {
        if (null == root) return null;

        inOrderTraversal(root);
        head.left = p;
        p.right = head;
        return head;
    }

    private void inOrderTraversal(Node node) {
        if (null != node.left) inOrderTraversal(node.left);
        if (null != p) {
            p.right = node;
            node.left = p;
        }
        if (null == head) head = node;
        p = node;
        if (null != node.right) inOrderTraversal(node.right);
    }

    class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val,Node _left,Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    };
}
