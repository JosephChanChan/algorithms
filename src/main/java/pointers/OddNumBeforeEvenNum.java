package pointers;

/**
 * 剑指Offer 21 easy
 *
 * Analysis:
 * 双指针解法。
 *  1.相向指针，l从左往右扫偶数，r从右往左扫奇数。
 *  2.快慢指针，s扫偶数，q扫奇数，q往s丢
 *
 *  时间复杂度：O(n)
 *  空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2020-11-15 15:46
 */
public class OddNumBeforeEvenNum {

    public int[] exchange(int[] nums) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            while (l < r && (nums[l] & 1) == 1) l++;
            while (l < r && (nums[r] & 1) == 0) r--;
            int t = nums[l];
            nums[l] = nums[r];
            nums[r] = t;
        }
        return nums;
    }

    public void quickAndSlow(int[] nums) {
        int s = 0, q ;
        for ( ; s < nums.length; s++) {
            if ((nums[s] & 1) == 0) break;
        }
        q = s + 1;
        for ( ; q < nums.length; q++) {
            if ((nums[q] & 1) == 1) {
                int t = nums[s];
                nums[s] = nums[q];
                nums[q] = t;
                s++;
            }
        }
    }
}
