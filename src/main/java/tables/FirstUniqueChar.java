package tables;

/**
 * 剑指Offer 50 easy
 *
 * Analysis:
 *  hash思想，统计每个字符出现次数，按照原字符顺序判断第一个只出现1次的字符。
 *  不用数组的话可用LinkedHashMap
 *
 * 时间复杂度：O(n)
 * 空间复杂度：O(128)
 *
 * @author Joseph
 * @since 2021-01-10 16:36
 */
public class FirstUniqueChar {

    public char firstUniqChar(String s) {
        if (null == s || s.equals("")) return ' ';

        // ascii码0~127
        int[] map = new int[128];
        char[] c = s.toCharArray();
        for (int i = 0; i < c.length; i++) {
            map[c[i]]++;
        }
        for (int i = 0; i < c.length; i++) {
            if (map[c[i]] == 1) return c[i];
        }
        return ' ';
    }
}
