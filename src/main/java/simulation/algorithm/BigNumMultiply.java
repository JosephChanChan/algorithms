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

    /*
        大数乘法
        Ai * Bj = C[i+j]
        C数组长度开多大？以两位数举例，最小两位数 10*10=100，最大两位数 99*99=9801
        C就开A.length+B.length+2
     */
    public String multiply(String num1, String num2) {
        char[] a = new StringBuilder(num1).reverse().toString().toCharArray();
        char[] b = new StringBuilder(num2).reverse().toString().toCharArray();
        int[] c = new int[a.length+b.length+2];

        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b.length; j++) {
                // Ai * Bj = C[i+j]
                int v = (a[i]-'0') * (b[j]-'0');
                c[i+j] += (v % 10);
                if (v >= 10) {
                    c[i+j+1] += v / 10;
                }
            }
        }
        for (int i = 0; i < c.length; i++) {
            //System.out.print(c[i]+"   ");
            if (c[i] >= 10) {
                c[i+1] += c[i] / 10;
                c[i] = c[i] % 10;
            }
            //System.out.println(c[i]);
        }
        String bb = "";
        // 跳过前导0
        for (int i = c.length-1; i >= 0; i--) {
            if (c[i] == 0 && bb.length() == 0) {
                continue;
            }
            bb += c[i];
        }
        if (bb.length() == 0) {
            bb += "0";
        }
        //System.out.println(bb);
        return bb;
    }
}
