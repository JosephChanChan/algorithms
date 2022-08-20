package graphs;

import java.util.*;

/**
 * lc 2059 medium
 *
 * Analysis:
 * 时间复杂度：O(n*x) n是数组长度，x是题目限定的取值范围
 *  这样算：队列长度最多有x个数，每个数最多有3*n个操作分支
 * 空间复杂度：O(x)
 *
 * @author Joseph
 * @since 2022/8/1
 */
public class MinOperationConvertNum {

    /**
         这道题一开始想是不是DP方向，后来发现没有明显的子问题，更别说最优子结构
         倒是很符合dfs思路，每次枚举Ai然后计算3个操作符判断，继续递归，但是每层最多有3000个分支，太大了。
         而且dfs求最短路问题还有个致命弱点，就是如果在某条分支上找到了答案，但是不能保证目前分支是最优的，
         所以得搜完所有分支才能确定最短路。bfs是层层递进，可保证第一次触达的点是最短的

         双向bfs有希望可以缩小点空间，但剪枝条件太少，主要还是看数据强度
     */

    LinkedList<Integer> q = new LinkedList<>();
    LinkedList<Integer> p = new LinkedList<>();
    Set<Integer> qVis = new HashSet<>();
    Set<Integer> pVis = new HashSet<>();

    public int minimumOperations(int[] nums, int start, int goal) {
        int n = nums.length;

        q.add(start);
        qVis.add(start);
        p.add(goal);
        pVis.add(goal);

        int d = -1;
        while (!q.isEmpty() && !p.isEmpty()) {
            if (q.size() > p.size()) {
                LinkedList<Integer> t = p;
                p = q;
                q = t;

                Set<Integer> tVis = pVis;
                pVis = qVis;
                qVis = tVis;
            }

            d++;
            int c = q.size();
            for (int i = 0; i < c; i++) {
                int node = q.removeFirst();
                for (int j = 0; j < nums.length; j++) {
                    int b1 = node + nums[j];
                    int b2 = node - nums[j];
                    int b3 = node ^ nums[j];
                    if (pVis.contains(b1) || pVis.contains(b2) || pVis.contains(b3)) {
                        return d + 1;
                    }
                    if (valid(b1) && !qVis.contains(b1)) {
                        qVis.add(b1);
                        q.add(b1);
                    }
                    if (valid(b2) && !qVis.contains(b2)) {
                        qVis.add(b2);
                        q.add(b2);
                    }
                    if (valid(b3) && !qVis.contains(b3)) {
                        qVis.add(b3);
                        q.add(b3);
                    }
                }
            }
        }
        return -1;
    }

    boolean valid(int a) {
        return a >= 0 && a <= 1000;
    }
}
