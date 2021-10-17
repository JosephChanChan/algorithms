package binary.search;

/**
 * lc 29 medium
 *
 * Analysis：
 *  二分答案的例题。
 * a/b=c，c应该在[0,a]的范围，在这个范围猜c，使得c*b=a，
 * 如果c*b>a，则c猜大了，缩小右边界，反之缩小左边界。
 *
 * 因为被限制了不能使用乘法、除法、mod，计算c*b时用快速乘法，这种技巧。
 *
 * 时间复杂度：O(logA) A是被除数
 * 空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2021-02-07 21:37
 */
public class TwoIntDivision {

    int max = Integer.MAX_VALUE, min = Integer.MIN_VALUE;

    public int divide(int dividend, int divisor) {
        long a = dividend, b = divisor;
        if (a == 0) return 0;

        // 判断两个数是否异号的小技巧，符号位异或后如果异号会是1
        boolean negative = (a^b) >>> 63 > 0;
        a = Math.abs(a); b = Math.abs(b);

        long l = 0, r = a;
        while (l + 1 < r) {
            long m = ((r-l) >> 1) + l;
            int ch = check(m, a, b);
            if (ch == 0) return (int) (negative ? -m : m);
            if (ch > 0) {
                r = m;
            }
            else {
                l = m;
            }
        }
        if (check(r, a, b) <= 0) return (int) (negative ? -r : r > max ? max : r);
        return (int) (negative ? -l : l > max ? max : l);
    }

    int check(long m, long a, long b) {
        long mb = quickMulti(m, b);
        if (mb == a) return 0;
        else if (mb > a) return 1;
        return -1;
    }

    long quickMulti(long m, long b) {
        /*
            快速乘法。原理就是5个4相乘加上5个2相乘。
            二进制的1、2、4、8... 这些位如果是1，则代表在这一位上会有1、2、4、8...个5相乘然后加起来
            5*6 = 5*(0110) = 5*(8*0+4*1+2*1+1*0)
         */
        long ans = 0;
        while (b > 0) {
            if ((b & 1) == 1) ans += m;
            m = m << 1;
            b = b >> 1;
        }
        return ans;
    }
}
