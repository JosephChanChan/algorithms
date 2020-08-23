package binary.search;

/**
 * leetcode 33 medium
 *
 * Question Description:
 *  参见 lc 33
 *
 * Analysis:
 *  solution1: 先findMinimum O(logN)时间，在判断搜左边/右边 O(logN)时间，共2个logN
 *  solution2: 画图分情况讨论，先判断t在上边的线还是下面的线，再判断m在t的左边还是右边，最后还要进一步判断m在s1/s2中。
 *  总共的有6种情况。合并结果操作相同的情况共有4种情况。
 *
 *  t > r:
 *      m > t -> r = m
 *      l <= m < t -> l = m
 *      m < l < t -> r = m
 *  t < r:
 *      m < t -> l = m
 *      t < m <= r -> r = m
 *      t < r <= m -> l = m
 *
 * @author Joseph
 * @since 2020-08-19 22:46
 */
public class SearchRotatedSortedArray {

    public static void main(String[] args) {
        int[] nums = {4,5,6,7,0,1,2};
        SearchRotatedSortedArray test = new SearchRotatedSortedArray();
        int search = test.search(nums, 0);
        System.out.println(search);
    }

    public int search(int[] nums, int target) {
        if (nums.length == 0) return -1;
        if (nums.length == 1 && nums[0] == target) return 0;

        return findK(nums, target);
    }

    public int findK(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l + 1 < r) {
            int m = (r - l) / 2 + l;
            if (nums[m] == target) {
                return m;
            }
            if (target > nums[r]) {
                if (nums[m] > target || nums[m] < nums[r]) {
                    r = m;
                }
                else if (nums[m] > nums[r]) {
                    l = m;
                }
            }
            else {
                if (nums[m] < target || nums[m] > nums[r]) {
                    l = m;
                }
                else if (nums[m] < nums[r]) {
                    r = m;
                }
            }
        }
        if (nums[l] == target) return l;
        if (nums[r] == target) return r;
        return -1;
    }

    private int findTarget(int[] nums, int target, int l, int r) {
        while (l + 1 < r) {
            int m = (r - l) / 2 + l;
            if (nums[m] == target) {
                return m;
            }
            else if (nums[m] > target) {
                r = m - 1;
            }
            else {
                l = m + 1;
            }
        }
        if (nums[l] == target) return l;
        if (r < nums.length && nums[r] == target) return r;
        return -1;
    }

    private int findMinimum(int[] nums) {
        int l = 0, r = nums.length - 1;
        while (l + 1 < r) {
            int m = (r - l) / 2 + l;
            if (nums[m] > nums[r]) {
                l = m;
            }
            else {
                r = m;
            }
        }
        return nums[l] > nums[r] ? r : l;
    }

}
