package tables;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 剑指Offer 30 easy
 *
 * Analysis:
 *  辅助栈记录降序的小于等于当前最小元素的元素。
 *
 *  时间复杂度：O(1)
 *  空间复杂度：因为用到辅助栈所以O(n)
 * @author Joseph
 * @since 2020-11-21 21:37
 */
public class MinStack {

    Deque<Integer> ordinal = new ArrayDeque<>();
    Deque<Integer> minStack = new ArrayDeque<>();

    /** initialize your data structure here. */
    public MinStack() {
    }

    public void push(int x) {
        ordinal.add(x);
        if (minStack.isEmpty() || x <= minStack.peekLast()) {
            minStack.add(x);
        }
    }

    public void pop() {
        if (!ordinal.isEmpty()) {
            int v = ordinal.pollLast();
            if (v == minStack.peekLast()) minStack.pollLast();
        }
    }

    public int top() {
        return ordinal.peekLast();
    }

    public int min() {
        return minStack.peekLast();
    }
}
