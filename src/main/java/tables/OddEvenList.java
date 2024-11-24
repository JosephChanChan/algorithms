package tables;

/**
 * lc 328 medium
 *
 * Analysis:
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2022/12/30
 */
public class OddEvenList {

    public ListNode oddEvenList(ListNode head) {
        if (null == head) {
            return null;
        }
        ListNode odd = head, even = head.next, firstEven = null, lastOdd = odd;
        while (null != odd && null != even) {
            if (null == firstEven) {
                firstEven = even;
            }

            ListNode oddNext = even.next, evenNext = null;
            odd.next = oddNext;
            if (null != oddNext) {
                evenNext = oddNext.next;
            }
            even.next = evenNext;

            lastOdd = odd;
            odd = oddNext;
            even = evenNext;
            if (null != odd) {
                lastOdd = odd;
            }
        }
        lastOdd.next = firstEven;
        return head;
    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
