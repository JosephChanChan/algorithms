package recursion;

/**
 * lc 509 easy & 剑指Offer 10 easy
 *
 * Analysis:
 * 备忘录递归自顶向下，循环自底向上
 *
 * 时间复杂度：O(n)
 * 空间复杂度：备忘录递归 O(n)，循环 O(1)
 *
 * @author Joseph
 * @since 2020-10-08 23:17
 */
public class FibonacciSequence {

    int ans = 0, mod = (int) 1e9 + 7;

    public int fib(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;

        return loop(n);
    }

    private int recursion(int n, int[] f) {
        if (f[n] > 0) return f[n];
        int n1, n2 ;
        n1 = f[n-1] > 0 ? f[n-1] : recursion(n-1, f);
        n2 = f[n-2] > 0 ? f[n-2] : recursion(n-2, f);
        f[n] = (n1 + n2) % mod;
        return f[n];
    }

    private int loop(int n) {
        int n1 = 1, n2 = 0;
        for (int i = 2; i <= n; i++) {
            ans = (n1 + n2) % mod;
            n2 = n1;
            n1 = ans;
        }
        return ans;
    }
}
