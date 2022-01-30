package string;

/**
 * lc 161 medium
 *
 * Analysis:
 * 编辑距离限定为1，如果两个字符串长度差大于1直接false
 * 如果长度相等，两个字符串最多只能有一个字符不同
 * 如果长度差1，短的加入一个缺失后完全相同，长的删除一个多余后完全相同
 * A cabc
 *    i
 * B cbc
 *    j
 * Ai!=Bj
 * 跳过a，假设a被删除，看i+1和j
 * 假设在j前面加入a，同样看i+1和j
 *
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2021-04-04 11:00
 */
public class OneEditDistance {

    public boolean isOneEditDistance(String s, String t) {
        char[] a = s.toCharArray();
        char[] b = t.toCharArray();
        if (Math.abs(a.length - b.length) > 1) return false;

        if (a.length == b.length) {
            int c = 0;
            for (int i = 0; i < a.length; i++) {
                if (a[i] != b[i]) c++;
                if (c > 1) return false;
            }
            return c == 1;
        }

        if (a.length < b.length) {
            char[] p = b;
            b = a;
            a = p;
        }

        int c = 0;
        for (int i = 0, j = 0; j < b.length; i++, j++) {
            if (a[i] != b[j]) {
                c++; j--;
            }
            if (c > 1) return false;
        }
        return true;
    }
}
