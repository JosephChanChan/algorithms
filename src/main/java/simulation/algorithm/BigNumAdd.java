package simulation.algorithm;

/**
 * 包含负数的大数加法。还要再刷
 * 正+负时，要去掉负号对比绝对值大小，用绝对值大的减小的。绝对值大的是负数时对结果取负数。
 * 大数减法，就是从低位开始模拟a-b，当a<b时，向高位借位直接减高位的值就行。
 *
 * 时间复杂度：O(max{length(s1), length(s2)})
 * 空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2020-12-27 16:59
 */
public class BigNumAdd {

    static boolean pN = false, qN = false;
    static int[] p, q ;

    public static void main(String[] args) {
        String s1 = "-29";
        String s2 = "100";

        if (s1.charAt(0) == '-') {
            pN = true;
            s1 = s1.substring(1, s1.length());
        }
        if (s2.charAt(0) == '-') {
            qN = true;
            s2 = s2.substring(1, s2.length());
        }

        p = new int[s1.length()];
        q = new int[s2.length()];

        // 存数组，数字高位在数组高位
        char[] c1 = s1.toCharArray();
        for (int i = 0, j = c1.length-1; i < c1.length; i++, j--) {
            p[j] = c1[i] - '0';
        }
        char[] c2 = s2.toCharArray();
        for (int i = 0, j = c2.length-1; i < c2.length; i++, j--) {
            q[j] = c2[i] - '0';
        }

        if (pN && qN) {
            // 负+负
            String ans = add();
            System.out.println("\"-"+ans+"\"");
        }
        else if ((!pN && !qN)) {
            // 正正
            String ans = add();
            System.out.println("\""+ans+"\"");
        }
        else {
            // 负+正
            String ans ;
            int[] beSub, sub ;
            if (p.length == q.length) {
                // 从高位开始比大小
                beSub = p; sub = q;
                for (int i = p.length-1; i >= 0; i--) {
                    if (p[i] == q[i]) {
                        continue;
                    }
                    if (p[i] > q[i]) {
                        beSub = p; sub = q;
                    }
                    else {
                        beSub = q; sub = p;
                    }
                    break;
                }
            }
            else if (p.length > q.length){
                beSub = p; sub = q;
            }
            else {
                beSub = q; sub = p;
            }
            ans = dre(beSub, sub);
            if (!"0".equals(ans) &&
                    ((beSub == p && pN) || (beSub == q && qN))) {
                ans = "-" + ans;
            }
            System.out.println("\""+ans+"\"");
        }
    }

    private static String dre(int[] beSub, int[] sub) {
        StringBuilder bu = new StringBuilder();
        for (int i = 0, j = 0; i < beSub.length || j < sub.length; i++, j++) {
            int a = i < beSub.length ? beSub[i] : 0;
            int b = j < sub.length ? sub[j] : 0;
            if (a < b) {
                a += 10;
                // 这里不会发生越界，因为beSub最高位肯定大于sub最高位
                // 所以要么不会进这里，要么i+1不是最高位
                beSub[i+1]--;
            }
            bu.append(a-b);
        }
        return String.valueOf(Integer.parseInt(bu.reverse().toString()));
    }

    private static String add() {
        StringBuilder bu = new StringBuilder();
        boolean overflow = false;
        for (int i = 0, j = 0; i < p.length || j < q.length; i++, j++) {
            int a, b ;
            a = i < p.length ? p[i] : 0;
            b = j < q.length ? q[j] : 0;
            int ans = a+b;
            if (overflow) {
                ans += 1;
                overflow = false;
            }
            if (ans >= 10) {
                ans = ans % 10;
                overflow = true;
            }
            bu.append(ans);
        }
        if (overflow) {
            bu.append("1");
        }
        return bu.reverse().toString();
    }

}
