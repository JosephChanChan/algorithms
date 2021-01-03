package dynamic.programming;

/**
 * 剑指Offer 46 medium
 *
 * Analysis:
 *  a1...an-2 an-1 an
 * 最后一段可以是 an/(an-1,an)，就看哪一段的方案数最大
 * 对于an，可能可以和an-1组合(an-1,an)，如果和an-1组合则最后一段是(an-1,an)
 * 最后一段的方案数是上一段 an-2的方案数
 * 如果an不能和an-1组合，则最后一段是an，上一段是 an-1，最后一段方案数是an-1的方案数。
 * 所以对于可以和an-1组合的，代表上一段即可以是 an-1 也可以是 an-2
 * 最后一段就应该是 an-1+an-2的方案数
 * 设f(n)是前n个数字划分的方案数
 * f(n)=max{f(n-1), f(n-2) && concat(an, an-1)}
 * 边界：f(0)=0, f(1)=1
 *
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 *
 * @author Joseph Chan
 * @since 2020-04-23
 */
public class DecodeWays {

    public int translateNum(int num) {
        char[] c = String.valueOf(num).toCharArray();
        int n = c.length;

        int[] f = new int[n];
        f[0] = 1;

        for (int i = 1; i < n; i++) {
            int k = f[i-1];
            if ((c[i-1] == '1' || c[i-1] == '2') && ((c[i-1] == '2' && c[i] <= '5') || (c[i-1] == '1'))) {
                k += i-2 >= 0 ? f[i-2] : 1;
            }
            f[i] = k;
        }
        return f[n-1];
    }
}