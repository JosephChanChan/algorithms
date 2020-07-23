package depth.first.search;

/**
 * @author Joseph Chan
 * @since 2020/07/23
 */
public class SetRightPointer2EachNode {

    public Node connect(Node root) {
        if (null == root) return null;
        if (null == root.left || null == root.right) return root;
        dfs(root.left, root.right);
        return root;
    }

    private void dfs(Node left, Node right) {
        left.next = right;
        // 递归 left 的两颗子树
        if (null != left.left && null != left.right) {
            dfs(left.left, left.right);
        }
        // 递归 left 和 right 中间连接的节点
        if (null != left.right && null != right.left) {
            dfs(left.right, right.left);
        }
        // 递归 right 的两颗子树
        if (null != right.left && null != right.right) {
            dfs(right.left, right.right);
        }
    }

    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }
}
