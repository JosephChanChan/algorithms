package tables;

/**
 * leetcode 86 medium
 *
 * Analysis:
 * 思路没什么难的，自然地想到选出所有小于x的节点，从原链表中删除，构造出一个新链表。
 * 最后合并新旧链表返回。
 *
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2020-10-01 23:43
 */
public class PartitionList {

    ListNode q = null, newHead = null;

    public ListNode partition(ListNode head, int x) {
        if (null == head) return null;

        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        /*
            遍历链表将小于x的节点插入q链表中，
            q链表中的节点位置还是相对于原链表中的位置没变
        */
        ListNode p = dummy, c = p.next;
        while (null != c) {
            if (c.val < x) {
                concatQ(c);
                p.next = c.next;
                c.next = null;
                c = p.next;
            }
            else {
                p = c;
                c = p.next;
            }
        }

        // null还是q代表链表所有元素都大于x，直接返回原链表就行
        if (null == q) return dummy.next;

        q.next = dummy.next;
        return newHead;
    }

    private void concatQ(ListNode node) {
        if (null == q) {
            q = node;
            newHead = q;
        }
        else {
            q.next = node;
            q = node;
        }
    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
