package simulation.algorithm;

/**
 * lc 2 medium
 *
 * Analysis:
 *  正整数大数相加，最简单的大数加法了，模拟法。
 *
 *  时间复杂度：O(max{length(l1), length(l2)})
 *  空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2020-12-27 15:27
 */
public class BigPositiveNumAdd {

    boolean overflow = false;

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (null == l1) return l2;
        if (null == l2) return l1;

        ListNode dummy = new ListNode(-1);
        ListNode p = l1, q = l2, tail = dummy, cur ;

        while (null != p || null != q) {
            int ans ;
            if (null != p && null != q) {
                ans = p.val + q.val;
            }
            else {
                ans = null == p ? q.val : p.val;
            }
            if (overflow) {
                ans += 1;
                overflow = false;
            }
            if (ans >= 10) {
                overflow = true;
                ans = ans % 10;
            }
            cur = new ListNode(ans);
            tail.next = cur;
            tail = cur;
            if (null != p) p = p.next;
            if (null != q) q = q.next;
        }
        if (overflow) {
            tail.next = new ListNode(1);
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
