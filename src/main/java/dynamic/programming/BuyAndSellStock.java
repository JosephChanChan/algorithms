package dynamic.programming;

/**
 * @author Joseph
 * @since 2019/8/18 22:33
 *
 * leetcode 121 question    easy
 *
 * Question Description:
 *  Say you have an array for which the ith element is the price of a given stock on day i.
 * If you were only permitted to complete at most one transaction (i.e., buy one and sell one share of the stock),
 * design an algorithm to find the maximum profit.
 * Note that you cannot sell a stock before you buy one.
 * Example 1:
 * Input: [7,1,5,3,6,4]
 * Output: 5
 * Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
 *              Not 7-1 = 6, as selling price needs to be larger than buying price.
 * Example 2:
 * Input: [7,6,4,3,1]
 * Output: 0
 * Explanation: In this case, no transaction is done, i.e. max profit = 0.
 *
 * Analysis:
 * 这题本身是不能直接用动态规划解的，力扣上归类为动态规划问题，我还想了好久的状态转移方程，推不出来，后来用暴力法水过去了。
 * 看了题解，了解到可以将原序列转化为一阶差分序列，这个差分序列的最大连续子段和就是原问题的最大利益。
 * 例如：
 * 7    1    5    3    6    4
 *  -6    4    -2   3    -2    一阶差分序列
 * 差分序列之间的和就是2个端点之间的差。回顾题目的本质，就是要找到一个最佳的买入点和最佳的卖出点，卖出点和买入点之间的差就是
 * 获取的利益，那么找到差分序列中最大的某一段连续子段和就是某2个顶点最大的差。
 * ok, 问题成功转化为之前学过的求最大连续子段和问题。
 *
 * 最大子序列和：
 * 设f(i)是以i为结尾的所有子序列中，和最大的那一个子序列，称作最优子序列，这个子序列必须包含至少一个元素。
 * （以i为结尾是必须包含i）
 * 首先要先认识到，肯定存在一个最优的子序列，这个最优子序列的终点假设是 k，则 k 在[1,n]之间。
 * 如果遍历 1~n 就是将每个子序列的最大和都计算了一遍。最后只需要将最大的那个 f(i) 记录下来就可以。
 * 计算 f(i)，f(i) 与 f(i-1) 的关系。f(i) 的设定是必须包含i，所以只需考虑f(i-1)，
 * 如果 f(i-1) 以i-1为终点的所有子序列和都是负数，那加上前面积累的负数只会更小。所以只取i就行。
 * 所以可以推导出：f(i) = max{a[i], f(i-1)+a[i]}
 *
 * 时间复杂度：O(2n)
 * 空间复杂度: O(1)
 */
public class BuyAndSellStock {

    public static void main(String[] args) {
        int[] prices = {7,1,5,3,6,4};
        BuyAndSellStock buyAndSellStock = new BuyAndSellStock();
        int result = buyAndSellStock.maxProfit(prices);
        System.out.println(result);
    }

    private int maxProfit(int[] prices) {
        // 初始序列只有小于2天的价格，无法交易
        if (prices.length < 2) {
            return 0;
        }
        prices = differenceSequence(prices);
        return calcMaxSubSequence(prices);
    }

    // 计算差分序列
    private int calcMaxSubSequence(int[] prices) {
        // -6    4    -2   3    -2
        // f(i) = max{a[i], f(i-1)+a[i]}
        // 差分序列全是负数时，返回0
        int max = 0, i1 = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] > prices[i] + i1) {
                i1 = prices[i];
            }
            else {
                i1 = prices[i] + i1;
            }
            if (i1 > max) {
                max = i1;
            }
        }
        return max;
    }

    // 生成差分序列
    private int[] differenceSequence(int[] original) {
        int[] difference = new int[original.length - 1];
        for (int i = 0; i < original.length - 1; i++) {
            difference[i] = original[i + 1] - original[i];
        }
        return difference;
    }

}
