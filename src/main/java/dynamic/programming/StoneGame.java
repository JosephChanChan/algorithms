package dynamic.programming;

/**
 * lc 877 medium
 *
 * Analysis:
 * f(i,j)为剩下区间i~j的石子时，作为先手能取到的最大价值
 * f(i,j)=max{ai+min{f(i+2,j), f(i+1,j-1)}, aj+min{f(i+1,j-1), f(i,j-2)}}
 *
 * 边界：
 * f(i,j)=ai, i==j
 * f(i,j)=max{ai, aj}, i+1==j
 *
 * 时间复杂度：O(n^2)
 * 空间复杂度：O(n^2)
 *
 * @author Joseph
 * @since 2021-03-31 18:27
 */
public class StoneGame {

    public boolean stoneGame(int[] piles) {
        int n = piles.length;
        int[][] f = new int[n][n];

        for (int i = 0; i < n; i++) {
            f[i][i] = piles[i];
        }
        for (int i = 0; i < n-1; i++) {
            f[i][i+1] = Math.max(piles[i], piles[i+1]);
        }

        for (int len = 3; len <= n; len++) {
            for (int i = 0; i <= n-len; i++) {
                // f(i,j)=max{ai+min{f(i+2,j), f(i+1,j-1)}, aj+min{f(i+1,j-1), f(i,j-2)}}
                int j = i+len-1;
                f[i][j] = piles[i]+Math.min(f[i+2][j], f[i+1][j-1]);
                f[i][j] = Math.max(f[i][j], piles[j]+Math.min(f[i+1][j-1], f[i][j-2]));
            }
        }
        // 作为先手面对完整的区间时取到的价值如果比后手能取到的多，就必胜
        return f[0][n-1] > f[1][n-1] || f[0][n-1] > f[0][n-2];
    }
}
