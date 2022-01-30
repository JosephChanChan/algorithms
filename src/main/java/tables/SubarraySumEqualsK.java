package tables;

import java.util.HashMap;
import java.util.Map;

/**
 * lc 560 medium
 *
 * Analysis:
 *
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2021-03-24 18:05
 */
public class SubarraySumEqualsK {

    public int subarraySum(int[] nums, int k) {
        return enumerateStartWithHash(nums, k);
    }

    int enumerateStartWithHash(int[] a, int k) {
        /*
            由于有负数，i~j窗口无法判断j++时是否会令窗口更大
            因为j可能滑入负数而更小，或i指向负数，需要i--使得更大。

            用hash解这题本质也是快速找起点的思路。前缀和的灵活应用
            sum-k=d
            在遍历过程中，i~j的和为sum，每次判断sum和k的差值关系
            如果d>0代表窗口值太大，如果i~j的窗口内能丢弃d的那部分，正好剩下的连续窗口值就等于k
            如果d<0代表窗口值太小，如果i~j的窗口内有负数部分也就是d，丢弃负数部分，就能使窗口值等于k
        */
        Map<Integer, Integer> map = new HashMap<>();

        int sum = 0, c = 0;
        for (int i = 0; i < a.length; i++) {
            sum += a[i];
            if (sum == k) c++;
            c += map.getOrDefault(sum - k, 0);
            map.put(sum, map.getOrDefault(sum, 0)+1);
        }
        return c;
    }

    int m1(int[] a, int k) {
        int c = 0;

        int[] ps = new int[a.length];
        ps[0] = a[0];
        for (int i = 1; i < ps.length; i++) {
            ps[i] = ps[i-1]+a[i];
        }
        for (int i = 0; i < a.length; i++) {
            for (int j = i; j < a.length; j++) {
                // sum(i~j) == k
                if (sum(i, j, ps) == k) c++;
            }
        }
        return c;
    }
    int sum(int i, int j, int[] ps) {
        if (i == 0) return ps[j];
        return ps[j]-ps[i-1];
    }
}
