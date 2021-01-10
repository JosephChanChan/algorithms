package tables;

/**
 * 剑指Offer 52 easy
 *
 * Analysis:
 *  当链表有交叉，一定会在某个节点相遇。
 *  如果p!=q，该移动哪个？如果移动较短的链表，则有可能会错过交叉点。因为较短链表的指针先到交叉点。
 *  只有移动较长的指针，把长度差距缩平了，再同时移动节点就可以了。
 *
 * 时间复杂度：O(n+m)
 * 空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2021-01-10 20:54
 */
public class CommonNodeOfLists {

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (null == headA || null == headB) return null;
        if (headA == headB) return headA;

        int diff, l, r ;
        l = length(headA);
        r = length(headB);
        diff = Math.max(l, r) - Math.min(l, r);

        ListNode p = headA, q = headB;
        while (null != p && null != q) {
            if (p == q) return q;
            if (diff-- > 0) {
                if (l > r) p = p.next;
                else q = q.next;
            }
            else {
                p = p.next;
                q = q.next;
            }
        }
        return null;
    }

    private int length(ListNode node) {
        int l = 0;
        while (null != node) {
            l++;
            node = node.next;
        }
        return l;
    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
