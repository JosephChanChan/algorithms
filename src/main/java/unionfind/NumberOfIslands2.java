package unionfind;

import java.util.*;

/**
 * lc 305 hard
 *
 * Analysis:
 *  将每个岛抽象成集合，当每次添加一个陆地时，上下左右合并相邻的岛。
 * 同时维护岛屿数
 *
 * 时间复杂度：O(m*n+k) k是positions 操作数量
 * 空间复杂度：O(m*n)
 *
 * @author Joseph
 * @since 2021-01-31 14:35
 */
public class NumberOfIslands2 {

    int count = 0;
    int[] root ;
    int[][] islands ;
    int[][] d = {{1, 0}, {-1, 0}, {0, -1}, {0, 1}};
    List<Integer> ans ;

    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        root = new int[m*n];
        for (int i = 0; i < root.length; i++) root[i] = i;

        islands = new int[m][n];
        ans = new ArrayList<>(positions.length);

        for (int i = 0; i < positions.length; i++) {
            if (islands[positions[i][0]][positions[i][1]] == 0) {
                count++;
                int a = positions[i][0] * n + positions[i][1];
                islands[positions[i][0]][positions[i][1]] = 1;

                // 合并上下左右4个方向的岛
                for (int j = 0; j < d.length; j++) {
                    int r = positions[i][0] + d[j][0];
                    int c = positions[i][1] + d[j][1];
                    if (r >= 0 && r < m && c >= 0 && c < n && islands[r][c] == 1) {
                        int b = r * n + c;
                        union(a, b);
                    }
                }
            }
            ans.add(count);
        }
        return ans;
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
