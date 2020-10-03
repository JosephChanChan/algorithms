package pointers;

/**
 * leetcode 209 medium
 *
 * Analysis:
 *  这个算法的确是O(n)的时间，但是在所有提交中比较靠后，去看了题解。
 * 由于提前算了前缀和多了一次O(n)，所以慢了一点。
 *
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2020-10-02 21:26
 */
public class MinimumSizeSubArray {


    // 2ms AC, faster than 38%
    public int minSubArrayLen(int s, int[] nums) {
        /*
            计算出sumSub，sum(i,j)=sum[i]-sum[j]+a[j]，通过O(1)时间得出任意某段子数组的和
            i=0,j=0，计算i~j的sum，如果sum>s，记录这个长度。
            接下来，j++扩大i~j没有意义，因为会使得sum更大并且长度更大。
            我们要找sum>=s的情况下，长度尽可能小。
            所以得缩小i~j的范围，看缩小sum后是否使得sum>=s，如果成立则得到更小的长度。
            j--?不行，i~j-1是枚举过的，所以只能缩小i，i++，求i+1~j的sum。
            如果i~j的sum=s，记录长度，然后i++,j++。
            如果sum<s，扩大i~j范围，j++
        */
        int n = nums.length;
        if (n == 0) return 0;
        int[] sumA = new int[n];
        sumA[0] = nums[0];
        for (int i = 1; i < n; i++) {
            sumA[i] = sumA[i-1] + nums[i];
        }

        int minLength = Integer.MAX_VALUE;
        int i = 0, j = 0;
        while (i <= j && j < n) {
            if (i == j && nums[i] >= s) return 1;
            int sum = calcSum(i, j, sumA, nums);
            if (sum >= s) {
                if (j - i + 1 < minLength) {
                    minLength = j-i+1;
                }
                i++;
                if (sum == s) j++;
            }
            else {
                j++;
            }
        }
        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }

    private int calcSum(int i, int j, int[] sum, int[] nums) {
        return sum[j] - sum[i] + nums[i];
    }
}
