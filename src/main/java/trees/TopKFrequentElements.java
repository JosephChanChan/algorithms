package trees;

import java.util.*;

/**
 * leetcode 347 medium
 *
 * Analysis:
 *  堆排序思想
 *
 * 时间复杂度：O(nlogk)
 * 空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2020-10-04 23:40
 */
public class TopKFrequentElements {

    public int[] topKFrequent(int[] nums, int k) {
        if (nums.length == 1) return nums;

        Map<Integer, Integer> count = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int c = count.getOrDefault(nums[i], 0);
            count.put(nums[i], ++c);
        }

        Queue<Integer[]> q = new PriorityQueue<>(k, (o1, o2) -> o1[1] - o2[1]);
        for (Map.Entry<Integer, Integer> e : count.entrySet()) {
            if (q.size() < k) {
                q.add(new Integer[]{e.getKey(), e.getValue()});
                continue;
            }
            Integer[] item = q.peek();
            if (item[1] < e.getValue()) {
                q.poll();
                q.add(new Integer[]{e.getKey(), e.getValue()});
            }
        }

        int[] ans = new int[k];
        for (int i = 0; i < k; i++) {
            Integer[] e = q.poll();
            ans[i] = e[0];
        }
        return ans;
    }
}
