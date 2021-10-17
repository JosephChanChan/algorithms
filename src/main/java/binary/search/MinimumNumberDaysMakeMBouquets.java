package binary.search;

/**
 * lc 1482 medium
 *
 * Analysis:
 *  二分答案+滑动窗口
 * check，需要k朵花凑一束，每个花只能用一次，需要连续的k朵，滑动窗口统计在给定时间内能否凑出m束
 *
 * 为什么能想到二分？
 * 因为答案要求最少天数，不妨设为d天，则答案域具有二分性，多于d天的满足题目条件可以凑出m束花，少于d天的不满足条件凑不出m束。
 * 则可以对答案域二分。
 *
 * 根据题目条件构造出check函数，要求连续的k个花凑出一束，则看数组内有多少个k大小的窗口开花了。
 *
 * 时间复杂度：O(N*log(max number))
 * 空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2021-09-12 15:12
 */
public class MinimumNumberDaysMakeMBouquets {

    public int minDays(int[] bloomDay, int m, int k) {
        int l = 1, r = 0, d, n ;
        int[] a = bloomDay;
        n = a.length;

        if (n < m*k) return -1;

        for (int i = 0; i < n; i++) {
            r = Math.max(r, a[i]);
        }

        while (l + 1 < r) {
            d = ((r - l) >> 1) + l;
            if (check(k, m, d, a)) {
                r = d;
            }
            else {
                l = d;
            }
        }
        if (check(k, m, l, a)) return l;
        if (check(k, m, r, a)) return r;
        return -1;
    }

    boolean check(int k, int m, int d, int[] a) {
        /*
            给定d天时间，连续k个花凑出一束花。
            在a内能否凑出m束花
        */
        int i = 0, j = 0;
        while (j < a.length) {
            //System.out.println("check i="+i+" j="+j+" d="+d);
            if (a[j] <= d) {
                // i~j连续的k朵花？
                if (j-i+1 == k) {
                    if (--m == 0) break;
                    i = j+1;
                    j = i;
                    continue;
                }
                j++;
            }
            else {
                i = j+1;
                j = i;
            }
        }
        //System.out.println("m="+m+" d="+d);
        return m == 0;
    }
}
