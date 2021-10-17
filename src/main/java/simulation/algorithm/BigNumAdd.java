package simulation.algorithm;

/**
 * lc 415 easy (不包含负数的大数加法)
 * 大数加法，包含负数。有负数变大数减法
 *
 * 时间复杂度：O(max{length(s1), length(s2)})
 * 空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2020-12-27 16:59
 */
public class BigNumAdd {

    public static void main(String[] args) {
        BigNumAdd m = new BigNumAdd();
        String add = m.add("1000", "11231232222");
        System.out.println(add);
    }

    public String add(String a, String b) {
        if (a.contains("-") || b.contains("-")) {
            return subtract(a, b);
        }
        return addStrings(a, b);
    }

    public String addStrings(String num1, String num2) {
        int n = num1.length();
        int m = num2.length();
        StringBuilder b = new StringBuilder();

        int digit = 0, i = n-1, j = m-1;
        while (i >= 0 || j >= 0 || digit > 0) {
            int p = i >= 0 ? num1.charAt(i)-'0' : 0;
            int q = j >= 0 ? num2.charAt(j)-'0' : 0;
            b.append((p+q+digit)%10);
            // (10~19)/10 = 1
            digit = (p+q+digit)/10;
            i--; j--;
        }
        b.reverse();
        //System.out.println(b);
        return b.toString();
    }

    public String subtract(String a, String b) {
        boolean negativeA = false;
        if (a.contains("-")) {
            negativeA = true;
            a = a.substring(1);
        }
        else {
            b = b.substring(1);
        }

        char[] max, min ;
        boolean ne = false;
        if (greater(a.toCharArray(), b.toCharArray())) {
            if (negativeA) ne = true;
            max = a.toCharArray();
            min = b.toCharArray();
        }
        else {
            if (!negativeA) ne = true;
            max = b.toCharArray();
            min = a.toCharArray();
        }

        int[] p = new int[max.length];
        int[] q = new int[min.length];
        for (int i = 0; i < max.length; i++) p[i] = max[i]-'0';
        for (int i = 0; i < min.length; i++) q[i] = min[i]-'0';

        StringBuilder ans = new StringBuilder();
        int i = p.length-1, j = q.length-1;
        for ( ; j >= 0; i--, j--) {
            if (p[i] < q[j]) {
                p[i] += 10;
                p[i-1]--;
            }
            ans.append(p[i]-q[j]);
        }

        for ( ; i >= 0; i--) {
            if (p[i] < 0) {
                p[i] += 10;
                p[i-1]--;
            }
            if (i == 0 && p[i] == 0) continue;
            ans.append(p[i]);
        }
        if (ne) ans.append("-");
        return ans.reverse().toString();
    }

    boolean greater(char[] a, char[] b) {
        if (a.length > b.length) return true;
        if (a.length < b.length) return false;

        for (int i = 0; i < a.length; i++) {
            if ((a[i]-'0') > (b[i]-'0')) return true;
        }
        return false;
    }

}
