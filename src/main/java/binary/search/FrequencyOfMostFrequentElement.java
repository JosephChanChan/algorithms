package binary.search;

import java.util.Arrays;

/**
 * lc 1838 medium
 *
 * Analysis:
 * 首先想一个问题。假设数组是升序的，我指定数组中一个元素 ai，
 * 让你把数组中其它元素在k次操作内变为ai，要怎么做？
 *
 * 只加不减，所以只能把小于ai的元素增加使得和ai一样大。
 * aj < ai，将aj变为ai的代价为 ai-aj
 * 那么从ai-1开始往前遍历，看k次操作最多能让多少个aj变为ai。
 * 设f(l,r)为将al~ar的全部元素变为ai的代价，那么怎么计算？
 * sum(l,r)为l~r的全部元素值的和。
 * ai*(r-l+1)是将l~r个元素从0变为ai的代价。
 * f(l,r)=ai*(r-l+1)-sum(l,r)
 * f(l,r)<=k，那么就可以将l~r的元素在k次操作内变为ai
 *
 * 时间复杂度：O(N*logN)
 * 空间复杂度：O(N)
 *
 * @author Joseph
 * @since 2021-04-25 16:57
 */
public class FrequencyOfMostFrequentElement {

    int n ;
    long[] pSum ;
    int[] a ;

    public int maxFrequency(int[] nums, int k) {
        if (nums.length == 1) return 1;

        this.n = nums.length;
        pSum = new long[n];
        a = nums;
        Arrays.sort(a);
        pSum[0] = a[0];

        for (int i = 1; i < n; i++) pSum[i] = pSum[i-1]+a[i];

        int l = 1, r = n;
        while (l + 1 < r) {
            int count = (l+r)>>1;
            if (check(count, k)) {
                l = count;
            }
            else {
                r = count;
            }
        }
        if (check(r, k)) return r;
        return l;
    }

    // 猜给定的k能使数组中count个数统一
    boolean check(int count, int k) {
        count--;// ai自己算一个
        for (int i = n-1; i >= 0; i--) {
            if (i < count) return false;

            // 小心int溢出，a[i]是int，a[i]*count可能溢出32位，变负数导致结果错误，所以a[i]计算前先转long
            long cost = (long)a[i]*count;
            long base = pSum[i]-(i-count-1 < 0 ? 0 : pSum[i-count-1])-a[i];
            if (cost - k <= base) {
                //System.out.println("a[i]="+a[i]+" k="+k+" count="+count+" base="+base+" cost="+cost);
                return true;
            }
        }
        return false;
    }
}
