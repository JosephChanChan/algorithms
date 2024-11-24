package greedy.algorithm;

/**
 * lc 122 medium
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

    /*
        第二次写
        f(i,j)为第i天结束后处于j状态的最大获利，j=0为空仓，j=1为持有
        按照题意分析状态转换，空仓 -> 持有，空仓 -> 空仓，持有 -> 持有，持有 -> 空仓
            f(i,0)=max{f(i-1,0), f(i-1,1)+(Pi-Pi-1) }
            f(i,1)=max{f(i-1,0), f(i-1,1)+(Pi-Pi-1) }
        会发现空仓和持有的状态转移是一样的，并且按照这个方程去写代码提交，也可以AC
        然后我把方程改成了：
            f(i)=max{f(i-1), f(i-1)+(Pi-Pi-1) }
        去掉了冗余的一维，一样可以AC，其实这个方程就变成了贪心的思想，只要第i天价格比i-1要高，就立即在i-1买入，i天卖出
     */
    int[] f ;
    public int maxProfit2(int[] prices) {
        int n = prices.length;
        f = new int[n+1];

        for (int i = 1; i <= n; i++) {
            int diff = i-2 < 0 ? 0 : prices[i-1]-prices[i-2];
            f[i] = Math.max(f[i-1], f[i-1] + diff);
            //System.out.println(String.format("第%s天，空仓获利=%s，持有获利=%s", i, f[i][0], f[i][1]));
        }
        return f[n];
    }
}
