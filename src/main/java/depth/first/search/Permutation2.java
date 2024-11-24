package depth.first.search;

import java.util.*;

/**
 * 剑指Offer 38 medium & lc 47
 *
 * Analysis:
 *  这题回溯有两种解法。
 *  普通回溯法：
 *  1.排序，让相同的字母相邻方便后期去重。
 *  2.按顺序枚举给每个位置枚举可选的字母，要靠变量标记某个字母是否选过。此外对于 b b' 这种重复字母去重。
 *  3.如果b没选，选择了b'，代表b不在当前递归分支上。又因为以b开头的排列已经枚举过了，如果选b'作为排列开头继续枚举则重复了。
 *  如果b已选，再选b'代表b和b'同时在当前递归分支上即 [a,b,b'] 这种排列是可以的，[a,b',b]则重复了。
 *
 *  交互回溯法，固定某个字母开头，继续递归剩下的字母。这种解法对于去重的不好写。
 *
 * @author Joseph
 * @since 2020-12-05 23:27
 */
public class Permutation2 {

    boolean[] vis ;
    List<List<Integer>> ans = new ArrayList();

    public List<List<Integer>> permuteUnique(int[] nums) {
        /*
            1 1' 2
            会发现根据题目要求，同层级间不能重复选之前选过的数
        */
        if (nums.length == 0) return ans;

        Arrays.sort(nums);

        vis = new boolean[nums.length];

        dfs(nums, new ArrayList());
        return ans;
    }

    void dfs(int[] a, List<Integer> mem) {
        if (mem.size() == a.length) {
            ans.add(new ArrayList(mem));
            return;
        }

        for (int i = 0; i < a.length; i++) {
            if (i > 0 && a[i-1] == a[i] && !vis[i-1]) {
                continue;
            }

            vis[i] = true;
            mem.add(a[i]);
            dfs(a, mem);
            vis[i] = false;
            mem.remove(mem.size()-1);
        }
    }

}
