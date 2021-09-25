package recursion;

/**
 * @author Joseph
 * @since 2019-09-23 20:44
 *
 * lc 21 easy
 *
 * Question Description:
 *  Merge two sorted linked lists and return it as a new list.
 *  The new list should be made by splicing together the nodes of the first two lists.
 * Example:
 * Input: 1->2->4, 1->3->4
 * Output: 1->1->2->3->4->4
 *
 * Analysis:
 *  最简单的使用双指针，分别遍历2个有序链表，依序将节点加入构成一个新链表返回即可。
 *  时间复杂度O(x+k) x，k分别是2个链表长度。
 *  递归解法也是类似的。
 */
public class MergeSortedList {

    public static void main(String[] args) {
        MergeSortedList list = new MergeSortedList();
        MergeSortedList.ListNode l1_1 = list.new ListNode(1);
        MergeSortedList.ListNode l1_2 = list.new ListNode(2);
        MergeSortedList.ListNode l1_3 = list.new ListNode(4);
        l1_1.next = l1_2;
        l1_2.next = l1_3;

        MergeSortedList.ListNode l2_1 = list.new ListNode(1);
        MergeSortedList.ListNode l2_2 = list.new ListNode(3);
        MergeSortedList.ListNode l2_3 = list.new ListNode(4);
        l2_1.next = l2_2;
        l2_2.next = l2_3;

        list.twoPointer(l1_1, l2_1);
    }

    // 递归解法
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (null == l1) {
            return l2;
        }
        if (null == l2) {
            return l1;
        }
        if (l1.val <= l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        }
        else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }

    // 双指针解法
    private ListNode twoPointer(ListNode l1, ListNode l2) {
        if (null == l1) {
            return l2;
        }
        if (null == l2) {
            return l1;
        }
        ListNode node, original ;
        if (l1.val <= l2.val) {
            node = l1;
            l1 = l1.next;
        }
        else {
            node = l2;
            l2 = l2.next;
        }
        original = node;
        while (null != l1 && null != l2) {
            if (l1.val <= l2.val) {
                ListNode next = l1.next;
                node.next = l1;
                l1.next = null;
                node = l1;
                l1 = next;
            }
            else {
                ListNode next = l2.next;
                node.next = l2;
                l2.next = null;
                node = l2;
                l2 = next;
            }
        }
        node.next = l1 == null ? l2 : l1;
        return original;
    }

    private class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
  }
}
