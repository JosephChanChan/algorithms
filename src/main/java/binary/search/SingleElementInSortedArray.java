package binary.search;

/**
 * leetcode 540 medium
 *
 * Analysis:
 * 题目提到了数组中只有一个单独的数，那么其余数都是成对出现，则共有k对+1个单独数，数组元素共有奇数个。
 * 每次二分判断当前的中间数是不是单独的数，如果不是则中间数左边/右边必然有和它相等的数，排除掉左边相等的数，
 * 左边假设有j对相等数，如果单独数在左边则 j对数+单独数 左边会有奇数个元素。
 * 只需要判断这个单独数是不是在左边，否则就是在右边。
 *
 * 时间复杂度：O(logN)
 * 空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2020-08-23 15:35
 */
public class SingleElementInSortedArray {

    public static void main(String[] args) {
        int[] nums = {3,3,7,7,10,11,11};
        SingleElementInSortedArray test = new SingleElementInSortedArray();
        int single = test.singleNonDuplicate(nums);
        System.out.println(single);
    }

    public int singleNonDuplicate(int[] nums) {
        if (nums.length == 1) return nums[0];

        int l = 0, r = nums.length - 1;
        while (l + 1 < r) {
            int m = (l + r) >> 1;
            if (nums[m-1] != nums[m] && nums[m+1] != nums[m]) {
                return nums[m];
            }
            int leftCount = m;
            if (nums[m-1] == nums[m]) {
                leftCount = m - 1;
            }
            if (leftCount % 2 > 0) {
                r = m;
            }
            else {
                l = m;
            }
        }
        if (l == 0 && nums[l] != nums[r]) return nums[l];
        if (r == nums.length - 1 && nums[l] != nums[r]) return nums[r];
        if (nums[l-1] != nums[l] && nums[l] != nums[r]) return nums[l];
        if (nums[r+1] != nums[r] && nums[l] != nums[r]) return nums[r];
        return -1;
    }
}
