package dynamic.programming;

/**
 *
 * lint code 79 medium & 阿里校招笔试某题
 *
 * Analysis:
 * 以往的匹配DP状态都是考虑A中前i个和B中前j个字符的匹配情况。
 * 但对于子串来说，如果已知f(i-1,j-1)的长度是3，按照这种前i个的定义，不知道这个最长公共子串的结尾是在哪里
 * 能否和当前的ai bj连接起来。如果i-1 != j-1 显然计算前面再长和当前的ai bj是连不起来的。
 *
 * 所以状态定义成只考虑以i j结尾的字符的最长公共子串长度，这样对于i+1 j+1就能知道能不能用上一步的状态值
 *
 * f(i,j)为A中以i结尾的和B中以j结尾的最长公共子串的长度
 * Ai == Bj
 *   f(i,j)=f(i-1,j-1)+1
 * Ai != Bj
 *   f(i,j)=0
 *
 * 边界：
 * f(0,0)=0
 * f(1,1)=1, Ai==Bj
 *
 * 时间复杂度：O(n*m)
 * 空间复杂度：O(n*m)
 *
 * @author Joseph
 * @since 2020-03-29 21:14
 */
public class LongestCommonSubstring {

    public int longestCommonSubstring(String A, String B) {
        int n = A.length();
        int m = B.length();

        char[] a = new char[n+1];
        char[] b = new char[m+1];
        for (int i = 0; i < n; i++) a[i+1] = A.charAt(i);
        for (int i = 0; i < m; i++) b[i+1] = B.charAt(i);

        int[][] f = new int[n+1][m+1];

        int max = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (a[i] == b[j]) {
                    f[i][j] = f[i-1][j-1] + 1;
                }
                else {
                    f[i][j] = 0;
                }
                max = Math.max(max, f[i][j]);
            }
        }
        return max;
    }


}
