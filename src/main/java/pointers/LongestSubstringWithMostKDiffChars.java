package pointers;


/**
 * lc 340 medium
 *
 * Analysis:
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2022-01-27 13:19
 */
public class LongestSubstringWithMostKDiffChars {

    // ascii码长度一个字节8位，可表示256个字符
    int[] m = new int[256];

    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        /*
            寻找一个合法的窗口，使得窗口最大
            hash记录字符出现次数，total记录总的相异字符数
         */
        if (null == s || k == 0) return 0;

        int total = 0, max = 0;

        char[] a = s.toCharArray();
        int i = 0, j = i, n = a.length;
        for ( ; i < n; ) {
            while (j < n) {
                // j字符第一次出现，且total+1超过了k，i~j是不合法窗口
                if (m[a[j]] == 0 && total + 1 > k) {
                    break;
                }
                // i~j是合法窗口，记录j字符
                max = Math.max(max, (j-i)+1);
                if (m[a[j]]++ == 0) {
                    total++;
                }
                j++;
            }
            // 不合法才跳出
            if (--m[a[i]] == 0) {
                total--;
            }
            i++;
        }
        return max;
    }
}
