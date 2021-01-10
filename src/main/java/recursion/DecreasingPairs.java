package recursion;

/**
 * 剑指Offer 51 hard
 *
 * Analysis:
 *  分治。
 * 两个有序数组合并过程中，a1和a2。p1指向a1数组尾部，p2指向a2数组尾部。
 * 若p1 > p2，则p1可以和p2以及p2之前的数组成逆序对，数量是p2当前在a2的位置。
 * 然后max{p1, p2}的元素放入一个新数组中。较大的一个指针左移，继续以上过程。
 * 若p1 < p2，则p1不能和p2组成逆序对，将p2放入新数组，左移寻找更小的元素。
 * 直到两个有序数组合并成一个有序数组。
 *
 * 时间复杂度：O(N*logN)
 * 空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2021-01-10 19:38
 */
public class DecreasingPairs {

    private int pairs = 0;

    public int reversePairs(int[] nums) {
        if (nums.length == 0 || nums.length == 1) return 0;
        dAndC(0, nums.length-1, nums);
        return pairs;
    }

    private int[] dAndC(int l, int r, int[] nums) {
        if (l == r) return new int[]{nums[l]};

        int m = (l + r) >> 1;
        int[] lefts = dAndC(l, m, nums);
        int[] rights = dAndC(m + 1, r, nums);

        // calc pairs and sort
        int[] sorted = new int[lefts.length + rights.length];
        int i = lefts.length-1, j = rights.length-1, k = sorted.length-1;
        while (i >= 0 && j >= 0) {
            if (lefts[i] > rights[j]) {
                pairs += j + 1;
                sorted[k--] = lefts[i--];
            }
            else {
                sorted[k--] = rights[j--];
            }
        }
        while (i >= 0) {
            sorted[k--] = lefts[i--];
        }
        while (j >= 0) {
            sorted[k--] = rights[j--];
        }
        return sorted;
    }
}
