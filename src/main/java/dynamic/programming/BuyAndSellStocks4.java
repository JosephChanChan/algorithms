package dynamic.programming;

/**
 * lc 188 hard
 *
 * Analysis:
 *  参考 BuyAndSellStocks3
 *
 * 时间复杂度：O(nk)
 * 空间复杂度：O(nk)
 *
 * @author Joseph
 * @since 2021-04-02 18:17
 */
public class BuyAndSellStocks4 {

    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        if (n == 0) return 0;
        int m = 2*k+2;
        int[][] f = new int[n+1][m];

        // 对于第一天所有阶段获利都是0
        for (int j = 1; j < m; j++) {
            f[1][j] = 0;
        }

        int max = 0;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j < m; j++) {
                if (j == 1) {
                    f[i][j] = f[i-1][j];
                }
                // 偶数都是持股阶段
                else if ((j&1) == 0) {
                    f[i][j] = Math.max(f[i-1][j-1], f[i-1][j]+(prices[i-1]-prices[i-2]));
                }
                // 卖出清仓阶段
                else {
                    f[i][j] = Math.max(f[i-1][j], f[i-1][j-1]+(prices[i-1]-prices[i-2]));
                    max = Math.max(max, f[i][j]);
                }
            }
        }
        return max;
    }
}
