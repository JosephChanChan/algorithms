package dynamic.programming;

/**
 * lc 714 medium
 *
 * Analysis:
 * f(i,j)前i天处于阶段j的最大获利
 * j=1，空仓阶段，今天卖出时要扣手续费
 * j=2，持股阶段，每天持股结束后要加上今天的股票涨跌价格
 *
 * 空仓->空仓(今天继续观望)/持股(今天刚买入)
 * 持股->持股(加上股票涨跌获利情况)/空仓(今天卖出了，扣手续费)
 *
 * f(i,1)=max{f(i-1,1), f(i-1,2)+(Pi-Pi-1-fee)}
 * f(i,2)=max{f(i-1,1), f(i-1,2)+(Pi-Pi-1)}
 *
 * 边界：
 * f(1,1/2)=0
 *
 * 观察转移方程，第二维阶段存在直接循环依赖，但是第一维还是递推的，整体具备顺序性
 *
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2021-04-02 20:11
 */
public class BuyAndSellStocksWithFee {

    public int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        int[][] f = new int[n+1][3];

        f[1][1] = f[1][2] = 0;

        for (int i = 2; i <= n; i++) {
            f[i][1] = Math.max(f[i-1][1], f[i-1][2]+(prices[i-1]-prices[i-2]-fee));
            f[i][2] = Math.max(f[i-1][1], f[i-1][2]+(prices[i-1]-prices[i-2]));
        }
        return f[n][1];
    }
}
