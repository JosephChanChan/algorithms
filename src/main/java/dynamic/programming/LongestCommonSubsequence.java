package dynamic.programming;

/**
 * lc 1143 medium
 *
 * Analysis:
 * f(i,j)为A前i个字符和B前j个字符的最长公共子序列长度
 * Ai == Bj
 *  f(i,j)=f(i-1,j-1)+1
 * Ai != Bj
 *  f(i,j)=max{f(i-1,j), f(i,j-1)}
 *
 * 边界：
 * f(0,0)=0
 * f(1,1)=A1 == B1 ? 1 : 0
 *
 * 时间复杂度：O(n^2)
 * 空间复杂度：O(n^2)
 *
 * @author Joseph
 * @since 2021-04-01 17:27
 */
public class LongestCommonSubsequence {

    public int longestCommonSubsequence(String text1, String text2) {
        int n = text1.length();
        int m = text2.length();
        char[] a = new char[n+1];
        char[] b = new char[m+1];

        for (int i = 0; i < n; i++) a[i+1] = text1.charAt(i);
        for (int i = 0; i < m; i++) b[i+1] = text2.charAt(i);

        int[][] f = new int[n+1][m+1];
        f[0][0] = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (a[i] == b[j]) {
                    f[i][j] = f[i-1][j-1] + 1;
                }
                else {
                    f[i][j] = Math.max(f[i-1][j], f[i][j-1]);
                }
            }
        }
        return f[n][m];
    }
}
