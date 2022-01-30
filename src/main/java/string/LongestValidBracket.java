package string;

import java.util.Stack;

/**
 * lc 32 hard
 *
 * Analysis：
 *  最长的有效子串左右两边一定是 '(......)'
 * 一个右括号没有左括号匹配就是非法右括号
 * 不管怎样变化，有效括号子串一定是左括号和右括号数量相等匹配，
 * 并且是相同数量的左括号在相同数量的右括号前面
 * 当遇到( 它可能是有效子串的一部分压入它的下标到栈中
 * 遇到 ）如果栈中有（ 匹配 就增加了有效子串长度，把（ 下标弹出
 * 栈中如果还有（ ，它是未被 ）匹配的，所以此时还是无效的，是目前有效子串的左边界
 * 用 ）减去栈顶 ( 下标计算子串长度
 * 栈底存放最近的一个非法右括号下标，当没有（ 时它是有效子串的左边界
 *
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2021-02-28 21:13
 */
public class LongestValidBracket {

    public int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack();
        stack.push(-1);

        int longest = 0;
        char[] c = s.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == '(') {
                stack.push(i);
                continue;
            }
            // 栈底永存一个非法右括号，数量大于1代表有左括号
            if (stack.size() > 1) {
                stack.pop();
                longest = Math.max(i - stack.peek(), longest);
            }
            else {
                stack.pop();
                stack.push(i);
            }
        }
        return longest;
    }
}
