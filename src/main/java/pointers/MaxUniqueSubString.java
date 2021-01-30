package pointers;

import java.util.*;

/**
 * 剑指Offer 48 medium
 *
 * Analysis:
 *  哈希表+双指针。
 *  哈希记录每个字符的位置，双指针扫描字符。
 *  遇到重复字符时，左指针i就向右收缩直到重复字符，哈希表中把重复字符k左边的字符都清空了。
 *  有点像滑动窗口的思想。
 *
 *  DP+Hash
 *
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2021-01-03 21:04
 */
public class MaxUniqueSubString {

    public int lengthOfLongestSubstring(String s) {
        return dpAndHash(s);
    }

    // 8ms AC
    public int twoPointers(String s) {
        if (null == s || s.length() == 0) return 0;

        // ascii码 0~255
        int[] hash = new int[256];

        int i = 0, j = 0, max = 0;
        for ( ; i < s.length(); i++) {
            while (j < s.length()) {
                if (hash[s.charAt(j)] == 0) {
                    max = Math.max(max, j-i+1);
                    hash[s.charAt(j++)]++;
                    continue;
                }
                break;
            }
            // j遇到重复字符，假设重复字符在k位置上，肯定在窗口内，i向前走直到移除重复字符
            hash[s.charAt(i)]--;
        }
        return max;
    }

    // 3ms AC
    private int dpAndHash(String s) {
        /*
            最长不重复子字符串，不一定是最后一个字符an，它或许是整个字符串中的某一连续的部分。
            所以只能计算某个开始到结束的连续位置。
            f(i)是以ai结尾的最长不重复子字符串的长度
            上一步f(i-1)以ai-1结尾的最长不重复子字符串长度，如果这个ai-1结尾的子字符串不包含ai，则f(i)=f(i-1)+1
            如果包含ai，就不能直接加上ai对吧。
            设前面出现过的ai为i1，当前的ai为i2。i1到i2的距离为d，如果d<=f(i-1)代表i1出现在ai-1结尾的最长不重复子串中。
            那么就不能直接加上ai，就要剔除最近的重复的i1。所以f(i)=d
            如果d>f(i-1)，i1不包含在ai-1结尾的最长不重复子串中，可以直接加上ai，f(i)=f(i-1)+1
            转移方程：
                if hasOccur(ai) && d(ai) <= f(i-1)
                    f(i)=d(ai)
                else
                    f(i)=f(i-1)+1
            边界：
                f(0)=1
        */
        if (null == s || s.length() == 0) return 0;
        if (s.length() == 1) return 1;

        char[] c = s.toCharArray();
        int n = c.length;
        int max = 0;

        // asicc码0~127
        int[] d = new int[128];
        for (int i = 0; i < d.length; i++) {
            d[i] = -1;
        }

        int[] f = new int[n];
        f[0] = 1;
        d[c[0]] = 0;
        for (int i = 1; i < n; i++) {
            if (d[c[i]] != -1 && i - d[c[i]] <= f[i-1]) {
                f[i] = i - d[c[i]];
            }
            else {
                f[i] = f[i-1] + 1;
            }
            d[c[i]] = i;
            max = Math.max(max, f[i]);
        }
        return max;
    }

}
