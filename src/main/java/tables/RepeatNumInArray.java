package tables;

/**
 * 剑指Offer 3 easy
 *
 * Analysis:
 *  最简单的，因为数字范围在0~n-1，开一个n的数组统计每个数字出现次数
 *  用O(n)时间，O(n)空间
 *
 *  因为数字范围在0~n-1范围，完全在数组长度范围内，就可以用数字=下标的数组特性。
 *  ai会在 0<=ai<=n-1 范围，将ai放到下标i的位置，若i的位置已经有一个ai，那么ai就是重复的数
 *  O(n)时间，O(1)空间
 *
 * @author Joseph
 * @since 2020-10-07 00:10
 */
public class RepeatNumInArray {

    public int findRepeatNumber(int[] nums) {
        return swapInArray(nums);
    }

    private int ordinary(int[] nums) {
        int[] map = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            map[nums[i]]++;
            if (map[nums[i]] > 1) return nums[i];
        }
        return -1;
    }

    private int swapInArray(int[] nums) {
        // 最多会移动n个数字，因为每次移动能将一个数字放到正确位置，就算每次换来一个错误数字。
        // 经过最多n次放置，所有数字将放置在正确位置，所以是O(n)
        for (int i = 0; i < nums.length; i++) {
            while (i != nums[i]) {
                if (nums[i] == nums[nums[i]]) return nums[i];
                int t = nums[nums[i]];
                nums[nums[i]] = nums[i];
                nums[i] = t;
            }
        }
        return -1;
    }

}
