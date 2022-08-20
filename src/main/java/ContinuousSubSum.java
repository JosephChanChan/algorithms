import java.util.HashSet;
import java.util.Set;

/**
 * lc 523 medium
 *
 * Analysis:
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2022/2/19 3:02 PM
 */
public class ContinuousSubSum {

    public boolean checkSubarraySum(int[] nums, int k) {
        /**
             数学分析，考同余定理
             数据范围是10^5，O(n^2)会超时
             用前缀和数组加快计算速度，f(i)是0~i的前缀和
             f(i,j)=f(j)-f(i-1)
             f(j)-f(i-1)=n，题目要求找n/k=x，找到这个n
             n/k=x => n=kx => f(j)-f(i-1)=kx => f(j)/k - f(i-1)/k = x
             k和x都是整数，这个差是整数意味着
             f(j)%k和f(i-1)%k的余数必须相同，否则两个式子的余数不同，两个式子相减后必然商不会是整数
             即 f(j)%k - f(i-1)%k = 0
             如果我们枚举右端点j时发现一个区间和%k的余数在之前出现过，意味着找到相同余数，即f(j)/k - f(i-1)/k = x成立
             则n/k=x成立
         */
        int n = nums.length;
        int[] f = new int[n];
        f[0] = nums[0];
        for (int i = 1; i < n; i++) {
            f[i] = f[i-1] + nums[i];
        }
        Set<Integer> mem = new HashSet<>();
        // 题目要求0是k的倍数，即一个区间和是0，也是答案
        mem.add(0);
        for (int i = 1; i < n; i++) {
            // 只记录到目前i-2的余数，保证子区间长度是2以上
            mem.add(i < 2 ? 0 : f[i-2]%k);
            int re = f[i]%k;
            if (mem.contains(re)) {
                return true;
            }
        }
        return false;
    }
}
