package pointers;

/**
 * lc 1052 medium
 *
 * Analysis:
 *  i~j窗口内的x分钟能全拿顾客满意度。
 * 剩下的0~i-1和j+1~n的满意度只能按带脾气的属性累积。
 * 因为并不能得知哪段窗口是获利最大的，只能枚举。
 * 枚举窗口时间O(n)，每一段窗口O(1)可以得知收益。
 *
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2021-01-24 20:03
 */
public class BookOwnerWIthBadTemper {

    // 3ms AC faster than 78%
    public int maxSatisfied(int[] customers, int[] grumpy, int X) {
        int n = customers.length;
        int[] p1 = new int[n];// 顾客满意度前缀和
        p1[0] = customers[0];
        int[] p2 = new int[n];// 带脾气的满意度前缀和
        p2[0] = grumpy[0] == 1 ? 0 : customers[0];
        for (int i = 1; i < n; i++) {
            p1[i] = p1[i-1]+customers[i];
        }
        for (int i = 1; i < n; i++) {
            p2[i] = p2[i-1] + (grumpy[i] == 1 ? 0 : customers[i]);
        }

        int max = 0;
        int i = 0, j = i + X-1;
        for ( ; j < n; j++, i++) {
            int sum = 0;
            if (i == 0) {
                sum += p1[j];
            }
            else {
                sum += p1[j] - p1[i-1];
            }
            if (i == 0) {
                sum += p2[n-1] - p2[j];
            }
            else {
                sum += p2[n-1] - p2[j] + p2[i-1];
            }
            max = Math.max(max, sum);
        }
        return max;
    }
}
