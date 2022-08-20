/**
 * lc 2367 easy
 *
 * Analysis:
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2022/8/10
 */
public class NumberArithmeticTriplets {

    /*
        数据量最大200^3，可以O(n^2)
        枚举i j k，当a[j]-a[i]!=diff时，剪枝
    */

    public int arithmeticTriplets(int[] nums, int diff) {
        int n = nums.length;
        int[] a = nums;

        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                if (a[j]-a[i] > diff) {
                    break;
                }
                if (a[j]-a[i] == diff) {
                    for (int k = j+1; k < n; k++) {
                        if (a[k]-a[j] > diff) {
                            break;
                        }
                        if (a[k]-a[j] == diff) {
                            ans++;
                        }
                    }
                }
            }
        }
        return ans;
    }
}
