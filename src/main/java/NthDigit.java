/**
 * lc 400 medium
 *
 * Analysis:
 *  数学分析题。
 * 分析1~9，10~99，100~999会发现每一段区间的字符数范围。如1~9有9个数共有1~9的字符。
 * 10~99共有90个数，共有180个字符，那么字符的起始范围是 10~189
 * 100~999，共有900个数，2700个字符，字符起始范围是 190~2899
 * 所以先确定n所在的范围，然后计算这个范围的起始字符偏移量，计算n所属的数字。
 * 确定这个数字的起始字符偏移量，然后就可计算出n是这个数字的第几位字符。
 *
 * 时间复杂度：O(logN) 以10为底的N的对数
 * 空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2021-05-22 15:38
 */
public class NthDigit {

    public int findNthDigit(int n) {
        if (n < 10) return n;

        long digit = 1, start = 1, end = (int)Math.pow(10, digit)-1, chars = (end-start+1)*digit, oldChars = chars;
        while (chars < n) {
            digit++;
            start *= 10;
            end = (int)Math.pow(10, digit)-1;
            // chars字符数是基于上一个范围最后一个数的偏移量计算
            oldChars = chars;
            chars = (oldChars+1)+(end-start+1)*digit-1;
            //System.out.println(chars+" "+(oldChars));
        }
        // 现在n肯定在 oldChars+1~chars 之间
        long offset = n-(oldChars+1);
        long a = (offset/digit)+start;
        // 算出a的第一个字符的起始偏移量
        long startOffset = (a-start)*digit+(oldChars+1);
        char[] c = String.valueOf(a).toCharArray();
        int ans = -1, j = 0;
        for (long i = startOffset; i <= n; i++, j++) ans = c[j]-'0';
        return ans;
    }
}
