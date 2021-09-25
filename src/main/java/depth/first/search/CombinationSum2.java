package depth.first.search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * lc 40 medium
 *
 * Analysis:
 * 这里的去重操作可以理解为：
 * [1, 2, 2, 3]
 *     k
 *    i-1 i
 *
 *      1
 *     / \
 *    2  2'
 *   /
 *  3
 *
 * 在第二层级，是从k开始枚举取数，已经取过i-1=2这个数了，第二层级以2为数字向下一级继续搜索。
 * 组成了 [1 2 3]
 * 接着i++ 到了2'，如果第二层级继续以2'为数字向下搜索，会出现[1 2' 3]这种重复组合
 * 所以去重的关键在于，在同一层级不允许重复选择之前出现过的数字，因为之前出现过的数字已经和下一级的所有可能的组合 组合过了。
 *
 * 时间复杂度：O(2^n)
 * 空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2021-04-11 22:58
 */
public class CombinationSum2 {

    int t ;
    int[] a ;
    List<List<Integer>> ans = new ArrayList();

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        t = target;
        a = candidates;

        Arrays.sort(a);

        dfs(0, 0, new ArrayList());
        return ans;
    }

    void dfs(int k, int sum, List<Integer> list) {
        if (sum == t) {
            ans.add(new ArrayList(list));
            return;
        }
        if (k == a.length) return;

        for (int i = k; i < a.length; i++) {
            if (sum + a[i] > t) break;
            // 去重的精髓，不允许同一层级重复选择之前已经选择过的数字
            if (i > 0 && a[i] == a[i-1] && k < i) continue;

            list.add(a[i]);
            dfs(i+1, sum + a[i], list);
            list.remove(list.size()-1);
        }
    }
}
