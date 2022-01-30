package tables;

/**
 * lc 143 medium
 *
 * Analysis:
 * 其实这个算法是在某年考研408大题里看到过，没想到考研大题和lc还有联系，是lc抄考研还是考研题目抄lc？
 *
 *  1.快慢指针找到链表中点，断开
 *  2.反转链表后半段
 *  3.遍历逆向链表插入前半段链表，依次 1->n->2->n-1...(2/n)-1->2/n
 *
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2020-09-21 23:24
 */
public class ReorderList {

    public void reorderList(ListNode head) {
        if (null == head || null == head.next || null == head.next.next) return;

        ListNode slow = head, fast = head;
        while (null != fast.next) {
            slow = slow.next;
            fast = fast.next;
            if (null != fast.next) fast = fast.next;
        }

        ListNode latter = slow.next;
        slow.next = null;
        latter = reverse(latter);

        while (null != latter) {
            ListNode kit = latter.next;
            latter.next = head.next;
            head.next = latter;
            head = latter.next;
            latter = kit;
        }
    }

    private ListNode reverse(ListNode head) {
        if (null == head.next) return head;

        ListNode prev = head, cur = head.next, t = null;
        while (null != cur) {
            t = cur.next;
            cur.next = prev;
            prev = cur;
            cur = t;
        }
        if (prev != head) {
            head.next = t;
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
