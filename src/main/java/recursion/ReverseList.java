package recursion;

import tables.component.ListNode;
import tables.component.OneLinkedNode;

/**
 * @author Joseph
 * @since 2019-09-17 20:18
 *
 * lc 206 easy
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
        ListNode one = new OneLinkedNode(1);
        ListNode two = new OneLinkedNode(2);
        ListNode three = new OneLinkedNode(3);
        ListNode four = new OneLinkedNode(4);
        ListNode five = new OneLinkedNode(5);
        one.setNext(two);
        two.setNext(three);
        three.setNext(four);
        four.setNext(five);
        five.setNext(null);

        ListNode listNode = reverseList.reverseList(one);
        while (null != listNode.getNext()) {
            System.out.println(listNode.getData());
            listNode = listNode.getNext();
        }
        System.out.println(listNode.getData());
    }

    public ListNode reverseList(ListNode head) {
        if (null == head) {
            return null;
        }
        ListNode original = head;
        while (null != original.getNext()) {
            original = original.getNext();
        }
        reverseNode(head, null);
        return original;
    }

    private void reverseNode(ListNode currentNode, ListNode formerNode) {
        if (null != currentNode.getNext()) {
            reverseNode(currentNode.getNext(), currentNode);
        }
        currentNode.setNext(formerNode);
    }

}
