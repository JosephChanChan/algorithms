package dynamic.programming;

/**
 * leetcode 322 medium
 *
 * Question Description:
 *  参见 lc 322
 *
 * Analysis:
 * 时间复杂度：O(amount * coins)
 * 空间复杂度：O(amount)
 *
 * @author Joseph
 * @since 2020-07-30
 */
public class CoinChange {

    /*
        最值型DP。
        如果存在一种最优解策略，最少的硬币序列构造出目标数的话，那么肯定存在最后一枚硬币ak。
        假设最少的硬币组合为 a1+a2+...+ak = amount
        它的子问题也是 a1+a2+...ak-1 = amount - ak，并且它的子问题也是最优的，如果存在比k-1枚硬币更少的组合凑出amount-ak
        那么这个组合加上ak就能凑出amount，与设定矛盾
        设状态 f(k) 为凑出k元的最少硬币数
        最后一枚硬币可能是 1、2、5元任何一个，那么上一个状态就是 k-1/k-2/k-5，选择最优的一个状态
            f(k) = min{f(k-1), f(k-2), f(k-5)} + 1
        边界：
            对于构造不出来的k，f(k)=无限大
            f(0)=0
     */

    /* AC 11ms faster than 89% */
    public int coinChange(int[] coins, int amount) {
        if (coins.length == 0) return 0;

        int k, j ;
        int[] f = new int[amount + 1];

        f[0] = 0;

        for (k = 1; k <= amount; k++) {
            int minimum = Integer.MAX_VALUE;
            for (j = 0; j < coins.length; j++) {
                if (k - coins[j] < 0) continue;
                if (f[k-coins[j]] < minimum) {
                    minimum = f[k-coins[j]];
                }
            }
            f[k] = minimum == Integer.MAX_VALUE ? minimum : minimum + 1;
        }
        return f[amount] == Integer.MAX_VALUE ? -1 : f[amount];
    }



}
