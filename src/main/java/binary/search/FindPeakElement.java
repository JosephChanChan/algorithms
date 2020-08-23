package binary.search;

/**
 * leetcode 162 medium
 *
 * Analysis:
 * m可能切在3种情况上，1.刚好切在峰上。2.切在左上升的坡上，代表左边有一个峰。3.切在右上升的坡上代表右边有一个峰。
 *
 * 时间复杂度：O(logN)
 * 空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2020-08-23 16:58
 */
public class FindPeakElement {

    public static void main(String[] args) {
        int[] nums = {1,2,1,3,4,10,20};
        FindPeakElement test = new FindPeakElement();
        int peakElement = test.findPeakElement(nums);
        // index of peakElement
        System.out.println(peakElement);
    }

    public int findPeakElement(int[] nums) {
        if (nums.length == 1) return 0;

        int l = 0, r = nums.length - 1;
        while (l + 1 < r) {
            int m = (l + r) >> 1;
            if (nums[m-1] < nums[m] && nums[m+1] < nums[m]) {
                return m;
            }
            if (nums[m-1] > nums[m]) {
                r = m;
            }
            else {
                l = m;
            }
        }
        return nums[l] > nums[r] ? l : r;
    }

}
