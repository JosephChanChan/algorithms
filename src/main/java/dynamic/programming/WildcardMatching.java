package dynamic.programming;

/**
 * lc 44 hard
 *
 * Analysis:
 *  这题和lc 10很像，同样的思路可以思考，都是匹配DP类型。
 *
 *  思考最后一步，即bj是什么字符的情况。
 * 对于bj是*的情况比较复杂，*意味着可以匹配a最后的0个或多个字符
 * 那就分两种情况看看。
 * *匹配a的0个字符，也就是说看前i个和前j-1个字符匹配
 * *匹配多个字符，那就要看前i-1个和前j个字符
 *
 * f(i,j)为a前i个字符和b前j个字符匹配情况
 * Bj == '?' || Bj == Ai
 *  f(i,j)=f(i-1,j-1)
 * Bj == '*'
 *  f(i,j)=f(i,j-1) || f(i-1,j)
 *
 * 边界：
 * f(0,0)=true, a&b是空串
 * f(i,0)=false,a非空，b是空串
 * f(0,j)=f(0,j-1) && Bj==*, a空串，b非空，则b中只有全部是*时才能匹配空串，否则字符和?都要具体匹配一个a的字符才行
 *
 * 时间复杂度：O(nm)
 * 空间复杂度：O(nm)
 *
 * @author Joseph
 * @since 2021-03-30 22:14
 */
public class WildcardMatching {

    public boolean isMatch(String s, String p) {
        char[] a = new char[s.length()+1];
        char[] b = new char[p.length()+1];
        int n = a.length;
        int m = b.length;

        for (int i = 0; i < s.length(); i++) {
            a[i+1] = s.charAt(i);
        }
        for (int i = 0; i < p.length(); i++) {
            b[i+1] = p.charAt(i);
        }

        boolean[][] f = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0 && j == 0) {
                    f[0][0] = true; continue;
                }
                // a非空，b空串
                if (i > 0 && j == 0) {
                    f[i][j] = false; continue;
                }
                // a空串，b非空
                if (i == 0 && j > 0) {
                    f[i][j] = f[i][j-1] && b[j]=='*'; continue;
                }
                // a非空，b非空
                if (b[j] == a[i] || b[j] == '?') {
                    f[i][j] = f[i-1][j-1];
                }
                else if (b[j] == '*') {
                    f[i][j] = f[i][j-1] || f[i-1][j];
                }
            }
        }
        return f[n-1][m-1];
    }
}
