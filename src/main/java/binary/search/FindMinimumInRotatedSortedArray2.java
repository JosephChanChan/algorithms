package binary.search;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * leetcode 154 hard & 剑指Offer 11 easy
 *
 * Question Description:
 *  参见 lc 154
 *
 * Analysis:
 * 时间复杂度：去重花费O(n)
 * 空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2020-07-31 17:15
 */
public class FindMinimumInRotatedSortedArray2 {


    public static void main(String[] args) {
        int[] nums = {2,2,2,0,1,1};
        FindMinimumInRotatedSortedArray2 test = new FindMinimumInRotatedSortedArray2();
        int min = test.findMin(nums);
        System.out.println(min);
    }

    public int findMin(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        nums = excludeDuplicates(nums);
        return binarySearch(0, nums.length-1, nums);
    }

    private int[] excludeDuplicates(int[] nums) {
        int i, j ;
        List<Integer> list = new LinkedList<>();
        for (i = 0, j = i + 1; i < nums.length; ) {
            while (j < nums.length && nums[i] == nums[j]) {
                j++;
            }
            list.add(nums[i]);
            i = j++;
        }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    /* AC 0ms faster than 100% */
    private int binarySearch(int l, int r, int[] nums) {
        while (l + 1 < r) {
            int mid = (r - l) / 2 + l;
            int m = nums[mid];
            if (m < nums[r]) {
                r = mid;
            }
            else if (m > nums[r]) {
                l = mid;
            }
        }
        return Math.min(nums[l], nums[r]);
    }
}
