package binary.search;

/**
 * lc 875 medium
 *
 * Analysis:
 *  每小时只能吃一堆香蕉，求吃H次，每次选一堆吃，吃完N堆最少需要多少速度K
 * 这个模拟过程简单，相当于在速度上有一个答案K，>=K的都可以在H次内吃完所有香蕉。
 * <K的不可能在H次内吃完所有。所以二分这个K就行。
 *
 * 时间复杂度：O(N*log 10e9)
 * 空间复杂度：(1)
 *
 * @author Joseph
 * @since 2021-10-30 14:07
 */
public class KokoEatingBananas {

    public int minEatingSpeed(int[] piles, int h) {
        int n = piles.length;
        int l = 1, r = (int) 10e9, k ;
        while (l + 1 < r) {
            k = ((r - l) >> 1) + l;
            if (eatDone(piles, k, h)) {
                r = k;
            }
            else {
                l = k;
            }
        }
        if (eatDone(piles, l, h)) return l;
        return r;
    }

    boolean eatDone(int[] a, int k, int h) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] <= k) {
                h--;
            }
            else {
                h -= (a[i] / k);
                if (a[i] % k > 0) h--;
            }
        }
        // 吃完所有香蕉后如果还有时间或时间刚刚好则OK
        return h >= 0;
    }
}
