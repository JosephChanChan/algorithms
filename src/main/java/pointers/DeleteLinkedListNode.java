package pointers;

/**
 * 剑指Offer 18 easy
 *
 * Analysis:
 *  这道题在leetcode上被改过，原题是给定head和beDeleted要求O(1)时间删除。
 * 在leetcode上只给了head，只能O(n)删，没什么意思。
 *
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2020-11-14 16:14
 */
public class DeleteLinkedListNode {

    public ListNode deleteNode(ListNode head, int val) {
        /*
            p,q 双指针
        */
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode p = dummy, q = dummy.next;
        while (null != q) {
            if (q.val == val) {
                p.next = q.next;
                q.next = null;
                break;
            }
            p = q;
            q = q.next;
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
