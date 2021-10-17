package pointers;

/**
 * lc 633 medium
 *
 * Analysis:
 * 时间复杂度：O(根号c)
 * 空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2021-05-25 14:28
 */
public class SumOfTwoSquaresNum {

    public boolean judgeSquareSum(int c) {
        /*
             枚举a，a应在 1~根号c，c-a^2=k
             对k开方，看能否使得 a^2+k^2=c
         */

        if (c == 0 || c == 1 || c == 2 || c == 4 || c == 5) return true;
        if (c == 3) return false;

        int sqrtc = (int) Math.sqrt(c);

        for (int a = 1; a <= sqrtc; a++) {
            int redundant = c-(a*a);
            int b = (int) Math.sqrt(redundant);
            if (a*a+b*b == c) return true;
        }
        return false;
    }

    public boolean twoPointers(int c) {
        /*
             l就是a，r就是b。
             如果l^2+r^2=c，true
             l^2+r^2 < c l++
             l^2+r^2 > c r--
             算法思想就是，a和b无论谁大，较大那个不会超过根号c，那么较小那个也自然在0~根号c范围内。
             就在0~根号c范围内找这2个数。最坏情况就是0~根号c范围每个数都试了一遍，不会有遗漏
         */

        if (c == 0 || c == 1 || c == 2 || c == 4 || c == 5) return true;
        if (c == 3) return false;

        int sqrtc = (int) Math.sqrt(c);

        int l = 0, r = sqrtc;
        while (l <= r) {
            int v = l*l + r*r;
            if (v == c) return true;
            if (v > c) r--;
            l++;
        }
        return false;
    }
















}
