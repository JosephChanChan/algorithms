package binary.search;

/**
 * lc 33 medium
 *
 * Analysis:
 *  solution1: 先findMinimum O(logN)时间，在判断搜左边/右边 O(logN)时间，共2个logN
 *  solution2: 画图分情况讨论，先看中点切在上面的线还是下面的线。再看t在中点左边还是右边，如果t在m右边则右移，左边则左移。
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
        int n = nums.length;
        int l = 0, r = n-1;
        while (l + 1 < r) {
            int m = (l+r) >> 1;
            if (nums[m] == target) {
                return m;
            }
            // m在上面的线
            if (nums[m] >= nums[0]) {
                // t在m的右边
                if (target > nums[m] || target < nums[0]) {
                    l = m;
                }
                else {
                    r = m;
                }
            }
            // m在下面的线
            else {
                // t在m的左边
                if (target >= nums[0] || target < nums[m]) {
                    r = m;
                }
                else {
                    l = m;
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
