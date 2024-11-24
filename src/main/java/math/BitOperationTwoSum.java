package math;

/**
 * lc 371 medium
 *
 * Analysis：
 * 时间复杂度：O(32)
 * 空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2023/1/10
 */
public class BitOperationTwoSum {

    /*
        二进制异或是无进位的加法，二进制与运算是进位后的加法
        然后将 (a^b) | ((a&b) << 1)
        直到没有进位
     */

    public int getSum(int a, int b) {
        while (b != 0) {
            // 进位t
            int t = (a & b) << 1;
            // 无进位加法
            a = a ^ b;
            // 如果t没有进位，则a是结果，如果t有进位则再次运算无进位加法
            b = t;
        }
        return a;
    }
}
