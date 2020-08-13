package depth.first.search;

import java.util.*;

/**
 * leetcode 90 medium
 *
 * Question Description:
 *  参见 lc 90
 *
 * Analysis:
 * 时间复杂度：小于O(2^n)
 * 空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2020-08-12 00:30
 */
public class Subsets2 {

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        if (null == nums || nums.length == 0) return list;

        Arrays.sort(nums);

        dfs(nums, 0, new ArrayList<>(), list);
        return list;
    }
    private void dfs(int[] nums, int startIdx, List<Integer> list, List<List<Integer>> lists) {
        lists.add(new ArrayList<>(list));

        int i ;
        for (i = startIdx; i < nums.length; i++) {
            // 去重的精髓
            if (i-1 >= 0 && nums[i-1] == nums[i] && i > startIdx) continue;
            list.add(nums[i]);
            dfs(nums, i + 1, list, lists);
            list.remove(list.size() - 1);
        }
    }

}
