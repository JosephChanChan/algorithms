package tables;

/**
 * lc 92 medium
 *
 * Analysis:
 *  区间链表反转，主要还是三个指针反转，多加些边界判断。第一步记录下m-1的节点，它要和反转后的新链表头连接。
 * 第二步对m~n反转，反转后注意对原来m节点指向n+1的处理，所以用origin记录原来的m节点。
 *
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2020-09-19 23:33
 */
public class ReverseLinkedList2 {

    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode mPrev = dummy;
        for (int i = 1; i < m; i++) {
            mPrev = mPrev.next;
        }

        ListNode reversedHead = reverse(mPrev.next, m, n);
        mPrev.next = reversedHead;
        return dummy.next;
    }

    private ListNode reverse(ListNode node, int m, int n) {
        ListNode prev = node, cur = node.next, t = null;
        for (int i = m; i < n; i++) {
            t = cur.next;
            cur.next = prev;
            prev = cur;
            cur = t;
        }
        // 如果没有发生链表反转，就没必要调整，因为n后可能还有元素
        if (prev != node) node.next = t;
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
