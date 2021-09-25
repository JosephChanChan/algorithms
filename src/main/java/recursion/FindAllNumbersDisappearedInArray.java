package recursion;

import java.util.LinkedList;
import java.util.List;

/**
 * lc 448 easy
 *
 * Analysis:
 *  在题目的限制下不用额外空间并且线性时间完成。主要利用了 1<=ai<=n 的条件。
 * 因为ai最大不超过n，所以将ai放在a[ai-1]的位置上，全部归位完毕后，扫一遍数组看哪个位置上的数和下标 不是 i=i+1 关系的就是缺失数字。
 *
 * 递归放置ai到ai-1位置时，最多会把数组的元素都归位一遍，这里用了O(n)
 * 回到主循环中，每个元素归位后不会再发起新的递归所以总体O(2n)
 *
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2021-03-23 16:01
 */
public class FindAllNumbersDisappearedInArray {

    public List<Integer> findDisappearedNumbers(int[] nums) {
        int n = nums.length;
        List<Integer> ans = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            if (nums[i] != i+1) {
                recursionFill(nums[i], nums);
            }
        }
        for (int i = 0; i < n; i++) {
            if (nums[i] != i+1) ans.add(i+1);
        }
        return ans;
    }
    void recursionFill(int k, int[] a) {
        if (a[k-1] != k) {
            int next = a[k-1];
            a[k-1] = k;
            recursionFill(next, a);
        }
    }
}
