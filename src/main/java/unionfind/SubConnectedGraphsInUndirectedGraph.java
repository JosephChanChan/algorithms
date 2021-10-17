package unionfind;

/**
 * lc 323 medium
 *
 * Analysis:
 *  并查集模板题
 *
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2021-01-31 14:49
 */
public class SubConnectedGraphsInUndirectedGraph {

    int count ;
    int[] root ;

    public int countComponents(int n, int[][] edges) {
        count = n;
        root = new int[n];
        for (int i = 0; i < n; i++) root[i] = i;

        for (int i = 0; i < edges.length; i++) {
            int a = edges[i][0];
            int b = edges[i][1];
            union(a, b);
        }
        return count;
    }

    private int find(int k) {
        if (root[k] == k) return k;
        return root[k] = find(root[k]);
    }

    private void union(int a, int b) {
        int p = find(a);
        int q = find(b);
        if (p != q) {
            count--;
            root[p] = q;
        }
    }
}
