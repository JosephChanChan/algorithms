package tables;

/**
 * lc 148 medium
 *
 * Analysis：
 *  归并
 * 时间复杂度：O(2N*logN)
 * 空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2021-03-06 16:13
 */
public class SortLinkedList {

    public ListNode sortList(ListNode head) {
        if (null == head) return null;
        return mergeSort(head);
    }

    ListNode mergeSort(ListNode node) {
        int c = 0;
        ListNode p = node;
        while (null != p) {
            c++;
            p = p.next;
        }
        return dAndC(node, c);
    }

    ListNode dAndC(ListNode node, int c) {
        if (c == 1) return node;
        if (c == 2) {
            ListNode min = node.val <= node.next.val ? node : node.next;
            ListNode max = min == node ? node.next : node;
            min.next = max;
            max.next = null;
            return min;
        }
        int m = c>>1;
        ListNode p = node;
        for (int i = 0; i < m; i++) {
            p = p.next;
        }
        ListNode q = p.next;
        p.next = null;

        ListNode left = dAndC(node, m+1);
        ListNode right = dAndC(q, c-(m+1));
        return merge(left, right);
    }

    ListNode merge(ListNode p, ListNode q) {
        ListNode dummy = new ListNode();
        ListNode n = dummy;
        while (null != p && null != q) {
            if (p.val < q.val) {
                n.next = p;
                p = p.next;
            }
            else {
                n.next = q;
                q = q.next;
            }
            n = n.next;
        }
        if (null != p) n.next = p;
        if (null != q) n.next = q;
        return dummy.next;
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
