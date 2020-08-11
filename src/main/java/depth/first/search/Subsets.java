package depth.first.search;

import java.util.ArrayList;
import java.util.List;

/**
 * leetcode 78 medium
 *
 * Question Description:
 *  参见 lc 78
 *
 * Analysis:
 *  dfs
 * 时间复杂度：O(2^n)
 * 空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2020-08-12 00:24
 */
public class Subsets {


    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        if (null == nums || nums.length == 0) return list;

        int i ;
        for (i = 1; i <= nums.length; i++) {
            dfs(nums, 0, i, new ArrayList<>(), list);
        }
        list.add(new ArrayList<>());
        return list;
    }

    private void dfs(int[] nums, int startIdx, int pickCount,
                     List<Integer> list, List<List<Integer>> lists) {
        if (list.size() == pickCount) {
            lists.add(new ArrayList<>(list));
            return;
        }
        if (startIdx == nums.length) return;

        int i ;
        for (i = startIdx; i < nums.length; i++) {
            list.add(nums[i]);
            dfs(nums, i + 1, pickCount, list, lists);
            list.remove(list.size() - 1);
        }
    }


}
