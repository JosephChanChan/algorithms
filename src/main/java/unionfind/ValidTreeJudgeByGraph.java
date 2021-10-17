package unionfind;

/**
 * lc 261 medium
 *
 * Analysis:
 *  并查集在建的过程中，将每条边合并起来。
 * 一棵树最终是无环的，有环则是非法的。
 * 怎么判断是否有环？
 * 现有节点a&b，a&b同属一个集合内，处理到某条边[a,b]代表a连接b，
 * 这时候就是有环了。
 * 因为a&b同属一个集合，代表有一个点k1能到a，现在a连接b，那么就可以从k1->a->b，b也有一条到根的路径，
 * 再从根到k1，就形成环路。
 * 有无可能本身a就连接b？没可能，因为题目说不会出现重复边。
 *
 * 时间复杂度：O(n^2)，最坏时任意一个顶点和其余n-1个点连接，则有 (n-1)+(n-2)+...+1 条边
 * 空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2021-01-31 10:58
 */
public class ValidTreeJudgeByGraph {

    int[] root ;
    int count ;

    public boolean validTree(int n, int[][] edges) {
        root = new int[n];
        count = n;
        for (int i = 0; i < n; i++) root[i] = i;

        for (int i = 0; i < edges.length; i++) {
            int a = edges[i][0];
            int b = edges[i][1];
            if (union(a, b)) return false;
        }
        return count == 1;
    }

    private int find(int k) {
        if (root[k] == k) return k;
        return root[k] = find(root[k]);
    }

    private boolean union(int a, int b) {
        int p = find(a);
        int q = find(b);
        if (p != q) {
            count--;
            root[p] = q;
            return false;
        }
        return true;
    }
}
