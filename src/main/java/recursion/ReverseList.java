package recursion;

/**
 * @author Joseph
 * @since 2019-09-17 20:18
 *
 * Question Description:
 *  翻转单方向的链表，要递归实现。
 *  leetcode 206 easy
 *
 * Analysis:
 *  时间复杂度：O(n)
 *  空间复杂度：O(1)
 *
 */
public class ReverseList {

    public static void main(String[] args) {
        ReverseList reverseList = new ReverseList();
        ReverseList.ListNode one = reverseList.new ListNode(1);
        ReverseList.ListNode two = reverseList.new ListNode(2);
        ReverseList.ListNode three = reverseList.new ListNode(3);
        ReverseList.ListNode four = reverseList.new ListNode(4);
        ReverseList.ListNode five = reverseList.new ListNode(5);
        one.next = two;
        two.next = three;
        three.next = four;
        four.next = five;
        five.next = null;
        ListNode listNode = reverseList.reverseList(one);
        while (null != listNode.next) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }

    public ListNode reverseList(ListNode head) {
        if (null == head) {
            return null;
        }
        ListNode original = head;
        while (null != original.next) {
            original = original.next;
        }
        reverseNode(head, null);
        return original;
    }

    private void reverseNode(ListNode currentNode, ListNode formerNode) {
        if (null != currentNode.next) {
            reverseNode(currentNode.next, currentNode);
        }
        currentNode.next = formerNode;
    }

    private class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
  }
}
