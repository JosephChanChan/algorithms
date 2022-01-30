package sweep.line;

import java.util.ArrayList;
import java.util.List;

/**
 * lc 57 medium
 *
 * Analysis:
 * 左到右遍历一次，当前区间对比每个区间，当前区间a，其他区间b
 * 会有3种关系：a&b 有交集，a&b 是包含关系，a&b 无交集
 * 有交集则合并a b，继续遍历
 * 包含关系，a包含b，b相当于被吞了不存在了。b包含a，则更新下a，后续工作是把原有区间收集起来就行
 * 无交集，把b收集起来
 *
 * 区间对比时有技巧，需要用短的对比长的，避免出现包含关系时判断错误。
 *
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2021-04-05 15:53
 */
public class InsertIntervals {

    public int[][] insert(int[][] intervals, int[] newInterval) {
        int[] now = newInterval;
        List<int[]> list = new ArrayList<>();

        int n = intervals.length;
        for (int i = 0; i < n; i++) {
            int[] p = intervals[i];
            // 看now和p有没有交集
            if (contact(now, p)) {
                now[0] = Math.min(now[0], p[0]);
                now[1] = Math.max(now[1], p[1]);
            }
            else {
                if (now[0] < p[0]) {
                    list.add(now);
                    now = p;
                }
                else {
                    list.add(p);
                }
            }
        }
        list.add(now);

        int[][] ans = new int[list.size()][2];
        for (int i = 0; i < list.size(); i++) {
            ans[i][0] = list.get(i)[0];
            ans[i][1] = list.get(i)[1];
        }
        return ans;
    }

    boolean contact(int[] a, int[] b) {
        if (a[0] <= b[0] && a[1] >= b[0]) return true;
        if (a[0] <= b[1] && b[1] <= a[1]) return true;
        if (a[1]-a[0] > b[1]-b[0]) {
            if (a[0] <= b[0] && a[1] >= b[1]) return true;
        }
        else {
            if (b[0] <= a[0] && b[1] >= a[1]) return true;
        }
        return false;
    }
}
