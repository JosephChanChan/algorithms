package binary.search;

/**
 * lc 81 medium
 *
 * Analysis:
 *
 * 时间复杂度：O(logN)
 * 空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2021-02-08 00:23
 */
public class SearchRotatedSortedArray2 {

    public boolean search(int[] nums, int target) {
        int n = nums.length;
        int l = 0, r = n-1;
        // 因为依靠nums[0]划分数组，使得具备二分性，如果nums[l]==nums[r]就无法判断是在数组的前半部分还是后半部分
        while (l < r && nums[l] == nums[r]) r--;

        while (l + 1 < r) {
            int m = (l+r) >> 1;
            if (nums[m] == target) {
                return true;
            }
            // 依据nums[0]判断二分性，当nums[0]==nums[r]时无法判断
            if (nums[m] >= nums[0]) {
                if (target >= nums[0] && target < nums[m]) {
                    r = m;
                }
                else {
                    l = m;
                }
            }
            else {
                if (target >= nums[0] || target < nums[m]) {
                    r = m;
                }
                else {
                    l = m;
                }
            }
        }
        if (nums[l] == target) return true;
        if (nums[r] == target) return true;
        return false;
    }
}
