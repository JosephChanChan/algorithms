/**
 * lc 204 medium
 *
 * Analysis:
 *
 * 时间复杂度：O(N*log(logN))
 * 空间复杂度：O(N)
 *
 * @author Joseph
 * @since 2021-04-27 14:42
 */
public class CountPrimes {

    public int countPrimes(int n) {
        /*
            筛选[2,n]质数的算法：埃氏筛法
            埃氏筛的原理是：任何合数可以被写成唯一形式的质数乘积（合数做分解质因式），即合数一定有质因子
            假设x是质数，则2x 3x 4x ... Jx <= n 都是合数，因为有因子x
            假设[2,n]都是质数，从小到大遍历每个数i，i是质数则从它开始生成合数标记。
            从i开始乘，因为一个合数y小于i的话，它一定被小于i的另一个质数标记过。
        */
        if (n <= 1) return 0;

        // 题目要求小于n，别忘了
        boolean[] prime = new boolean[n];
        for (int i = 2; i < n; i++) prime[i] = true;

        int c = 0;
        for (int i = 2; i < n; i++) {
            if (prime[i]) {
                c++;
                // i是质数，那么i的倍数因为有因子i都是合数
                for (int j = (i<<1); j < n; j += i) {
                    prime[j] = false;
                }
            }
        }
        return c;
    }
}
