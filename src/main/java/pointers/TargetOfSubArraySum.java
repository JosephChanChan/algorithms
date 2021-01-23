package pointers;

import java.util.*;

/**
 * 剑指Offer 57 II easy
 *
 * Analysis:
 * 40min AC 还需再刷
 *
 *  使得某段连续序列 Ai+Ai+1+Ai+2+...+Aj = t
 * Ai+...+Aj == t，i++, j++
 * Ai+...+Aj > t，i++
 * Ai+...+Aj < t，j++
 *
 * 时间复杂度：O(target)
 * 空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2021-01-17 17:32
 */
public class TargetOfSubArraySum {

    public int[][] findContinuousSequence(int target) {
        if (target <= 2) return new int[][]{{}};

        int[] subSum = new int[target+1];
        subSum[1] = 1;
        for (int i = 2; i <= target; i++) {
            subSum[i] = subSum[i-1]+i;
        }

        int i, j ;
        List<int[]> list = new LinkedList<>();
        for (i = 1, j = 2; i < j && j < target; ) {
            int sum = subSum[j] - subSum[i-1];
            if (sum == target) {
                mark(i++, j++, list);
            }
            else if (sum > target) {
                i++;
            }
            else {
                j++;
            }
        }
        return list.toArray(new int[0][]);
    }

    private void mark(int i, int j, List<int[]> list) {
        int[] a = new int[j - i + 1];
        for (int k = i, p = 0; k <= j; k++) a[p++] = k;
        list.add(a);
    }
}
