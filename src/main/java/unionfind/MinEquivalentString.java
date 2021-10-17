package unionfind;

/**
 * lc 1061 medium
 *
 * Analysis:
 *  等价字符之间都维护在同个集合内。每个集合内维护字典序最小的字符为根。
 * 每个根合并时取字典序最小的为根。
 *
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2021-01-31 15:16
 */
public class MinEquivalentString {

    char[] root = new char[26];

    public String smallestEquivalentString(String A, String B, String S) {
        for (char i = 'a'; i <= 'z'; i++) root[i-'a'] = i;

        for (int i = 0; i < A.length(); i++) {
            char a = A.charAt(i);
            char b = B.charAt(i);
            union(a, b);
        }
        char[] ans = new char[S.length()];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = find(S.charAt(i));
        }
        return new String(ans);
    }

    private char find(char c) {
        if (root[c-'a'] == c) return c;
        return root[c-'a'] = find(root[c-'a']);
    }

    private void union(char a, char b) {
        char p = find(a);
        char q = find(b);
        if (p != q) {
            char min = p > q ? q : p;
            char max = min == q ? p : q;
            root[max-'a'] = min;
        }
    }
}
