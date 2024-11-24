package greedy.algorithm;

import java.util.*;

/**
 * lc 2233 medium
 *
 * Analysis：
 * 时间复杂度：O(n*log(n))
 * 空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2022/9/20
 */
public class MaxProductAfterKIncrements {

    /**
         贪心
         考虑a<b，k=1情况，当然给a+1，积最大
         扩大序列长度，如果a<b<c< ...< z，k=1，显然也是给a+1，积最大
     */

    int mod = (int) 1e9 + 7;
    Queue<Integer> q = new PriorityQueue<>();

    public int maximumProduct(int[] nums, int k) {
        int n = nums.length;
        if (n == 1) {
            return nums[0] + k;
        }
        long ans = 1;
        for (int a : nums) {
            q.add(a);
        }
        while (k > 0) {
            int i = q.poll();
            q.add(++i);
            k--;
        }
        while (q.size() > 0) {
            int i = q.poll();
            ans = (ans * i) % mod;
        }
        return (int) ans;
    }
}
