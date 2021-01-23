import java.util.*;

/**
 * 剑指Offer 61 easy
 *
 * Analysis:
 * 简单的分析题。需要注意的是连续的定义是每个数字i与i+1的差必须是1
 *
 * 时间复杂度：O(N*logN)
 * 空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2021-01-18 22:53
 */
public class IsStraightNums {

    public boolean isStraight(int[] nums) {
        Arrays.sort(nums);
        int zero = 0, diff = 0;
        // 从第一个非0数字开始，i与i+1的差必须为1，且中间缺的数可以被0填平
        int i = 0;
        for ( ; i < nums.length; i++) {
            if (nums[i] != 0) break;
            zero++;
        }
        for ( ; i < nums.length-1; i++) {
            int d = nums[i+1] - nums[i];
            if (d == 0) return false;
            diff += d-1;
        }
        return diff <= zero;
    }
}
