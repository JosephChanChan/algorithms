package stack;

import java.util.*;

/**
 * lc 84 hard
 *
 * Analysis:
 *  暴力法枚举起点i和终点j。
 * 计算每一段i~j的矩形面积，时间O(n^2)
 * 中心扩散法，从每个柱子为中心向左右两边扩散，计算每段i~j的矩形面积。
 * 左边界是第一个小于当前中心高度的，右边界同理。取左右边界最小高度即是当前i~j的矩形面积，时间同样O(n^2)。
 * 如果用中心扩散法，O(1)时间能知道左边界，同理得到右边界就能O(n)时间计算面积。
 * 单调栈适合找左边第一个小于/大于自身元素 和 右边第一个小于/大于自身元素 的场景。
 * 由于单调栈递增，每个柱子左边界已知即ai-1，右边界未知，所以对于每个新加入的元素都有可能是当前栈顶元素的右边界。
 * 当出现左边界&右边界时，就可计算当前以当前柱子ai的高度最多可伸展的宽度。
 *
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2021-01-31 23:01
 */
public class MaxRectangleAreaInHistogram {

    public int largestRectangleArea(int[] heights) {
        // 栈保存每个柱子的下标，方便计算宽度
        Stack<Integer> s = new Stack<>();

        int area = 0;
        for (int i = 0; i < heights.length; i++) {
            int h = heights[i];
            // 栈中每个元素都有已知的左边界，新加元素可能成为栈顶的右边界
            while (!s.isEmpty() && heights[s.peek()] > h) {
                int ch = heights[s.pop()];
                // 弹出元素后，栈顶就是当前柱子的左边界，右边界是新加元素
                // 去掉左边界和右边界中间的宽度是当前柱子的高度可延伸的宽度
                // 右边界-左边界-1 = 中间宽度
                int w = i - (s.isEmpty() ? -1 : s.peek()) - 1;
                area = Math.max(area, ch * w);
            }
            s.push(i);
        }
        // 计算剩余的柱子，右边界固定是柱子个数
        while (!s.isEmpty()) {
            int h = heights[s.pop()];
            int w ;
            if (s.isEmpty()) {
                w = heights.length;
            }
            else {
                w = heights.length - s.peek() - 1;
            }
            area = Math.max(area, h * w);
        }
        return area;
    }
}
