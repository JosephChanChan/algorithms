package dynamic.programming;

/**
 * lc 45 medium
 *
 * Analysis:
 *
 * 时间复杂度：贪心 O(n) DP O(n^2)
 * 空间复杂度：贪心 O(1) DP O(n)
 *
 * @author Joseph
 * @since 2021-07-04 23:38
 */
public class JumpGame2 {

    public int jump(int[] nums) {
        return greedy(nums);
    }

    int greedy(int[] a) {
        /**
             题目给了保证，一定能跳到终点。
             基于这个条件，也就是说从起点出发，每次从当前可及的范围内选择下次可跳的最远的点。
             这样每次跳跃时考虑的都是跳到下次可跳的最远的点。
             每一次局部选择都是最优的
         */
        int n = a.length;
        int f = 0;

        for (int i = 0; i < n-1; ) {
            if ((n-i-1) <= a[i]) {
                f++; break;
            }
            int max = i+1;
            for (int j = i+1; j <= i+a[i] && j < n; j++) {
                // 选出下次起跳可以跳的最远位置
                if (max+a[max] <= j+a[j]) {
                    max = j;
                }
            }
            f++;
            i = max;
        }
        return f;
    }

    int dp(int[] nums) {
        /**
             f(i)为从起点跳到i点的最少代价
             f(i)=min{f(j) | j<i && (i-j)<=v[j]}+1
             边界：
             f(0)=0
         */
        int n = nums.length;
        int[] f = new int[n];
        f[0] = 0;

        for (int i = 1; i < n; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < i; j++) {
                if ((i-j) <= nums[j] && min > f[j]) {
                    min = f[j];
                }
            }
            f[i] = min+1;
        }
        return f[n-1];
    }
}
