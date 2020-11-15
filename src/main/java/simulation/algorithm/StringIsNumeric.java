package simulation.algorithm;

/**
 * 剑指Offer 20 medium
 *
 * Analysis:
 *  二维状态机表，表示每个状态后可直接接入的状态
 * e/E后不可再接入 ./e/E
 * .后不可再接入 .
 *
 * 这题“面向测试用例编程”，有很多想不到的坑
 * 1. 是合法的
 * .234 是合法的
 * 46.e3 是合法的
 * 4e6.e3 是非法的
 *
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2020-11-15 15:19
 */
public class StringIsNumeric {

    boolean numeric = false;
    boolean exponent = false;
    boolean decimal = false;

    boolean[][] stateM = {
            // 数字 e/E     +/-    .   字母
            {true, true, false, true, false},//         数字
            {true, false, true, false, false},//        e/E
            {true, false, false, true, false},//        +/-
            {true, true, false, false, false},//        .
            {false, false, false, false, false}//       字母
    };

    public boolean isNumber(String s) {
        if (null == s) return false;
        s = s.trim();
        char[] ch = s.toCharArray();
        if (ch.length == 0) return false;

        for (int i = 0; i < ch.length; i++) {
            char c = ch[i];
            if (c == '.') {
                if (exponent || decimal) return false;
                decimal = true;
            }
            if (c == 'e' || c == 'E') {
                if (!numeric || exponent) return false;
                exponent = true;
            }
            int role = mapping(c);
            if (role == 0) numeric = true;
            if (role == 4) return false;
            if (i == ch.length-1) {
                if (role == 1 || role == 2 || (role == 3 && !numeric)) return false;
            }
            else {
                char next = ch[i+1];
                int role2 = mapping(next);
                if (!stateM[role][role2]) return false;
            }
        }
        return true;
    }

    private int mapping(char c) {
        if (c >= '0' && c <= '9') return 0;
        else if (c == 'e' || c == 'E') return 1;
        else if (c == '+' || c == '-') return 2;
        else if (c == '.') return 3;
        else return 4;
    }
}
