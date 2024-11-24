package dynamic.programming;

import java.util.*;

/**
 * lc 368 medium
 *
 * Analysis:
 *  先排序，使得数组按升序 a1<a2<a3<...<an
 * 这样数组任意两个元素都是不同的，所以任意 j < i都有Sj < Si
 * 且如果Sj Si存在倍数关系，则肯定是 Si % Sj=0 且 Sj % Si=Sj
 *
 * 假设存在一种最大的整除序列P
 * P1,P2...Pk，这个序列满足：P1<P2<...<Pk，最后一个元素Pk去掉后剩下的P1~Pk-1仍是原序列中能挑出的最大整除序列。
 * Pk和Pk-1显然满足：Pk-1<Pk && Pk % Pk-1 = 0
 * 那么Pk是否和序列中其它元素存在倍数关系？
 * Pk-1和其它元素存在倍数关系，Pj < Pk-1 && Pk-1/Pj=x，Pk/pk-1=y
 * 则 Pk-1=Pj*x，Pk=pk-1*y，Pk=Pj*x*y
 * 所以是Pk只要和Pk-1比较就行。
 *
 * f(i)是以Ai结尾的最大整除序列的长度
 * f(i)=max{f(j) | j < i && Aj < Ai && Ai % Aj=0} + 1
 * 边界：f(0)=0, f(1)=1
 *
 * 时间复杂度：O(n^2)
 * 空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2020-10-06 10:51
 */
public class LargestDivisibleSubset {

    public List<Integer> largestDivisibleSubset(int[] nums) {
        List<Integer> ans = new LinkedList<>();
        int n = nums.length;
        if (n == 0) return ans;
        if (n == 1) {
            ans.add(nums[0]);
            return ans;
        }

        Arrays.sort(nums);

        int[] f = new int[n];
        // 保存当前状态从哪个前置状态转移过来的
        int[] track = new int[n];
        f[0] = 1;
        track[0] = 0;
        for (int i = 1; i < n; i++) {
            int max = 0, maxIdx = i;
            for (int j = 0; j < i; j++) {
                if (nums[i] % nums[j] == 0 && f[j] > max) {
                    max = f[j];
                    maxIdx = j;
                }
            }
            f[i] = max + 1;
            track[i] = maxIdx;
        }
        int max = 0, maxIdx = 0;
        for (int i = 0; i < n; i++) {
            if (f[i] > max) {
                max = f[i];
                maxIdx = i;
            }
        }
        // 根据track[]选出最优序列的元素
        while (true) {
            ans.add(nums[maxIdx]);
            if (maxIdx == track[maxIdx]) break;
            maxIdx = track[maxIdx];
        }
        return ans;
    }
}
