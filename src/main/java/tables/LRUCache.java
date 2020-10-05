package tables;

import java.util.*;

/**
 * leetcode 146 medium
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

    private int size, capacity ;

    private DoublyNode dummy, tail ;

    private Map<Integer, DoublyNode> quick ;

    public LRUCache(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        dummy = new DoublyNode(-1, -1, null, null);
        tail = dummy;
        quick = new HashMap<>((capacity & 1) == 1 ? capacity + 1 : capacity);
    }

    // O(1)
    public int get(int key) {
        DoublyNode node = quick.get(key);

        if (null == node) return -1;

        // moved it to tail
        if (tail != node) {
            DoublyNode prev = node.prev;
            DoublyNode next = node.next;
            prev.next = next;
            next.prev = prev;

            tail.next = node;
            node.prev = tail;
            node.next = null;

            tail = node;
        }
        return node.val;
    }

    // O(1)
    public void put(int key, int value) {
        // get and if it is exists then was moved to tail
        DoublyNode node ;
        if (-1 != get(key)) {
            node = quick.get(key);
            node.val = value;
            return;
        }

        if (size + 1 > capacity) {
            node = dummy.next;
            dummy.next = node.next;
            if (null != node.next) {
                node.next.prev = dummy;
            }
            if (tail == node) {
                tail = dummy;
            }
            node.next = null;
            node.prev = null;
            quick.remove(node.key);
            size--;
        }

        // add a new node to tail
        node = new DoublyNode(key, value, null, null);
        tail.next = node;
        node.prev = tail;
        tail = node;
        quick.put(key, node);
        size++;
    }

    private class DoublyNode {
        int key;
        int val;
        DoublyNode prev;
        DoublyNode next;

        public DoublyNode(int key, int val, DoublyNode prev, DoublyNode next) {
            this.key = key;
            this.val = val;
            this.prev = prev;
            this.next = next;
        }
    }

}
