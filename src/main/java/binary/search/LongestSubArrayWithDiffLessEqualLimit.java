package binary.search;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * lc 1438 medium
 *
 * Analysis:
 *  二分答案+滑动窗口+单调队列
 *
 * 首先看看二分。根据题意设满足条件的最长子数组长度为k，则所有子数组被划分为2种类型。
 * 1.满足条件的子数组长度<=k的那些子数组
 * 2.剩下的不满足条件的子数组
 * 可以通过猜一个长度k，然后校验是否有子数组满足limit的情况下长度<=k
 *
 * check函数，枚举长度k的窗口，校验窗口的最大差值。如果每次计算一遍窗口的最大和最小值要O(n)，
 * 维护最大堆、最小堆？是的，我是这么写但是第55/61 case就TLE了。
 * 看了题解用单调队列就过了。
 *
 * 为什么最大堆和最小堆TLE，单调队列可以？
 * 对比下2种数据结构：
 *              插入       检索max/min      删除
 * 堆：         logN          O(1)         logN
 * 单调队列：    均摊O(1)       O(1)         O(1)这里是删除max/min
 *
 * 在本题中滑动窗口要频繁插入、删除。对于单调队列每个元素只是入队、出队一次，N次插入每次时间均摊O(1)。
 * 对于堆N次插入，每次可能要logN调整。删除也是，综上堆在频繁插入和删除都比较慢，所以超时。
 *
 * 时间复杂度：O(N*logN)
 * 空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2021-09-05 23:01
 */
public class LongestSubArrayWithDiffLessEqualLimit {

    Deque<Integer> max ;
    Deque<Integer> min ;

    public int longestSubarray(int[] nums, int limit) {
        int[] a = nums;
        int n = a.length;

        int l = 0, r = n, m ;
        while (l + 1 < r) {
            m = (l + r) >> 1;
            if (check(m, limit, a)) {
                l = m;
            }
            else {
                r = m;
            }
        }
        if (check(r, limit, a)) return r;
        if (check(l, limit, a)) return l;
        return 0;
    }

    boolean check(int k, int limit, int[] a) {
        // 最大队列，队头是窗口内最大元素，单调减
        max = new ArrayDeque<>(k);
        // 最小队列，队头是窗口内最小元素，单调增
        min = new ArrayDeque<>(k);

        int i = 0, j = 0;
        while (j < a.length) {
            // 最大队列是单调减的，队头比新元素小就要弹出
            while (!max.isEmpty() && max.peekLast() < a[j]) max.pollLast();
            // 最小队列是单调增的，队头比新元素大就要弹出
            while (!min.isEmpty() && min.peekLast() > a[j]) min.pollLast();
            max.addLast(a[j]);
            min.addLast(a[j]);
            // 窗口成型了就计算并i右移
            if (j-i+1 == k) {
                if (max.peekFirst() - min.peekFirst() <= limit) {
                    return true;
                }
                if (a[i] == max.peekFirst()) {
                    max.pollFirst();
                }
                if (a[i] == min.peekFirst()) {
                    min.pollFirst();
                }
                i++;
            }
            // 每次j都要右移
            j++;
        }
        return false;
    }

}
