package dynamic.programming;

/**
 * lc 213 medium
 *
 * Analysis:
 * 破圈。最优策略可能是不偷第一个房子，那么我考虑2~n的房子计算一遍。
 * 最优策略也可能是不偷最后一个房子，那么我考虑1~n-1的房子计算一遍，取两种策略的较大值。
 *
 * f(i,j)偷前i间房子且第i间偷或不偷的最大获利，j=0第i间不偷，j=1第i间偷
 * f(i,0)=max{f(i-1,1), f(i-1,0)}
 * 这里有点反直觉的是，对于第i间房子我决定不偷，不一定意味着一定偷i-1间房子，有可能i-2房子最多获利。比如：
 * 1 100  2     1
 *   i-2  i-1   i
 * 所以要考虑取前一阶段两种状态最好的。
 *
 * f(i,1)=f(i-1,0)+a[i]
 *
 * 边界：
 * 偷1~n-1房子时，这里第一间房子从0开始
 * f(0,0)=0
 * f(0,1)=a[0]
 * 偷2~n房子时，第一间从1开始
 * f(0,0)=0
 * f(0,1)=a[1]
 *
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2021-04-15 15:16
 */
public class HouseRobber2 {

    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;
        if (n == 1) return nums[0];

        // 考虑偷 1~n-1间房子
        int[][] f = new int[n-1][2];
        f[0][0] = 0;
        f[0][1] = nums[0];
        for (int i = 1; i < nums.length-1; i++) {
            f[i][0] = Math.max(f[i-1][1], f[i-1][0]);
            f[i][1] = f[i-1][0]+nums[i];
        }

        int max = Math.max(f[n-2][0], f[n-2][1]);

        // 考虑偷 2~n 间房子
        f[0][0] = 0;
        f[0][1] = nums[1];
        for (int i = 2, j = 1; i < nums.length; i++, j++) {
            f[j][0] = Math.max(f[j-1][1], f[j-1][0]);
            f[j][1] = f[j-1][0]+nums[i];
        }
        return Math.max(max, Math.max(f[n-2][0], f[n-2][1]));
    }
}
