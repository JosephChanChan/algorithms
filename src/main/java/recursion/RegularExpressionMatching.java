package recursion;

/**
 * 剑指Offer 19 hard & lc 10 hard
 *
 * Analysis:
 *  S: x x x x
 *      i
 *
 *  P: x x x x
 *     j
 *
 * 当Si != Pj时：
 *     Pj+1是*，j往后移动
 *     Pj+1非*，直接返回false
 * 当Si = Pj时：
 *     Pj+1是*，分治3种情况，Pj不匹配Si f(i, j+2) || Pj尽量多匹配Si f(i+1, j) || Pj只匹配一个Si f(i+1, j+2)
 *     Pj+1非*，i&j都往后移动一个
 *
 * 边界：
 *     当S匹配完成，i指向越界时，P还有待匹配字符：
 *         Pj+1是*，j往后移动2个
 *         Pj+1非*，return false
 *
 *
 * @author Joseph
 * @since 2020-11-15 13:55
 */
public class RegularExpressionMatching {

    char[] sc, pc ;
    private char star = '*';
    private char any = '.';

    // 第一次写
    public boolean isMatch(String s, String p) {
        if (null == s || null == p) return false;
        sc = s.toCharArray();
        pc = p.toCharArray();

        return match(0, 0);
    }
    private boolean match(int i, int j) {
        if (i == sc.length && j == pc.length) return true;
        if (i < sc.length && j == pc.length) return false;
        // S已匹配完成，P还有待匹配字符
        if (i == sc.length && j < pc.length) {
            // 必须Pj+1是*，才有希望继续匹配
            if (j == pc.length-1) return false;
            if (star != pc[j+1]) return false;
            return match(i, j + 2);
        }

        if (sc[i] != pc[j] && any != pc[j]) {
            if (j == pc.length-1 || star != pc[j+1]) return false;
            return match(i, j + 2);
        }
        else {
            // Pj+1是*，要分治3种情况计算。非*则i&j都往后移动
            if (j < pc.length-1 && star == pc[j+1]) {
                return match(i, j + 2) || match(i + 1, j + 2) || match(i + 1, j);
            }
            return match(i + 1, j + 1);
        }
    }

    // 第二次写，用时1h30min
    public boolean isMatch2(String s, String p) {
        /*
            递归定义
            匹配si和pj的字符，对于si==pj则i++,j++
            对于j+1是*，有三条分支：1.当前pj只匹配一个si。2.当前pj尽可能多匹配si。3.当前pj不匹配si
            对于si!=pj，看j+1是*，则忽略当前pj

            处理特殊case：.*，这种直接返回true
            s还没匹配完，p已经越界，返回false
            s已越界，p还没匹配完，返回false
        */
        if (s.length() == 0 && p.length() == 0) return true;
        if (s.length() > 0 && p.length() == 0) return false;
        return match(s.toCharArray(), p.toCharArray(), 0, 0);
    }
    boolean match(char[] sc, char[] pc, int i, int j) {
        if (j == pc.length && i < sc.length) return false;
        if (j == pc.length && i == sc.length) return true;

        if (i < sc.length && (sc[i] == pc[j] || pc[j] == '.')) {
            if (j < pc.length-1 && pc[j+1] == '*') {
                return match(sc, pc, i+1, j) || match(sc, pc, i+1, j+2) || match(sc, pc, i, j+2);
            }
            return match(sc, pc, i+1, j+1);
        }
        else if (j < pc.length-1 && pc[j+1] == '*') {
            return match(sc, pc, i, j+2);
        }
        // si!=pj 且 pj+1不是*
        return false;
    }

    // 第3次写，第1次dp 60min
    boolean dp(String s, String p) {
        char[] a = new char[s.length()+1];
        for (int i = 0; i < s.length(); i++) {
            a[i+1]=s.charAt(i);
        }
        char[] b = new char[p.length()+1];
        for (int i = 0; i < p.length(); i++) {
            b[i+1]=p.charAt(i);
        }

        /*
            思考b的最后一个字符和a的最后一个字符能否匹配上
            bj是普通字符，bj==ai，就看前j-1和前i-1能否匹配
            bj是. bj==ai，同样看j-1和i-1

            bj是*，*作用于前面的字符，需要看 bj-1==ai 吗？
            bj-1 == ai，就看前j-2和前i-1能否匹配
            bj-1 != ai，只能把 b[j-1]* 这个组合给省略，看前j-2和前i字符能否匹配

            f(i,j)为a前i个字符和b前j个字符能否匹配

            bj != '*'
                f(i,j)=(bj==ai || bj=='.') && f(i-1,j-1)
            bj == '*'
                f(i,j)=((bj-1 == ai || bj-1 == '.') && f(i-1,j)) || f(i,j-2)

            边界：
            f(0,0)=true, a b都是空串
            f(i,0)=false, i>0, a非空串，b是空串，b匹配不了任何a字符
            f(0,j)=f(0,j-2) && j>0 && bj=='*'
            f(0,j)=false, j>0 && bj != '*'
        */
        int n = a.length;
        int m = b.length;

        boolean[][] f = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // a是空串 b是空串
                if (i == 0 && j == 0) {
                    f[i][j] = true; continue;
                }
                // a非空 b是空
                if (i > 0 && j == 0) {
                    f[i][j] = false; continue;
                }
                // a是空 b非空
                if (i == 0 && j > 0) {
                    if (b[j] != '*') {
                        f[i][j] = false;
                    }
                    else {
                        f[i][j] = f[i][j-2];
                    }
                    continue;
                }
                /*
                    a非空 b非空
                    bj != '*'
                        f(i,j)=(bj==ai || bj=='.') && f(i-1,j-1)
                    bj == '*'
                        f(i,j)=(bj-1 == ai || bj-1 == '.') && f(i-1,j) || f(i,j-2)
                */
                if (b[j] != '*') {
                    f[i][j] = (b[j] == a[i] || b[j] == '.') && f[i-1][j-1];
                }
                else {
                    f[i][j] = ((b[j-1] == a[i] || b[j-1] == '.') && f[i-1][j]) || f[i][j-2];
                }
            }
        }
        return f[n-1][m-1];
    }
}
