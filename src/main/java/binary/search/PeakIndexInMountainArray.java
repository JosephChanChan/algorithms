package binary.search;

/**
 * lc 852 easy
 *
 * Analysis:
 * 题目数据貌似符合 i-2 < i-1 < i > i+1 > i+2 这种
 * 根据特点二分搜索最高点，当前m不是最高点则判断最高点在做还是右
 *
 * 时间复杂度：O(logN)
 * 空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2021-07-26 22:02
 */
public class PeakIndexInMountainArray {

    public int peakIndexInMountainArray(int[] arr) {
        int n = arr.length;
        int l = 0, r = n-1;
        while (l + 1 < r) {
            int m = (l+r) >> 1;
            if (arr[m-1] < arr[m] && arr[m] > arr[m+1]) return m;
            if (arr[m-1] < arr[m]) {
                l = m;
            }
            else if (arr[m] > arr[m+1]) {
                r = m;
            }
        }
        if (arr[l] > arr[r]) return l;
        return r;
    }
}
