package recursion;

import tables.component.Node;
import tables.component.OneLinkedNode;

/**
 * @author Joseph
 * @since 2019-09-17 20:18
 *
 * leetcode 206 easy
 *
 * Question Description:
 *  翻转单方向的链表，要递归实现。
 *
 * Analysis:
 *  时间复杂度：O(n)
 *  空间复杂度：O(1)
 *
 */
public class ReverseList {

    public static void main(String[] args) {
        ReverseList reverseList = new ReverseList();
        Node<Integer> one = new OneLinkedNode<>(1);
        Node<Integer> two = new OneLinkedNode<>(2);
        Node<Integer> three = new OneLinkedNode<>(3);
        Node<Integer> four = new OneLinkedNode<>(4);
        Node<Integer> five = new OneLinkedNode<>(5);
        one.setNext(two);
        two.setNext(three);
        three.setNext(four);
        four.setNext(five);
        five.setNext(null);

        Node<Integer> listNode = reverseList.reverseList(one);
        while (null != listNode.getNext()) {
            System.out.println(listNode.getData());
            listNode = listNode.getNext();
        }
        System.out.println(listNode.getData());
    }

    public Node<Integer> reverseList(Node<Integer> head) {
        if (null == head) {
            return null;
        }
        Node<Integer> original = head;
        while (null != original.getNext()) {
            original = original.getNext();
        }
        reverseNode(head, null);
        return original;
    }

    private void reverseNode(Node<Integer> currentNode, Node<Integer> formerNode) {
        if (null != currentNode.getNext()) {
            reverseNode(currentNode.getNext(), currentNode);
        }
        currentNode.setNext(formerNode);
    }

}
