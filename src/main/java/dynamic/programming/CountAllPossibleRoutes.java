package dynamic.programming;

import java.util.HashMap;
import java.util.Map;

/**
 * lc 1575 hard
 *
 * Analysis:
 *  记忆化搜索，递归树剪枝
 *  碰到没有明确方向的DP时，可以考虑记忆化搜索。
 *  思考问题自顶向下，画出递归树，看看哪些地方有重复计算的分支，如果有那很可能是记忆化搜索。
 *
 *  f(i,w)为还剩w个油从i点出发经过终点的路径数
 *  f(i, w-|i-j|)=sum{f(j, w), i!=j, |i-j|<=w} + i == e ? 1 : 0
 *  根据最后一步可以推导出上面公式，终点上一步肯定是从每个j点还剩足够的油走过来的
 *
 *  DP的本质就是解决重复计算的子问题，所以记忆化搜索也是DP一种表现形式。
 *
 * 时间复杂度：O(n^2*fuel)
 * 空间复杂度：O(n^2*fuel)
 *
 * @author Joseph
 * @since 2021-03-19 15:56
 */
public class CountAllPossibleRoutes {

    int s, e, f;
    double mod = 1e9+7;
    int[] ls = null;

    Map<Node, Integer> ans = new HashMap<>();

    public int countRoutes(int[] locations, int start, int finish, int fuel) {
        s = locations[start];
        e = locations[finish];
        f = fuel;
        ls = locations;
        Node n = new Node(s, f);
        return dfs(n);
    }

    // 一刷 120min
    int dfs(Node node) {
        if (node.j == e && node.w <= 1) {
            ans.put(node, 1);
            return 1;
        }

        if (node.j != e) {
            int cost = Math.abs(node.j - e);
            if (cost > node.w) {
                ans.put(node, 0);
                return 0;
            }
        }

        // 当前点j可达终点e
        long cases = node.j == e ? 1 : 0;
        for (int i = 0; i < ls.length; i++) {
            if (ls[i] == node.j) continue;
            if (node.w < Math.abs(node.j - ls[i])) continue;
            Node n = new Node(ls[i], node.w - Math.abs(node.j - ls[i]));
            if (ans.containsKey(n)) {
                cases += ans.get(n);
            }
            else {
                cases += dfs(n);
            }
        }
        ans.put(node, (int)(cases%mod));
        return (int)(cases%mod);
    }

    class Node {
        int j;
        int w;
        public Node(int j, int w) {
            this.j = j;
            this.w = w;
        }
        public int hashCode() {
            return (31 * j) * w;
        }
        public boolean equals(Object node) {
            if (!(node instanceof Node)) return false;
            Node n = (Node) node;
            return n.j == this.j && n.w == this.w;
        }
    }
}
