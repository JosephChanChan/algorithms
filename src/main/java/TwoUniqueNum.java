/**
 * 剑指Offer 56 medium
 *
 * Analysis:
 *  两个相同数字异或结果为0.
 * 如果数组中只有一个数字唯一，其它数字都出现两次，则遍历异或一遍，
 * 最后只剩唯一的数字，其它出现两次的数字都在异或中相抵消失。
 * 现在数组有两个唯一数字。
 * 假设为 [a,b,c,c,d,d,e,e,f,f]
 * 还是遍历异或一遍，剩下的计算结果是a^b，且a^b>0，因为a!=b，所以必然二进制中至少有一位是1.
 * 从低位开始的第一个1，记录为第k位。
 * 以数组中每一位数字的第k位是否为1为依据分开两个子数组。
 * 相同数字必然被分到相同组，划分完毕后，a和b也会被区分到不同组。
 * 再分别对两组异或就得到答案
 *
 *  时间复杂度：O(n)
 *  空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2021-01-16 22:54
 */
public class TwoUniqueNum {

    public int[] singleNumbers(int[] nums) {
        int[] ans = new int[2];
        if (nums.length == 2) {
            ans[0] = nums[0];
            ans[1] = nums[1];
            return ans;
        }

        int p = nums[0];
        for (int i = 1; i < nums.length; i++) {
            p = p^nums[i];
        }
        int k = 0;
        do {
            if ((p & 1) == 1) break;
            p = p >> 1;
        }
        while (++k < 32);

        int mask = 1 << k;
        for (int i = 0; i < nums.length; i++) {
            if ((nums[i] & mask) > 0) {
                ans[0] = ans[0]^nums[i];
            }
            else {
                ans[1] = ans[1]^nums[i];
            }
        }
        return ans;
    }

}
