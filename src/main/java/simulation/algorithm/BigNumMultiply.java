package simulation.algorithm;

/**
 * lc 43 medium
 *
 * Analysis:
 *
 * 时间复杂度：O(nm)
 * 空间复杂度：O(n+m)
 *
 * @author Joseph
 * @since 2021-03-29 15:54
 */
public class BigNumMultiply {

    public String multiply(String num1, String num2) {
        if (null == num1) return num2;
        if (null == num2) return num1;

        if (num1.equals("1")) return num2;
        if (num2.equals("1")) return num1;

        if (num1.equals("0") || num2.equals("0")) return "0";

        char[] a = num1.toCharArray();
        char[] b = num2.toCharArray();
        int[] mul = new int[a.length + b.length];

        /*
            mul数组长度是有技巧的。
            假设a长度为p，b长度为q。

            a最大为10^p-1，b最大为10^q-1
            (10^p-1)*(10^q-1)=10^(p+q)-10^p-10^q+1
            10^(p+q)长度为p+q+1
            显然 (10^p-1)*(10^q-1) < 10^(p+q)
            所以a*b长度最大 < p+q+1

            a最小为10^(p-1)，b最小为10^(q-1)
            10^(p-1)*10^(q-1)=10^(p+q-2) 长度为 p+q-1

            所以 a*b 长度在 [p+q-1, p+q]

            Ai * Bj 在i+j位置上
        */

        for (int i = a.length-1; i >= 0; i--) {
            for (int j = b.length-1; j >= 0; j--) {
                int p = a[i]-'0';
                int q = b[j]-'0';
                mul[i+j] += p*q;
            }
        }

        StringBuilder ans = new StringBuilder();
        for (int i = mul.length-1; i > 0; i--) {
            mul[i-1] += mul[i]/10;
            mul[i] = mul[i]%10;
        }
        for (int i = 0; i < mul.length; i++) {
            if (i == mul.length-1 && mul[i] == 0) continue;
            ans.append(mul[i]);
        }
        // a*b可能有前导0
        if (ans.charAt(0) == '0') ans.delete(0, 1);
        return ans.toString();
    }
}
