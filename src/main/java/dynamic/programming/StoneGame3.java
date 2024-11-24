package dynamic.programming;

/**
 * lc 1406 hard
 *
 * Analysis:
 *  博弈+序列DP
 *
 * 题目要求维护Alice（简称A）Bob（B）各自得到的和。
 * A目前为止取得的石子和是X，B是Y。
 * A和B都维护一个自己的和与对手的和的差，A是Da，B是Db。
 * Da=X-Y Db=Y-X
 * A肯定是使Da最大化，同理B。
 *
 * 先手A对于当前i~n石子可以取1/2/3个石子，设取了j个石子价值m
 * Da = m-Db
 *
 * f(i)为先手面对i~n的石子时，能获得的最大石子价值和对手的最大石子价值的差
 * f(i)=max{sum(i,j) - f(i+j)| 1<=j<=3}
 *
 * 边界：
 * f(i,j)=sum(i,j), i+j-1 >= n-1 全部取完剩下的石子
 *
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2021-04-01 13:10
 */
public class StoneGame3 {

    public String stoneGameIII(int[] stoneValue) {
        int n = stoneValue.length;
        int[] f = new int[n];

        int[] sum = new int[n];
        sum[n-1] = stoneValue[n-1];
        for (int i = n-2; i >= 0; i--) {
            sum[i] += sum[i+1] + stoneValue[i];
        }

        for (int i = n-1; i >= 0; i--) {
            int stones = 0;
            f[i] = Integer.MIN_VALUE;
            for (int j = 1; j <= 3; j++) {
                // 从i~n的石子全部取完
                if (i+j-1 >= n-1) {
                    f[i] = Math.max(f[i], sum[i]); continue;
                }
                // 从i开始取j个石子，让对手取i+j~n的石子
                // 对手在i+j~n能取到的和我的石子价值的差
                // 反过来就是我和对手的石子的价值差
                stones += stoneValue[i+j-1];
                f[i] = Math.max(f[i], stones-f[i+j]);
            }
        }
        if (f[0] > 0) return "Alice";
        else if (f[0] == 0) return "Tie";
        return "Bob";
    }
}
