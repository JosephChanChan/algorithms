package string;

import java.util.ArrayList;
import java.util.List;

/**
 * lc 271 medium
 *
 * Analysis:
 * 主要思想是选定一个字符作为字符串的分隔符，一个字符为转义字符。
 * :;为分隔符，:为转义字符。
 * 对每一个字符串中自带的转义字符做转义。
 * 就跟OS中'\'一样，必须有'\'来修饰'\'
 *
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2021-04-04 16:15
 */
public class EncodeAndDecodeStrings {

    // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {
        StringBuilder b = new StringBuilder();
        for (String s : strs) {
            char[] c = s.toCharArray();
            if (c.length == 0) {
                b.append("");
            }
            for (int i = 0; i < c.length; i++) {
                if (c[i] == ':') {
                    b.append(":");
                }
                b.append(c[i]);
            }
            b.append(":;");
        }
        return b.toString();
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {
        List<String> ans = new ArrayList<>();
        if (null == s || s.length() == 0) {
            ans.add("");
            return ans;
        }

        char[] c = s.toCharArray();
        StringBuilder b = new StringBuilder();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == ':') {
                i++;
                if (c[i] == ';') {
                    if (b.length() == 0) {
                        ans.add("");
                    }
                    else {
                        ans.add(b.toString());
                        b = new StringBuilder();
                    }
                    continue;
                }
            }
            b.append(c[i]);
        }
        return ans;
    }
}
