package greedy.algorithm;

/**
 * lc 122 easy
 *
 * Analysis:
 *  可以买卖多次，那当然是交易越多获利越多。
 * 所以对于每一个价格都尝试买入，什么时候卖出？
 * 当aj>aj+1时卖出。
 * aj+1立刻又买入，等待下一个aj时刻
 * 可以证明下这个策略是对的。
 * ai=1，aj=5>aj+1=4，ak=6
 * ai买入，如果选择后面的ak卖出，因为ak>aj可单次获利最大
 * 列不等式
 * ak-ai > aj-ai + ak-aj+1
 *  5 > 4+2
 * 显然错误
 * 化简后右边还有 aj-aj+1，因为aj > aj+1 所以显然右边的式子获利更大
 *
 * 时间复杂度：O(n^2)
 * 空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2021-03-25 17:55
 */
public class BuyAndSellStocks2 {

    public int maxProfit(int[] prices) {
        int n = prices.length;

        int max = 0;
        for (int i = 0; i < n; i++) {
            int s = prices[i];
            int sum = 0;
            for (int j = i+1; j < n; j++) {
                if (j == n-1) {
                    if (s < prices[j]) sum += prices[j] - s;
                }
                else if (prices[j] > prices[j+1]) {
                    sum += prices[j] - s;
                    s = prices[j+1];
                }
            }
            max = Math.max(max, sum);
        }
        return max;
    }
}
