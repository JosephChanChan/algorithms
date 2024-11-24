package dynamic.programming;

/**
 * lc 91 medium
 *
 * Analysis:
 *  和剑指offer 46稍有不同，在边界的处理上
 *
 * @author Joseph
 * @since 2022/2/4 6:38 PM
 */
public class DecodeWays2 {

    /*
        设计状态->思考最后一步->突破点：思考如何缩减问题的规模
        将长度n的字符串解码的方法数，规模缩减为n-1长度的字符串解码的方法数
        所以最后一步是，将最后一个字符或者最后2个字符解码

        f(i)为前i个字符解码的方法数
        f(i)= f(i-1) && canDecode(i) + f(i-2) && canDecode(i-1, i)

        边界：
        f(-i)=0
        f(0)=1
        f(1)=0/1，根据第一个字符是否0来决定
        这题的边界处理有点麻烦，动态规划的边界值是根据题目要求来定义的，看边界值需要定义成什么
     */

    public int numDecodings(String s) {
        int n = s.length();

        if (n == 1 && s.equals("0")) return 0;

        int[] f = new int[n+1];
        f[0] = 1;
        f[1] = s.charAt(0) == '0' ? 0 : 1;
        char[] c = new char[n+1];
        for (int i = 0; i < n; i++) {
            c[i+1] = s.charAt(i);
        }
        for (int i = 2; i <= n; i++) {
            if (c[i] > '0') {
                f[i] += f[i-1];
            }
            if (i > 1 && (c[i-1] == '1' || (c[i-1] == '2' && c[i] <= '6'))) {
                f[i] += f[i-2];
            }
        }
        return f[n];
    }
}
