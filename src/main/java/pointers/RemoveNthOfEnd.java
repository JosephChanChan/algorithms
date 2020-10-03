package pointers;

/**
 * leetcode 19 medium
 *
 * Analysis:
 *  三指针，其中快慢指针，快指针先走k步，之后快慢一起走，直到快指针到尾。
 * 如果链表长度为n，此时慢指针差n-k步到尾，就是题目要删的节点。
 *
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2020-10-01 00:07
 */
public class RemoveNthOfEnd {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        if (null == head) return null;
        if (null == head.next && n == 1) return null;

        ListNode s = dummy.next, f = dummy, p = dummy;
        // f先走n步，之后s和f再同时走，直到f到尾
        for (int i = 0; i < n; i++) {
            f = f.next;
        }

        while (null != f.next) {
            f = f.next;
            s = s.next;
            p = p.next;
        }

        p.next = s.next;
        s.next = null;

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
