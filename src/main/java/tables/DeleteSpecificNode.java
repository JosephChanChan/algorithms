package tables;


/**
 * lc 237 medium
 *
 * Analysis:
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2022/12/29
 */
public class DeleteSpecificNode {

    /*
        无法访问node之前的节点就暗示着无法修改node之前的节点指针指向下一个。
        需要思考其它办法，结合所有node.val是唯一的，容易想到将node.next后面的值覆盖前面的节点，删除最后一个节点
     */

    public void deleteNode(ListNode node) {
        if (null == node) {
            return;
        }
        // 题目保证node非尾结点
        ListNode p = node, q = node.next;
        while (null != p) {
            p.val = q.val;
            if (null == q.next) {
                p.next = null;
                break;
            }
            p = q;
            q = q.next;
        }
    }

    // 时间O(1)
    public void deleteNode2(ListNode node) {
        if (null == node) {
            return;
        }
        // 题目保证node非尾结点
        ListNode p = node, q = node.next;
        // 还可以进一步优化，node.next覆盖node，然后把node.next删除
        p.val = q.val;
        p.next = q.next;
        q.next = null;
    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
