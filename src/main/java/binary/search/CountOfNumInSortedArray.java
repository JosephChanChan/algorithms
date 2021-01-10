package binary.search;

/**
 * 剑指Offer 53 easy
 *
 * Analysis:
 *  二分找到t的位置，从左右两边扩计数。
 *
 * 时间复杂度：O(logN) 在数组全是同一个数时，最坏情况会是O(n)
 * 空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2021-01-10 21:12
 */
public class CountOfNumInSortedArray {

    public int search(int[] nums, int target) {
        if (nums.length == 0) return 0;
        if (nums.length == 1 && nums[0] == target) return 1;

        int i = binarySearch(nums, target);
        if (i == -1) return 0;
        int c = 1;
        int j = i;
        while (j > 0 && nums[j-1] == target) {
            c++; j--;
        }
        j = i;
        while (j < nums.length-1 && nums[j+1] == target) {
            c++; j++;
        }
        return c;
    }

    private int binarySearch(int[] a, int t) {
        int l = 0, r = a.length-1, m ;
        while (l + 1 < r) {
            m = (l + r) >> 1;
            if (a[m] == t) {
                return m;
            }
            if (a[m] > t) {
                r = m;
            }
            else {
                l = m;
            }
        }
        if (a[l] == t) return l;
        if (a[r] == t) return r;
        return -1;
    }
}
