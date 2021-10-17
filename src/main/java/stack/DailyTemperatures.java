package stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * lc 739 medium
 *
 * Analysis:
 *  问题可抽象成，找ai的右边界，ai右边界就是最近的温度比它高的aj
 * 单调栈就适合解决找左右第一个比它大或小的问题。
 *
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2021-03-25 16:28
 */
public class DailyTemperatures {

    public int[] dailyTemperatures(int[] T) {
        Deque<Integer> stack = new ArrayDeque<>();

        int n = T.length;
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            if (stack.isEmpty()) {
                stack.addLast(i); continue;
            }
            while (!stack.isEmpty() && T[stack.peekLast()] < T[i]) {
                int j = stack.pollLast();
                ans[j] = i - j;
            }
            stack.addLast(i);
        }
        return ans;
    }
}
