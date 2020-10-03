package pointers;

import java.util.*;

/**
 * leetcode 18 medium
 *
 * Analysis:
 * 依然是a+b+c+d=t的式子，用双指针找c,d将时间复杂度降一级到三次方。
 * 不是最优的解，应该还有一些搜索空间可以剪枝，例如当t是负数时，我们枚举到a>0时，b,c,d都会是>0的，所以可以剪枝。
 *
 * 时间复杂度：O(n^3)
 * 空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2020-09-29 22:58
 */
public class FourSum {

    // 4ms AC, faster than 92%
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> ans = new LinkedList<>();

        if (nums.length < 4) return ans;

        Arrays.sort(nums);

        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (i > 0 && nums[i] == nums[i-1]) continue;

            for (int j = i + 1; j < n; j++) {
                int a = nums[i], b = nums[j];

                // 去重
                if (i != j - 1 && nums[j] == nums[j-1]) continue;

                // 当前a,b固定情况下，取到l,r的最小值，如果都比t大，可以剪枝
                if (j + 2 < n && a + b + nums[j+1] + nums[j+2] > target) continue;
                // 当前a,b固定情况下，取到l,r的最大值，如果都比t小，可以剪枝
                if (a + b + nums[n-2] + nums[n-1] < target) continue;
                // 当t是负数时，a枚举到正数，往后都是正数，可以剪枝
                if (target <= 0 && (a > 0 || (a == 0 && b > 0))) return ans;

                int l = j + 1, r = n - 1;
                while (l < r) {
                    int c = nums[l], d = nums[r];
                    int sum = a + b + c + d;
                    if (sum == target) {
                        List<Integer> list = new ArrayList<>(2);
                        list.add(a); list.add(b); list.add(c); list.add(d);
                        ans.add(list);

                        while (l < r && c == nums[l]) l++;
                        while (l < r && d == nums[r]) r--;
                    }
                    else if (target > sum) {
                        while (l < r && c == nums[l]) l++;
                    }
                    else {
                        while (l < r && d == nums[r]) r--;
                    }
                }
            }
        }
        return ans;
    }
}
