package pointers;

/**
 * lc 581 medium
 *
 * Analysis:
 *  思维题。
 * 先从左边找到第一个无序开始的地方i，满足 ai>ai+1
 * 右边找到第一个无序开始的地方j，满足 aj-1>aj
 * 假设 i~j这一段升序后，它应满足 0~i-1 <= min(i~j) 和 max(i~j) <= j+1~n
 *
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2021-03-24 22:28
 */
public class ShortestUnsortedContinuousSubarray {

    public int findUnsortedSubarray(int[] nums) {
        int n = nums.length;
        int i = 0, j = n-1;

        while (i < n-1 && nums[i] <= nums[i+1]) i++;
        if (i == n-1) return 0;

        while (i <= j && nums[j-1] <= nums[j]) j--;
        if (i > j) return 0;

        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int k = i; k <= j; k++) {
            min = Math.min(min, nums[k]);
            max = Math.max(max, nums[k]);
        }

        //System.out.println("i~j="+i+"~"+j+" min="+min+" max="+max);
        int l = i-1, r = j+1;
        while (l >= 0) {
            if (nums[l] > min) {
                i = l;
            }
            l--;
        }
        while (r < n) {
            if (nums[r] < max) {
                j = r;
            }
            r++;
        }
        //System.out.println("i~j="+i+"~"+j);
        return j-i+1;
    }
}
