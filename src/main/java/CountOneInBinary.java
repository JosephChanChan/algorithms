/**
 * 剑指Offer 15 easy & lc 191 easy
 *
 * Analysis:
 * 有个trick地方在于，n如果是负数时要注意最高位与后的值是负数，要判断与后结果非0，而不是>0
 *
 *  时间复杂度：O(length(n))
 *  空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2020-11-08 15:26
 */
public class CountOneInBinary {

    // 常规解法 O(length(n))
    public int hammingWeight(int n) {
        int count = 0, idx = 1;
        for (int i = 0; i < 32; i++) {
            if ((n & idx) != 0) count++;
            idx = idx << 1;
        }
        return count;
    }

    // 装逼解法 上界 O(length(n))
    public int trickCalc(int n) {
        int count = 0;
        while (n != 0) {
            count++;
            // 消除n中最右边的1
            n = (n - 1) & n;
        }
        return count;
    }

}
