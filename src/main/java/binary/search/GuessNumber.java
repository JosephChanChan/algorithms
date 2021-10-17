package binary.search;

/**
 * lc 374 easy
 *
 * Analysis:
 *
 * 时间复杂度：O(logN)
 * 空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2021-08-07 15:56
 */
public class GuessNumber {

    public int guessNumber(int n) {
        if (n == 1) return 1;

        int l = 1, r = n, m ;
        while (l + 1 < r) {
            m = l + (r-l >> 1);
            int res = guess(m);
            if (res == 0) return m;
            if (res < 0) {
                r = m;
            }
            else {
                l = m;
            }
        }
        if (guess(l) == 0) return l;
        return r;
    }

    int guess(int k) {
        return 0;
    }
}
