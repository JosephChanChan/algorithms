package pointers;

/**
 * lc 1839 medium
 *
 * Analysis:
 *
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2021-04-25 15:33
 */
public class LongestSubstringOfAllVowelsInOrder {

    public int longestBeautifulSubstring(String word) {
        if (null == word || word.length() < 5) return 0;

        char[] c = word.toCharArray();
        // a e i o u

        int valid = 0, max = 0, cur = 0;
        int[] h = new int[26];

        for (int i = 0; i < c.length; i++) {
            int j = i;
            while (j < c.length) {
                // 当前j字符比上次字符大，代表乱序
                if (c[j]-'a' < cur) {
                    // move i to j remove i char
                    int k = i;
                    for ( ; k < j; k++) {
                        if (--h[c[k]-'a'] == 0) valid--;
                    }

                    // seek next a
                    for ( ; j < c.length; j++) {
                        if (c[j] == 'a') break;
                    }
                    i = j-1;
                    cur = 0;
                    break;
                }

                if (h[c[j]-'a']++ == 0) valid++;
                if (valid >= 5) {
                    max = Math.max(max, j-i+1);
                }
                cur = c[j]-'a';
                j++;
            }
            if (j == c.length) i = j;
        }
        return max;
    }
}
