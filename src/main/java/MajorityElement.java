/**
 * lc 169 easy
 *
 * Analysis:
 *  题目保证x会出现超过n/2次
 * 从头到尾统计每个数字出现次数，当下一个数非当前数时次数减一，直到0更换下一个数
 *
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2021-03-27 15:27
 */
public class MajorityElement {

    public int majorityElement(int[] nums) {
        int ans = -1, c = 0;
        for (int i = 0; i < nums.length; i++) {
            if (ans == nums[i]) {
                c++;
            }
            else if (--c <= 0) {
                ans = nums[i];
                c = 1;
            }
        }
        return ans;
    }
}
