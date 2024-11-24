package math;

import java.util.*;
import java.math.*;

/**
 * lc 2280 medium
 *
 * Analysis:
 * 时间复杂度：O(nlog(n))
 * 空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2022/9/24
 */
public class MinLinesRepresentLineChart {

    /**
        按时间升序排序后，计算斜率
     */

    int ans = 1;

    public int minimumLines(int[][] stockPrices) {
        int n = stockPrices.length;
        if (n <= 1) {
            return 0;
        }
        int[][] a = stockPrices;
        Arrays.sort(stockPrices, (o1, o2) -> o1[0] - o2[0]);

        for (int i = 1; i < n-1; i++) {
            boolean sameK = calcK(a[i-1][0], a[i-1][1], a[i][0], a[i][1], a[i+1][0], a[i+1][1]);
            if (!sameK) {
                ans++;
            }
        }
        return ans;
    }

    boolean calcK(int x, int y, int x1, int y1, int x2, int y2) {
        // 如果2条线的斜率相同则满足：
        // (y1-y)/(x1-y) = (y2-y1)/(x2-x1) 式子通分变为 (y1-y)*(x2-x1)=(y2-y1)*(x1-x)
        // 除法转乘法主要避免除法精度问题
        return ((long) y1 - y) * (x2 - x1) == ((long) y2 - y1)*(x1 - x);
    }

    // 老6题目，不单只考察思路，还考察计算精度，BigDecimal无法满足只能过77/82
    BigDecimal calcK(int x, int y, int x1, int y1) {
        return BigDecimal.valueOf(y1 - y).divide(BigDecimal.valueOf(x1 - x), 9, RoundingMode.HALF_UP);
    }
}
