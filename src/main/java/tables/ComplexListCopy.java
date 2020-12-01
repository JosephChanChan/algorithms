package tables;

/**
 * 剑指Offer 35 medium
 *
 * Analysis:
 *
 *  解法一：常规思路，先建一条copyList，然后遍历原链表，给copyList建random指针。
 *  找每一个节点的random都可能要花O(n)时间，所以
 *  时间复杂度：O(n^2)
 *  空间复杂度：O(1)
 *
 *  解法二：标准题解，35min AC 需要再刷
 *  1.构建一条原链表和copyList交织的链表。形如：1->1'->2->2'->3->3'->N
 *  2.遍历交织链表建random节点。3.分开交织链表。
 *  时间复杂度：O(n)
 *  空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2020-11-29 16:30
 */
public class ComplexListCopy {

    public Node copyRandomList(Node head) {
        if (null == head) return null;
        buildCopyList(head);
        buildRandom(head);
        return divide(head);
    }

    private void buildCopyList(Node node) {
        Node p, q, t ;
        p = node;
        do {
            t = p.next;
            q = new Node(p.val);
            p.next = q;
            q.next = t;
            p = t;
        }
        while (null != p);
    }

    private void buildRandom(Node node) {
        Node p, q ;
        p = node;
        q = node.next;
        while (true) {
            Node random = p.random;
            if (null == random) {
                q.random = null;
            }
            else {
                q.random = random.next;
            }
            p = q.next;
            if (null == p) return;
            q = p.next;
        }
    }

    private Node divide(Node node) {
        Node copy = node.next;
        Node p = node, q = p.next;

        do {
            p.next = q.next;
            p = q;
            q = q.next;
        }
        while (null != q);
        return copy;
    }

    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
}
