package dynamic.programming;

/**
 * lc 309 medium
 *
 * Analysis:
 *  首先需要分析下根据题目条件，最后一天结束后可以处于什么阶段？
 * ”没有买、观望、空仓“阶段，这一阶段表示的是没有做任何动作
 * ”持股“阶段，这一阶段表示今天刚买入，或之前就已买入，今天继续持有股票
 * ”冷冻“阶段，这一阶段表示今天刚卖出，所以进入了冷冻
 *
 * 继续分析阶段之间的关系。
 *         买        卖
 * ”未持股“ -> ”持股“ -> ”未持股且冷冻“
 *  阶段1      阶段2     阶段3
 * 如果冷冻阶段直接进入持股阶段会出现直接的循环依赖，是不行的。
 * 所以冷冻阶段进入没有买阶段，让它们变成间接依赖。
 *
 * f(i,j)为前i天结束后处于j阶段的最大获利
 * 今天处于观望，昨天也可以处于观望，或昨天处于冷冻因为刚把股票卖出
 * f(i,1)=max{f(i-1,1), f(i-1,3)}
 *
 * 今天持有股票，昨天也可以持有股票，那么今天股票涨了或跌了都要加上今天的这份盈利情况
 * 或者昨天处于观望阶段，今天才刚买入
 * f(i,2)=max{f(i-1,1), f(i-1,2)+(P[i]-P[i-1])}
 *
 * 今天处于冷冻阶段，代表昨天是持股阶段，今天刚卖了
 * f(i,3)=f(i-1,2)+(P[i]-P[i-1])
 *
 * 仔细观察转移方程会发现第二维表示阶段，会出现间接循环依赖问题，但是没关系，第一维是具备方向性的，所以整体具备方向性。
 *
 * 边界：
 * 负无穷代表此阶段不存在
 * f(0,1)=0    f(0,2)=f(0,3)=负无穷
 *
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2021-03-26 12:31
 */
public class BuyAndSellStocksWithCooldown {

    public int maxProfit(int[] prices) {
        int n = prices.length;
        int old = 0, now = 1;
        int[][] f = new int[2][4];
        f[now][1] = 0;
        f[now][2] = Integer.MIN_VALUE;
        f[now][3] = Integer.MIN_VALUE;

        for (int i = 1; i <= n; i++) {
            old = 1-old; now = 1-now;
            f[now][1] = Math.max(f[old][1], f[old][3]);
            f[now][2] = Math.max(f[old][1], f[old][2]+((i-2) < 0 ? 0 : prices[i-1]-prices[i-2]));
            f[now][3] = f[old][2]+((i-2) < 0 ? 0 : prices[i-1]-prices[i-2]);
        }
        // 最后一天必须处于清仓阶段
        return Math.max(f[now][1], f[now][3]);
    }
}
