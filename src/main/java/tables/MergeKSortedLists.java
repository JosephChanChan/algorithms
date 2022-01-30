package tables;

import java.util.*;

/**
 * lc 23 hard
 *
 * Analysis:
 *  method1用min-heap排序k个链表中的元素，再输出构造新链表
 *
 * 时间复杂度：O(nlogk)
 * 空间复杂度：O(k)
 *
 *  method2，分治法，每次对半切分数组，合并l~r的链表，每个递归返回已排序的新链表。
 * 时间复杂度计算，有k个链表每次对半分，分到最小粒度1停止，递归树深度为logk，每层递归要合并当层所有链表，每层自然有n个元素要处理，
 * 所以自然是递归深度*每层递归的处理时间。
 * 空间计算，递归中new了dummy node，每次合并都要new，最深层要合并k/2个链表所以要new k/2个dummy node
 *
 * 时间复杂度：O(nlogk)
 * 空间复杂度：O(k)
 *
 * @author Joseph
 * @since 2020-10-05 13:16
 */
public class MergeKSortedLists {

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) return null;

        return method1(lists);
    }

    private ListNode method1(ListNode[] lists) {
        ListNode dummy = new ListNode(-1);

        int k = lists.length;
        Queue<ListNode> q = new PriorityQueue<>(k, (o1, o2) -> o1.val - o2.val);
        for (int i = 0; i < k; i++) {
            if (null != lists[i]) {
                q.add(lists[i]);
            }
        }

        ListNode cur = dummy;
        while (!q.isEmpty()) {
            ListNode n = q.poll();
            if (null != n.next) {
                q.add(n.next);
            }
            cur.next = n;
            cur = n;
        }
        return dummy.next;
    }

    private ListNode method2(ListNode[] lists) {
        // divide and conquer
        return divideConqure(lists, 0, lists.length-1);
    }

    private ListNode divideConqure(ListNode[] lists, int l, int r) {
        if (l == r) return lists[l];

        // divide
        int m = (l + r) >> 1;
        ListNode leftList = divideConqure(lists, l, m);
        ListNode rightList = divideConqure(lists, m+1, r);

        // conquer
        ListNode p = leftList, q = rightList ;
        if (null == p || null == q) return null == p ? q : p;

        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        while (null != p && null != q) {
            if (p.val > q.val) {
                cur.next = q;
                q = q.next;
            }
            else {
                cur.next = p;
                p = p.next;
            }
            cur = cur.next;
        }
        cur.next = null == p ? q : p;
        return dummy.next;
    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

}
