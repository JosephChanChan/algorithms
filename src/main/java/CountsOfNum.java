
/**
 * 剑指Offer 43 & lc 233 hard
 *
 * Analysis:
 *  数学分析题，要找每一位中出现1的可能数。
 *  XXX Y XX
 *  以百位为例，求Y在百位出现1的次数
 * case 1:
 *     高位有 000 ~ (XXX-1)种排列，低位有00~99种排列，因为低位任取都小于 XXXYXX
 *     000100
 *     ...
 *     (XXX-1)199
 *
 * case 2 以XXX开头 :
 *     Y > 1，Y取1，XXX100 ~ XXX199，取决于低位的排列数
 *     Y = 0，不可能取1
 *     Y = 1，XXX100 ~ XXX1XX，取决于低位的排列数
 *
 * 依照上面的算法依次计算个位~digit位的1的个数累加
 *
 * 需要二刷
 *
 * 时间复杂度：O(logN)
 * 空间复杂度：O(n) 用了char[]
 */
public class CountsOfNum {

    public int countDigitOne(int n) {
        if (n <= 0) return 0;
        if (n < 10) return 1;

        String num = String.valueOf(n);
        char[] c = num.toCharArray();

        int m = c.length, ans = 0;
        // 从最高位算到最低位  2 3 4 5
        //                   a  i b
        for (int i = 0; i < m; i++) {
            // a是高位数，高位数共有 0~a-1种排列
            int a = n / (int) Math.pow(10.0d, m - i);
            // b是低位数，低位数共有 10^b 种排列
            int b = (int) Math.pow(10.0d, m - i - 1);
            ans += a * b;
            // 算完 0~(XXX-1)的开头排列，当前i位是1的排列数后。
            // 算 XXX开头排列，当前位i是1的排列
            if (c[i] - '0' > 1) {
                ans += (int) Math.pow(10.0d, m-i-1);
            }
            if (c[i] - '0' == 1) {
                ans += n % (int) Math.pow(10.0d, m-i-1) + 1;
            }
        }
        return ans;
    }
}