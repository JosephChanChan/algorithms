package dynamic.programming;

import java.util.List;

/**
 * lc 120 medium
 *
 * Analysis:
 *  最优策略中必然有最后一步(i,j)，由于每步只能向下走，上一步是(i,j)相邻的坐标(i-1, j-1)和(i-1, j)
 * 根据题目定义选出min{(i-1, j-1), (i-1,j)}即可。
 *
 * f(i,j)为以(i,j)结尾的最小路V径和。
 * f(i,j)=min{f(i-1,j-1), f(i-1,j)} + (i,j)
 *
 * 边界：f(0,0)=(0,0)
 *
 * 时间复杂度：O(n^2)
 * 空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2020-05-10 15:17
 */
public class MinimumPathInTriangle {

    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[][] f = new int[2][n];

        int old = 0, now = 1;
        f[now][0] = triangle.get(0).get(0);

        for (int i = 1; i < n; i++) {
            old = 1-old; now = 1-now;
            List<Integer> row = triangle.get(i);
            for (int j = 0; j < row.size(); j++) {
                if (j == 0) {
                    f[now][j] = f[old][0] + row.get(j);
                }
                else if (j == row.size()-1) {
                    f[now][j] = f[old][j-1] + row.get(j);
                }
                else {
                    f[now][j] = Math.min(f[old][j], f[old][j-1]) + row.get(j);
                }
            }
        }
        int min = Integer.MAX_VALUE;
        for (int j = 0; j < n; j++) {
            min = Math.min(min, f[now][j]);
        }
        return min;
    }
}
