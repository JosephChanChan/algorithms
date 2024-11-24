package dynamic.programming;

/**
 * lc 2370 medium
 *
 * Analysis:
 * 时间复杂度：O(26n)
 * 空间复杂度：O(26n)
 *
 * @author Joseph
 * @since 2022/8/10
 */
public class LongestIdealSubsequence {

    /*
        f(i,j)为A前i个元素中以j结尾的最长理想子序列长度，a<=j<=z
        f(i,j)=f(i-1,j)，Ai != j
        f(i,j)=max{ f(i-1,p)，p != j && d(p,j) <= k} + 1，Ai == j
        边界：
        f(0,j)=0,空串
    */

    public int longestIdealString(String s, int k) {
        int n = s.length();
        char[] a = s.toCharArray();
        int[][] f = new int[n+1][26];

        int ans = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < 26; j++) {
                if (a[i-1]-'a' != j) {
                    f[i][j] = f[i-1][j];
                    ans = Math.max(ans, f[i][j]);
                    continue;
                }
                int max = 0;
                for (int p = 0; p < 26; p++) {
                    if (validD(p, a[i-1]-'a', k)) {
                        max = Math.max(max, f[i-1][p]);
                    }
                }
                f[i][j] = max+1;
                ans = Math.max(ans, f[i][j]);
            }
        }
        return ans;
    }

    boolean validD(int p, int q, int k) {
        return Math.abs(p-q) <= k;
    }
}
