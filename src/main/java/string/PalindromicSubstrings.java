package string;

/**
 * lc 647 medium
 *
 * Analysis:
 *  求回文子串数的中心扩散法
 *
 * 奇数串 a b a
 *         i
 *
 * 偶数串 a b b a
 *         l r
 *
 * 时间复杂度：O(n^2)
 * 空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2021-03-25 15:26
 */
public class PalindromicSubstrings {

    public int countSubstrings(String s) {
        if (null == s || s.length() == 0) return 0;

        char[] c = s.toCharArray();

        int ans = 1, n = c.length;

        for (int i = 1; i < n; i++) {
            ans++;
            int l = i-1, r = i+1;
            while (l >= 0 && r < n) {
                if (c[l] != c[r]) break;
                ans++;
                l--; r++;
            }
        }

        for (int i = 0; i < n-1; i++) {
            int l = i, r = l+1;
            while (l >= 0 && r < n) {
                if (c[l] != c[r]) break;
                ans++;
                l--; r++;
            }
        }
        return ans;
    }
}
