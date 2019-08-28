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
 * 这个最优子序列有2种情况。1：只包含i一个元素。2：包含了i和i前面的多个元素。
 * 这2种情况选择出最优的一种，就是 f(i) = max{a[i], f(i-1)+a[i]}
 *
 */
public class BuyAndSellStock {

    public static void main(String[] args) {
        int[] prices = {7,1,5,3,6,4};
        BuyAndSellStock buyAndSellStock = new BuyAndSellStock();
        int result = buyAndSellStock.maxProfit(prices);
        System.out.println(result);
    }

    private int maxProfit(int[] prices) {
        prices = differenceSequence(prices);
        return calcMaxSubSequence(prices);
    }

    private int calcMaxSubSequence(int[] prices) {
        int max = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] > max + prices[i]) {
                max = prices[i];
            }
            else {
                max = max + prices[i];
            }
        }
        return max;
    }

    private int[] differenceSequence(int[] original) {
        if (original.length == 1) {
            return original;
        }
        int[] difference = new int[original.length - 1];
        for (int i = 0; i < original.length - 1; i++) {
            difference[i] = original[i + 1] - original[i];
        }
        return difference;
    }
















}
