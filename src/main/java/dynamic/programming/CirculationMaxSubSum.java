package dynamic.programming;

/**
 * Question Description:
 *  参见 lintcode 1724 medium
 *
 * Analysis:
 *  线性的最大连续子段和问题，f(i)以ai结尾的最大连续字段和，f(i)=max{f(i-1) + ai, ai}
 *  对于线性的最大连续子段和问题比较好做，但是现在变成圈了。
 *  破圈：某个最优序列肯定存在，ai ai+1 ... aj 有可能跨了头和尾，也就是说ai可能在后半段，aj会在前半段。
 *  对于破圈就要枚举从哪里开始断开，然后去计算从此处断开后的最大连续子段和，这样时间复杂度是 O(n^2)，
 *  标准的题解做法是，ai ... aj是正数且和最大，剩下的那段是负数，对数组每个元素取反之后，ai...aj的和就是负数，
 *  而另一段的和就从负转正，用f(i)求得这个取反后的最大连续子段和 max2，
 *  然后用整个数组的和 - max2 取绝对值就是原数组另一段的最大连续子段和。
 *
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 */
public class CirculationMaxSubSum {

    public static void main(String[] args) {
        CirculationMaxSubSum circulationMaxSubSum = new CirculationMaxSubSum();
        int[] A = {5, -3, 5};
        int i = circulationMaxSubSum.maxSubarraySumCircular(A);
        System.out.println(i);
    }

    public int maxSubarraySumCircular(int[] A) {
        if (A.length == 1) {
            return A[0];
        }

        int previous ;
        int i, n = A.length, max = Integer.MIN_VALUE, max2 = max;

        // calc 0~n
        previous = A[0];
        for (i = 1; i < n; i++) {
            previous = Math.max(previous + A[i], A[i]);
            max = Math.max(previous, max);
        }

        // 如果序列中只有负数，最小的序列号必定是最小的负数，长度只有1，不存在跨段
        if (max < 0) {
            return max;
        }

        // 取反后再求最大连续子数组和
        previous = -A[0];
        int sum = previous;
        for (i = 1; i < n; i++) {
            int contrast =  -A[i];
            sum += contrast;
            previous = Math.max(previous + contrast, contrast);
            max2 = Math.max(previous, max2);
        }

        return Math.max(max, Math.abs(sum - max2));
    }

}