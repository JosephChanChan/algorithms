package string;

/**
 * 剑指Offer 5 easy
 *
 * Analysis:
 *  先遍历一遍计数空格，空格 -> %20 一个空格占3个长度。
 *  开数组，顺序复制元素，OK
 *
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2020-10-08 16:55
 */
public class ReplaceSpaces {

    public String replaceSpace(String s) {
        char[] c = s.toCharArray();
        if (c.length == 0) return s;

        int n = c.length, spaces = 0;
        for (int i = 0; i < n; i++) {
            if (c[i] == ' ') spaces++;
        }

        char[] cc = new char[(n - spaces) + 3 * spaces];

        for (int i = 0, j = 0; i < c.length; i++) {
            if (c[i] != ' ') {
                cc[j++] = c[i];
                continue;
            }
            cc[j++] = '%';
            cc[j++] = '2';
            cc[j++] = '0';
        }
        return new String(cc);
    }
}
