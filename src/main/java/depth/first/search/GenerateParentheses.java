package depth.first.search;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * lc 22 medium
 *
 * Analysis:
 *  有效括号表达式定理：当前左括号数量>=右括号数量
 * n对括号，会有n个( 和 )，表达式长度是2n
 * dfs枚举每个位置是 ( 还是 )，根据定理剪枝
 *
 * 时间复杂度：O(2^n)
 * 空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2020-01-31 22:05
 */
public class GenerateParentheses {

    List<String> ans = new ArrayList();

    public List<String> generateParenthesis(int n) {
        dfs(0, 0, n, new StringBuilder());
        return ans;
    }

    void dfs(int left, int right, int n, StringBuilder b) {
        if (left == right && left == n) {
            ans.add(b.toString());
            return;
        }

        if (left < right) return;

        // 当前位置是什么括号
        if (left < n) {
            b.append("(");
            dfs(left+1, right, n, b);
            b.delete(b.length()-1, b.length());
        }
        if (left > right) {
            b.append(")");
            dfs(left, right+1, n, b);
            b.delete(b.length()-1, b.length());
        }
    }

}
