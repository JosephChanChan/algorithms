package graphs;

import java.util.*;

/**
 * leetcode 133 medium
 *
 * Question Description:
 *  参见 lc 133
 *
 * Analysis:
 * 时间复杂度：O(n+m)
 * 空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2020-08-02 23:09
 */
public class CloneGraph {

    private Set<Integer> visited = new HashSet<>(128);
    private Map<Integer, Node> newGraphs = new HashMap<>(128);
    // 一开始用PriorityQueue过到一半case超时，仔细阅读代码改成了链表顺利AC
    // 为什么？原因就在于链表和最小堆的数据结构差异上
    private Queue<Node> queue = new LinkedList<>();

    public Node cloneGraph(Node node) {
        if (null == node) return null;
        if (node.neighbors.size() == 0) return new Node(node.val);

        // 初始化原点
        queue.add(node);
        bfs();
        return newGraphs.get(node.val);
    }

    // 28ms faster than 59%
    private void bfs() {
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            visited.add(node.val);
            Node newNode = getNode(node);
            if (node.neighbors.size() > 0) {
                // 建关系
                int i ;
                List<Node> neighbors = node.neighbors;
                List<Node> newNeighbors = new ArrayList<>(neighbors.size());
                for (i = 0; i < neighbors.size(); i++) {
                    Node neighbor = neighbors.get(i);
                    newNeighbors.add(getNode(neighbor));
                    if (!visited.contains(neighbor.val)) {
                        queue.add(neighbors.get(i));
                    }
                }
                newNode.neighbors = newNeighbors;
            }
        }
    }

    private Node getNode(Node node) {
        Node newNode ;
        if (null == (newNode = newGraphs.get(node.val))) {
            newNode = new Node(node.val);
            newGraphs.put(newNode.val, newNode);
        }
        return newNode;
    }

    /*public Node cloneGraph(Node node) {
        *//*
            第二种思路，分步来，先克隆点再克隆边，时间复杂度一样。AC 28ms
            1.clone nodes
            2.clone edges
        *//*

        Map<Integer, Node> newGraphs = new HashMap<>(32);
        Map<Integer, Node> oldGraphs = new HashMap<>(32);

        cloneNodes(node, newGraphs, oldGraphs);

        cloneEdges(newGraphs, oldGraphs);

        return newGraphs.get(node.val);
    }*/

    private void cloneEdges(Map<Integer, Node> newGraphs, Map<Integer, Node> oldGraphs) {
        for (Map.Entry<Integer, Node> entry : newGraphs.entrySet()) {
            Node n = entry.getValue();
            Node old = oldGraphs.get(n.val);

            List<Node> neighbors = n.neighbors;
            List<Node> oldNeighbors = old.neighbors;
            for (int i = 0; i < oldNeighbors.size(); i++) {
                Node neighbor = newGraphs.get(oldNeighbors.get(i).val);
                neighbors.add(neighbor);
            }
        }
    }

    private void cloneNodes(Node root, Map<Integer, Node> newGraphs, Map<Integer, Node> oldGraphs) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node old = queue.poll();
            oldGraphs.put(old.val, old);

            getNewNode(old, newGraphs);
            List<Node> neighbors = old.neighbors;
            for (int i = 0; i < neighbors.size(); i++) {
                if (newGraphs.containsKey(neighbors.get(i).val)) continue;
                queue.add(neighbors.get(i));
            }
        }
    }

    private Node getNewNode(Node old, Map<Integer, Node> newGraphs) {
        Node n = newGraphs.get(old.val);
        if (null == n) {
            n = new Node(old.val);
            newGraphs.put(old.val, n);
        }
        return n;
    }

    static class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }
}
