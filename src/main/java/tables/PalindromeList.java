package tables;

/**
 * lc 234 easy
 *
 * Analysis:
 *  根据回文串比对特点，从中间断开链表，前半部分构造反转链表和后半部对比
 *
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2021-03-27 14:41
 */
public class PalindromeList {

    public boolean isPalindrome(ListNode head) {
        return halfReverse(head);
    }

    boolean halfReverse(ListNode head) {
        if (null == head) return false;
        if (null == head.next) return true;

        int c = 0;
        ListNode k = head;
        while (null != k) {
            c++;
            k = k.next;
        }

        ListNode p, n, q ;
        p = null;
        n = head;
        q = head.next;
        for (int i = 1; i <= (c/2); i++) {
            n.next = p;
            p = n;
            n = q;
            if (null != q) q = q.next;
        }

        ListNode l = p;
        ListNode r = n;
        if ((c&1) == 1) {
            r = n.next;
        }

        while (null != l) {
            if (l.val != r.val) return false;
            l = l.next;
            r = r.next;
        }
        return true;
    }

    // 时间O(n) 空间O(n)
    boolean copyAndReverse(ListNode head) {
        if (null == head) return false;
        if (null == head.next) return true;

        ListNode p = convert(head);
        ListNode q = head;
        while (null != q) {
            if (null == p || p.val != q.val) return false;
            p = p.next;
            q = q.next;
        }
        return true;
    }

    ListNode convert(ListNode node) {
        ListNode d = new ListNode(-1);

        if (null == node.next) return new ListNode(node.val);

        ListNode h = node;
        ListNode k = new ListNode(h.val);
        h = h.next;
        d.next = k;
        while (null != h) {
            ListNode g = new ListNode(h.val);
            k.next = g;
            k = g;
            h = h.next;
        }

        ListNode p, n, q;
        p = d.next; n = p.next; q = p.next.next;
        while (null != n) {
            n.next = p;
            p = n;
            n = q;
            if (null != q) q = q.next;
        }
        d.next.next=null;
        return p;
    }

    public class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
