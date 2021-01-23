package pointers;

/**
 * 剑指Offer 57 easy
 *
 * Analysis:
 * 双指针。p&q分别指向数组头和尾。
 * p+q<t，p右移。
 * p+q>t，q左移。
 *
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2021-01-17 16:36
 */
public class TwoSum {

    public int[] twoSum(int[] nums, int target) {
        int[] ans = new int[2];
        int p = 0, q = nums.length-1;
        while (p < q) {
            if (nums[p] + nums[q] == target) {
                ans[0] = nums[p];
                ans[1] = nums[q];
                break;
            }
            if (nums[p] + nums[q] < target) {
                p++;
            }
            else {
                q--;
            }
        }
        return ans;
    }
}
