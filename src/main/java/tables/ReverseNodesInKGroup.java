package tables;

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

    public ListNode reverseKGroup(ListNode head, int k) {
        if (null == head || null == head.next) return head;

        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        int c = 0;
        ListNode node = head;
        while (null != node) {
            c++;
            node = node.next;
        }

        ListNode mP = dummy, m = dummy.next;
        for (int i = 0; i < (c / k); i++) {
            ListNode[] a = reverse(m, k);
            // 新链表头
            mP.next = a[1];
            mP = m;
            // 最后一组直接将反转的局部链表尾部指向下一个起点
            if (i == (c/k)-1) {
                m.next = a[0];
            }
            else {
                // 下一个起点
                m = a[0];
            }
        }
        return dummy.next;
    }

    // 反转局部，k个一组，返回下一个起点和当前局部链表的头
    ListNode[] reverse(ListNode node, int k) {
        ListNode p = node, n = node.next, q ;
        p.next = null;
        for (int i = 1; i < k; i++) {
            q = null != n ? n.next : null;
            n.next = p;

            p = n;
            n = q;
        }
        return new ListNode[]{n, p};
    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
