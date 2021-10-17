package binary.search;

/**
 * lc 34 medium
 *
 * Analysis:
 *  二分找t的左右边界。找左边界时找到t后继续向左搜索。右边界同理
 *
 * 时间复杂度：O(logN)
 * 空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2020-08-22 20:56
 */
public class FindFirstAndLastPositionElementSortedArray {

    public int[] searchRange(int[] nums, int target) {
        int n = nums.length;
        if (n == 0) return new int[]{-1, -1};
        int l = 0, r = n-1, i = -1, p = i, q = i;

        p = findIdx(target, nums, 0, n-1, true);
        q = findIdx(target, nums, 0, n-1, false);

        if (p == -1 || q == -1) return new int[]{-1, -1};

        return new int[]{p, q};
    }

    int findIdx(int t, int[] a, int l, int r, boolean left) {
        if (l > r) return -1;

        int ans = -1;
        while (l + 1 < r) {
            int m = (l+r) >> 1;
            if (a[m] == t) {
                ans = m;
                if (left) r = m;
                else l = m;
            }
            else if (a[m] > t) {
                r = m;
            }
            else {
                l = m;
            }
        }
        if (left) {
            if (a[l] == t) return l;
            if (a[r] == t) return r;
        }
        else {
            if (a[r] == t) return r;
            if (a[l] == t) return l;
        }
        return ans;
    }
}
