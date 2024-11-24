package dynamic.programming;

/**
 * lc 265 hard
 *
 * Analysis:
 * f(i,j)前i间房子被粉刷且第i间是j颜色的最少代价
 * f(i,j)=min{f(i-1,k)+c[j] | k!=j}
 *
 * 边界：
 * f(0,j)=c[j]
 *
 * 转移方程本身需要时间O(nk^2)
 * 观察上一阶段状态只需要用到上一层的最小值和次小值，所以在每一阶段枚举本层的时候顺便记录下本层的最小值和次小值。
 * 在下一阶段就不用枚举本阶段的状态了。
 *
 * 时间复杂度：O(nk)
 * 空间复杂度：O(nk)
 *
 * @author Joseph
 * @since 2021-04-19 14:06
 */
public class PaintHouse2 {

    public int minCostII(int[][] costs) {
        int n = costs.length;
        int k = costs[0].length;
        int[][] f = new int[n][k];

        int min = k == 1 ? 0 : -1, minor = min;
        for (int j = 0; j < k; j++) {
            f[0][j] = costs[0][j];
            if (min == -1 || f[0][j] < f[0][min]) {
                minor = min;
                min = j;
            }
            else if (minor == -1 || f[0][j] < f[0][minor]) {
                minor = j;
            }
        }

        for (int i = 1; i < n; i++) {
            int curMin = k == 1 ? 0 : -1, curMinor = curMin;
            for (int j = 0; j < k; j++) {
                int v = j != min ? min : minor;

                f[i][j] = f[i-1][v]+costs[i][j];

                if (curMin == -1 || f[i][j] < f[i][curMin]) {
                    curMinor = curMin;
                    curMin = j;
                }
                else if (curMinor == -1 || f[i][j] < f[i][curMinor]) {
                    curMinor = j;
                }
            }
            min = curMin; minor = curMinor;
        }
        return f[n-1][min];
    }
}
