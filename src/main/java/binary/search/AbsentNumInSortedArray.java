package binary.search;

/**
 * 剑指Offer 53 easy
 *
 * Analysis:
 *  这题有点恶心，没看清楚数组的元素范围。数组元素范围应该是0~nums.length
 * 题目说每个数字是0~n-1，[0,1] 长度是2，数字元素是0~1，答案说缺少2，什么鬼？
 * 害我提交了几次错误。
 *
 * 时间复杂度：O(logN)
 * 空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2021-01-10 22:02
 */
public class AbsentNumInSortedArray {

    public int missingNumber(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length == 1 && nums[0] != 0) return 0;
        if (nums[nums.length-1] != nums.length) return nums.length;
        if (nums[0] != 0) return 0;

        int l = 0, r = nums.length-1, m;
        while (l + 1 < r) {
            m = (l + r) >> 1;
            if (nums[m] - nums[l] == m - l) {
                l = m;
            }
            else {
                r = m;
            }
        }
        return nums[l] + 1;
    }
}
