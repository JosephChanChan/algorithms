package stack;

import java.util.Stack;

/**
 * lc 224 hard
 *
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2021-04-14 13:54
 */
public class BasicCalculator {


    // 中缀表达式直接计算，因为不涉及* / 可以直接从左到右依次计算

    Stack<Character> ops = new Stack();
    Stack<Integer> nums = new Stack();

    public int calculate(String s) {
        //System.out.println(s);
        s = s.replace(" ", "");
        //System.out.println(s);
        s = s.replace("+-", "-");
        //System.out.println(s);
        s = s.replace("-+", "-");
        //System.out.println(s);
        s = s.replace("(+", "(");
        s = s.replace("(-", "(0-");

        nums.push(0);

        /*
             符号栈ops 数字栈nums
             ops存运算符和括号。
             遇到运算符看ops栈顶，优先级>=自己的，弹出栈顶，取出nums两个数字计算。直到ops栈顶优先级<自己。
             例外是栈顶是(，直接入栈

             遇到 )，一直弹出运算符计算，直到遇到(
         */
        char[] c = s.toCharArray();
        StringBuilder b = new StringBuilder();

        for (int i = 0; i < c.length; i++) {
            if (c[i] == ' ') continue;

            if (c[i] >= '0' && c[i] <= '9') {
                int j = i;
                while (j < c.length && c[j] >= '0' && c[j] <= '9' ) {
                    b.append(c[j]); j++;
                }
                i = j-1;
                nums.push(Integer.parseInt(b.toString()));
                b.delete(0, b.length());
            }
            else if (c[i] == '+' || c[i] == '-') {
                while (!ops.isEmpty() && ops.peek() != '(') {
                    calc();
                }
                ops.push(c[i]);
            }
            else if (c[i] == '(') {
                ops.push(c[i]);
            }
            else {
                while (!ops.isEmpty() && ops.peek() != '(') {
                    calc();
                }
                if (!ops.isEmpty()) ops.pop();
            }
        }
        while (!ops.isEmpty()) {
            calc();
        }
        return nums.isEmpty() ? 0 : nums.pop();
    }

    void calc() {
        char p = ops.pop();
        int b = nums.pop();
        int a = nums.pop();
        nums.push(p == '+' ? a+b : a-b);
    }


    // 后缀表达式解法 时间 O(n) 空间 O(n)

    Stack<Integer> z = new Stack();
    Stack<Character> t = new Stack();

    public int calculate2(String s) {
        if (null == s) return -1;

        String suffix = buildSuffix(s);
        String[] ops = suffix.split(",");

        for (int i = 0; i < ops.length; i++) {
            String op = ops[i];
            if (op.equals("+")) {
                z.push(z.pop() + z.pop());
            }
            else if (op.equals("-")) {
                int a = z.pop();
                int b = z.pop();
                z.push(b-a);
            }
            else {
                z.push(Integer.parseInt(op));
            }
        }
        return z.pop();
    }

    String buildSuffix(String s) {
        StringBuilder b = new StringBuilder();
        b.append("0").append(",");

        s = s.replaceAll("\\(\\+", "(0+");
        s = s.replaceAll("\\(\\-", "(0-");
        char[] c = s.toCharArray();

        for (int i = 0; i < c.length; i++) {
            if (c[i] == ' ') continue;

            if (Character.isDigit(c[i])) {
                int j = i;
                while (j < c.length && Character.isDigit(c[j])) b.append(c[j++]);
                i = j-1;
                b.append(",");
            }
            else if (c[i] == '(') {
                t.push(c[i]);
            }
            else if (c[i] == ')') {
                while (t.peek() != '(') b.append(t.pop()).append(",");
                t.pop();
            }
            else {
                while (!t.isEmpty() && mapping(t.peek()) >= mapping(c[i])) {
                    if (t.peek() == '(') break;
                    b.append(t.pop()).append(",");
                }
                t.push(c[i]);
            }
        }
        while (!t.isEmpty()) b.append(t.pop()).append(",");
        b.delete(b.length()-1, b.length());
        return b.toString();
    }

    int mapping(char c) {
        if (c == '+' || c == '-') return 0;
        if (c == '*' || c == '/') return 1;
        return 2;
    }
}
