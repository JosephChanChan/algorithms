package unionfind;

/**
 * 并查集模板。
 * 并查集适合解决集合合并，判断元素间的关系，是否存在交集。
 *
 * @author Joseph
 * @since 2021-01-30 20:17
 */
public class UnionFindTemplate {

    int[] root ;

    public UnionFindTemplate(int n) {
        root = new int[n+1];
        for (int i = 0; i < root.length; i++) root[i] = i;
    }

    /**
     * 找元素k的根节点。要确保k在集合中，否则要加判断。
     * 均摊时间是O(1)
     *
     * @param k 元素
     * @return root of k
     */
    public int find(int k) {
        if (root[k] == k) return k;
        // 路径压缩，当前k的父节点指向最终找到的根节点
        return root[k] = find(root[k]);
    }

    /**
     * 合并两个元素所在的集合
     * 时间O(1)
     *
     * @param a 元素
     * @param b 元素
     */
    public void union(int a, int b) {
        int p = find(a);
        int q = find(b);
        if (p != q) {
            root[p] = q;
        }
    }


}
