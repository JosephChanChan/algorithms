package sweep.line;

import java.util.Arrays;

/**
 * lc 435 medium
 *
 * @author Joseph
 * @since 2023/8/27
 */
public class NoOverlappingIntervals {

    /*
        变形为在所有区间中选出最多的不重叠区间数量
        我们先按照左端点排序
        f(i)为以Ai为结尾的最大不重叠区间数量
        f(i)=max{f(j) | j < i && Ai[0] > Aj[1]}

        超时O(n^2)
    */
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 1) {
            return 0;
        }
        Arrays.sort(intervals, (o1, o2) -> {
            if (o1[0]==o2[0]) {
                return o1[1]-o2[1];
            }
            return o1[0]-o2[0];
        });
        int n = intervals.length;
        int[] f = new int[n];
        f[0] = 1;
        int ans = 0;
        for (int i = 1; i < n; i++) {
            int max = 0;
            for (int j = 0; j < i; j++) {
                if (intervals[j][1] <= intervals[i][0]) {
                    if (max < f[j]) {
                        max = f[j];
                    }
                }
            }
            f[i] = max+1;
            ans = Math.max(ans, f[i]);
        }
        return n-ans;
    }

    /*
        会议室问题变种，问题变形为找出最多不重叠区间数量。
        只有让区间尽可能早地结束，流出更多空间给后面的区间，才能有更多不重叠数量。
        贪心，按照右端点排序
     */
    public int eraseOverlapIntervals2(int[][] intervals) {
        if (intervals.length == 1) {
            return 0;
        }
        Arrays.sort(intervals, (o1, o2) -> {
            return o1[1]-o2[1];
        });
        int ans = 0;
        int p = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            if (p > intervals[i][0]) {
                ans++;
            }
            else {
                p = intervals[i][1];
            }
        }
        return ans;
    }
}
