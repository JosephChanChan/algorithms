package tables;

/**
 * @author Joseph
 * @since 2020-03-20 22:51
 *
 * 2019年计算机考研中统考408科目其中一道大题
 *
 * 给定某个单链表 a1->a2->a3->...->an
 * 反转使得 a1->an->a2->an-1->a3->an-2->...
 * 要求：   时间复杂度O(n)，空间复杂度O(1)
 *
 * O(n^2)的算法是我写的，不是最优解，写了35min
 * O(n)的算法是最优解。
 *
 * 可见408科目还是挺难的
 */
public class RearrangeLinkedList {

    public static void main(String[] args) {
        Node one = new Node(1);
        Node two = new Node(2);
        Node three = new Node(3);
        Node flour = new Node(4);
        Node five = new Node(5);
        Node six = new Node(6);
        one.setNext(two);
        two.setNext(three);
        three.setNext(flour);
        flour.setNext(five);
        five.setNext(six);

        RearrangeLinkedList rearrangeLinkedList = new RearrangeLinkedList();
        /*rearrangeLinkedList.rearrange(one, six, 1, 6);*/
        rearrangeLinkedList.rearrange(one);
        rearrangeLinkedList.print(one);
    }

    private void print(Node head) {
        while (null != head) {
            System.out.println(head.data);
            head = head.next;
        }
    }

    /* 时间复杂度 O(n^2) */
    private void rearrange(Node head, Node tail, int left, int right) {
        if (head.data < tail.data) {
            Node temp = head.next;
            head.next = tail;
            tail.next = temp;
            head = temp;
            tail = foundTail(head, --right - ++left);
            rearrange(head, tail, left, right);
            if (head == tail)
                tail.next = null;
        }
    }
    private Node foundTail(Node head, int step) {
        Node tail = head;
        while (step > 0 && null != tail) {
            tail = tail.next;
            step--;
        }
        return tail;
    }

    /* 时间复杂度 O(n) */
    private void rearrange(Node node) {
        // 双指针，链表后半段逆转排列，再合并
        Node left = node;

        // 快慢指针
        Node slow = left, fast = slow.next;
        while (null != fast.next) {
            slow = slow.next;
            fast = fast.next;
            if (null != fast.next)
                fast = fast.next;
        }

        // 逆转链表后半段，主要就是双指针
        Node right = slow.next;
        slow.next = null;
        Node p = right, q = right.next;
        while (null != q) {
            Node n = q.next;
            q.next = p;
            p = q;
            q = n;
        }
        right.next = null;
        right = p;

        // 合并链表
        while (null != left && null != right) {
            Node nextL = left.next;
            Node nextR = right.next;
            left.next = right;
            right.next = nextL;
            left = nextL;
            right = nextR;
        }
    }


    private static class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
        }

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }
}
