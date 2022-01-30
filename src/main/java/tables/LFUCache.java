package tables;

import java.util.*;

/**
 * lc 460 hard
 *
 * Analysis:
 * 总体思想用双哈希表。一个记录节点，一个记录频次链表。
 * 频次链表用自定义双向链表，可优化升频时原链表删除节点的时间。否则用LinkedList删除节点最坏会是O(n)
 *
 * 注意：
 * 1.node升频时，如果minFreqQueue空了注意更新minFreq
 * 2.双链删节点时，前后指针要更新。被删节点是tail更新tail
 * 3.双链加节点时，接上tail，更新tail
 *
 * 时间复杂度：get O(1) put O(1)
 * 空间复杂度：O(capacity)
 *
 * @author Joseph
 * @since 2021-04-28 15:48
 */
public class LFUCache {

    int minFreq, maxFreq, count, limit ;
    Map<Integer, Node> nodeMap = new HashMap();
    Map<Integer, DoublyList> freqMap = new HashMap();

    public LFUCache(int capacity) {
        this.count = 0;
        this.limit = capacity;
        this.minFreq = 1;
        this.maxFreq = 1;
    }

    public int get(int key) {
        if (limit == 0) return -1;

        Node n = getNode(key);
        if (null == n) return -1;
        return n.val;
    }

    public void put(int key, int value) {
        if (limit == 0) return;

        if (nodeMap.containsKey(key)) {
            Node node = getNode(key);
            node.val = value;
        }
        else {
            if (count == limit) evit();

            Node node = new Node(key, value, 1);
            minFreq = 1;

            nodeMap.put(key, node);
            DoublyList q = freqMap.getOrDefault(minFreq, new DoublyList());
            q.addLast(node);
            freqMap.put(minFreq, q);

            count++;
        }
    }

    Node getNode(int key) {
        Node n = nodeMap.get(key);
        if (null == n) return n;
        freqUp(n);
        return n;
    }

    void freqUp(Node node) {
        DoublyList curQ = freqMap.get(node.freq);
        curQ.remove(node);

        int freq = node.freq++;
        DoublyList nextQ = freqMap.getOrDefault(node.freq, new DoublyList());
        nextQ.addLast(node);
        freqMap.put(node.freq, nextQ);

        if (maxFreq < node.freq) {
            maxFreq = node.freq;
        }

        if (minFreq == freq && curQ.size == 0) {
            nextMinFreq();
        }
    }

    void evit() {
        DoublyList minQ = freqMap.get(minFreq);
        Node n = minQ.removeFirst();

        nodeMap.remove(n.key);

        if (minQ.size == 0) {
            nextMinFreq();
        }
        count--;
    }

    void nextMinFreq() {
        int i = minFreq+1;
        for ( ; i <= maxFreq; i++) {
            if (freqMap.containsKey(i) && freqMap.get(i).size > 0) {
                minFreq = i; break;
            }
        }
        // 没有节点了
        if (maxFreq < i) minFreq = 1;
    }

    class Node {
        int key;
        int val;
        int freq;

        Node prev;
        Node next;

        public Node(int k, int v, int f) {
            this.key = k;
            this.val = v;
            this.freq = f;
        }
    }

    class DoublyList {
        int size;
        Node dummy;
        Node head;
        Node tail;
        public DoublyList() {
            this.dummy = new Node(-1, -1, -1);
            dummy.next = head;
            this.size = 0;
        }
        public void addLast(Node node) {
            if (null == head) {
                head = node;
                dummy.next = head;
                head.prev = dummy;
                tail = head;
            }
            else {
                tail.next = node;
                node.prev = tail;
                tail = node;
            }
            size++;
        }
        public void remove(Node node) {
            Node p = node.prev;
            Node q = node.next;
            p.next = q;
            if (null != q) {
                q.prev = p;
            }
            node.prev = null;
            node.next = null;
            if (tail == node) tail = p;
            size--;
        }
        public Node removeFirst() {
            Node n = dummy.next;
            remove(n);
            return n;
        }
    }
}
