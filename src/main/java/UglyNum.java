/**
 * 剑指Offer 49 medium & lc 264
 *
 * Analysis:
 *  数学分析题。
 *  根据丑数定义，丑数j会是另一个丑数i的倍数。
 * i*2^k/i*3^k/i*5^k=j
 * 从已有的丑数序列中，从小到大乘2得到第一个大于当前最大丑数M的丑数M2。
 * 从已有的丑数序列中，从小到大乘3得到第一个大于当前最大丑数M的丑数M3
 * 从已有的丑数序列中，从小到大乘5得到第一个大于当前最大丑数M的丑数M5。
 * M2/M3/M5中选最小的加入丑数序列尾部。下一个丑数k+1，只可能从刚刚新生成丑数的指针+1的位置开始。
 * 其它两个指针不动，因为他们下一轮生成的丑数可能是最小的。
 * 重复上述过程，直到得到第N个丑数。
 *
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2021-01-10 16:26
 */
public class UglyNum {

    public int nthUglyNumber(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;

        int t2 = 0, t3 = 0, t5 = 0;
        int m2 = 1, m3 = 1, m5 = 1, m = 1;
        int[] f = new int[1690];
        f[0] = 1;
        for (int num = 1; num < n; ) {
            m2 = f[t2] << 1;
            m3 = f[t3] * 3;
            m5 = f[t5] * 5;
            m = Math.min(Math.min(m2, m3), m5);
            if (m == m2) t2++;
            else if (m == m3) t3++;
            else t5++;
            if (m > f[num-1]) {
                f[num++] = m;
            }
        }
        return f[n-1];
    }
}
