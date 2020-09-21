package tables;

/**
 * leetcode 25 hard
 *
 * Analysis:
 *  把思路理清晰点，分段反转，先统计总数，按k分组一共需要反转多少次。
 * 每次指针m移到第k组的起始位置，开始反转。每次反转完毕返回新链表的头和m-1接上。
 * 此时m已经是反转后的链表尾部，m-1移到m，m+1就是下一个m并开始新一轮k个反转。
 *
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2020-09-20 21:05
 */
public class ReverseNodesInKGroup {

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode mPrev = dummy;

        int count = 0;
        ListNode node = head, m ;
        while (null != node) {
            count++;
            node = node.next;
        }

        int times = count / k;
        for (int i = 0; i < times; i++) {
            m = mPrev.next;
            ListNode newHead = reverse(m, k);
            mPrev.next = newHead;
            mPrev = m;
        }
        return dummy.next;
    }

    private ListNode reverse(ListNode node, int k) {
        ListNode prev = node, cur = node.next, t = null;
        for (int i = 1; i < k; i++) {
            t = cur.next;
            cur.next = prev;
            prev = cur;
            cur = t;
        }
        if (node != prev) {
            node.next = t;
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
