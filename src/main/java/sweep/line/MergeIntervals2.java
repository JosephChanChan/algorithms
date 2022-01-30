package sweep.line;

import java.util.Arrays;

/**
 * lc 253 medium
 *
 * Analysis：
 *
 * 时间复杂度：O(n)
 * 空间复杂度：O(2n)
 *
 * @author Joseph
 * @since 2021-03-07 00:01
 */
public class MergeIntervals2 {

    public int minMeetingRooms(int[][] intervals) {
        int n = intervals.length;
        // n个会议，每个有开始和结束时间，共有2n个事件，二维 0=开始时间/结束时间，1=事件标志位，0=开始，1=结束
        int[][] e = new int[2*n][2];
        for (int i = 0; i < n; i++) {
            e[i<<1][0] = intervals[i][0];
            e[i<<1][1] = 0;
            e[(i<<1)+1][0] = intervals[i][1];
            e[(i<<1)+1][1] = 1;
        }

        // 将事件发生的时间顺序排序
        Arrays.sort(e, (o1, o2) -> {
            // 两个事件事件重叠，因为题目要求，重叠时间不算冲突，所以结束事件需排前面
            if (o1[0] == o2[0]) {
                return o1[1] == 1 ? -1 : 1;
            }
            return o1[0]-o2[0];
        });

        int max = 0, c = 0;
        for (int i = 0; i < e.length; i++) {
            if (e[i][1] == 0) {
                c++;
                max = Math.max(c, max);
            }
            else {
                c--;
            }
        }
        return max;
    }
}
