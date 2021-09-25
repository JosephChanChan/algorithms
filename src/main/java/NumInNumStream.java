/**
 * 剑指Offer 44 & 400 medium
 *
 * Analysis:
 * 数学分析题
 *
 * 1~9 直接映射
 * 10~99   一个数占两位，下标10指向10的'1'，所以开始下标是10。区间范围的数设为m，开始下标+(2m-1)=结束下标，
 * 所以[(99-10)+1]*2-1+10=189
 * 100~999 一个数占3位，开始下标190，结束下标= 900*3+190=2890
 *
 * 总结：
 * 设数位 digit
 * 结束下标= (digit * m)-1 + start
 *
 * 从10开始计算每个区间的结束下标，n<=某个结束下标时代表某个区间范围，n-这个区间的开始下标/该区间每个数的占位=某个具体数字
 *
 * 时间复杂度：O(logN)
 * 空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2021-01-01 20:05
 */
public class NumInNumStream {

    public int findNthDigit(int n) {
        if (n < 10) return n;

        int num = 10;
        long m = 90, exponent = 1;
        long start = 10, end = (exponent+1)*m-1 + start;
        for ( ; n > end; end = (exponent+1)*m-1 + start) {
            exponent++;
            start = end + 1;
            m *= 10;
            num *= 10;
        }
        // n <= end
        // n和该区间的开始下标的差，代表从开始下标到n有多少位，差/位数=从开始下标到n之间有k个数字
        long k = (n - start)/(exponent+1);
        // 余数决定了在某个具体数字中，第n位指向哪个数字
        long mod = (n - start)%(exponent+1);
        // 该区间的开始数字+k个数字就定位到某个具体数字 ans
        long ans = k + num;
        char[] c = String.valueOf(ans).toCharArray();
        // 余数代表 ans 中指向第几个字符
        return c[(int) mod] - '0';
    }
}
