package tables;

import java.util.*;

/**
 * leetcode 61 medium
 *
 * Analysis:
 *  一开始用暴力从头到尾将尾结点返回，头插到头部，一共旋转k次，显然时间为O(nk)，TLE。
 * 后来琢磨出了解法二：
 *  1.构造一条与原链表顺序反转的逆向链表，并把每个节点存储hash O(n)时间和O(n)空间
 *  2.将逆向链表头尾相连
 *  3.tail.next就是原链表的tail-1，通过hash拿到 O(1)
 *  4.原链表tail-1 unlink tail，返回tail头插 O(1)
 *
 * 解法一慢在每次要从头遍历到尾，返回尾结点头插，这样要花O(n)时间，所以解法二妙在每次用O(1)时间拿到尾结点进行头插。
 * 时间从O(nk)优化到O(k)，这下没问题了吧？
 * 还是TLE，一看数据k居然 2000000000，我去... 猜测是考规律的，想了想，旋转n次相当于还是原链表，那旋转k次实际只有k % n次
 * 需要反转尾结点的。k%n的结果最大就是n-1，所以按解法一 实际优化到了O(n)，解法二还是暴力每次从头到尾，O(n^2)
 * 结果解法二反而比较慢，2ms。典型的数据量n应该太小了，中间要new Key很耗时间。
 *
 * @author Joseph
 * @since 2020-09-20 22:33
 */
public class RotateList {

    /* AC 1ms faster than 43% */
    public ListNode rotateRight1(ListNode head, int k) {
        if (null == head) return head;
        if (null == head.next) return head;

        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode node = head;
        int count = 0;
        while (null != node) {
            count++;
            node = node.next;
        }

        if (k == count) return head;
        if (k > count) k = k % count;

        for (int i = 0; i < k; i++) {
            ListNode tail = rotate(dummy.next);
            head = dummy.next;
            tail.next = head;
            dummy.next = tail;
        }
        return dummy.next;
    }

    private ListNode rotate(ListNode head) {
        if (null == head) return null;

        ListNode p = head, cur = p.next;
        if (null == cur) {
            return p;
        }
        while (null != cur.next) {
            p = cur;
            cur = cur.next;
        }
        p.next = null;
        return cur;
    }

    /* AC 2ms faster than 13% */
    public ListNode rotateRight2(ListNode head, int k) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        if (null == head) return null;
        if (null == head.next) return head;

        Map<Key, ListNode> map = new HashMap<>(32);
        ListNode[] reversed = constructReversedList(head, map);
        ListNode left = reversed[0], right = reversed[1];

        int size = map.size();
        if (k == size) return dummy.next;
        if (k > size) k = k % size;

        ListNode node = left;
        for (int i = 0; i < k; i++) {
            ListNode next = node.next;

            ListNode prev = map.get(new Key(next.val, next));
            ListNode cur = map.get(new Key(node.val, node));
            prev.next = null;
            cur.next = dummy.next;
            dummy.next = cur;

            node = next;
        }
        return dummy.next;
    }

    private ListNode[] constructReversedList(ListNode head, Map<Key, ListNode> map) {
        ListNode[] ans = new ListNode[2];
        ListNode left = null, right = null;
        ListNode cur = head;
        while (null != cur) {
            ListNode p = new ListNode(cur.val);
            map.put(new Key(p.val, p), cur);

            if (null == right) right = p;
            p.next = left;
            left = p;
            cur = cur.next;
        }
        ans[0] = left;
        ans[1] = right;
        right.next = left;
        return ans;
    }

    class Key {
        int val;
        ListNode self;
        public Key(int val, ListNode node) {
            this.val = val;
            this.self = node;
        }

        public int hashCode() {
            return val * 31;
        }

        public boolean equals(Object obj) {
            Key o = (Key) obj;
            if (o.val != this.val) return false;
            if (o.self != this.self) return false;
            return true;
        }
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

}
