/**
 * lc 136 easy
 *
 * Analysis：
 *  利用 a^a=0 的性质
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2021-03-06 12:48
 */
public class UniqueIntInArray {

    public int singleNumber(int[] nums) {
        int num = 0;
        for (int i = 0; i < nums.length; i++) num = num ^ nums[i];
        return num;
    }
}
