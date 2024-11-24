package binary.search;

import java.util.Random;

/**
 * lc 215 medium
 *
 * Analysis:
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2020-08-27 21:50
 */
public class KthLargestElementInArray {

    int t = 0;

    public int findKthLargest(int[] nums, int k) {
        // 大到小排序，第1大在数组中下标就是第0元素
        this.t = k-1;
        return quickSel(0, nums.length-1, nums);
    }

    int quickSel(int i, int j, int[] a) {
        if (i == j) {
            //System.out.println("i==j "+i);
            return a[i];
        }
        int l = i, r = j, baseVal = a[(l+r) >> 1];
        while (l <= r) {
            // 左边 >= baseVal
            while (l <= r && a[l] > baseVal) l++;
            while (l <= r && a[r] < baseVal) r--;
            if (l <= r) {
                int t = a[l];
                a[l] = a[r];
                a[r] = t;
                l++;
                r--;
            }
        }
        /*for (int p = 0; p < a.length; p++) {
            System.out.print(a[p]+" ");
        }
        System.out.println("base="+baseVal+" l="+l+" r="+r);*/
        // 快排模板，可能会将数组分为3端，i~r，r+1，l~j
        // t是第几大元素，就看t落在哪一段
        if (t <= r) {
            return quickSel(i, r, a);
        }
        if (t >= l) {
            return quickSel(l, j, a);
        }
        //System.out.println("a[r+1]= "+(r+1));
        return a[r+1];
    }
}
