package string;

/**
 * lc 6 medium
 *
 * Analysis:
 *
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2021-04-22 14:49
 */
public class ZigZagConversion {

    public String convert(String s, int numRows) {
        /*
            规律题
            分析规律，每次都是从上到下，下到上的行。
            模拟行的上下转移过程。
        */
        if (null == s || s.length() == 1 || numRows == 1) return s;

        int n = numRows;
        StringBuilder[] b = new StringBuilder[n+1];
        for (int i = 1; i <= n; i++) b[i] = new StringBuilder();

        char[] c = s.toCharArray();
        int r = 1;
        boolean down = true;
        for (int i = 0; i < c.length; i++) {
            b[r].append(c[i]);
            r += down ? 1 : -1;
            if (r > n) {
                down = false;
                r = n-1;
            }
            if (r < 1) {
                down = true;
                r = 2;
            }
        }

        StringBuilder ans = new StringBuilder();
        for (int i = 1; i < b.length; i++) {
            ans.append(b[i].toString());
        }
        return ans.toString();
    }
}
