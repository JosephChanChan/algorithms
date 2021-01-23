package tables;

import java.util.*;

/**
 * 剑指Offer 59 II medium
 *
 * Analysis:
 * 时间复杂度：pop, push, max 均摊时间 O(1)
 * 空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2021-01-18 16:49
 */
public class MaxNumInQueue {

    // a[0]是元素值，a[1]该值出现次数
    Deque<Integer[]> maxQ = new ArrayDeque<>();
    // 双端队列本身
    Deque<Integer> que = new ArrayDeque();

    public int max_value() {
        if (maxQ.isEmpty()) return -1;
        return maxQ.getFirst()[0];
    }

    public void push_back(int value) {
        que.add(value);
        while (!maxQ.isEmpty() && maxQ.getLast()[0] < value) {
            maxQ.pollLast();
        }
        if (!maxQ.isEmpty() && maxQ.getLast()[0] == value) {
            maxQ.getLast()[1]++;
        }
        else {
            maxQ.add(new Integer[]{value, 1});
        }
    }

    public int pop_front() {
        if (que.isEmpty()) return -1;

        int k = que.pollFirst();
        if (k == maxQ.getFirst()[0]) {
            if (--maxQ.getFirst()[1] == 0) {
                maxQ.pollFirst();
            }
        }
        return k;
    }
}
