package tables;

/**
 * lc 138 medium
 *
 * Analysis:
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2022/12/28
 */
public class CopyListWithRandomPointer {

    public Node copyRandomList(Node head) {
        /*
            1.遍历旧链表创建新链表，A->A`->B->B`
            2.依次更新新节点的random
            3.拆分链表
         */
        if (null == head) {
            return null;
        }
        Node cur = head, next, newNode, newHead = null;
        while (null != cur) {
            next = cur.next;
            newNode = new Node(cur.val);
            cur.next = newNode;
            newNode.next = next;
            cur = next;
            if (null == newHead) {
                newHead = newNode;
            }
        }
        cur = head;
        while (null != cur) {
            newNode = cur.next;
            Node random = cur.random;
            if (null != random) {
                newNode.random = random.next;
            }
            cur = newNode.next;
        }
        cur = head;
        while (null != cur) {
            newNode = cur.next;
            next = newNode.next;
            cur.next = next;
            if (null != next) {
                newNode.next = next.next;
            }
            cur = next;
        }
        return newHead;
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
