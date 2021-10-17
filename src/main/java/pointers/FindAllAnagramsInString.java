package pointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * lc 438 medium
 *
 * Analysis:
 *  根据字母异位词的特点，i~j窗口内应出现p串的所有字符并次数相同。字母异位词就是相同数量的字符不同的排列。
 *
 * 对s固定p长度的窗口滑动，判断窗口内的所有字符是否p的字符
 * 用hash，需要判断26个字符出现次数。O(26n)
 * 再做优化？
 * 对窗口内的p字符出现次数计数，
 * 滑入字符，si是合法字符且出现次数<p中应出现次数 +1
 * 滑出字符，si是合法字符且减一后出现次数<p中应出现次数 -1
 * 维护了一个窗口内有效字符的有效出现次数，直接判断validC == p长度
 * 没有多余常数 O(n)
 *
 *  n是s串的长度，len是p串的长度也是窗口大小。
 *
 * 时间复杂度：滑动窗口 O(n) 字母排序 O(n*(len*log(len)+2len)
 * 空间复杂度：滑动窗口 O(1) 字母排序 O(1)
 *
 * @author Joseph
 * @since 2021-03-23 15:06
 */
public class FindAllAnagramsInString {

    int validC = 0;
    int[] pHash = new int[26];
    int[] winHash = new int[26];

    public List<Integer> findAnagrams(String s, String p) {
        return slidingWindow(s, p);
    }

    List<Integer> slidingWindow(String s, String p) {
        List<Integer> ans = new ArrayList<>();

        if (null == s || null == p) return ans;

        char[] a = s.toCharArray();
        char[] b = p.toCharArray();

        for (int i = 0; i < b.length; i++) pHash[b[i]-'a']++;

        int i = 0, j = i;
        for ( ; i < a.length; ) {
            while (j < a.length) {
                if (winHash[a[j]-'a']++ < pHash[a[j]-'a']) {
                    validC++;
                }
                if (j - i + 1 < b.length) {
                    j++; continue;
                }
                if (validC == b.length) {
                    ans.add(i);
                }
                j++; break;
            }
            if (--winHash[a[i]-'a'] < pHash[a[i]-'a']) {
                validC--;
            }
            i++;
        }
        return ans;
    }


    String origin ;

    // TLE 36/36 cases
    List<Integer> usingSort(String s, String p) {
        List<Integer> ans = new ArrayList<>();
        if (null == s || s.length() == 0) return ans;

        // p按字符序重排
        char[] pc = p.toCharArray(), tc ;
        Arrays.sort(pc);
        origin = new String(pc);

        int i = 0, j = p.length()-1, n = s.length();
        while (j < n) {
            tc = s.substring(i, j+1).toCharArray();
            Arrays.sort(tc);
            String t = new String(tc);
            if (origin.equals(t)) {
                ans.add(i);
            }
            i++; j++;
        }
        return ans;
    }
}
