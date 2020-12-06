package backtracking.algorithm;

import java.util.*;

/**
 * 剑指Offer 38 medium & lc 47
 *
 * Analysis:
 *  这题回溯有两种解法。
 *  普通回溯法：
 *  1.排序，让相同的字母相邻方便后期去重。
 *  2.按顺序枚举给每个位置枚举可选的字母，要靠变量标记某个字母是否选过。此外对于 b b' 这种重复字母去重。
 *  3.如果b没选，选择了b'，代表b不在当前递归分支上。又因为以b开头的排列已经枚举过了，如果选b'作为排列开头继续枚举则重复了。
 *  如果b已选，再选b'代表b和b'同时在当前递归分支上即 [a,b,b'] 这种排列是可以的，[a,b',b]则重复了。
 *
 *  交互回溯法，固定某个字母开头，继续递归剩下的字母。这种解法对于去重的不好写。
 *
 * @author Joseph
 * @since 2020-12-05 23:27
 */
public class Permutation2 {

    boolean[] used ;
    List<Character> list ;
    Queue<String> q = new LinkedList<>();

    public String[] permutation(String s) {
        if (null == s) return new String[0];
        if (s.length() == 1) return new String[]{s};

        char[] c = s.toCharArray();
        used = new boolean[c.length];
        list = new ArrayList<>(c.length);

        Arrays.sort(c);
        dfs(0, c);

        int n = q.size();
        String[] ans = new String[q.size()];
        for (int i = 0; i < n; i++) {
            ans[i] = q.poll();
        }
        return ans;
    }

    private void dfs(int len, char[] c) {
        if (len == c.length) {
            q.add(string());
            return;
        }
        for (int i = 0; i < c.length; i++) {
            if (!used[i]) {
                if (i > 0 && c[i-1] == c[i] && !used[i-1]) continue;
                used[i] = true;
                list.add(c[i]);
                dfs(len+1, c);
                used[i] = false;
                list.remove(list.size()-1);
            }
        }
    }

    private String string() {
        char[] c = new char[list.size()];
        for (int i = 0; i < list.size(); i++) {
            c[i] = list.get(i);
        }
        return new String(c);
    }
}
