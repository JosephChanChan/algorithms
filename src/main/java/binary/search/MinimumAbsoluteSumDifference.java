package binary.search;

import java.util.Arrays;

/**
 * lc 1818 medium
 *
 * Analysis:
 *  Di是|ai-bi|的差值，如果存在Dj=|aj-bi| && i!=j && Dj < Di
 * 则代表存在一种方案使得总差值和totalSum更小。
 * 对于每个Di我们希望能找出min{Dj}，使得该项的差值最小。
 * delta = {Di-Dj | 0 <= i <= n}
 * delta就是这一项i替换后的差值缩小了多少，我们希望找到最大的delta代表找到替换后能缩小最多差值的一项i
 * max{delta} = max{Di-Dj | 0 <= i <= n}
 *
 * 显然要试探每个ai，对每个ai又要尝试每个aj获得最小的Dj。当计算出每项的Dj后，记录并更新最大的delta
 * 这是暴力解需要n^2时间
 *
 * 对于bi我们用二分在a中找最接近bi的aj，则时间优化到N*logN
 *
 * 时间复杂度：O(logN)
 * 空间复杂度：O(N)
 *
 * @author Joseph
 * @since 2021-08-07 22:58
 */
public class MinimumAbsoluteSumDifference {

    public int minAbsoluteSumDiff(int[] nums1, int[] nums2) {
        int mod = (int) (1e9 + 7);
        int[] a = nums1;
        int[] b = nums2;
        int[] sorted = new int[a.length];

        int diffSum = 0;
        for (int i = 0; i < a.length; i++) {
            sorted[i] = a[i];
            diffSum = (diffSum + Math.abs(a[i]-b[i])) % mod;
        }
        //System.out.println(diffSum);

        Arrays.sort(sorted);

        int delta = 0;
        for (int i = 0; i < a.length; i++) {
            int di = Math.abs(a[i]-b[i]);
            int aj = search(b[i], sorted);
            int dj = Math.abs(aj-b[i]);
            //System.out.println(di+" "+dj+" "+" "+aj);
            // 存在一种方案使得第i项的差值更小
            if (dj < di) {
                // 记录下全局能使得差值更小的方案
                delta = Math.max(delta, Math.abs(di-dj));
                //System.out.println(delta);
            }
        }
        // 这里的溢出比较恶心，可能存在 1.diffSum < delta的情况
        // 2.diffSum可能是mod+1，delta可能是mod-1 则 [(mod+1)-(mod-1)]%mod = 1-(mod-1) < 0，所以要加个mod变成正数再模掉
        // 可以替换成long写
        //System.out.println(diffSum+" "+delta);
        return (diffSum - delta + mod) % mod;
    }

    int search(int t, int[] a) {
        // xxxoo，{xxx} <= t
        int l = 0, r = a.length-1, m ;
        while (l + 1 < r) {
            m = (l+r) >> 1;
            if (a[m] == t) return a[m];
            if (a[m] < t) {
                l = m;
            }
            else {
                r = m;
            }
        }
        if (Math.abs(a[l]-t) < Math.abs(a[r]-t)) return a[l];
        return a[r];
    }
}
