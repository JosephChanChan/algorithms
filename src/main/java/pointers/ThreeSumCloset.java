package pointers;

import java.util.*;

/**
 * leetcode 16 medium
 *
 * Analysis:
 * 1.还是双指针做法，枚举a
 * 2.从a开始左右枚举b,c。因为解只有一个，从左到右计算，a左边每个数肯定与a组合过，所以没必要重复计算
 * 3.打擂台更新minimum并记录更新的组合
 *  时间复杂度：O(n^2)
 *  空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2020-09-29 21:37
 */
public class ThreeSumCloset {

    int minimum = Integer.MAX_VALUE, ans ;

    public int threeSumClosest(int[] nums, int target) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];

        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            int a = nums[i];
            int l = i + 1, r = nums.length - 1;
            while (l < r) {
                int b = nums[l], c = nums[r];
                int sum = a + b + c;
                int diff = Math.abs(sum - target);
                if (diff < minimum) {
                    minimum = diff;
                    ans = sum;
                }
                if (sum == target) {
                    return sum;
                }
                else if (sum < target) {
                    while (l < r && b == nums[l]) l++;
                }
                else {
                    while (l < r && c == nums[r]) r--;
                }
            }
        }
        return ans;
    }

}
