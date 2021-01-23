package dynamic.programming;

/**
 * 剑指Offer 63 medium & lc 121 easy
 *
 * Analysis；
 *  想想在最后一天卖出的时候能获得的最大利润，就是拿最后一天的股票价格减去前面某天买入的股票价格
 * 当然要去选择最低的一条股票价格。
 * b(i)是前i天买入的最低股票价格，如果第i天股票价格<b(i-1)，那自然b(i)=a[i]
 * 否则b(i)=b(i-1)
 * 然后我们只需要去看在哪天卖出利润最大就好
 * 边界：b(1)=a[1]
 *
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2021-01-21 20:55
 */
public class MaxProfitForStock {


    public int maxProfit(int[] prices) {
        int maxP = 0;
        if (prices.length <= 1) return maxP;

        int[] b = new int[prices.length];
        b[0] = prices[0];
        for (int i = 1; i < b.length; i++) {
            b[i] = Math.min(b[i-1], prices[i]);
        }
        for (int i = 1; i < b.length; i++) {
            if (prices[i] > b[i-1]) {
                maxP = Math.max(maxP, prices[i] - b[i-1]);
            }
        }
        return maxP;
    }
}
