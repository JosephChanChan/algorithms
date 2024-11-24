package math;

/**
 * lc 396 medium
 *
 * Analysis:
 * time: O(n)
 * space: O(n)
 *
 * @author Joseph
 * @since 2023/8/6
 */
public class RotateFunction {

    public int maxRotateFunction(int[] nums) {
        /*
            考数学分析，观察f(0)到f(1)发现f(0)的每个Ai都加上了一遍自己
            除了最后一个Ai*n-1，因为移到了首项，所以f(1)中要减去Ai*n-1
            定义sum为数组的和，如果f(0)+sum，是不是等于f(0)的每个Ai都加上了一遍自己？
            然后f(0)+sum-[(An-1*n-1)-(An-1)]就等于f(1)了，代入验算下。
            所以化简 f(0)+sum-(An*n)=f(1)，再抽象i从1开始
            f(i)=f(i-1)+sum-(An-i*n)
        */
        int n = nums.length;
        int[] f = new int[n];
        // 先算f(0)
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum+=nums[i];
            f[0] += nums[i]*i;
        }
        // f(i)=f(i-1)+sum-(An-i*n)
        int ans = f[0];
        for (int i = 1; i < n; i++) {
            f[i] = f[i-1]+sum-nums[n-i]*n;
            //System.out.print(f[i]+" ");
            ans = Math.max(ans, f[i]);
        }
        return ans;
    }
}
