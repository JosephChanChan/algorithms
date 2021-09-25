package recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * lc 442 medium
 *
 * Analysis:
 * 记得lc上有几道类似的题要求不用额外空间找出数组中重复的元素。
 * 既然题目限制了时间和空间，一般这类题要么对数组元素有限制要么对重复元素有限制。
 *
 * 1 <= ai <= n 将ai放到ai-1的位置上。那么位置i上就应该是i+1。
 * 还有个技巧是将 ai != i+1 的位置标记为-1，免得后面会干扰元素归位。
 *
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2021-04-28 11:55
 */
public class FindAllDuplicatesInArray {

    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> ans = new ArrayList();

        if (nums.length <= 1) return ans;

        // 1 <= ai <= n 将ai放到ai-1的位置上。那么位置i上就应该是i+1
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i+1) {
                int t = nums[i];
                nums[i] = -1;
                dfsPlace(t, nums, ans);
            }
        }
        return ans;
    }

    void dfsPlace(int num, int[] a, List<Integer> ans) {
        if (num == a[num-1]) {
            ans.add(num);
            return;
        }

        int t = a[num-1];
        a[num-1] = num;
        if (t == -1) return;
        dfsPlace(t, a, ans);
    }
}
