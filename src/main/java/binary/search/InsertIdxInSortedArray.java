package binary.search;

/**
 * lc 35 easy
 *
 * Analysis:
 *  二分模板题
 *
 * 时间复杂度：O(logN)
 * 空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2021-02-08 21:52
 */
public class InsertIdxInSortedArray {

    public int searchInsert(int[] nums, int target) {
        int l = 0, r = nums.length-1;

        while (l + 1 < r) {
            int m = ((r-l)>>1) + l;
            if (nums[m] == target) return m;
            if (nums[m] > target) {
                r = m;
            }
            else {
                l = m;
            }
        }
        if (nums[l] == target || nums[r] == target) return nums[l] == target ? l : r;
        if (target > nums[r]) return r+1;
        if (target > nums[l]) return l+1;
        return l;
    }
}
