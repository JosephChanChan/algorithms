package depth.first.search;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * leetcode 117 medium
 *
 * Question Description:
 *  参见 lc 117
 *
 * Analysis:
 * 这题显然用BFS会更好解决。
 * 对于DFS，这题和116区别在于树不是完美的，其中子节点可能缺失，
 * 对于每层节点先建立连接，在递归子树。
 * 对于每个节点都是连接自己的兄弟节点，没有则连接堂兄节点。
 * 如何找堂兄节点？这就是先建立连接的好处，顺着parent找到
 *
 * 时间复杂度：BFS O(n) DFS O(n)
 * 空间复杂度：BFS O(n) DFS 不算栈空间的话O(1)
 *
 * @author Joseph
 * @since 2020-07-22 23:46
 */
public class SetRightPointer2EachNode2 {

    public static void main(String[] args) {
        SetRightPointer2EachNode2 test = new SetRightPointer2EachNode2();
        Node root = new Node(1);
        Node l = new Node(2);
        Node r = new Node(3);
        Node l1 = new Node(4);
        Node l2 = new Node(5);
        Node r1 = new Node(7);
        root.left = l;
        root.right = r;
        l.left = l1;
        l.right = l2;
        r.right = r1;
        test.connect(root);
    }

    public Node connect(Node root) {
        if (null == root) return null;
        /*List<Node> list = new ArrayList<>(2);
        list.add(root);
        bfs(list);*/
        dfs(root);
        return root;
    }

    /* 0ms AC faster than 100% */
    private void dfs(Node node) {
        // 左子树连接右子树，如果没有兄弟节点就找堂兄节点
        if (null != node.left) {
            if (null != node.right) {
                node.next = node.right;
            }
            else {
                node.next = findCousin(node);
            }
        }
        if (null != node.right) {
            // 把右子树连接到最近的一个堂兄节点
            node.next = findCousin(node);
        }
        // 先让右子树全部建立连接，等计算左子树就能找到所有叔叔节点
        if (null != node.right) {
            dfs(node.right);
        }
        if (null != node.left) {
            dfs(node.left);
        }
    }

    private Node findCousin(Node parent) {
        Node node = parent.next;
        while (null != node) {
            if (null != node.left) return node.left;
            if (null != node.right) return node.right;
            node = node.next;
        }
        return null;
    }



    /* BFS AC 1ms faster than 60% */
    private void bfs(List<Node> nodeList) {
        if (nodeList.size() == 0) return;
        List<Node> list = new ArrayList<>(nodeList.size() << 1);
        int n = nodeList.size();
        for (int i = 0; i < n; ) {
            // concat each node
            Node node = nodeList.get(i);
            if (++i < n) {
                node.next = nodeList.get(i);
            }
            // collect each sub node
            if (null != node.left) {
                list.add(node.left);
            }
            if (null != node.right) {
                list.add(node.right);
            }
        }
        bfs(list);
    }

    static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node(int val) {this.val = val;}
    }
}
