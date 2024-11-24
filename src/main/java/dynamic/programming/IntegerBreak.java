package dynamic.programming;

/**
 * lc 343 medium
 *
 * Analysis:
 * time: O(n^2)
 * space: O(n)
 *
 * @author Joseph
 * @since 2023/7/30
 */
public class IntegerBreak {

    public int integerBreak(int n) {
        /*
            f(n)为拆分n为一个序列相加等于n，且这个序列的乘积最大
            如果最后一个数是Ak，上一个数就是n-Ak，对n-Ak可以继续拆分也可以不拆，就看哪种情况乘积最大
            f(n)=max{f(n-Ak)*Ak, (n-Ak)*Ak | 1<=Ak<=n}
            f(0)=0
            f(1)=1
            f(2)=1
        */
        int[] f = new int[n+1];
        f[0]=0;
        f[1]=1;
        f[2]=1;
        for (int i = 3; i <= n; i++) {
            int max = 0;
            for (int k = 1; k <= i; k++) {
                max = Math.max(Math.max(f[i-k]*k, (i-k)*k), max);
            }
            f[i] = max;
        }
        return f[n];
    }
}
