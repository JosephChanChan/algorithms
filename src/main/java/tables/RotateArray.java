package tables;

/**
 * lc 189 medium
 *
 * Analysis：
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2022/12/28
 */
public class RotateArray {

    /*
        k > n时，只需要移动最右边k%n个数
        原地置换的算法，数组反转。发现最右边k%n个数会被移到开头的k个数中，如果一开始将整个数组反转，然后分别将0~k-1 k~n-1两段反转回来就行
     */

    public void rotate(int[] nums, int k) {
        int n = nums.length;
        if (k == n) {
            return;
        }
        if (k > n) {
            k = k % n;
        }
        particalReverse(0, n, nums);
        // 0~k-1 k~n-1反转
        particalReverse(0, k, nums);
        particalReverse(k, n, nums);
    }

    void particalReverse(int i, int j, int[] a) {
        for (int p = i, q = j-1; p < q; p++, q--) {
            int temp = a[p];
            a[p] = a[q];
            a[q] = temp;
        }
    }
}
