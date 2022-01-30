/**
 * lc 7 easy
 *
 * Analysis:
 *
 * 时间复杂度：数学法 双指针 O(length)
 * 空间复杂度：数学法O(1)    双指针O(length)
 *
 * @author Joseph
 * @since 2021-02-01 20:40
 */
public class ReverseInt {

    int limit1 = Integer.MAX_VALUE, limit2 = Integer.MIN_VALUE, multiLim ;

    public int reverse(int x) {
        /*
            123 -> 321
            -123 -> -321
            9 -> 9
            10 -> 1
         */
        return doMath(x);
    }

    private int doMath(int x) {
        /*
            借鉴了Integer.parseInt()源码。
            原数字模除10，取余数，放到新数字开头。
            精髓在对于新数字做乘法和加法之前的判断是否溢出。
            以正数举例：
            正数边界：2147483647
            设x=2147483648，反转后必然溢出对吧
            按照算法逐个取余数，放到新数字 ans
            最后一次时 ans已经变成 846384741 还差数字2要加，ans*10就会溢出，因为 8463847410 > 2147483647
            所以要做一次乘法前的溢出检查，某个正数/10 应小于等于 正数边界/10。这是 multiLim 存在的原因
            最后是加上一个余数后ans的溢出检查，设ans= 2147483640，最后从x取出的余数是8，
            2147483640 + 8 > 2147483647 式子左边的计算会溢出，
            所以转换式子 2147483640 > 2147483647 - 8
         */
        if (x == 0) return 0;
        if ((x > 0 && x < 10) || (x < 0 && x > -10)) return x;
        boolean n = x < 0;

        multiLim = n ? limit2 / 10 : limit1 / 10;
        int len = String.valueOf(Math.abs(x)).toCharArray().length;

        int ans = 0, digit ;
        while (len-- > 0) {
            if (!n && ans > multiLim) return 0;
            if (n && ans < multiLim) return 0;
            ans *= 10;
            digit = x % 10;
            if (!n && ans > limit1 - digit) return 0;
            if (n && ans < limit2 + -digit) return 0;
            ans += digit;
            x = x / 10;
        }
        return ans;
    }

    private int twoPointers(int x) {
        /*
            双指针交换x字符串化后的每个字符，简单很多。
            利用异常catch取巧，判断溢出
         */
        if (x == 0) return 0;
        if ((x > 0 && x < 10) || (x < 0 && x > -10)) return x;
        boolean n = x < 0;
        x = Math.abs(x);

        char[] c = String.valueOf(new Integer(x)).toCharArray();

        for (int i = 0, j = c.length-1; i < j; i++, j--) {
            char t = c[i];
            c[i] = c[j];
            c[j] = t;
        }

        int ans = 0;
        try {
            ans = n ? Integer.parseInt("-"+new String(c)) : Integer.parseInt(new String(c));
        }
        catch (Exception e) {
            return 0;
        }
        return ans;
    }
}
