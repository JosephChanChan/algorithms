package prefix.sum.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * lc 930 medium
 *
 * Analysis:
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2022/3/16
 */
public class BinarySubarrayWithSum {

    public int numSubarraysWithSum(int[] nums, int goal) {
        /**
         s(i)为0~i的前缀和
         记录前缀和出现的次数k，如果s(i)和goal不等，s(i)-goal=d，看前面出现的前缀和中是否有d
         即s(j)=d，舍弃这部分前缀和等于d的子数组，剩下的j+1~i的新子数组的和仍是goal
         */
        int n = nums.length;
        int[] s = new int[n];
        s[0] = nums[0];
        for (int i = 1; i < n; i++) {
            s[i] = s[i-1] + nums[i];
        }
        int ans = 0;
        // 前缀和 -> 数量
        Map<Integer, Integer> m = new HashMap<>();

        for (int i = 0; i < n; i++) {
            // 0~i的前缀和
            int sum = s[i];
            if (sum == goal) {
                ans++;
            }
            int d = sum - goal;
            // 如果前面有k段前缀和等于d的子数组，舍弃它们，和仍然是goal
            if (d >= 0 && m.containsKey(d)) {
                ans += m.get(d);
            }
            m.put(sum, m.getOrDefault(sum, 0) + 1);
        }
        return ans;
    }
}
