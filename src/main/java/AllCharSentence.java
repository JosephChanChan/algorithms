/**
 * lc 5734 easy
 *
 * Analysis:
 *
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2021-04-18 22:34
 */
public class AllCharSentence {

    int[] hash = new int[26];

    public boolean checkIfPangram(String sentence) {
        if (null == sentence) return false;
        char[] c = sentence.toCharArray();
        if (c.length < 26) return false;

        for (int i = 0; i < c.length; i++) {
            hash[c[i]-'a']++;
        }
        int ans = 0;
        for (int i = 0; i < hash.length; i++) if (hash[i] > 0) ans++;
        return ans == 26;
    }
}
