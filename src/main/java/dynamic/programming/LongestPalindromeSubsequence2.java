package dynamic.programming;

/**
 * lc 1682 medium
 *
 * Analysis:
 * 感觉这题有hard难度了。
 *
 * 时间复杂度：O(n^2)
 * 空间复杂度：O(n^2)
 *
 * @author Joseph
 * @since 2021-05-01 22:17
 */
public class LongestPalindromeSubsequence2 {

    public int longestPalindromeSubseq(String s) {
        /*
             Ai==Bj
                 f(i,j,Ai)=max{f(i+1,j-1,c)|c!=Ai}+2
                 f(i,j,c)=f(i+1,j-1,c)}

             Ai!=Bj
                f(i,j,c)=Math.max{f(i+1,j-1,c), f(i,j-1,c), f(i+1,j,c)}
                为什么要考虑i~j-1 i+1~j这两个范围？
                因为Ai/Bj可能和 i+1/j-1处的字符相同

             边界：
             f(i,j,c)=2, i=j-1 && Ai==Bj
         */
        char[] c = s.toCharArray();
        int n = c.length;

        int[][][] f = new int[n][n][26];

        int max = 0;
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i <= n-len; i++) {
                int j = i+len-1;
                if (len == 2) {
                    if (c[i] == c[j]) f[i][j][c[i]-'a'] = 2;
                    continue;
                }
                if (c[i] == c[j]) {
                    for (char p = 'a'; p <= 'z'; p++) {
                        if (p == c[i]) continue;

                        f[i][j][p-'a'] = f[i+1][j-1][p-'a'];
                        f[i][j][c[i]-'a'] = Math.max(f[i][j][c[i]-'a'], f[i+1][j-1][p-'a']+2);
                    }
                }
                else {
                    for (char p = 'a'; p <= 'z'; p++) {
                        f[i][j][p-'a'] = Math.max(f[i+1][j-1][p-'a'], Math.max(f[i+1][j][p-'a'], f[i][j-1][p-'a']));
                    }
                    /*if (len == 4 && i == 1) {
                            System.out.println("区间 "+i+" ~ "+j+" 以字符 "+p+" 为两端的长度为 "+f[i][j][p-'a']);
                    }*/
                }

            }
        }
        for (char p = 'a'; p <= 'z'; p++) {
            max = Math.max(max, f[0][n-1][p-'a']);
        }
        return max;
    }
}
