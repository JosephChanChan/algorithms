package pointers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * lc 151 medium
 *
 * Analysis:
 *
 * 朴素双指针解法。n是字符长度
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2021-05-19 23:14
 */
public class ReverseWordsInString {

    public String reverseWords(String s) {
        return inPlace(s);
    }

    // 只用了答案所需的必要空间，O(1)
    String inPlace(String s) {
        s = s.trim();

        // 先统计字符数+有效空格数
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            count++;
            if (s.charAt(i) == ' ') {
                int j = i;
                while (j < s.length() && s.charAt(j) == ' ') j++;
                i = j-1;
            }
        }

        char[] ans = new char[count];

        int i = 0, j = 0, r = ans.length-1;
        while (j < s.length()) {
            // i右移直到第一个字符
            while (i < s.length() && s.charAt(i) == ' ') i++;
            j = i;
            // j右移直到第一个空格
            while (j < s.length() && s.charAt(j) != ' ') j++;
            j--;

            // copy i~j to ans
            for (int k = 0, p = j; k < j-i+1; k++, p--, r--) {
                ans[r] = s.charAt(p);
            }
            if (r > 0) ans[r--] = ' ';

            j++;
            i = j;
        }
        return new String(ans);
    }

    // 因为用list保存了每个单词，所以额外空间O(n)
    String extraSpace(String s) {
        // 去除两端空格
        s = s.trim();

        char[] c = s.toCharArray();
        List<String> list = new ArrayList();
        int i = 0, j = i;
        while (j < c.length) {
            // i右移直到第一个字符
            while (i < c.length && c[i] == ' ') i++;
            j = i;
            // j右移直到第一个空格
            while (j < c.length && c[j] != ' ') j++;
            j--;

            char[] e = new char[j-i+1];
            for (int k = 0, p = i; k < e.length; k++, p++) e[k] = c[p];
            list.add(new String(e));

            i = j+1;
            j++;
        }

        Collections.reverse(list);
        StringBuilder ans = new StringBuilder();
        for (i = 0; i < list.size(); i++) {
            ans.append(list.get(i));
            if (i < list.size()-1) ans.append(" ");
        }
        return ans.toString();
    }
}
