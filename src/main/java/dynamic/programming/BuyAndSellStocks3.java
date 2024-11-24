package dynamic.programming;

/**
 * lc 123 hard
 *
 * Analysis:
 * f(i,j)为前i天结束后，处于j阶段的最大价值
 * 阶段1：第一次买前
 * 阶段2：第一次买后，卖前
 * 阶段3：第一次卖后，第二次买前
 * 阶段4：第二次买后，卖前
 * 阶段5：第二次卖后
 * 持股的阶段，当每天结束后，第二天若继续持股要加上当天股票涨跌的获利
 *
 * f(i,2/4)=max{f(i-1,j-1), f(i-1,j)+(Pi-Pi-1)}
 * f(i,3/5)=max{f(i-1,j-1)+(Pi-Pi-1), f(i-1,j)}
 *
 * 边界：
 * f(1,1/2/3/4/5)=0，第1天结束后，无论处在阶段几都是没有获利的
 *
 * 时间复杂度：O(n)
 * 空间复杂度：O(n^2)
 *
 * @author Joseph
 * @since 2021-04-02 17:49
 */
public class BuyAndSellStocks3 {

    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[][] f = new int[n+1][6];

        f[1][1] = f[1][2] = f[1][3] = f[1][4] = f[1][5] = 0;

        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= 5; j++) {
                if (j == 1) {
                    f[i][j] = f[i-1][j];
                }
                else if (j == 2 || j == 4) {
                    f[i][j] = Math.max(f[i-1][j-1], f[i-1][j]+(prices[i-1]-prices[i-2]));
                }
                else if (j == 3 || j == 5) {
                    f[i][j] = Math.max(f[i-1][j], f[i-1][j-1]+(prices[i-1]-prices[i-2]));
                }
            }
        }
        return Math.max(f[n][1], Math.max(f[n][3], f[n][5]));
    }
}
