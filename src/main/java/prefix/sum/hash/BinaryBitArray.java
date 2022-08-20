package prefix.sum.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * lc 525 medium
 *
 * Analysis:
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2022/2/19 4:40 PM
 */
public class BinaryBitArray {

    public int findMaxLength(int[] nums) {
        /**
             f(i)为0~i的子数组中0和1的个数的差，如果f(i)=2代表0~i的区间中0数量比1数量多2个
             如果f(j)不等于0，设f(j)=2，尝试看前面的子数组中找是否有f(i)=2，i<j
             舍弃f(i)那段子数组则i+1~j这段子数组的0和1数量会相等

             证明设g(i,0)是0~i区间内0的数量，g(i,1)是0~i区间内1的数量，
             g(i,0)=x, g(j,1)=y
             g(j,0)=x+x1，g(j,1)=y+y1
             i<j，x1和y1即i+1~j这段区间内0和1的增量
             如果 (x+x1)-(y+y1)=2，x-y=2，变形代入得到 2+x1-y1=2，
             推出x1=y1
             即在i+1~j这段区间，0和1的增量是相同的，记录这段区间的长度
         */
        int n = nums.length;
        int[] f = new int[n];
        f[0] = nums[0] == 0 ? 1 : -1;
        for (int i = 1; i < n; i++) {
            f[i] = f[i-1] + (nums[i] == 0 ? 1 : -1);
        }
        int ans = 0;
        // 0和1的差 -> 下标
        Map<Integer, Integer> mem = new HashMap<>();
        mem.put(f[0], 0);
        for (int i = 1; i < n; i++) {
            if (f[i] == 0) {
                // 下标从0开始
                ans = Math.max(ans, i + 1);
            }
            else if (mem.containsKey(f[i])) {
                ans = Math.max(ans, i - mem.get(f[i]));
            }
            mem.putIfAbsent(f[i], i);
        }
        return ans;
    }
}
