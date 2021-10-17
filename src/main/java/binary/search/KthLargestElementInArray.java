package binary.search;

import java.util.Random;

/**
 * lc 215 medium
 *
 * Analysis:
 * 直接将数组按大到小排序，每次分区完后看看k在左边还是右边。
 * 有几点要注意的：
 * 1.快排的模板分区后，r必定小于等于l，r<l，有可能r和l相邻，也有可能r+1<l
 * r和l之间差了一个数，这个数恰好可能是第k大的数，所以如果 r+1<l 时要把空位补上 r++
 *
 * 之后再判断k<=r 去搜索左边，否则肯定k>=l 搜索右边
 *
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2020-08-27 21:50
 */
public class KthLargestElementInArray {

    public int findKthLargest(int[] nums, int k) {
        return quickSelect(nums, 0, nums.length-1, k);
    }

    int quickSelect(int[] a, int start, int end, int k) {
        // 大 -> 小
        if (start >= end) return a[end];

        int l = start, r = end, p = a[(l+r)>>1];
        while (l <= r) {
            while (l <= r && a[l] > p) l++;
            while (l <= r && a[r] < p) r--;
            if (l <= r) {
                int t = a[l];
                a[l] = a[r];
                a[r] = t;
                l++; r--;
            }
        }
        // r+1 < l 时把空位补上
        if (r+1 < l) r++;
        if (k-1 <= r) return quickSelect(a, start, r, k);
        return quickSelect(a, l, end, k);
    }
}
