/**
 * 剑指Offer 56 medium
 *
 * Analysis:
 *  数组中有k个重复数字，每个数字重复3次，则共有3k个重复数字
 * 一个重复数字展开二进制表示，每一位从低位到高位相加，该位如果是1，则3个重复数字相加后必然该位结果是3
 * 所有3k个重复数字，从低位到高位，每位相加，结果是3的倍数，因为每个重复数字该位是1的话就是3
 * 本来第i位是可以被3整除的，现在加上唯一的那个数字，如果唯一的那个数字在第i位也是1，如果该位是1的数字有j个，
 * 则是3j+1，不能被3整除。
 * 所以将每位数字求和模除3，余数代表唯一数字在第i位有值，将每位结果保存便是唯一数的二进制表示
 *
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2021-01-17 01:40
 */
public class OneUniqueInThreeRepeated {

    public int singleNumber(int[] nums) {
        /*

        */
        if (nums.length == 1) return nums[0];

        char[] ans = new char[32];

        for (int i = 0; i < 32; i++) {
            int k = 1 << i, c = 0;
            for (int j = 0; j < nums.length; j++) {
                c += (nums[j] & k) > 0 ? 1 : 0;
            }
            ans[31-i] = c % 3 == 1 ? '1' : '0';
        }
        int unique = 0;
        for (int i = 0; i < 32; i++) {
            if (ans[i] == '1') {
                unique += (1 << 31-i);
            }
        }
        return unique;
    }
}
