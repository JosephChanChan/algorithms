package sweep.line;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * lc 56 medium
 *
 * Analysis：
 *  扫描线。先将起点排序，保存最近的一个区间，遍历区间时判断ai区间是否在最近的区间内，
 *  如果重叠了就更新最近的区间的结束位置为两者间较远的。
 *  没有重叠就更新最近的区间为ai
 *
 * 时间复杂度：O(N*logN)
 * 空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2021-03-02 23:39
 */
public class MergeIntervals {

    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (o1, o2) -> o1[0]-o2[0]);

        int n = intervals.length;
        int[] lastest = intervals[0];
        List<Integer[]> list = new ArrayList<>();
        for (int i = 1; i < n; i++) {
            if (intervals[i][0] <= lastest[1]) {
                lastest[1] = Math.max(lastest[1], intervals[i][1]);
            }
            else {
                list.add(new Integer[]{lastest[0], lastest[1]});
                lastest = intervals[i];
            }
        }
        list.add(new Integer[]{lastest[0], lastest[1]});
        int[][] ans = new int[list.size()][2];
        for (int i = 0; i < ans.length; i++) {
            ans[i][0] = list.get(i)[0];
            ans[i][1] = list.get(i)[1];
        }
        return ans;
    }
}
