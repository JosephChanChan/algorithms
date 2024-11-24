package dynamic.programming;

/**
 * lc 115 hard
 *
 * Analysis:
 * f(i,j)为a前i个和b前j个字符匹配的不同子序列个数
 * Ai==Bj
 * 如果前j-1的字符的前i-1的字符中出现了k次，那么再接上Bj，Bj就在前i字符中出现了k次
 * 如果前j个字符在前i-1字符中已经出现了x次，代表前i-1的字符中有x个Ai字符称为 Ai'，那么将这x个Ai'全部替换成当前的Ai
 * 还是可以和Bj匹配，所以前j个字符在A中又出现了x次
 *     f(i,j)=f(i-1,j-1)+f(i-1,j)
 * Ai!=Bj
 * 如果当前的Bj和Ai不匹配，只能看前j个字符是否在前i-1中出现了
 *     f(i,j)=f(i-1,j)
 *
 * 边界：
 * 当A的长度小于B时，B肯定在A中出现0次
 * f(0,0)=0
 * f(i,j)=0, i < j
 * f(i,0)=1, 对于B是空串在A中出现1次，为什么这么定义？
 * 这个是需要有意义才这么定义的，思考用到f(i,0)的状态。
 * A: aa
 * B: a
 * f(2,1)=f(1,0)+f(1,1)= ? + 1
 * 可以看到这种情况需要f(i,0)是1，才能算到正确答案。
 *
 * 时间复杂度：O(nm)
 * 空间复杂度：O(nm)
 *
 * @author Joseph
 * @since 2021-04-01 19:15
 */
public class DistinctSubsequence {

    public int numDistinct(String s, String t) {
        int n = s.length();
        int m = t.length();
        char[] a = new char[n+1];
        char[] b = new char[m+1];

        for (int i = 0; i < n; i++) a[i+1] = s.charAt(i);
        for (int i = 0; i < m; i++) b[i+1] = t.charAt(i);

        int[][] f = new int[n+1][m+1];
        f[0][0] = 1;

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                if (i == 0 && j == 0) continue;

                // A空串 B非空串
                if (i == 0) {
                    f[i][j] = 0; continue;
                }
                // A非空 B空串
                if (j == 0) {
                    f[i][j] = 1; continue;
                }

                // A比B短，现在及以后的B字符不可能出现在A中，都是0
                if (i < j) break;

                if (a[i] != b[j]) {
                    f[i][j] = f[i-1][j];
                }
                else {
                    f[i][j] = f[i-1][j-1] + f[i-1][j];
                    /*if (i == 6 && j == 2) {
                        System.out.println("b出现了"+f[i-1][j-1]+" ba出现了"+f[i-1][j]);
                    }*/
                }
            }
        }
        return f[n][m];
    }
}
