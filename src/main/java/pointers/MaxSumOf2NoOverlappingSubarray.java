package pointers;

/**
 * lc 1031 medium
 *
 * Analysis:
 * 时间复杂度：O(n^2)
 * 空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2022/9/27
 */
public class MaxSumOf2NoOverlappingSubarray {

    /**
         前缀和+滑动窗口
         如果这题是不定长，那2个窗口有4个点i j p q
         两个窗口是定长的分别L和M，只要枚举j和q，用前缀和快速算出窗口最大和
     */
    int[] sums ;

    public int maxSumTwoNoOverlap(int[] nums, int firstLen, int secondLen) {
        int n = nums.length;
        sums = new int[n];
        sums[0] = nums[0];
        for (int i = 1; i < n; i++) {
            sums[i] = sums[i-1]+nums[i];
        }
        int ans = 0;
        for (int j = 0; j < n; j++) {
            if (j < firstLen-1) {
                continue;
            }
            for (int q = 0; q < n; q++) {
                if (q < secondLen-1) {
                    continue;
                }
                if (cross(j, q, firstLen, secondLen)) {
                    //System.out.println("j="+j+" q="+q);
                    continue;
                }
                int a = sums[j] - (j-(firstLen-1)-1 < 0 ? 0 : sums[j-(firstLen-1)-1]);
                int b = sums[q] - (q-(secondLen-1)-1 < 0 ? 0 : sums[q-(secondLen-1)-1]);
                //System.out.println("j="+j+" q="+q+" a="+a+" b="+b+" a+b="+(a+b));
                ans = Math.max(ans, a+b);
            }
        }
        return ans;
    }

    boolean cross(int j, int q, int L, int M) {
        int i = j-(L-1);
        int p = q-(M-1);
        return (i <= p && p <= j) || (i<= q && q <= j) || (p <= i && i <= q);
    }
}
