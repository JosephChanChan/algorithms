package depth.first.search;

import java.util.ArrayList;
import java.util.List;

/**
 * lc 216 medium
 *
 * Analysis:
 *
 * 时间复杂度：O(2^k)
 * 空间复杂度：O(k)
 *
 * @author Joseph
 * @since 2021-04-12 10:34
 */
public class CombinationSum3 {

    int t, k ;
    List<List<Integer>> ans = new ArrayList();

    public List<List<Integer>> combinationSum3(int k, int n) {
        /*
            1.解集中不能有重复组合
            2.组合中不能有重复数字

            为满足1&2，每一层枚举只能从 i+1开始
        */
        if (k == 0 || n == 0) return ans;

        this.t = n;
        this.k = k;

        dfs(1, 0, new ArrayList());
        return ans;
    }

    void dfs(int idx, int sum, List<Integer> mem) {
        if (k == mem.size()) {
            if (sum == t) {
                ans.add(new ArrayList(mem));
            }
            return;
        }

        for (int i = idx; i <= 9; i++) {
            if (sum + i > t) return;

            mem.add(i);
            dfs(i+1, sum + i, mem);
            mem.remove(mem.size()-1);
        }
    }
}
