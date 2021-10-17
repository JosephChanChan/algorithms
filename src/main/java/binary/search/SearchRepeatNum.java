package binary.search;

import java.util.Arrays;

/**
 * lc 287 medium
 *
 * Analysis：
 *  使用O(n)空间用位图法统计数字次数，O(n)时间
 * 使用O(1)空间，排序后遍历，O(N*logN)时间
 * 使用二分，O(1)空间，O(N*logN)时间
 *
 * @author Joseph
 * @since 2021-03-07 14:34
 */
public class SearchRepeatNum {

    public int findDuplicate(int[] nums) {
        return binary(nums);
    }

    int bitMap(int[] a) {
        int[] b = new int[a.length];
        for (int i : a) {
            b[i]++;
            if (b[i] > 1) return i;
        }
        return -1;
    }

    int sort(int[] a) {
        Arrays.sort(a);
        for (int i = 0; i < a.length; i++) {
            if (i < a.length-1 && a[i] == a[i+1]) return a[i];
        }
        return -1;
    }

    int binary(int[] a) {
        /*
            a长n+1，根据题意元素范围1~n，且其中有一个元素重复2次或以上
            重复元素设为k，k在[1,n]。二分时的中位m，如果k在m右边，则a中小于m的数应是1~m-1有m-1个。
            扫一遍a，统计小于m的数。大于m-1则代表k在m的左边。
        */
        int len = a.length;
        int n = len-1;
        int l = 1, r = n;
        while (l + 1 < r) {
            int m = (l+r)>>1;
            int small = smaller(m, a);
            if (small > m-1) {
                r = m;
            }
            else {
                l = m;
            }
        }
        if (repeat(l, a) > 1) return l;
        if (repeat(r, a) > 1) return r;
        return -1;
    }

    int smaller(int m, int[] a) {
        int c = 0;
        for (int i : a) {
            if (i < m) c++;
        }
        return c;
    }

    int repeat(int i, int[] a) {
        int c = 0;
        for (int k : a) {
            if (k == i) c++;
        }
        return c;
    }
}
