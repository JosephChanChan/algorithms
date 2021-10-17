package binary.search;

/**
 * lc 274 medium
 *
 * Analysis:
 *  二分答案。在0和最大论文数间搜索。
 * H指数，有H偏论文每篇至少被引用了H次以上。
 * 二分空间跟论文数有关，有可能给出的N篇论文每篇都被引用了N次以上，这H指数最大就是N
 *
 * 时间复杂度：O(N*logN)
 * 空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2021-08-14 16:28
 */
public class HIndex {

    public int hIndex(int[] citations) {
        if (null == citations || citations.length == 0) return 0;

        int l = 0, r = 5000, m ;// 5000是题目给出的论文最大数量
        while (l + 1 < r) {
            m = (l + r) >> 1;
            if (check(m, citations)) {
                l = m;
            }
            else {
                r = m;
            }
        }
        if (check(r, citations)) return r;
        return l;
    }

    boolean check(int h, int[] a) {
        int c = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] >= h) c++;
        }
        return c >= h;
    }
}
