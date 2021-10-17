package binary.search;

/**
 * lc 275 medium
 *
 * Analysis:
 *  二分答案。在274题中论文引用次数无序，每次check只能遍历一次看看是否有h偏论文每篇引用次数至少h次以上。
 * 最大的H指数必然还是0~n篇论文中，所以还是可以二分答案。
 * 这里引用次数有序，对于每次check就可以优化成常数时间。
 * 思考，每次猜M，我们直接定位到第N-M+1篇论文上看这篇论文引用次数是否>=M，
 * 如果满足则共有M篇论文且第N-M+1~N上的论文引用次数都>=M
 * 则M是个可能解，记录下。
 * 如果第M篇论文的引用次数<M，则M不是个H指数。
 *
 * 时间复杂度：O(logN)
 * 空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2021-08-14 17:10
 */
public class HIndex2 {

    public int hIndex(int[] citations) {
        if (null == citations || citations.length == 0) return 0;

        int n = citations.length;
        int l = 0, r = n, m ;
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

    boolean check(int m, int[] a) {
        return a[a.length-m] >= m;
    }
}
