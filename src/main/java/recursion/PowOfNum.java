package recursion;

/**
 * @author Joseph
 * @since 2019-09-22 20:26
 *
 * leetcode easy
 *
 * Question Description:
 *  Implement pow(x, n), which calculates x raised to the power n (xn).
 * Example 1:
 * Input: 2.00000, 10
 * Output: 1024.00000
 * Example 2:
 * Input: 2.10000, 3
 * Output: 9.26100
 * Example 3:
 * Input: 2.00000, -2
 * Output: 0.25000
 * Explanation: 2-2 = 1/22 = 1/4 = 0.25
 * Note:
 * -100.0 < x < 100.0
 * n is a 32-bit signed integer, within the range [−231, 231 − 1]
 */
public class PowOfNum {


    public static void main(String[] args) {
        PowOfNum powOfNum = new PowOfNum();
        double v = powOfNum.myPow(0.00001, 2147483647);
        System.out.println(v);
    }

    private double myPow(double x, int n) {
        if (x == 0) return 0;
        if (n == 0) return 1;

//        double v = calc(x, x, n, 1);
        double v = recursionCalc(x, n);
        if (n < 0) {
            v = 1 / v;
        }
        return v;
    }


    private double recursionCalc(double x, int pow) {
        double cur = x;
        int temp = Math.abs(pow);
        for (int i = 1; i < temp; i++) {
            cur *= x;
        }
        return cur;
    }

    private double calc(double x, int pow) {
        if (pow == 1) return x;
        return calc(x, --pow) * x;
    }

    // tail recursion
    private double calc(double cur, double x, int pow, int count) {
        if (Math.abs(pow) == count) return cur;
        cur *= x;
        return calc(cur, x, pow, ++count);
    }


}
