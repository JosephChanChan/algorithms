package recursion;

import java.util.HashMap;
import java.util.Map;

/**
 * leetcode 70 easy & 剑指Offer 10
 *
 * Question Description:
 *  You are climbing a stair case. It takes n steps to reach to the top.
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 * Note: Given n will be a positive integer.
 * Example 1:
 * Input: 2
 * Output: 2
 * Explanation: There are two ways to climb to the top.
 * 1. 1 step + 1 step
 * 2. 2 steps
 * Example 2:
 * Input: 3
 * Output: 3
 * Explanation: There are three ways to climb to the top.
 * 1. 1 step + 1 step + 1 step
 * 2. 1 step + 2 steps
 * 3. 2 steps + 1 step
 *
 * Analysis:
 *  有2种解法，递推和递归。递推就是动态规划解法，递归就是自顶向下的普通解法，加上备忘录，时间复杂度可以媲美动态规划。
 *  这里就用递归+备忘录。
 *  根据题意可知，当前的阶梯都是由前一阶梯或前二阶梯走上来的。假设当前是第3级阶梯，则上一阶梯可能是第1级阶梯或第2级阶梯，
 *  则无论是在第1级还是第2级阶梯，都只需要再走一步，这一步可能是跨1级阶梯或跨2级阶梯到达第3级阶梯。
 *  所以走到第3级阶梯的走法是 第1级阶梯的走法 + 第2级阶梯的走法 的和。
 *  设立 f(n) 是走到第n级阶梯的所有走法，则 f(3) = f(1) + f(2)，假设 f(2)=k f(1)=j。
 *  以上公式可以看作是递推关系式，也是递归关系式。
 *  则 f(3) 一共有 k+j 种走法。
 *  边界：f(0) = 0, f(1) = 1, f(2) = 2
 *
 *  时间复杂度：O(n)
 *  空间复杂度：O(n)
 *
 *  @author Joseph
 *  @since 2019-09-21 20:43
 */
public class ClimbStairs {


    public static void main(String[] args) {
        ClimbStairs climbStairs = new ClimbStairs();
        int i = climbStairs.climbStairs(5);
        System.out.println(i);
    }


    private int climbStairs(int n) {
        Map<Integer, Integer> memorandum = new HashMap<>();
        return doCalc(n, memorandum);
    }

    private int doCalc(int num, Map<Integer, Integer> memorandum) {
        if (num == 0) return 0;
        if (num == 1) return 1;
        if (num == 2) return 2;

        if (null != memorandum.get(num)) {
            return memorandum.get(num);
        }
        int val = doCalc(num - 1, memorandum) + doCalc(num - 2, memorandum);
        memorandum.put(num, val);
        return val;
    }
}
