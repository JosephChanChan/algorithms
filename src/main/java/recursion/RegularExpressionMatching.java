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
 *     Pj+1是*，分治3种情况，f(i, j+2) || f(i+1, j) || f(i+1, j+2)
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
}
