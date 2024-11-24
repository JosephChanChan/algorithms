package dynamic.programming;

/**
 * lc 97 medium
 *
 * Analysis:
 * 首先明确一点，c是由a b的字符交错形成的，所以c的长度=a+b
 * 并且c的每一个字符都应该在a b中能找到对应字符
 *
 * f(i,j)为A前i个字符和B前j个字符可拼出C前i+j字符
 * f(i,j)= (c[i+j]==a[i] && f(i-1,j)) || (c[i+j]==b[j] && f(i,j-1))
 *
 * 边界：
 * c的空串可由a/b的空串组成
 * f(0,0)=true
 * f(0,j) a是空串，要考虑b所有字符匹配c
 * f(i,0) b是空串，要考虑a所有字符匹配c
 *
 * 时间复杂度：O(nm)
 * 空间复杂度：O(nm)
 *
 * @author Joseph
 * @since 2021-04-01 23:53
 */
public class IntervalString {

    public boolean isInterleave(String s1, String s2, String s3) {
        int n = s1.length();
        int m = s2.length();
        int l = s3.length();
        if (n+m != l) return false;

        char[] a = new char[n+1];
        char[] b = new char[m+1];
        char[] c = new char[l+1];
        for (int i = 0; i < n; i++) a[i+1] = s1.charAt(i);
        for (int i = 0; i < m; i++) b[i+1] = s2.charAt(i);
        for (int i = 0; i < l; i++) c[i+1] = s3.charAt(i);

        boolean[][] f = new boolean[n+1][m+1];
        f[0][0] = true;

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                if (i == 0 && j == 0) continue;

                // A空串，B非空串
                if (i == 0) {
                    f[i][j] = b[j] == c[i+j] && f[i][j-1];
                    continue;
                }

                // A非空串，B空串
                if (j == 0) {
                    f[i][j] = a[i] == c[i+j] && f[i-1][j];
                    continue;
                }

                // A&B 都非空串
                f[i][j] = (b[j] == c[i+j] && f[i][j-1]) || (a[i] == c[i+j] && f[i-1][j]);
            }
        }
        return f[n][m];
    }
}
