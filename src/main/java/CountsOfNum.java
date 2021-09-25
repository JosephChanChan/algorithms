
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
 * 这个算法可以适用1~9，直接套用此模板可分析。
 * 对于0，需要适当改动，例如：XXX Y XX，当XXX开头是0时，Y取0是没意义的。
 *
 * 时间复杂度：O(logN)
 * 空间复杂度：O(n) 用了char[]
 */
public class CountsOfNum {

    public int countDigitOne(int n) {
        if (n == 0) return 0;
        if (n < 10) return 1;
        if (n == 10) return 2;

        int ans = 0, hi = 0, low = 0;
        String num = String.valueOf(n);
        for (int i = num.length()-1; i >= 0; i--) {
            // 算 00~XX-1 开头
            // 高位有 00~xx-1 共xx种排列
            hi = 0; low = 1;
            if (i > 0) {
                hi = (Integer.parseInt(num.substring(0, i)));
            }
            // 低位有 000~xxx 共 10^d 种排列 d是低位位数
            if (i < num.length()-1) {
                low = (int) Math.pow(10, num.length()-i-1);
            }
            ans += (hi * low);

            // XX开头且Y>1，Y取1且低位可取全部排列
            // 如果Y是最低位，则它的高位都固定了，Y只能取1一次
            if (num.charAt(i) > '1') {
                ans += Math.pow(10, num.length()-i-1);
            }
            // XX开头且Y=1，低位只能取到 000~XXX 种排列
            // 如果Y是最低位，则它的高位都固定了，Y只能取1一次
            if (num.charAt(i) == '1') {
                ans += i < num.length()-1 ? Integer.parseInt(num.substring(i+1))+1 : 1;
            }
        }
        return ans;
    }
}