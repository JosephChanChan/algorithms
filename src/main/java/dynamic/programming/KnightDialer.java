package dynamic.programming;

/**
 * lc 935 medium
 *
 * Analysis:
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2022/9/17
 */
public class KnightDialer {

    /**
         DP
         刚看的时候觉得很复杂，要模拟象棋的跳跃然后计算n-1次跳出的路径数？题目说了方案数很大，不可能模拟
         那么思考方向应该是数学分析找规律一类的题目
         其实对于每个数字，从它开始跳去其它格子都是固定好了的，并且从其它格子跳到它这里也是固定好了的
         比如数字7，只能从2或6跳到7，如果知道了数字2、数字6的方案数，再跳到7的方案数就等于 2+6的方案数
            f(i,j)为用j次跳到i的方案数
            f(i,j)=sum{ f(k,j-1) | canJump(k, i)}
         边界：
            f(i,1)=1
     */

    long mod = (long) 1e9 + 7;
    long[][] f ;
    // 对于每个数字可以从哪里跳到它
    int[][] jump = new int[][]{
            {4, 6}, {8, 6}, {7, 9}, {4, 8}, {3, 9, 0}, {}, {7, 0, 1}, {2, 6}, {1, 3}, {2, 4}};

    public int knightDialer(int n) {
        //System.out.println(mod);
        if (n == 1) {
            return 10;
        }
        f = new long[10][n+1];
        for (int i = 0; i < 10; i++) {
            f[i][1] = 1;
        }
        long ans = 0;
        for (int j = 2; j <= n; j++) {
            for (int i = 0; i < 10; i++) {
                // f(i,j)=sum{ f(k,j-1) | canJump(k, i)}
                int[] pres = jump[i];
                for (int preIdx : pres) {
                    f[i][j] = (f[i][j] + f[preIdx][j-1]) % mod;
                }
                if (j == n) {
                    ans = (ans + f[i][j]) % mod;
                }
            }
        }
        return (int) ans;
    }
}
