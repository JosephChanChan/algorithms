package dynamic.programming;

import java.util.PriorityQueue;

/**
 * lc 1738 medium
 *
 * Analysis:
 * 时间复杂度：O(nm*log(nm))
 * 空间复杂度：O(nm)
 *
 * @author Joseph
 * @since 2022/3/19
 */
public class FindKthLargestXORCoordinateVal {

    public int kthLargestValue(int[][] matrix, int k) {
        /**
             有重复子问题且子问题的解就是最优解，dp
             f(i,j)为从原点到(i,j)的坐标异或结果
             f(i,j)=f(i-1,j)^f(i,j-1)^f(i-1,j-1)^a(i,j)
         */
        int n = matrix.length;
        int m = matrix[0].length;

        // 容量k的最小堆
        PriorityQueue<Integer> q = new PriorityQueue<>(k);

        int[][] f = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0 && j == 0) {
                    f[0][0] = matrix[0][0];
                }
                else if (i == 0) {
                    f[0][j] = f[0][j-1] ^ matrix[0][j];
                }
                else if (j == 0) {
                    f[i][0] = f[i-1][0] ^ matrix[i][0];
                }
                else {
                    f[i][j] = f[i-1][j] ^ f[i][j-1] ^ f[i-1][j-1] ^ matrix[i][j];
                }
                if (q.size() < k) {
                    q.add(f[i][j]);
                }
                else if (q.peek() < f[i][j]) {
                    q.poll();
                    q.add(f[i][j]);
                }
                //System.out.println(i+" "+j+" "+f[i][j]);
            }
        }
        return q.peek();
    }
}
