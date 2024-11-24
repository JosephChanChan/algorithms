package binary.search;

import java.util.Map;
import java.util.TreeMap;

/**
 * lc 729 medium
 *
 * Analysis:
 * 时间复杂度：book次数 * O(log(n))
 * 空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2022/7/6
 */
public class ScheduleDate {

    class MyCalendar {

        TreeMap<Integer, int[]> map = new TreeMap<>();

        public MyCalendar() {
        }

        public boolean book(int start, int end) {
            Map.Entry<Integer, int[]> e = map.ceilingEntry(start);
            if (null != e) {
                int[] p = e.getValue();
                if (isJoin(p[0], p[1]-1, start, end-1)) {
                    return false;
                }
            }
            e = map.floorEntry(start);
            if (null != e) {
                int[] q = e.getValue();
                if (isJoin(q[0], q[1]-1, start, end-1)) {
                    return false;
                }
            }
            map.put(start, new int[]{start, end});
            return true;
        }

        boolean isJoin(int k1, int k2, int t1, int t2) {
            return (t1 <= k1 && k1 <= t2) || (k1 <= t1 && t2 <= k2) || (k1 <= t1 && t1 <= k2);
        }
    }
}
