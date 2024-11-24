package dynamic.programming;

/**
 * lc 72 hard
 *
 * Analysis:
 *  作为经典的匹配DP，应熟练于心，30min AC
 * 打表可解，打表要考虑两个空字符串开始枚举
 * f(i,j)为S前i个字符和T前j个字符匹配的最少代价
 * f(i,j)=min{f(i-1,j-1), f(i-1,j), f(i,j-1)}+1, Si != Tj
 * f(i,j)=f(i-1,j-1), Si == Tj
 *
 * 边界：
 * f(i,j)=0, i/j < 0
 * f(0,0)=0, 两个空串的情况
 * f(1,1)=1, Si != Tj
 * f(1,1)=0, Si == Tj
 * f(0,j)=f(0,j-1)+1
 * f(i,0)=f(i-1,0)+1
 *
 * 时间复杂度：O(nm)
 * 空间复杂度：O(m)
 */
public class EditDistance {

    public int minDistance(String word1, String word2) {
        if (null == word1 || null == word2) return 0;
        if (0 == word1.length() || 0 == word2.length()) return Math.abs(word1.length()-word2.length());

        // s和t增加一格空串的情况，匹配DP最好都从空串开始打表
        char[] s = new char[word1.length()+1];
        char[] t = new char[word2.length()+1];
        for (int i = 1; i < s.length; i++) s[i] = word1.charAt(i-1);
        for (int j = 1; j < t.length; j++) t[j] = word2.charAt(j-1);
        int n = s.length;
        int m = t.length;

        int old = 0, now = 1;
        int[][] f = new int[2][m];
        // 滚动数组只能初始化第一行，第一行就是S从空串变成T的过程，由于S是空串只需要每次加Tj就行
        for (int j = 1; j < m; j++) {
            f[now][j] = f[now][j-1] + 1;
        }

        for (int i = 1; i < n; i++) {
            old = 1-old; now = 1-now;
            for (int j = 0; j < m; j++) {
                if (j == 0) {
                    f[now][j] = f[old][j] + 1;
                    continue;
                }
                if (s[i] == t[j]) {
                    f[now][j] = f[old][j-1];
                    continue;
                }
                f[now][j] = Math.min(f[old][j-1], Math.min(f[old][j], f[now][j-1])) + 1;
            }
        }
        return f[now][m-1];
    }

}