package tables;

/**
 * @author Joseph
 * @since 2020-03-28 14:17
 *
 * leetcode 141 easy
 *
 * Question Description:
 *  输入一个链表，判断链表是否有环。因为题目很简单这里就不放简介了，可以去官网看。
 *
 * Analysis:
 *  抓住一点，只要链表有环，遍历就不可能结束，并且快指针一定会在某次循环中追上慢指针。
 *  时间复杂度：O(n)
 *  空间复杂度：O(1)
 */
public class CircularLinkedList {


    public boolean hasCycle(ListNode head) {
        if (null == head ||
            null == head.next)
            return false;

        ListNode slow = head, fast = head;
        while (null != fast.next && null != fast.next.next) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) return true;
        }
        return false;
    }


    class ListNode {
      int val;
      ListNode next;
      ListNode(int x) {
          val = x;
          next = null;
      }
    }

}
