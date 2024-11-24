package dynamic.programming;

/**
 * lc 1140 medium
 *
 * Analysis:
 * f(i,j)为剩i~n-1个石子时，M为j能取到的最大价值
 * 对于当前先手，能取的数量为 1<=x<=2j
 * 如果 i+2j-1 >= n-1 可以全部取完，让对手取不到
 * 如果 i+2j-1 < n-1 取不完，对手可以取 (i+x)~n-1
 * 对手肯定会取最多的石子，所以当前先手只能取到 sum(i,n-1)-f(i+x, Math.max(j, x))
 *
 * if i+2j-1 >= n-1
 *     f(i,j)=sum(i,j)
 * if i+2j-1 < n-1
 *     f(i,j)=max{sum(i,j)-f(i+x,Math.max(j,x)) | 1<=x<=2j}
 *
 * 边界：
 * 从后往前计算 n-1 最小，0最大
 * f(n-1,j)=an-1
 * f(n-2,j)=an-1+an-2
 *
 * 时间复杂度：O(n^3)
 * 空间复杂度：O(n^2)
 *
 * @author Joseph
 * @since 2021-03-31 23:03
 */
public class StoneGame2 {

    public int stoneGameII(int[] piles) {
        int n = piles.length;

        int[][] f = new int[n][n+1];

        // stones随着i越往前，保存的是石头的后缀和
        int stones = 0;
        for (int i = n-1; i >= 0; i--) {
            stones += piles[i];
            for (int j = 1; j <= n; j++) {
                // 可以全部取完剩下石子，让对手取0
                if (i+2*j-1 >= n-1) {
                    f[i][j] = stones; continue;
                }
                for (int x = 1; x <= 2*j; x++) {
                    // 当先手从i开始取x个石子时，对手从i+x开始取，显然对手会取能取到最大的
                    // 先手只能取到i~n的后缀和 - f(i+x,j or x)
                    f[i][j] = Math.max(f[i][j], stones-f[i+x][Math.max(j,x)]);
                }
            }
        }
        return f[0][1];
    }
}
