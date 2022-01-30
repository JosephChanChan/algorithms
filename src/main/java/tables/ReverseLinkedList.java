package tables;

/**
 * 剑指Offer 24 & lc 206 easy
 *
 * Analysis:
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2020-09-19 23:28
 */
public class ReverseLinkedList {

    public ListNode reverseList(ListNode head) {
        ListNode prev, cur, t ;
        prev = null;
        cur = head;
        while (null != cur) {
            t = cur.next;
            cur.next = prev;
            prev = cur;
            cur = t;
        }
        return prev;
    }

    // 用Dummy辅助节点
    public ListNode reverseList2(ListNode head) {
        ListNode dummy = new ListNode(-1);

        dummy.next = head;
        ListNode p = dummy, q = dummy.next;

        while (null != q) {
            ListNode t = q.next;
            q.next = p;
            p = q;
            q = t;
        }
        if (null != dummy.next) {
            dummy.next.next = q;
            dummy.next = p;
        }
        return dummy.next;
    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
