package tables;


import tables.component.ListNode;

/**
 * lc 25 hard
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

    int count = 0;

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode node = head;
        while (null != node) {
            count++;
            node = node.next;
        }

        int t = count / k;
        node = dummy.next;
        ListNode prev = dummy;
        for (int i = 0; i < t; i++) {
            // 返回翻转后的链表新的头和旧的头
            ListNode[] a = reverse(node, k);
            prev.next = a[0];
            prev = a[1];
            node = a[1].next;
        }
        return dummy.next;
    }

    ListNode[] reverse(ListNode node, int k) {
        ListNode org = node;
        ListNode p = node;
        ListNode c = node.next;
        ListNode q = null;
        if (null != c) {
            q = c.next;
        }

        for (int i = 1; i < k; i++) {
            c.next = p;
            p = c;
            c = q;
            if (null != q) {
                q = q.next;
            }
        }
        org.next = c;
        return new ListNode[]{p, org};
    }
}
