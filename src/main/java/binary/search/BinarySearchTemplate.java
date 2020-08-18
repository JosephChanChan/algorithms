package binary.search;

/**
 * 二分查找模板
 *
 * @author Joseph
 * @since 2020-08-18 10:20
 */
public class BinarySearchTemplate {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 10};
        BinarySearchTemplate template = new BinarySearchTemplate();
        int i = template.binarySearch(nums, 9);
        System.out.println(i);
    }

    private int binarySearch(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left + 1 < right) {
            // 防止 int 溢出
            int mid = ((right - left) >> 1) + left;
            if (nums[mid] == target) {
                return mid;
            }
            else if (nums[mid] > target) {
                right = mid - 1;
            }
            else {
                left = mid + 1;
            }
        }
        if (nums[left] == target) return left;
        if (nums[right] == target) return right;
        return -1;
    }
}
