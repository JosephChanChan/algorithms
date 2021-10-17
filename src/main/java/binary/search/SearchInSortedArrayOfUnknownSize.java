package binary.search;

/**
 * lc 702 medium
 *
 * Analysis:
 * 没有给定数组的右边界，但是可以通过log(数组实际长度)时间试出一个右边界，
 * 猜数组右边界，然后计算中值，如果中值>t 或 发生越界，代表当前中值可以确保t在中值左边范围内，可以返回。
 * 如果中值 < t，则倍增右边界，直到右边界越界或产生的中值>t
 * 这样确定右边界时间 log(N) N是数组长度
 *
 * 再通过普通二分找到t
 *
 * 时间复杂度：O(logN)
 * 空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2021-04-10 22:46
 */
public class SearchInSortedArrayOfUnknownSize {

    public int search(ArrayReader reader, int target) {
        int l = 0, r = confirmBoundary(reader, target);
        while (l + 1 < r) {
            int m = (l+r) >> 1;
            int mv = reader.get(m);

            if (mv == target) return m;
            else if (mv < target) {
                l = m;
            }
            else {
                r = m;
            }
        }
        if (reader.get(l) == target) return l;
        if (reader.get(r) == target) return r;
        return -1;
    }

    int confirmBoundary(ArrayReader reader, int t) {
        int l = 0, r = 2;
        while (l + 1 < r) {
            int m = (l+r) >> 1;
            int mv = reader.get(m);
            if (mv == t) return m;
            else if (mv < t) {
                r = r << 1;
            }
            else {
                return m;
            }
        }
        if (reader.get(l) >= t) return l;
        return r;
    }

    // mock
    class ArrayReader {
        int get(int k) {return -1;}
    }
}
