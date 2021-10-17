package binary.search;

/**
 * lc 278 easy
 *
 * Analysis:
 *  n很大并且很简单的算法就是线性筛查。
 * 肯定是log级的算法，根据特性如果当前的m是bad version，那么m<=r的都是bad version。
 * 要找到第一个bad version只可能是l<=m，这个l在m的左边
 *
 * 时间复杂度：O(logN)
 * 空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2021-08-07 15:42
 */
public class FirstBadVersion {
    public int firstBadVersion(int n) {
        if (n == 1) return 1;

        int l = 1, r = n, m ;
        while (l + 1 < r) {
            m = l + ((r-l) >> 1);
            if (isBadVersion(m)) {
                r = m;
            }
            else {
                l = m;
            }
        }
        if (isBadVersion(l)) return l;
        return r;
    }

    boolean isBadVersion(int n) {
        return true;
    }
}
