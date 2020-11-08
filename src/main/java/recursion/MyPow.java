package recursion;

/**
 * 剑指Offer 16 medium & lc 50 medium
 *
 * Analysis:
 *  这题好险不用考虑大数，不然真不好做，但是看题目给的数据范围挺大的，按照下面的范围绝对会溢出。
 *  但是1ms AC了，题目实际数据范围肯定缩小了。
 *      -100.0 < x < 100.0
 *      n 是 32 位有符号整数，其数值范围是 [−2^31, 2^31 − 1]
 *
 *  时间复杂度：O(log(n))
 *  空间复杂度：O(log(n))
 *
 * @author Joseph
 * @since 2020-11-08 17:14
 */
public class MyPow {

    public static void main(String[] args) {
        MyPow myPow = new MyPow();
        double v = myPow.myPow(10.0, 10000000);
        System.out.println(v);
    }

    public double myPow(double x, int n) {
        /*
            1.x=0, ans=0
            2.n=0, ans=1
            3.n<0, ans= 1/x^n
            4.n>0, ans= x^n
        */
        if (x == 0.0) return 0.0;
        if (n == 0) return 1.0;
        if (x == 1.0) return x;

        boolean patch = n == Integer.MIN_VALUE;
        if (patch) n++;

        // 计算x^n
        double ans = calc(x, n < 0 ? -n : n);
        if (patch) ans *= x;
        if (n < 0) return 1.0/ans;
        return ans;
    }

    private double calc(double x, int n) {
        /*
            f(x, n) =
                f(x, n/2)^2, n%2==0
                f(x, n/2)^2 * x, n%2!=0

            边界：
                x^2, n==2
                x, n==1
        */
        if (n == 1) return x;
        if (n == 2) return x * x;

        double ans = calc(x, n >> 1);

        if ((n & 1) == 0) {
            return ans * ans;
        }
        else {
            return ans * ans * x;
        }
    }
}
