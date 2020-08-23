package binary.search;

/**
 * leetcode 34 medium
 *
 * Question Description:
 *  参见 lc 34
 *
 * Analysis:
 *  找最左边的时候，target前的数如果还是target继续缩小范围往左边找，同理处理最右边
 *  时间复杂度：O(logN)
 *  时间复杂度：O(1)
 *
 * @author Joseph
 * @since 2020-08-22 20:56
 */
public class FindFirstAndLastPositionElementSortedArray {

    public static void main(String[] args) {
        int[] nums = {1,1,2,8,8,8,8,10,10};
        FindFirstAndLastPositionElementSortedArray test = new FindFirstAndLastPositionElementSortedArray();
        int[] result = test.searchRange(nums, 8);
        System.out.println(result[0] + " " + result[1]);
    }

    public int[] searchRange(int[] nums, int target) {
        if (nums.length == 0) return new int[]{-1, -1};

        int first = findFirst(nums, target);
        int last = findLast(nums, target);
        return new int[]{first, last};
    }

    private int findFirst(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l + 1 < r) {
            int m = (l + r) >> 1;
            if (nums[m] == target) {
                if (m == 0 || m > 0 && nums[m-1] < nums[m]) {
                    return m;
                }
                r = m;
            }
            else if (nums[m] < target) {
                l = m;
            }
            else {
                r = m;
            }
        }
        if (nums[l] == target) return l;
        if (nums[r] == target) return r;
        return -1;
    }

    private int findLast(int[] nums, int target) {
        int l = 0, r = nums.length - 1, last = r;
        while (l + 1 < r) {
            int m = (l + r) >> 1;
            if (nums[m] == target) {
                if (m == last || m < last && nums[m] < nums[m+1]) {
                    return m;
                }
                l = m;
            }
            else if (nums[m] < target) {
                l = m;
            }
            else {
                r = m;
            }
        }
        if (nums[r] == target) return r;
        if (nums[l] == target) return l;
        return -1;
    }
}
