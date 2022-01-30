package tables;

import java.util.*;

/**
 * lc 146 medium
 *
 * Analysis:
 *  实际上就是考LRU的实现原理和链表知识。知道hash+list可以实现LRU，并且在30min内能写出来就行。
 *
 * 时间复杂度：get(key), put(key, value) O(1)
 * 空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2020-10-04 22:31
 */
public class LRUCache {

    int count, capacity;
    Map<Integer, Node> map = new HashMap<>();
    DoublyList que = new DoublyList();

    public LRUCache(int capacity) {
        this.count = 0;
        this.capacity = capacity;
    }

    public int get(int key) {
        if (count == 0) return -1;
        Node node = getNode(key);
        if (null == node) return -1;
        return node.val;
    }

    public void put(int key, int value) {
        Node node = getNode(key);
        if (null != node) {
            node.val = value;
            return;
        }
        if (count == capacity) {
            evict();
        }
        node = new Node(key, value);
        map.put(key, node);
        que.addLast(node);
        count++;
    }

    void evict() {
        Node old = que.eldest();
        map.remove(old.key);
        que.remove(old);
        count--;
    }

    Node getNode(int key) {
        // 让entry移到队尾
        Node node = map.get(key);
        if (null != node) {
            que.remove(node);
            que.addLast(node);
        }
        return node;
    }

    class Node {
        int key;
        int val;
        Node prev;
        Node next;
        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    class DoublyList {
        Node head = new Node(-1, -1);
        Node tail = head;

        public Node eldest() {
            return head.next;
        }
        public void addLast(Node node) {
            tail.next = node;
            node.prev = tail;
            tail = node;
        }
        public void remove(Node node) {
            Node p = node.prev;
            Node q = node.next;
            p.next = q;
            if (null != q) q.prev = p;
            node.prev = null;
            node.next = null;
            if (tail == node) {
                tail = p;
            }
        }
    }

}
