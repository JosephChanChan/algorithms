package pointers;

/**
 * lc 142 medium
 *
 * Analysis:
 *  s,f快慢指针，s走一步，f走两步，如sf相遇代表有环，否则f遇到null代表无环。
 *  相遇后s回到头，f在相遇点每次走一步，下次相遇时就在环处。
 *  如果要返回index，记录下相遇的node，s拉到头开始计数到Node返回。f从node开始计数到尾+s的计数可得链表长度。
 *
 *  为什么s回到头，两个指针再一起走一步下次就能相遇在环处？
 *  这个算法是floyd快慢指针算法。
 *
 *  时间复杂度：O(n)
 *  空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2020-10-01 21:25
 */
public class LinkedListCycle2 {

    public ListNode detectCycle(ListNode head) {
        if (null == head) return null;
        if (null == head.next) return null;

        boolean cycle = false;
        ListNode s = head, f = head;
        while (null != f) {
            s = s.next;
            f = f.next;
            if (null != f) f = f.next;
            if (s == f) {
                cycle = true;
                break;
            }
        }

        if (!cycle) return null;

        s = head;
        while (s != f && f != head) {
            s = s.next;
            f = f.next;
        }
        return s;
    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
