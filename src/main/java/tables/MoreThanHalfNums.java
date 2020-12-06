package tables;

import java.util.*;

/**
 * 剑指Offer 39 easy & lc 169
 *
 * Analysis:
 *  哈希表记录每个数字的频率，打个擂台就好。如果数字的范围不大可以直接用位数组思想更快。
 *
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2020-12-06 17:20
 */
public class MoreThanHalfNums {

    int max = 0, count = 0;
    Map<Integer, Integer> map = new HashMap<>();

    public int majorityElement(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int c = map.getOrDefault(nums[i], 0);
            if (c + 1 > count) {
                count = c + 1;
                max = nums[i];
            }
            if (count > (nums.length >> 1)) break;
            map.put(nums[i], c+1);
        }
        return max;
    }
}
