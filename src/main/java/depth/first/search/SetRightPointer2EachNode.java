package depth.first.search;

/**
 * lc 116 medium
 *
 * Question Description:
 *  参见 lc 116
 *
 * Analysis:
 * 这题显然用BFS会更好解决。
 * 对于DFS，可以这样考虑，涉及到串联同一层级的节点，两颗树间最多有四个节点，将其分别连接。
 * 再递归四个节点中相邻的三组节点。
 *
 * 时间复杂度：O(n)
 * 空间复杂度：O(logN)
 *
 * @author Joseph
 * @since 2020-07-22 23:46
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
