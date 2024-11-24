package dynamic.programming;

/**
 * lc 516 medium
 *
 * Analysis:
 * f(i,j)区间i~j最长回文子序列长度
 * Ai!=Aj
 * f(i,j)=max{f(i+1,j), f(i,j-1)}
 *
 * Ai==Aj
 * f(i,j)=max{f(i+1,j), f(i,j-1), f(i+1,j-1)+2}
 *
 * 边界：
 * f(i,j)=1, i==j&&Ai==Aj
 * f(i,j)=2, i+1==j&&Ai==Aj
 *
 * 时间复杂度：O(n^2)
 * 空间复杂度：O(n^2)
 *
 * @author Joseph
 * @since 2021-04-16 14:13
 */
public class LongestPalindromeSubsequence {

    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        if (n == 1) return 1;

        int[][] f = new int[n][n];
        char[] c = s.toCharArray();

        for (int i = 0; i < n; i++) f[i][i] = 1;
        for (int i = 0; i <= n-2; i++) {
            int j = i+1;
            f[i][j] = 1;
            if (c[i] == c[j]) f[i][j] = 2;
        }

        for (int len = 3; len <= n; len++) {
            for (int i = 0; i <= n-len; i++) {
                int j = i+len-1;
                f[i][j] = Math.max(f[i+1][j], f[i][j-1]);
                if (c[i] == c[j]) {
                    f[i][j] = Math.max(f[i][j], f[i+1][j-1]+2);
                }
            }
        }
        return f[0][n-1];
    }
}
