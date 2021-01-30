package depth.first.search;

import java.util.*;

/**
 * leetcode 46 medium
 *
 * Given a collection of distinct integers, return all possible permutations.
 * Example:
 * Input: [1,2,3]
 * Output:
 * [
 *   [1,2,3],
 *   [1,3,2],
 *   [2,1,3],
 *   [2,3,1],
 *   [3,1,2],
 *   [3,2,1]
 * ]
 *
 * Analysis:
 *  递归回溯法。思想就是每层递归都是给当前的i位置选择一个数，例如：[1,2,3] 第一个位置可选 1，2，3 选了1后递归第二个位置。
 *  第二个位置可选2，3，选2，第三个位置可选3。
 *  最后回溯到第一个位置时继续枚举2，递归第二个位置，可选的数有1，3。所以得有全局记录当前已选的数避免重复选择。
 *
 *  时间复杂度：O(A(n,n)) A(n,m)是排列公式。
 *  空间复杂度：o(n)
 *
 * @author Joseph
 * @since 2020-03-23 23:36
 */
public class Permutation {


    boolean[] vis ;
    List<Integer> list = new LinkedList<>();
    List<List<Integer>> ans = new LinkedList<>();

    public List<List<Integer>> permute(int[] nums) {
        int n = nums.length;
        if (n == 0) return ans;
        if (n == 1) {
            List<Integer> l = new LinkedList<>();
            l.add(nums[0]);
            ans.add(l);
            return ans;
        }
        vis = new boolean[n];
        dfs(nums);
        return ans;
    }

    private void dfs(int[] nums) {
        if (list.size() == nums.length) {
            ans.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!vis[i]) {
                vis[i] = true;
                list.add(nums[i]);
                dfs(nums);
                vis[i] = false;
                list.remove(list.size()-1);
            }
        }
    }

}
