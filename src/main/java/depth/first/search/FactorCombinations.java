package depth.first.search;

import java.util.ArrayList;
import java.util.List;

/**
 * lc 254 medium
 *
 * Analysis:
 *
 * 时间复杂度：带剪枝的dfs难以计算
 * 空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2021-04-08 12:05
 */
public class FactorCombinations {

    List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> getFactors(int n) {
        /*
            所有因子组合即搜索类问题
            因子从最小2开始，枚举f能模除n的话，f有可能是构造n的一个因式组合之一
            下一层就要构造 n/f 的积

            对于任意层要构造的a，√a*√a=a 如果f > √a 那么本层选择f，下一层也要从f开始枚举，结果必定 > a
            所以本层对于要构造a，f只需要枚举到√a
        */
        if (n <= 1) return ans;

        dfs(2, n, new ArrayList());
        // 删除最后一个n
        ans.remove(ans.size()-1);
        return ans;
    }

    void dfs(int lastF, int remain, List<Integer> mem) {
        if (lastF > remain) return;

        for (int f = lastF; f < remain; f++) {
            // 只需要枚举到 remain 的平方根
            if (f*f > remain) break;

            if (remain % f == 0) {
                mem.add(f);
                dfs(f, remain/f, mem);
                mem.remove(mem.size()-1);
            }
        }
        mem.add(remain);
        ans.add(new ArrayList(mem));
        mem.remove(mem.size()-1);
    }


    /*
        一个更简单的解法，不过时间复杂度更高
        总体思想还是一样，对于每层都要构造n，所以枚举f看f是否n的因子。
        但是为了避免重复，每层的f从上一层的f开始枚举，比如上一层f是4，则本层也从4开始枚举，
        这是避免重复的原理之一，每一层不能倒回去选之前选过的一些数字。
     */

    List<List<Integer>> ans2 = new ArrayList();

    public List<List<Integer>> getFactors2(int n) {
        if (n <= 2) return ans2;

        dfs(2, n, new ArrayList());
        return ans2;
    }

    void dfs2(int k, int n, List<Integer> mem) {
        if (n == 1) {
            if (mem.size() > 1) ans2.add(new ArrayList(mem));
            return;
        }

        for (int i = k; i <= n; i++) {
            if (n % i == 0) {
                mem.add(i);
                dfs(i, n/i, mem);
                mem.remove(mem.size()-1);
            }
        }
    }
}
