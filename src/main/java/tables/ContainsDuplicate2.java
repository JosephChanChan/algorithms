package tables;

import java.util.HashSet;
import java.util.Set;

/**
 * lc 219 easy
 *
 * Analysis:
 *  i&j的差的绝对值，代表2个点之间的距离。
 * 那么从i走k步的范围内遇到相同的数则返回OK
 * 这给我们一个提示，维护一个k大小的窗口用常数时间判断窗口内是否存在与当前ai相同的数
 *
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2021-08-07 15:28
 */
public class ContainsDuplicate2 {

    public boolean containsNearbyDuplicate(int[] nums, int k) {
        if (null == nums || nums.length <= 1 || k == 0) return false;

        Set<Integer> vis = new HashSet();
        for (int i = 0; i < nums.length; i++) {
            if (vis.contains(nums[i])) return true;
            if (vis.size() == k) {
                vis.remove(nums[i-k]);
            }
            vis.add(nums[i]);
        }
        return false;
    }
}
