package string;

/**
 * lc 13 easy
 *
 * Analysis:
 *
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2021-04-04 00:05
 */
public class RomanToInteger {

    public int romanToInt(String s) {
        /*
            特殊处理4 9，对于当前字符小于下一个字符的
        */
        if (null == s || s.length() == 0) return 0;

        int ans = 0;
        char[] c = s.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (i < c.length-1 && mapping(c[i]) < mapping(c[i+1])) {
                ans += mapping(c[i+1]) - mapping(c[i]);
                i++;
                continue;
            }
            ans += mapping(c[i]);
        }
        return ans;
    }

    int mapping(char a) {
        switch(a) {
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            case 'M': return 1000;
        }
        return Integer.MIN_VALUE;
    }
}
