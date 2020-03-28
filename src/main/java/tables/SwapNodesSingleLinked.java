package tables;

import tables.component.Node;
import tables.component.OneLinkedNode;

/**
 * @author Joseph
 * @since 2019/8/31 21:03
 *
 * leetcode 24 Medium
 *
 * Question Description:
 *  Given a linked list, swap every two adjacent nodes and return its head.
 * You may not modify the values in the list's nodes, only nodes itself may be changed.
 * Example:
 * Given 1->2->3->4, you should return the list as 2->1->4->3.
 *
 * Analysis:
 *  将单链表分解成一对节点、一对节点的处理。
 *  递归函数只处理一对的节点，将下一对继续递归。
 *  值得注意的是，每一次递归回来后，要在本层递归处理的一对节点中的尾节点接上下一层递归返回来的新节点，
 *  否则本层递归的尾节点的下一节点指针还是老的节点。
 *
 *  时间复杂度：O(n/2)
 *  空间复杂度：O(1)
 */
public class SwapNodesSingleLinked {

    public static void main(String[] args) {
        Node<Integer> node1 = new OneLinkedNode<>(1);
        Node<Integer> node2 = new OneLinkedNode<>(2);
        Node<Integer> node3 = new OneLinkedNode<>(3);
        Node<Integer> node4 = new OneLinkedNode<>(4);
        Node<Integer> node5 = new OneLinkedNode<>(5);
        node1.setNext(node2);
        node2.setNext(node3);
        node3.setNext(node4);
        node4.setNext(node5);
        node5.setNext(null);

        SwapNodesSingleLinked swapNodesSingleLinked = new SwapNodesSingleLinked();
        Node<Integer> node = swapNodesSingleLinked.swapPairs(node1);

        while (null != node) {
            System.out.print(node.getData()+" ");
            node = node.getNext();
        }
    }

    private Node<Integer> swapPairs(Node<Integer> head) {
        return recursiveSwap(head);
    }

    private Node<Integer> recursiveSwap(Node<Integer> node) {
        if (null == node) {
            return node;
        }
        Node<Integer> next = node.getNext();
        if (null == next) {
            return node;
        }
        Node<Integer> nextPair = next.getNext();
        next.setNext(node);
        node.setNext(recursiveSwap(nextPair));
        return next;
    }

    
}
