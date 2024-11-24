package greedy.algorithm;

/**
 * lc 334 medium
 *
 * Analysis：
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2023/1/6
 */
public class IncreasingTripletSubsequence {

    /**
         普通枚举i j k要n^3时间，如果换成枚举j，往左边和右边找i k时间降为n^2。
         如何在枚举j时，通过O(1)时间知道0~j-1中<Aj的数？
         类似dp的思想：通过预处理，得到leftMin数组，记录每个数字左边的最小值，rightMax同理

         O(n)时间，空间O(1)的做法：
         开两个变量min mid，如果Ai > mid，则min, mid, Ai组成i j k
         如果min < Ai < mid，则mid更新为Ai，后面更大概率找到符合的k
         如果Ai < min，同理min更新为Ai，后面更大概率找到符合的j。虽然现在min在mid后面，但是mid前面有一个原来的min`，
         后面遇到一个Ai > mid，也可以构成min`，mid，Ai的递增3元子序列
     */

    public boolean increasingTriplet(int[] nums) {
        return greedy(nums);

    }

    boolean greedy(int[] a) {
        int n = a.length;
        if (n < 3) {
            return false;
        }
        int min = a[0], mid = Integer.MAX_VALUE;
        for (int i = 1; i < n; i++) {
            int k = a[i];
            if (mid == Integer.MAX_VALUE && k > min) {
                mid = k;
            }
            else if (k > mid) {
                return true;
            }
            else if (k > min) {
                mid = k;
            }
            else if (k < min) {
                min = k;
            }
        }
        return false;
    }

    boolean dp(int[] a) {
        int n = a.length;
        int[] left = new int[n];
        int[] right = new int[n];
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            left[i] = min;
            min = Math.min(min, a[i]);
        }
        int max = 0;
        for (int i = n-1; i >= 0; i--) {
            right[i] = max;
            max = Math.max(max, a[i]);
        }

        for (int i = 0; i < n; i++) {
            boolean hasMin = left[i] < a[i];
            boolean hasMax = right[i] > a[i];
            if (hasMin && hasMax) {
                return true;
            }
        }
        return false;
    }
}
