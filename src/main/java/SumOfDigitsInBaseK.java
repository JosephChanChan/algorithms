/**
 * lc 5738 easy
 *
 * Analysis:
 *  十进制n，转k进制数算法：n%k n/k
 *
 * 时间复杂度：O(log(k,n)) 以k为底，n的对数
 * 空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2021-04-25 15:35
 */
public class SumOfDigitsInBaseK {

    public int sumBase(int n, int k) {
        int ans = 0;
        if (k == 10) {
            while (n > 0) {
                ans += n%10;
                n = n/10;
            }
        }
        else {
            int v = 0;
            while (n >= k) {
                v = v*10 + (n%k);
                n = n/k;
            }
            if (n > 0) v = v*10 + n;

            while (v > 0) {
                ans += v%10;
                v = v/10;
            }
        }
        return ans;
    }
}
