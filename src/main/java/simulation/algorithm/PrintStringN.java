package simulation.algorithm;

/**
 * 剑指Offer 17 easy
 *
 * Analysis:
 *  本题实际上想考察的是大数加法
 *
 * 大数模拟:
 *  共需要循环 n位数的最大值 那么多次，每次循环执行 incr() print()，其中incr()最坏需要O(n)时间，print()需要O(n)时间，
 *  所以 n^10 * (n+n)
 *  时间复杂度：O(n^11)
 *  空间复杂度：O(n)
 *
 * 投机取巧：
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2020-11-08 18:08
 */
public class PrintStringN {

    private int count = 0;

    public int[] printNumbers(int n) {
        int l = (int) Math.pow(10, n);
        char[] s = new char[n];
        int[] ans = new int[l - 1];

        for (int i = 0; i < s.length; i++) {
            s[i] = '0';
        }
        while (incr(s)) {
            print(s, ans);
        }
        return ans;
    }

    // 大数加法，最低位自增1，当最高位进位时代表当前长度最大数值溢出
    private boolean incr(char[] s) {
        for (int i = s.length-1; i >= 0; i--) {
            int v = s[i] - '0' + 1;
            if (v < 10) {
                s[i] = (char) (v + '0');
                return true;
            }
            else {
                s[i] = '0';
                if (i == 0) return false;
            }
        }
        return false;
    }

    private void print(char[] s, int[] ans) {
        ans[count++] = Integer.parseInt(new String(s));
    }


    // 投机取巧的做法
    public int[] trick(int n) {
        // 注意到题目返回int[]数组，数组长度最大为2^31-1，则代表最大的数为2^31-1
        // 那我们直接打印就行了
        int board = ((int) myPow(10.0, n)) - 1;
        // 0 < n
        int[] ans = new int[board];

        for (int i = 1; i <= board; i++) {
            ans[i - 1] = i;
        }
        return ans;
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
