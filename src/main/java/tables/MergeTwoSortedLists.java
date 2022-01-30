package tables;

/**
 * 剑指Offer 25 easy & lc 21 easy
 *
 * Analysis:
 *  排队法，两个链表节点互相比较，小的在前。
 *
 * 时间复杂度：O(n+m)
 * 空间复杂度：O(1) 不算最后构造的输出链表
 *
 * @author Joseph
 * @since 2020-09-19 23:09
 */
public class MergeTwoSortedLists {

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode h1 = l1;
        ListNode h2 = l2;
        ListNode tail = dummy;

        ListNode min ;
        while (null != h1 && null != h2) {
            if (h1.val > h2.val) {
                min = h2;
                h2 = h2.next;
            }
            else {
                min = h1;
                h1 = h1.next;
            }
            tail.next = min;
            tail = min;
        }
        if (null != h1) {
            tail.next = h1;
        }
        if (null != h2) {
            tail.next = h2;
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
