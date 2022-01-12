package greedy.algorithm;

import java.util.Arrays;

/**
 * lc 5735 medium
 *
 * Analysis:
 * 表面看是01背包，实际数据范围限死了，背包内存超限制。
 * 这题是贪心。要买尽可能多的雪糕，那就用钱先从价值最小的开始买起。
 *
 * 时间复杂度：O(N*logN)
 * 空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2021-04-18 22:33
 */
public class MostIceCream {

    public int maxIceCream(int[] costs, int coins) {
        int n = costs.length;
        if (n == 0) return 0;

        long sum = 0;
        for (int i = 0; i < n; i++) sum += costs[i];
        if (sum <= coins) return n;

        Arrays.sort(costs);

        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (coins >= costs[i]) {
                ans++;
                coins -= costs[i];
                continue;
            }
            break;
        }
        return ans;
    }
}
