package binary.search;

/**
 * lc 154 hard & 剑指Offer 11 easy
 *
 * Analysis:
 *
 * 时间复杂度：最坏情况下去重花费O(n)
 * 空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2020-07-31 17:15
 */
public class FindMinimumInRotatedSortedArray2 {

    public int findMin(int[] nums) {
        int[] a = nums;
        int n = a.length;
        if (n == 1) return a[0];

        int l = 0, r = n-1, m ;

        while (l < r && a[l] == a[r]) r--;

        while (l + 1 < r) {
            m = (l+r) >> 1;
            if (a[l] < a[r]) {
                r = m;
            }
            else if (a[m] < a[0]) {
                r = m;
            }
            else {
                l = m;
            }
        }
        return Math.min(a[l], a[r]);
    }
}
