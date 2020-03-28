package tables;

import tables.component.Node;
import tables.component.OneLinkedNode;

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
        Node<Integer> one = new OneLinkedNode<>(1);
        Node<Integer> two = new OneLinkedNode<>(2);
        Node<Integer> three = new OneLinkedNode<>(3);
        Node<Integer> flour = new OneLinkedNode<>(4);
        Node<Integer> five = new OneLinkedNode<>(5);
        Node<Integer> six = new OneLinkedNode<>(6);
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

    private void print(Node<Integer> head) {
        while (null != head) {
            System.out.println(head.getData());
            head = head.getNext();
        }
    }

    /* 时间复杂度 O(n^2) */
    private void rearrange(Node<Integer> head, Node<Integer> tail, int left, int right) {
        if (head.getData() < tail.getData()) {
            Node<Integer> temp = head.getNext();
            head.setNext(tail);
            tail.setNext(temp);
            head = temp;
            tail = foundTail(head, --right - ++left);
            rearrange(head, tail, left, right);
            if (head == tail)
                tail.setNext(null);
        }
    }
    private Node<Integer> foundTail(Node<Integer> head, int step) {
        Node<Integer> tail = head;
        while (step > 0 && null != tail) {
            tail = tail.getNext();
            step--;
        }
        return tail;
    }

    /* 时间复杂度 O(n) */
    private void rearrange(Node<Integer> node) {
        // 双指针，链表后半段逆转排列，再合并
        Node<Integer> left = node;

        // 快慢指针
        Node<Integer> slow = left, fast = slow.getNext();
        while (null != fast.getNext()) {
            slow = slow.getNext();
            fast = fast.getNext();
            if (null != fast.getNext())
                fast = fast.getNext();
        }

        // 逆转链表后半段，主要就是双指针
        Node<Integer> right = slow.getNext();
        slow.setNext(null);
        Node<Integer> p = right, q = right.getNext();
        while (null != q) {
            Node<Integer> n = q.getNext();
            q.setNext(p);
            p = q;
            q = n;
        }
        right.setNext(null);
        right = p;

        // 合并链表
        while (null != left && null != right) {
            Node<Integer> nextL = left.getNext();
            Node<Integer> nextR = right.getNext();
            left.setNext(right);
            right.setNext(nextL);
            left = nextL;
            right = nextR;
        }
    }
    
}
