package tables;

/**
 * 剑指Offer 6 easy
 *
 * Analysis:
 *  先反转链表顺便计数，开数组，遍历链表
 *
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2020-10-08 16:42
 */
public class ReversePrintList {

    public int[] reversePrint(ListNode head) {
        return reverseList(head);
    }

    private int[] reverseList(ListNode node) {
        if (null == node) return new int[0];

        int[] ans ;
        int count = 0;
        ListNode dummy = new ListNode(-1);
        dummy.next = node;

        ListNode p = dummy, c = node, n ;
        while (null != c) {
            n = c.next;
            c.next = p;
            p = c;
            c = n;
            count++;
        }
        dummy.next.next = null;
        dummy.next = p;

        ans = new int[count];
        c = dummy.next;
        for (int i = 0; i < count; i++) {
            ans[i] = c.val;
            c = c.next;
        }
        return ans;
    }

    public class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
    }
}
