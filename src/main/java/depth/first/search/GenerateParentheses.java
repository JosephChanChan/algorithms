package depth.first.search;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Joseph
 * @since 2020-01-31 22:05
 *
 * leetcode 22 medium
 *
 * Question Description:
 *  Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 * For example, given n = 3, a solution set is:
 * [
 *   "((()))",
 *   "(()())",
 *   "(())()",
 *   "()(())",
 *   "()()()"
 * ]
 *
 * Analysis:
 *  回溯。
 *  第一次想到的算法 AC 16ms。
 *  每次生成一对圆弧，这对圆弧要么放已有圆弧外面，要么依次放入每一对已有圆弧里面。
 *  放入后，递归下一对圆弧。
 *  这种算法会有重复，用hash去重。
 *  时间复杂度：未知
 *  空间复杂度：O(n)
 *
 *  看解题报告后理解的 DFS AC 1ms。
 *  dfs就是每次优先递归 (，当 ( 数量满后，再递归 ) 去闭合。
 *  其实思想就是利用了 ) 总是出现在 ( 之后，并且 ) 每次出现时数量应该小于 (。
 *  因为 ) 是用来闭合 ( 的。
 *  时间复杂度：未知
 *  空间复杂度：O(n)
 */
public class GenerateParentheses {

    int n = 0;
    private Set<String> hashSet = new HashSet<>();
    private List<String> validString = new ArrayList<>();

    public static void main(String[] args) {
        GenerateParentheses generateParentheses = new GenerateParentheses();
        generateParentheses.generateParenthesis(2);
    }

    public List<String> generateParenthesis(int n) {
        if (n == 0) return new ArrayList<>();
        if (n == 1) {
            List<String> list = new ArrayList<>();
            list.add("()");
            return list;
        }

        this.n = n;
        /*recursiveGenerate(0, new StringBuilder());*/
        /*return new ArrayList<>(hashSet);*/

        depthGenerate(1, 1, new StringBuilder());
        return validString;
    }

    /** AC 16ms */
    private void recursiveGenerate(int step, StringBuilder builder) {
        if (step == n) {
            hashSet.add(builder.toString());
            return;
        }

        String parentheses = "()";
        builder.append(parentheses);

        recursiveGenerate(step + 1, builder);

        int i = builder.lastIndexOf("()");
        builder.delete(i, i + 2);

        // 遍历依次插入已有的括号中
        for (int k = 1; k <= step; k++) {
            builder = insert(k, builder, parentheses);
            recursiveGenerate(step + 1, builder);
            delete(k + 1, builder);
        }
    }

    private StringBuilder insert(int i, StringBuilder builder, String chars) {
        StringBuilder newBuilder = new StringBuilder();
        char[] charArray = builder.toString().toCharArray();
        for (char c : charArray) {
            if (c == '(' && --i == 0) {
                newBuilder.append(c);
                newBuilder.append(chars);
                continue;
            }
            newBuilder.append(c);
        }
        return newBuilder;
    }

    private void delete(int i, StringBuilder builder) {
        char[] charArray = builder.toString().toCharArray();
        for (int k = 0; k < charArray.length; k++) {
            if (charArray[k] == '(' && --i == 0) {
                builder.delete(k, k + 2); break;
            }
        }
    }

    /** AC 1ms */
    private void depthGenerate(int left, int right, StringBuilder builder) {
        if (builder.length() == n << 1) {
            validString.add(builder.toString());
            return;
        }

        if (left <= n) {
            builder.append("(");
            depthGenerate(left + 1, right, builder);
            builder.deleteCharAt(builder.lastIndexOf("("));
        }
        if (left > right && right <= n) {
            builder.append(")");
            depthGenerate(left, right + 1, builder);
            builder.deleteCharAt(builder.lastIndexOf(")"));
        }
    }



}
