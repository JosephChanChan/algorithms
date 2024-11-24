package tables;

import tables.component.ListNode;

/**
 * lc 2074 medium
 *
 * Analysis:
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2022/9/20
 */
public class ReverseOddLengthList {

    /**
         k个反转的升级版，却只是中等难度... lc的题难度递增趋势
         正常做法是按照链表原地反转
         也可以先将链表转成数组，然后对数组按规则反转元素，此时数组就是反转好后的顺序，赋值回原链表就行
         这个方法机灵点在于少了链表原地反转的逻辑，可以专心处理按规则反转的逻辑
     */

    public ListNode reverseEvenLengthGroups(ListNode head) {
        int n = 0;
        ListNode node = head;
        while (null != node) {
            n++;
            node = node.next;
        }
        int done = 0, step = 1;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode pre = dummy;
        for ( ; done < n; ) {
            boolean canDo =
                    (step + done < n && (step & 1) == 0) ||
                            (step + done >= n && ((n - done) & 1) == 0);
            if (canDo) {
                ListNode[] headAndT = reverse(pre.next, step);
                pre.next = headAndT[0];
                pre = headAndT[1];
            }
            else {
                int copy = step;
                while (copy-- > 0 && null != pre) {
                    pre = pre.next;
                }
            }
            done += step;
            step++;
        }

        return dummy.next;
    }

    ListNode[] reverse(ListNode node, int step) {
        ListNode oldHead = node;
        ListNode p = node, c = p.next, q = null;
        if (null != c) {
            q = c.next;
        }
        while (step-1 > 0 && null != c) {
            c.next = p;
            p = c;
            c = q;
            if (null != q) {
                q = q.next;
            }
            step--;
        }
        oldHead.next = c;
        return new ListNode[]{p, oldHead};
    }
}
