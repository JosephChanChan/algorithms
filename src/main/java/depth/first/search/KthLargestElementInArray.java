package depth.first.search;

import java.util.Random;

/**
 * leetcode 215 medium
 *
 * Analysis:
 * 基于快速排序的快速选择算法。每次快速排序根据l~r的范围内确定一个p，p的左边都是小于等于p的元素，右边都是大于等于p的元素。
 * 那么p的位置满足第k个大直接返回。否则判断这个k在p的左边/右边，这里用二分优化了一下。
 *
 * 此外如果每次快排的时候选最左的值作为轴值，是10ms AC，faster than 38%，不是最优的？
 * 看了题解，在l~r随机选一个轴值，1ms AC，O(n)的时间。我也不知道这种随机的轴值算法快在哪里
 *
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2020-08-27 21:50
 */
public class KthLargestElementInArray {

    Random random = new Random();

    public int findKthLargest(int[] nums, int k) {
        if (k == 0) return 0;
        // 第k个大的元素就是第 (n-k)+1 个小的元素
        // 如第1个大的元素就是第n个小的元素
        int orderedK = nums.length - k;
        return quickSelect(nums, orderedK, 0, nums.length-1);
    }

    private int quickSelect(int[] nums, int orderedK, int l, int r) {
        if (l > r) return -1;

        int t = doQuickSort(nums, l, r);
        if (t == orderedK) return nums[t];

        int r1 = t - 1;
        int l1 = t + 1;
        if (orderedK >= l1) {
            return quickSelect(nums, orderedK, l1, r);
        }
        else {
            return quickSelect(nums, orderedK, l, r1);
        }
    }

    private int doQuickSort(int[] nums, int l, int r) {
        if (l == r) return l;
        int pivot = random.nextInt(r - l) + l;
        // move to left
        swap(nums, l, pivot);
        int p = l;
        while (l < r) {
            while (l < r && nums[p] <= nums[r]) r--;
            while (l < r && nums[p] >= nums[l]) l++;
            swap(nums, l, r);
        }
        swap(nums, l, p);
        return l;
    }

    private void swap(int[] nums, int l, int r) {
        int temp = nums[r];
        nums[r] = nums[l];
        nums[l] = temp;
    }
}
