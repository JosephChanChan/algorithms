import java.util.HashSet;
import java.util.Set;

/**
 * lc 41 hard
 *
 * Analysis:
 *
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2021-04-11 17:01
 */
public class FirstMissingPositiveNum {

    public int firstMissingPositive(int[] nums) {
        return mark(nums);
    }

    int mark(int[] a) {
        /*
            把数组中所有正数k，放到下标k-1位置
            负数和超过数组长度的正数不理会
            如果第一个缺失的正数在数组长度范围内，数组中将会有一个位置缺失，a[i]!=i+1
            如果第一个缺失正数不在数组范围内，意味着数组所有元素都是 a[i]==i+1
            答案就是 n+1
        */
        if (a.length == 0) return 1;

        for (int i = 0; i < a.length; i++) {
            if (a[i] > 0 && a[i] <= a.length) place(a, a[i]);
        }
        for (int i = 0; i < a.length; i++) {
            if (a[i] != i+1) return i+1;
        }
        return a.length+1;
    }

    // 把k放到下标k-1的位置
    void place(int[] a, int k) {
        if (k == a[k-1]) return;

        int t = a[k-1];
        a[k-1] = k;
        if (t > 0 && t <= a.length) {
            place(a, t);
        }
    }

    int hash(int[] a) {
        Set<Integer> vis = new HashSet();
        for (int i = 0; i < a.length; i++) vis.add(a[i]);
        for (int i = 1; i <= 300; i++) {
            if (!vis.contains(i)) return i;
        }
        return -1;
    }
}
