package greedy.algorithm;

/**
 * lc 134 medium
 *
 * Analysis:
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2022/2/5 3:43 PM
 */
public class GasStation {

    /*
        贪心。
        设从加油站x触发最多能到加油站k，x ... k，k+1
        k+1就是从x出发无法到达的加油站。
        下一次枚举起点有必要从x~k之间的任何一个加油站开始吗？没必要
        因为如果从x出发到达y，y是x~k之间的任意，剩余油量有2种情况，>0或=0，
        第一种情况，从x出发到达y，剩余油量>0再加上y的油量都到不了k+1，所以从y出发剩余油量是0加y油量更到不了k+1。
        第二种情况，从x出发到达y，剩余油量=0，等同于从y直接出发同样到不了k+1。

        所以基于以上分析，如果从x出发无法环绕一周，则下一次直接从k+1即第一个无法到的的加油站重新出发，才可能完成环绕。
    */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;

        for (int i = 0; i < n; ) {
            int[] ans = canFinished(i, gas, cost);
            if (ans[0] > 0) {
                return i;
            }
            i = ans[1];
        }
        return -1;
    }

    int[] canFinished(int p, int[] gas, int[] cost) {
        int[] ans = new int[2];
        int remain = 0, k = 0, n = gas.length;
        while (k < n) {
            int j = (p + k) % n;
            remain += gas[j];
            remain -= cost[j];
            if (remain < 0) {
                ans[0] = 0;
                // 下一次起点从p+k+1开始，p~k是可达的
                ans[1] = p + k + 1;
                return ans;
            }
            k++;
        }
        ans[0] = 1;
        return ans;
    }
}
