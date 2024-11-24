package depth.first.search;

import java.util.*;

/**
 * @author Joseph
 * @since 2019-09-22 20:26
 *
 * lc 50 medium
 *
 * Analysis:
 * 时间复杂度：O(logN)
 * 空间复杂度：O(1)
 */
public class PowOfNum {


    public static void main(String[] args) {
        PowOfNum powOfNum = new PowOfNum();
        double v = powOfNum.myPow(34.00515, -3);
        System.out.println(v);
    }

    /**
         f(j)为i的j次方值
         f(j)=f(j/2)*f(j/2+1), j是奇数
         f(j)=f(j/2)*f(j/2), j是偶数
         边界：
         f(0)=1, f(1)=i, f(2)=i^2
     */

    public double myPow(double x, int n) {
        if (n == 0) {
            return 1.0d;
        }
        long p = (long) n;
        boolean negative = p < 0;
        if (negative) {
            p = -p;
        }

        Map<Long, Double> f = new HashMap<>();
        f.put(0L, 1.0d);
        f.put(1L, x);

        double ans = dfsMem(x, p, f);
        if (negative) {
            //System.out.println(ans);
            return 1/ans;
        }
        return ans;
    }

    double dfsMem(double x, long j, Map<Long, Double> f) {
        if (j == 0) {
            return 1;
        }
        if (j == 1) {
            return x;
        }
        if (f.containsKey(j)) {
            return f.get(j);
        }
        if ((j & 1) == 0) {
            double v = dfsMem(x, j/2, f);
            f.put(j, v*v);
            return v*v;
        }
        double v = dfsMem(x, j/2, f) * dfsMem(x, j/2 + 1, f);
        f.put(j, v);
        return v;
    }


}
