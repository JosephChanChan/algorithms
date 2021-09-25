package depth.first.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * lc 399 medium
 *
 * Analysis:
 *  关键是把每个关系式抽象成有向图，变量是顶点，商是边权值。
 *  a/b=2
 *  a->b 权值2
 *  b->a 权值1/2
 *
 *  b/c=3
 *  b->c 权值3
 *  c->b 权值1/3
 *
 *  a/c ?
 *  a->b->c a是2b，b是3c，所以a=2b=2(3c)=6c
 *  a/c=6
 *
 * 两个变量之间比值有传递关系，a和b有比值关系，b和c有比值关系，a和c便具有比值关系。
 * 所以也可以用并查集做。
 *
 * N是图中的顶点数，query是问题数，每次query都可能会遍历一次图
 * 时间复杂度：图深搜 O(query*N) 并查集 O(query*logN)
 * 空间复杂度：图深搜 O(N^2) 并查集 O(N)
 * @author Joseph
 * @since 2021-03-21 16:23
 */
public class EvaluateDivision {


    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        return graph(equations, values, queries);
    }

    Map<Integer, List<Double[]>> g = new HashMap<>();
    Map<String, Integer> map = new HashMap<>();

    public double[] graph(List<List<String>> equations, double[] values, List<List<String>> queries) {
        // 将字符串映射成ID 方便编码
        int id = 0;
        for (List<String> e : equations) {
            if (!map.containsKey(e.get(0))) {
                map.put(e.get(0), id++);
            }
            if (!map.containsKey(e.get(1))) {
                map.put(e.get(1), id++);
            }
        }
        // 邻接表表示图 a/b=2 a=2b b=(1/2)a
        for (int i = 0; i < equations.size(); i++) {
            List<String> e = equations.get(i);
            double w = values[i];
            int a = map.get(e.get(0));
            int b = map.get(e.get(1));
            List<Double[]> list = g.getOrDefault(a, new ArrayList<>());
            list.add(new Double[]{(double)b, w});
            g.put(a, list);

            list = g.getOrDefault(b, new ArrayList<>());
            list.add(new Double[]{(double)a, 1/w});
            g.put(b, list);
        }

        // a/b 从a出发深搜b，a/b就是a到b的路径上的权重积
        double[] ans = new double[queries.size()];
        for (int i = 0; i < ans.length; i++) {
            List<String> query = queries.get(i);
            Integer a = map.get(query.get(0));
            Integer b = map.get(query.get(1));
            if (null == a || null == b) {
                ans[i] = -1.0d;
            }
            else {
                ans[i] = dfs(a, b, 1.0d, new boolean[id])[1];
            }
        }
        return ans;
    }

    double[] dfs(int node, int end, double w, boolean[] vis) {
        List<Double[]> list = g.get(node);
        for (Double[] d : list) {
            if ((double)end == d[0]) {
                return new double[]{1.0d, w *= d[1]};
            }
            if (vis[d[0].intValue()]) continue;
            vis[d[0].intValue()] = true;
            double[] ans = dfs(d[0].intValue(), end, w * d[1], vis);
            if (ans[0] == 1.0d) return ans;
            vis[d[0].intValue()] = false;
        }
        return new double[]{0.0d, -1.0d};
    }



    int[] root ;
    double[] weight ;

    double[] unionFind(List<List<String>> equations, double[] values, List<List<String>> queries) {
        // 设等式有n个，如果所有等式变量都不同，最多会有2n个变量
        root = new int[2 * equations.size()];
        weight = new double[2 * equations.size()];

        // 将变量字符串映射成id 数字，方便编码
        int id = 0;
        for (List<String> e : equations) {
            if (!map.containsKey(e.get(0))) {
                map.put(e.get(0), id++);
            }
            if (!map.containsKey(e.get(1))) {
                map.put(e.get(1), id++);
            }
        }

        // 初始化并查集，每个变量初始父类指向自己，并且权值1
        for (Map.Entry<String, Integer> e : map.entrySet()) {
            root[e.getValue()] = e.getValue();
            weight[e.getValue()] = 1;
        }

        // 根据题目给出的 a/b=w 合并两个变量间的倍数关系
        for (int i = 0; i < values.length; i++) {
            union(map.get(equations.get(i).get(0)), map.get(equations.get(i).get(1)), values[i]);
        }

        // 计算两个变量间的比值关系，如果两个变量不在一个集合内
        // 代表找不到一个中间变量来统一两个变量的比值关系，这样的a/b是未知的
        double[] ans = new double[queries.size()];
        for (int i = 0; i < ans.length; i++) {
            Integer a = map.get(queries.get(i).get(0));
            Integer b = map.get(queries.get(i).get(1));
            if (null == a || null == b) {
                ans[i] = -1.0d;
            }
            else {
                ans[i] = query(a, b);
            }
        }
        return ans;
    }

    /*
        w=a/b
    */
    void union(int a, int b, double w) {
        int p = find(a);
        int q = find(b);
        if (p != q) {
            root[p] = q;
            weight[p] = w * weight[b] / weight[a];
        }
    }

    int find(int a) {
        if (root[a] == a) return a;
        int r = find(root[a]);
        if (r != root[a]) {
            // 父亲节点指向祖父节点的倍数关系 * 自己指向父亲节点的倍数关系
            weight[a] = weight[a] * weight[root[a]];
            root[a] = r;
        }
        return r;
    }

    /*
        a和b不在一个集合内，无法换算
        a和b在一个集合内，已知a/c=x b/c=y 那么就可以将a b换算成c来计算a/b的比值
        a=cx
        b=cy
        a/b=x/y
     */
    double query(int a, int b) {
        int p = find(a);
        int q = find(b);
        if (p != q) return -1.0d;
        return weight[a]/weight[b];
    }
}
