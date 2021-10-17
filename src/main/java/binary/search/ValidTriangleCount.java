package binary.search;

import java.util.Arrays;

/**
 * lc 611 medium
 *
 * Analysis:
 *  枚举ijk需要时间O(n^3)。
 * 根据三角形三条边的特性：任意两边之和大于第三边，需要校验 i+j>k j+k>i i+k>j
 * 将数组a排序后，只需要枚举i,j，然后在[j+1, n]范围内二分搜索最大的k，这个k满足i+j>k，
 * 然后j+1到k范围内的数都可以作为第三边和i&j组合构成有效三角形。
 * 避免重复枚举，i&j需要i<j
 *
 * 时间复杂度：O(N*logN)
 * 空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2021-09-21 18:29
 */
public class ValidTriangleCount {

    public int triangleNumber(int[] nums) {
        int[] a = nums;
        int n = a.length;

        Arrays.sort(a);

        int sum = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                // 如果i&j有一条边是0，则不可能构成三角形
                if (a[i] == 0) break;
                if (a[j] == 0) continue;
                // 找maxK，使得i+j>maxK
                int k = maxK(j, a[i]+a[j], a);
                // k必须小于i+j才行
                if (a[i]+a[j] > a[k]) {
                    sum += (k - j);
                }
            }
        }
        return sum;
    }

    int maxK(int j, int v, int[] a) {
        int l = j+1, r = a.length-1, k ;
        while (l + 1 < r) {
            k = (l + r) >> 1;
            // k太大了
            if (v <= a[k]) {
                r = k;
            }
            // i+j > k
            else {
                l = k;
            }
        }
        if (v > a[r]) return r;
        return l;
    }
}
