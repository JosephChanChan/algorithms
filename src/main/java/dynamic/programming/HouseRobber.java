package dynamic.programming;

/**
 * @author Joseph
 * @since 2019/8/27 14:44
 *
 * leetcode 198 easy
 *
 * Question Description:
 * You are a professional robber planning to rob houses along a street.
 * Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that
 * adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.
 * Given a list of non-negative integers representing the amount of money of each house,
 * determine the maximum amount of money you can rob tonight without alerting the police.
 * Example 1:
 * Input: [1,2,3,1]
 * Output: 4
 * Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
 *              Total amount you can rob = 1 + 3 = 4.
 * Example 2:
 * Input: [2,7,9,3,1]
 * Output: 12
 * Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
 *              Total amount you can rob = 2 + 9 + 1 = 12.
 *
 * Analysis:
 * 状态是可能影响下一个步骤决策的因素
 * 决策：对于每一间房子可以选择偷与不偷与不能偷
 * 这个状态就是偷了第i间房子会影响到第i+1间房子，i+1间房子就不能偷
 * 所以对于第i间房子，决策有2种情况，偷：偷了第i-2间房子后，直接跳过i-1，去偷i房子。
 * 不偷：偷了i-1后直接跳过i房子。
 * 我们对于第i间房子只能做这2种情况，所以只需要看哪种情况获利最大。
 * 设 f(i) 为偷前i间房子能获得的最大利益。
 * 得到状态转移方程 f(i) = max{f(i-2) + a[i], f(i-1)}
 * 最优子结构：第i间房子的最优解要么是从i-2的最优解过来的，要么是从i-1最优解过来的，
 * 每一个母问题的最优解是从子问题的最优解推导出来的，所以满足最优子结构特性。
 * 无后效性：某个阶段的状态一旦确定，该状态以后的过程演变和这个阶段以前的状态和决策无关，
 * 只和当前状态有关。如 f(i) 一旦确定后，对于偷i+1还是i+2只和当前的i有关，
 * 因为如果决定偷i，那么只能跳过i+1，去偷i+2，所以能影响到i后面的，只有当前对i的决策。
 *
 * 时间复杂度：O(n)，空间复杂度 O(1)
 * 有趣的是，在leetcode提交时，这个算法测了很多次都只是消耗 0ms 就AC了，比100%的提交都要快
 * 但是内存的使用却是 34mb，比 100% 的提交消耗内存都要多。
 *
 */
public class HouseRobber {


    public static void main(String[] args) {
        int[] nums = {2, 1, 1, 2};
        HouseRobber houseRobber = new HouseRobber();
        int profit = houseRobber.rob(nums);
        System.out.println(profit);
    }

    private int rob(int[] nums) {
        int i2 = 0, i1 = 0, max = 0;

        // f(i) = max{f(i-2) + a[i], f(i-1)}

        for (int i = 0; i < nums.length; i++) {
            i1 = getRobedValue(i-1, i1);
            i2 = getRobedValue(i-2, i2) + nums[i];
            int cmp = Math.max(i1, i2);
            if (max < cmp) {
                max = cmp;
            }
            i2 = i1;
            i1 = cmp;
        }

        if (nums.length == 0) {
            return 0;
        }
        return max;
    }

    private int getRobedValue(int house, int i) {
        if (house < 0) {
            return 0;
        }
        return i;
    }




}
