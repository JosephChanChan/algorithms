package dynamic.programming;

/**
 * lc 2369 medium
 *
 * Analysis:
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2022/8/10
 */
public class ValidPartitionForArray {

    /*
        f(i)为A前i个元素按规则划分后的有效性，v(i,j)为Ai~Aj的元素是否满足规则
        f(i)=v(i-1,i)&&f(i-2) or v(i-2,i)&&f(i-3)
        边界：
        f(0)=t, f(1)=f, f(2)=v(0,1)
    */

    public boolean validPartition(int[] nums) {
        int n = nums.length;
        int[] a = nums;
        boolean[] f = new boolean[n+1];
        f[0] = true;
        f[1] = false;
        f[2] = valid(0, 1, a);

        for (int i = 3; i <= n; i++) {
            f[i] = (valid(i-2, i-1, a) && f[i-2]) || (valid(i-3, i-1, a) && f[i-3]);
        }
        return f[n];
    }

    boolean valid(int i, int j, int[] a) {
        if (i >= j) {
            return false;
        }
        boolean val = a[j] == a[i] && i+1 == j;
        if (!val && j-i == 2) {
            val = (a[i] == a[i+1] && a[i+1] == a[j]) || (a[i] + 1 == a[i+1] && a[i+1] + 1 == a[j]);
        }
        return val;
    }
}
