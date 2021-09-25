package depth.first.search;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * lc 301 hard
 *
 * Analysis:
 *  括号匹配问题有一个重要条件是①"当前已匹配的右括号数量必须<=已匹配的左括号数量，否则当前右括号是非法右括号"。
 * 没有继续搜索的必要，直接剪枝。
 * 题目特征提醒我们，要求返回最少删除括号的所有合法的字符串，那就是得搜索所有合法的可能性，所以是搜索问题。
 * 在搜索过程中要收集分支上的字符，事后要搜索另一条分支，所以还得回溯，深搜+回溯。
 *
 * 搜索的决策，也就是分支条件。对于每个括号有两种选择，放弃会怎么样，拿上会怎么样。
 * 加上①条件剪枝就可以勉强AC，135ms
 *
 * 但其实对于每个括号不用都去搜索两个分支。如果已提前知道多余的左括号数量和多余的右括号数量，②在搜索时就可以对"放弃当前括号"的分支剪枝
 *
 * 剪枝条件①+② 就可以3ms AC
 *
 * 时间复杂度：深搜+剪枝 无法分析时间
 * 空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2021-03-18 19:02
 */
public class RemoveInvalidParentheses {

    Set<String> ans = new HashSet<>();

    public List<String> removeInvalidParentheses(String s) {
        int left = 0, right = 0;
        int unuseLeft = 0, unuseRight = 0;

        char[] c = s.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == '(') unuseLeft++;
            else if (c[i] == ')' && unuseLeft > 0) unuseLeft--;
            else if (c[i] == ')') unuseRight++;
        }

        dfs(0, c, 0, 0, unuseLeft, unuseRight, new StringBuilder());

        List<String> list = new ArrayList<>(ans.size());
        for (String sr : ans) list.add(sr);
        if (list.size() == 0) list.add("");
        return list;
    }
    void dfs(int i, char[] c, int l, int r, int ul, int ur, StringBuilder b) {
        if (i == c.length) {
            if (l == r) ans.add(b.toString());
            return;
        }

        if (c[i] == '(') {
            // 丢弃左括号
            if (ul > 0) {
                dfs(i+1, c, l, r, ul-1, ur, b);
            }
            b.append(c[i]);
            dfs(i+1, c, l+1, r, ul, ur, b);
            b.deleteCharAt(b.length()-1);
        }
        else if (c[i] == ')') {
            // 丢弃右括号
            if (ur > 0) {
                dfs(i+1, c, l, r, ul, ur-1, b);
            }
            if (l < r+1) return;
            b.append(c[i]);
            dfs(i+1, c, l, r+1, ul, ur, b);
            b.deleteCharAt(b.length()-1);
        }
        else {
            b.append(c[i]);
            dfs(i+1, c, l, r, ul, ur, b);
            b.deleteCharAt(b.length()-1);
        }
    }
}
