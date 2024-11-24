package stack;

import java.util.Objects;
import java.util.Stack;

/**
 * lc 150 medium
 *
 * Analysis:
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2022/12/28
 */
public class EvaluateReversePolishNotation {

    /*
        题目保证给定的表达式是正确的且有解
     */

    Stack<Integer> nums = new Stack<>();

    public int evalRPN(String[] tokens) {
        if (tokens.length == 1) {
            return Integer.parseInt(tokens[0]);
        }
        for (String token : tokens) {
            if (isOperation(token)) {
                int a = nums.pop();
                int b = nums.pop();
                nums.push(calc(a, b, token));
            }
            else {
                nums.push(Integer.parseInt(token));
            }
        }
        return nums.pop();
    }


    boolean isOperation(String s) {
        return Objects.equals(s, "+") || Objects.equals(s, "-") || Objects.equals(s, "*") || Objects.equals(s, "/");
    }

    int calc(int a, int b, String c) {
        if (Objects.equals(c, "+")) {
            return a+b;
        }
        else if (Objects.equals(c, "-")) {
            return b-a;
        }
        else if (Objects.equals(c, "*")) {
            return a*b;
        }
        else {
            return b/a;
        }
    }
}
