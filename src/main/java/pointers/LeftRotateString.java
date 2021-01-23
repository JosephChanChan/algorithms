package pointers;

/**
 * 剑指Offer 58 easy
 *
 * Analysis:
 *  双指针copy就行
 *
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2021-01-17 23:04
 */
public class LeftRotateString {

    public String reverseLeftWords(String s, int n) {
        if (null == s || n == 0) return null;

        char[] ans = new char[s.length()];
        char[] c = s.toCharArray();

        int l = 0, r = n-1, i = r+1, j = 0;
        while (i < c.length) {
            ans[j++] = c[i++];
        }
        while (l <= r) {
            ans[j++] = c[l++];
        }
        return new String(ans);
    }
}
