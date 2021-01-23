package tables;

import java.util.*;

/**
 * 剑指Offer 59 hard & lc 239
 *
 * Analysis:
 *  使用双端队列保存当前窗口内有可能成为最大值得元素
 * 队列头一直会是当前窗口最大值。
 * 队列头为a1，窗口右移加入a2，对于a2>a1，当前窗口最大值就会变成a2，直到a2被移出前都是，所以将队列中全部元素淘汰。
 * 对于a2<a1，在a1被移出后，a2有可能成为最大，a2又是比a1后移出的，所以a2加到尾部。
 * 对于a2==a1，同上。
 * 队列头保存的最大值，被滑出窗口后，队列也要做更新，所以每次滑动时要判断滑出的元素是否队列头。
 *
 * 时间复杂度：O(n)，每个元素最多入队和出队一次，上界是O(2n)
 * 空间复杂度：O(k)
 *
 * @author Joseph
 * @since 2021-01-18 15:00
 */
public class MaxNumInSlidingWindow {

    public int[] maxSlidingWindow(int[] nums, int k) {
        return usingDeque(nums, k);
    }

    private int[] usingDeque(int[] nums, int k) {
        if (k == 0 || k == 1) return nums;

        Deque<Integer> que = new ArrayDeque<>(k);
        int l = 0, r = k-1, i = 0;
        int[] ans = new int[nums.length-k+1];

        for (int j = l; j <= r; j++) {
            enqueue(que, nums, j);
        }
        ans[i++] = nums[que.getFirst()];

        while (++r < nums.length) {
            if (que.getFirst() < ++l) que.pollFirst();
            enqueue(que, nums, r);
            ans[i++] = nums[que.getFirst()];
        }
        return ans;
    }

    private void enqueue(Deque<Integer> que, int[] nums, int j) {
        if (que.isEmpty()) {
            que.add(j);
        }
        else if (nums[que.getFirst()] >= nums[j]) {
            while (!que.isEmpty() && nums[que.getLast()] <= nums[j]) que.pollLast();
            que.add(j);
        }
        else if (nums[que.getFirst()] < nums[j]) {
            que.clear();
            que.add(j);
        }
    }

    // 使用最大堆的时间是 O(n*logK) 在第49case 会TLE
    private int[] usingMaxHeap(int[] nums, int k) {
        if (k == 1) return nums;
        Queue<Integer> maxQ = new PriorityQueue<>(k, (o1, o2) -> o2 - o1);

        int l = 0, r = k-1;
        for (int i = l; i <= r; i++) maxQ.add(nums[i]);

        int i = 0;
        int max = maxQ.peek();
        int[] ans = new int[nums.length - k + 1];
        ans[i++] = max;

        if (r == nums.length-1) return ans;

        do {
            maxQ.remove(nums[l++]);
            maxQ.add(nums[++r]);
            ans[i++] = maxQ.peek();
        }
        while (r < nums.length-1);
        return ans;
    }
}
