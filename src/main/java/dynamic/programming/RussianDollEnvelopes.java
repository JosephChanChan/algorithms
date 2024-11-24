package dynamic.programming;

import java.util.*;

/**
 * lc 354 hard
 *
 * Analysis:
 *  在宽度有序的情况下，只要按照高度选择一个最长的可以套在一起的子序列，
 * 假设存在一个按照高度可以套在一起的最长子序列q
 * q1,q2,q3...qn，它的宽度必然也是从小到大的，因为q就是在宽度有序的序列下选择出的高度最长子序列
 * 如何在无序的高度序列下，选择出高度最长的子序列，就是LIS问题
 * f(i)是以i结尾的最长高度子序列长度
 * 方程在这题中还要加上一个宽度的约束条件，因为按宽度排序后存在多个宽度相同的元素，题目要求宽高都要大于才可套
 * f(i) = max{f(j) | j < i && w[j] < w[i] && h[j] < h[i]} + 1
 * 边界：f(0)=0, f(1)=1
 *
 * 时间复杂度：O(n^2)
 * 空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2020-10-05 22:32
 */
public class RussianDollEnvelopes {

    public int maxEnvelopes(int[][] envelopes) {
        int n = envelopes.length;
        if (n == 0) return 0;
        if (n == 1) return 1;

        // sort by width
        Arrays.sort(envelopes, (o1, o2) -> o1[0] - o2[0]);

        int[] f = new int[n];
        f[0] = 1;
        for (int i = 1; i < n; i++) {
            int curW = envelopes[i][0], curH = envelopes[i][1], prevMax = 0;
            for (int j = 0; j < i; j++) {
                if (envelopes[j][0] < curW &&
                    envelopes[j][1] < curH &&
                    f[j] > prevMax)
                    prevMax = f[j];
            }
            f[i] = prevMax + 1;
        }

        int maxL = 0;
        for (int i = 0; i < n; i++) {
            maxL = Math.max(f[i], maxL);
        }
        return maxL;
    }

}
