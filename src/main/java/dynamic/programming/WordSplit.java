package dynamic.programming;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * lc 139 medium
 *
 * Analysis：
 *  f(i)为s前i个元素分段后的可行性
 *  想知道f(i)是否可分段，就要知道s前i个元素最后一段，如果最后一段是单词，并且最后一段前的元素也可分段，则f(i)可分段
 *  但是不知道最后一段，所以得枚举最后一段的起点j，j~i即最后一段且0~j-1也可分段即f(j)成立
 *  还有个特例需要考虑，就是0~i是完整的单词，那f(i)直接成立
 *
 * f(i)= or{f(j) && isTerm(j+1,i), j < i} || isTerm(0, i)
 *
 * 边界：
 * f(0)=isTerm(0, 0)
 *
 * 时间复杂度：dp O(n^2)
 * 空间复杂度：dp O(n)
 *
 * @author Joseph
 * @since 2021-03-06 13:24
 */
public class WordSplit {

    Set<String> hash = new HashSet<>();

    boolean dp(String s, List<String> wordDict) {
        for (String word : wordDict) {
            hash.add(word);
        }
        boolean[] f = new boolean[s.length()];
        f[0] = hash.contains(s.substring(0, 1));

        int n = s.length();
        for (int i = 1; i < n; i++) {
            if (hash.contains(s.substring(0, i+1))) {
                f[i] = true; continue;
            }
            for (int j = 0; j < i; j++) {
                if (f[j] && hash.contains(s.substring(j+1, i+1))) {
                    f[i] = true; break;
                }
            }
        }
        return f[n-1];
    }

    // TLE 35/42 cases
    boolean usingDfs(String s, List<String> wordDict) {
        for (String word : wordDict) {
            hash.add(word);
        }
        return dfs(s, 0);
    }
    boolean dfs(String s, int i) {
        if (i == s.length()) return true;
        for (int j = i; j < s.length(); j++) {
            if (hash.contains(s.substring(i, j+1))) {
                if (dfs(s, j+1)) return true;
            }
        }
        return false;
    }
}
