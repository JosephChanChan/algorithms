package tables;

/**
 * leetcode 206 easy
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

    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
