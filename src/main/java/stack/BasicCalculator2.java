package stack;

import java.util.Stack;

/**
 * lc 227 medium
 *
 * Analysis:
 *
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2021-04-14 15:17
 */
public class BasicCalculator2 {

    Stack<Integer> nums = new Stack();
    Stack<Character> ops = new Stack();

    public int calculate(String s) {
        /*
            有 + - * / 四种运算符，将数字和符号分开，
            遇到符号入栈时，如果前面有同级符号，先将它们弹出计算，这样符合同级运算从左到右。
            如果前面符号比自己大，也应该先计算它们。
            如果前面符号较小，则当前符号优先运算，入栈
        */

        if (null == s || s.length() == 0) return 0;

        nums.push(0);

        char[] c = s.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == ' ') continue;

            if (Character.isDigit(c[i])) {
                int j = i, a = 0;
                while (j < c.length && Character.isDigit(c[j])) a = a*10 + c[j++]-'0';
                nums.push(a);
                i = j-1;
            }
            else {
                while (!ops.isEmpty() && mapping(ops.peek()) >= mapping(c[i])) calc();
                ops.push(c[i]);
            }
        }
        while (!ops.isEmpty()) calc();
        return nums.pop();
    }

    void calc() {
        int a = nums.pop();
        int b = nums.pop();
        char op = ops.pop();
        if (op == '+') {
            nums.push(a+b);
        }
        else if (op == '-') {
            nums.push(b-a);
        }
        else if (op == '*') {
            nums.push(a*b);
        }
        else {
            nums.push(b/a);
        }
    }

    int mapping(char op) {
        if (op == '+' || op == '-') return 0;
        if (op == '*' || op == '/') return 1;
        return -1;
    }
}
