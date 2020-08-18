package binary.search;

/**
 * leetcode 153 medium
 *
 * Question Description:
 *  参见 lc 153
 *
 * Analysis:
 * 时间复杂度：最好O(logN) 最差O(n)
 * 空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2020-07-31 17:15
 */
public class FindMinimumInRotatedSortedArray {

    public int findMin(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];

        return binarySearch(0, nums.length-1, nums);
    }

    /* AC 0ms faster than 100% */
    private int binarySearch(int l, int r, int[] nums) {
        if (l >= r) return nums[l];
        int m = (l + r) >> 1;
        if (m > 0 && nums[m-1] > nums[m]) {
            return nums[m];
        }
        if (m < nums.length-1 && nums[m] > nums[m+1]) {
            return nums[m+1];
        }
        return Math.min(binarySearch(l, m - 1, nums), binarySearch(m + 1, r, nums));
    }

    /* AC 0ms faster than 100% */
    private int loop(int[] nums) {
        int i, minimum = Integer.MAX_VALUE;
        for (i = 0; i < nums.length; i++) {
            if (minimum > nums[i]) minimum = nums[i];
        }
        return minimum;
    }
}
