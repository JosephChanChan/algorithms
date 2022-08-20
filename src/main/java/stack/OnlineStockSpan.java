package stack;

import java.util.LinkedList;

/**
 * lc 901 medium
 *
 * Analysis:
 *
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2022/7/22
 */
public class OnlineStockSpan {

    int idx = 0;
    LinkedList<int[]> stack = new LinkedList<>();

    /*
        本质对于Ai，期望找到一个大于它的左边界Ak
        Ak > Ai && k < i
        只要我们记录下左边界的下标，就可以直到左边界Ak到Ai的距离

        单调栈就是用来解决此类 "在数组中找到左 / 右边第一个比自己小 / 大的元素"

        这题有点DP的意思，因为对于每个Ai要重新计算0~i-1的数字，这是暴力做法，有重复计算的子问题，
        所以自然想到DP，但是对于状态f(i)一个维度的状态，无法构造出状态转移关系，本质是f(i-1)和f(i)没有最优关系
        所以无法DP
     */

    public int next(int price) {
        while (stack.size() > 0) {
            int[] top = stack.getLast();
            if (top[1] <= price) {
                // 弹出栈顶，直到一个Ak > Ai
                stack.removeLast();
                continue;
            }
            // Ak > Ai
            break;
        }
        if (stack.size() == 0) {
            stack.addLast(new int[]{idx++, price});
            return idx;
        }
        int[] top = stack.getLast();
        stack.addLast(new int[]{idx, price});
        return idx++ - top[0];
    }
}
