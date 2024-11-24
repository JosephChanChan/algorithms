package pointers;

/**
 * lc 2414 medium
 *
 * Analysis：
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2022/9/19
 */
public class LengthOfLongestAlphabeticalSubstring {

    /**
     滑动窗口，保持一个区间合法，记录最大
     */

    int ans = 1;

    public int longestContinuousSubstring(String s) {
        char[] c = s.toCharArray();
        if (c.length == 1) {
            return 1;
        }

        int i = 0, j = 1;
        int last = c[0]-'a';
        while (j < c.length) {
            if (last+1 == c[j]-'a') {
                last = c[j]-'a';
                ans = Math.max(ans, j-i+1);
            }
            else {
                i = j;
                last = c[i]-'a';
            }
            j++;
        }
        return ans;
    }
}
