package depth.first.search;

import java.util.ArrayList;
import java.util.List;

/**
 * leetcode 241 medium
 *
 * Analysis:
 * 分治，以每个算术符号二分，计算左边算术式子所有结果和右边算术式子所有结果再合并。
 * 时间复杂度：O(n^2)
 * 空间复杂度：O(所有计算结果)
 *
 * @author Joseph
 * @since 2020-08-25 22:04
 */
public class DifferentWaysAddParentheses {

    public List<Integer> diffWaysToCompute(String input) {
        if (input.length() == 0) return new ArrayList<>();

        char[] a = input.toCharArray();
        return divide(a, 0, a.length-1);
    }

    // 计算l~r的算术式子所有计算结果，从每个符号二分两个更小的算术式计算
    private List<Integer> divide(char[] a, int l, int r) {
        List<Integer> result = new ArrayList<>();
        if (l == r) {
            result.add(Integer.parseInt(String.valueOf(a[l])));
            return result;
        }

        // traverse each operator
        for (int i = l; i <= r; i++) {
            char operator = a[i];
            if (operator == '+' || operator == '-' || operator == '*') {
                // divide
                List<Integer> lResult = divide(a,  l, i-1);
                List<Integer> rResult = divide(a,  i+1, r);

                // merge
                for (int j = 0; j < lResult.size(); j++) {
                    for (int k = 0; k < rResult.size(); k++) {
                        result.add(calc(lResult.get(j), rResult.get(k), operator));
                    }
                }
            }
        }
        if (result.size() == 0) {
            result.add(convertInt(l, r, a));
        }
        return result;
    }

    private int calc(int a, int b, char operator) {
        if (operator == '+') return a+b;
        else if (operator == '-') return a-b;
        else return a*b;
    }

    private int convertInt(int l, int r, char[] a) {
        StringBuilder builder = new StringBuilder();
        for (int i = l; i <= r; i++) {
            builder.append(a[i]);
        }
        return Integer.parseInt(builder.toString());
    }

}
