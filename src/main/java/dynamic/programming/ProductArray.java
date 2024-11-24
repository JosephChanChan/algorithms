package dynamic.programming;

/**
 * 剑指Offer 66 medium & lc 238
 *
 * Analysis:
 *  b(i)按题意应该是 a0*a1*...*ai-1 * ai+1*...*an-1
 * 如果能使用除法，则一开始算出 m = a0*...*an-1 的积，中间算b(i)=m/a[i]就行。时间O(n)
 * 但现在不能用除法，暴力解就是每次枚举b(i)，时间O(n^2)
 * 如果每次算b(i)时，已经知道a0*...*ai-1，和ai+1*...an-1，直接把两边乘起来不就行了！
 * 设f(i)=a0*...ai-1，则f(i)=f(i-1)*ai-1
 * g(i)=an-1*...*ai+1，则g(i)=g(i+1)*ai+1
 * 边界：f(0)=1，g(n-1)=1
 *
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2021-01-23 16:56
 */
public class ProductArray {

    public int[] constructArr(int[] a) {
        int n = a.length;
        if (n == 0) return new int[0];
        if (n == 1) return a;

        int[] f = new int[n];
        int[] g = new int[n];
        int[] b = new int[n];
        f[0] = 1; g[n-1] = 1;

        // f(i)要从左到右算
        for (int i = 1; i < n; i++) {
            f[i] = f[i-1] * a[i-1];
        }
        // g(i)要从右往左算
        for (int i = n-2; i >= 0; i--) {
            g[i] = g[i+1] * a[i+1];
        }

        // b(i)=f(i)*g(i)
        b[0] = g[0];
        b[n-1] = f[n-1];
        for (int i = 1; i < n-1; i++) {
            b[i] = f[i]*g[i];
        }
        return b;
    }
}
