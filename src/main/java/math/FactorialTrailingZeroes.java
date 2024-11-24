package math;

/**
 * lc 172 medium
 *
 * Analysis:
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2022/12/28
 */
public class FactorialTrailingZeroes {

    int five = 0, ans = 0;

    /*
        多少个尾数0，就是有多少因子10，10可以分解因式为且只能2*5。
        所以n!，看1-n的每个数字分解因式，看有多少个2和5的因子，2和5的数量较小值可以组成多少个2*5
        而因子5数量肯定比因子2数量要少，因为每个偶数都有因子2，而一个数只有是5的倍数才有因子5，所以只要枚举数字的因子5的数量
     */

    public int trailingZeroes(int n) {
        while (n > 1) {
            int a = n;
            while (a % 5 == 0) {
                five++;
                a /= 5;
            }
            n--;
        }
        return five;
    }
}
