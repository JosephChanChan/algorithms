package stack;

import java.util.Stack;

/**
 * lc 772 hard
 *
 * Analysis:
 *
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2021-04-14 16:08
 */
public class BasicCalculator3 {

    Stack<Character> ops = new Stack();
    Stack<Integer> nums = new Stack();

    public int calculate(String s) {
        int n = s.length();
        char[] c = s.toCharArray();

        nums.push(0);

        for (int i = 0; i < n; i++) {
            if (c[i] >= '0' && c[i] <= '9') {
                int j = i, v = 0;
                while (j < n && c[j] >= '0' && c[j] <= '9') {
                    v = v*10 + (c[j]-'0');
                    j++;
                }
                nums.push(v);
                i = j-1;
            }
            else if (c[i] == '(') {
                ops.push(c[i]);
            }
            else if (c[i] == ')') {
                // 弹出ops运算符计算，直到 (
                while (!ops.isEmpty() && ops.peek() != '(') {
                    calc();
                }
                // 前面一定有个 (
                ops.pop();
            }
            else {
                // + - * /
                while (!ops.isEmpty() && mapping(ops.peek()) >= mapping(c[i])) {
                    calc();
                }
                ops.push(c[i]);
            }
        }
        while (!ops.isEmpty()) calc();
        return nums.pop();
    }

    void calc() {
        char p = ops.pop();
        int b = nums.pop();
        int a = nums.pop();
        if (p == '+') nums.push(a+b);
        else if (p == '-') nums.push(a-b);
        else if (p == '*') nums.push(a*b);
        else nums.push(a/b);
    }

    int mapping(char c) {
        if (c == '+' || c == '-') return 1;
        if (c == '*' || c == '/') return 2;
        return -1;
    }
}
