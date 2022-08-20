package tables;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

/**
 * lintcode 519 easy
 *
 * Analysis:
 *  一致性哈希雏形
 *
 * @author Joseph
 * @since 2022-01-31 21:59
 */
public class ConsistentHash {

    TreeSet<Range> ranges = new TreeSet<>((o1, o2) -> {
        int s1 = o1.right - o1.left;
        int s2 = o2.right - o2.left;
        if (s1 == s2) {
            return o2.machine - o1.machine;
        }
        return s1 - s2;
    });

    /*
     * @param n: a positive integer
     * @return: n x 3 matrix
     */
    public List<List<Integer>> consistentHashing(int n) {
        // write your code here
        List<List<Integer>> ans = new ArrayList<>();

        initialize();

        if (n == 1) {
            convert(ans);
            return ans;
        }

        for (int i = 2; i <= n; i++) {
            // 先取最大的区间，相同再取编号小的机器
            Range max = ranges.pollLast();
            int left = max.left;
            int right = max.right;
            int mid = (left + right) >> 1;

            max.left = left;
            max.right = mid;
            ranges.add(max);

            Range newRange = new Range(mid+1, right, i);
            ranges.add(newRange);
        }
        convert(ans);
        return ans;
    }

    void convert(List<List<Integer>> ans) {
        for (Range range : ranges) {
            List<Integer> list = new ArrayList<>();
            list.add(range.left);
            list.add(range.right);
            list.add(range.machine);
            ans.add(list);
        }
    }

    void initialize() {
        ranges.add(new Range(0, 359, 1));
    }

    class Range {
        int left;
        int right;
        int machine;
        public Range(int l, int r, int m) {
            this.left = l;
            this.right = r;
            this.machine = m;
        }
        public int hashCode() {
            return left * right * machine * 31;
        }
        public boolean equals(Object o) {
            Range p = (Range) o;
            return left == p.left && right == p.right && machine == p.machine;
        }
    }
}
