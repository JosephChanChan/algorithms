package dynamic.programming;

/**
 * lc 256 medium
 *
 * Analysis:
 *  对于n间房子，存在一种最优策略对每间房子选择一个颜色。
 * a1 a2 ... an-1 an 对于最后一步就是第n间房子选择什么颜色，它选什么颜色还会影响到前面的n-1的颜色。
 * 如果n是红色，n-1只能从蓝色和绿色中选择一个。n是蓝色，n-1则从红色和绿色中选择。
 * 而总体的代价是n选择红色的花费+ n-1选择蓝色或绿色的花费，然后从中选择出花费较小的组合。
 *
 * f(i,j)前i间房子刷完并且第i间房子刷成j颜色的最小代价
 * f(i,j)=min{f(i-1,k1), f(i-1,k2) | k1!=k2!=j} + costs[i][j]
 *
 * 边界：
 * 第一间房子刷成 红、蓝、绿的代价
 * f(0,0)=costs[0][0]
 * f(0,1)=costs[0][1]
 * f(0,2)=costs[0][2]
 *
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2020-05-02 15:55
 */
public class PaintHouse {


    public int minCost(int[][] costs) {
        int n = costs.length;
        if (n == 0) return 0;
        if (n == 1) return Math.min(costs[0][0], Math.min(costs[0][1], costs[0][2]));

        int old = 0, now = 1;
        int[][] f = new int[2][3];
        f[now][0] = costs[0][0];
        f[now][1] = costs[0][1];
        f[now][2] = costs[0][2];

        for (int i = 1; i < n; i++) {
            old = 1-old; now = 1-now;
            for (int j = 0; j < 3; j++) {
                if (j == 0) {
                    f[now][j] = Math.min(f[old][1], f[old][2]) + costs[i][j];
                }
                else if (j == 1) {
                    f[now][j] = Math.min(f[old][0], f[old][2]) + costs[i][j];
                }
                else {
                    f[now][j] = Math.min(f[old][0], f[old][1]) + costs[i][j];
                }
            }
        }
        return Math.min(f[now][0], Math.min(f[now][1], f[now][2]));
    }
}
