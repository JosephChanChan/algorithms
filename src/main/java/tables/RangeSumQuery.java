package tables;

/**
 * lc 303 easy
 *
 * Analysis:
 * 时间复杂度：建前缀和时O(n)，这个时间均摊到后面的n次区域和查询后是O(1)
 * 空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2022/2/13 12:28 PM
 */
public class RangeSumQuery {

    int[] s ;

    public RangeSumQuery(int[] nums) {
        if (nums.length == 0) {
            s = new int[0];
        }
        s = new int[nums.length];
        s[0] = nums[0];
        for (int i = 1; i < s.length; i++) {
            s[i] = s[i-1] + nums[i];
        }
    }

    public int sumRange(int left, int right) {
        // s(i,j) = s[j] - s[i-1]
        if (left == 0) {
            return s[right];
        }
        return s[right] - s[left-1];
    }
}
