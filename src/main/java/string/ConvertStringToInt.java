package string;

/**
 * 剑指Offer 67 medium
 *
 * Analysis:
 *  依题意截取数字部分。注意处理边角case，如：前导0，+/-号后没有数字，全空格，范围溢出
 *
 * 时间复杂度：O(s length)
 * 空间复杂度：O(s length)
 *
 * @author Joseph
 * @since 2021-01-23 18:18
 */
public class ConvertStringToInt {

    char[] max = String.valueOf(Integer.MAX_VALUE).toCharArray();
    char[] min = String.valueOf(Integer.MIN_VALUE).toCharArray();

    public int strToInt(String str) {
        if (str.length() == 0) return 0;

        boolean positive = true;
        int l = 0, r ;
        for ( ; l < str.length(); l++) {
            if (str.charAt(l) == ' ') continue;
            if ((str.charAt(l) >= '0' && str.charAt(l) <= '9') || str.charAt(l) == '-' || str.charAt(l) == '+') {
                break;
            }
            return 0;
        }
        if (l == str.length()) return 0;

        StringBuilder b = new StringBuilder();
        if (str.charAt(l) == '+') l++;
        else if (str.charAt(l) == '-') {
            l++;
            positive = false;
        }
        // l期望指向数字，跳过前导0
        for ( ; l < str.length(); l++) {
            if (str.charAt(l) >= '0' && str.charAt(l) <= '9') {
                if (str.charAt(l) > '0' || b.length() > 0) b.append(str.charAt(l));
                continue;
            }
            break;
        }
        if (b.length() == 0) return 0;

        if (positive && !gt(b.toString().toCharArray())) return number(b.toString().toCharArray());
        if (!positive && !lt(b.toString().toCharArray())) return 0-number(b.toString().toCharArray());
        return positive ? Integer.MAX_VALUE : Integer.MIN_VALUE;
    }

    private boolean gt(char[] c) {
        if (c.length > max.length) return true;
        if (c.length < max.length) return false;
        for (int i = 0; i < c.length; i++) {
            if (max[i] < c[i]) return true;
            if (max[i] > c[i]) return false;
        }
        return false;
    }

    private boolean lt(char[] c) {
        if (c.length > min.length-1) return true;
        if (c.length < min.length-1) return false;
        for (int i = 0; i < c.length; i++) {
            if (min[i+1] < c[i]) return true;
            if (min[i+1] > c[i]) return false;
        }
        return false;
    }

    private int number(char[] c) {
        int a = 0;
        for (int i = 0; i < c.length; i++) {
            a = a * 10 + c[i]-'0';
        }
        return a;
    }
}
