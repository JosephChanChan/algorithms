package binary.search;

import java.util.TreeSet;

/**
 * lc 220 medium
 *
 * @author Joseph
 * @since 2021-08-15 15:05
 */
public class ContainsDuplicate3 {

    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (null == nums || nums.length == 0 || k <= 0) return false;
        return binary(nums, k, t);
    }

    /**
     * 暴力法慢在每次窗口移动后要花k的时间检查。
     * 如果每次logK时间找到和u最接近的数就能知道窗口内是否存在一个数在[u-t, u+t]
     *
     * 时间复杂度：O(N*logK)
     * 空间复杂度：O(1)
     */
    boolean binary(int[] a, int k, int t) {
        int i = 0, j = 1, n = a.length;
        TreeSet<Long> windows = new TreeSet<>();
        windows.add((long) a[i]);
        while (j < n) {
            long u = a[j];
            // 对于abs(nums[i] - nums[j]) <= t 其实就是距离的概念。等价于在窗口内找到[u-t, u+t]范围内的数
            // 直接考虑窗口内和u最接近的2个数：小于等于u的最大的数l、大于等于u的最小的数r
            Long l = windows.floor(u);
            Long r = windows.ceiling(u);
            if (null != l && Math.abs(u-l) <= t) return true;
            if (null != r && Math.abs(u-r) <= t) return true;
            if (windows.size() == k) {
                windows.remove((long) a[i++]);
            }
            windows.add((long) a[j++]);
        }
        return false;
    }

    /**
     * 暴力朴素解法，最坏情况窗口长度k到n时，时间是n^2
     * 时间复杂度：(n-k+1)*k
     * 空间复杂度：O(1)
     */
    boolean plain(int[] a, int k, int t) {
        int i = 0, j = 1, n = a.length;
        while (j < n) {
            if (check(i, j, a, t)) return true;
            if (Math.abs(i-j) == k) {
                i++;
            }
            j++;
        }
        return false;
    }

    boolean check(int i, int j, int[] a, int t) {
        // 从j往前计算，匹配有abs(nums[i] - nums[j]) <= t?
        for (int p = i; p < j; p++) {
            if (((long) Math.abs((long) a[p]- (long) a[j])) <= t) return true;
        }
        return false;
    }
}
