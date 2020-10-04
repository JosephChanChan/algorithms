package pointers;

/**
 * leetcode 283 easy
 *
 * Analysis:
 *  i,j指针，j指向0，i从j开始向右遍历，i指向非0，i,j互换。i>j，i走过的数都是0，所以j++即可。
 * 直到i遍历完数组。
 *
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 */
public class MoveZeros {

    public void moveZeroes(int[] nums) {
        if (nums.length < 2) return;

        int i, j = 0;
        while (j < nums.length && nums[j] != 0) {
            j++;
        }
        i = j;
        for (; i < nums.length; i++) {
            if (nums[i] != 0) {
                int temp = nums[j];
                nums[j] = nums[i];
                nums[i] = temp;
                j++;
            }
        }
    }
}
