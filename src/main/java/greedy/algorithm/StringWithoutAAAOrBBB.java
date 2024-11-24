package greedy.algorithm;

/**
 * lc 984 medium
 *
 * Analysis:
 * 时间复杂度：O(a+b)
 * 空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2022/11/19
 */
public class StringWithoutAAAOrBBB {

    /*
        按题意不能连续出现3个a或b，也就是a或b最多连续出现2次，假设a的数量为n，则需要n/3个b
        题目保证存在一种排列。也就是说a和b的数量都是够的，按照贪心的思想优先安排最多字母数的字符，等到连续出现2个时插入另一个字符
        经过试验这种思路是错的
        按上面的思路会发现，当ab数量相同时，优先使用a，a用完后就只能用b就会出现违例，因为每次a用的数量是b的2倍

        那如何保证最后不会出现只能连续使用a或b的情况？按这种思路应该是尽量使得a或b的数量均等，谁多先用谁
    */
    public String strWithout3a3b(int a, int b) {
        return m2(a, b);
    }

    String m2(int a, int b) {
        StringBuilder bb = new StringBuilder();
        int p = a, q = b;
        for (int i = 0; i < a+b; i++) {
            if (p == 0 || q == 0) {
                bb.append(p == 0 ? 'b' : 'a');
                continue;
            }
            char c ;
            // 先特判是否快违例了
            if (bb.length() > 1 && bb.charAt(bb.length()-1) == bb.charAt(bb.length()-2)) {
                c = bb.charAt(bb.length()-1) == 'a' ? 'b' : 'a';
                bb.append(c);
            }
            else {
                c = p > q ? 'a' : 'b';
                bb.append(c);
            }
            if (c == 'a') {
                p--;
            }
            else {
                q--;
            }
        }
        return bb.toString();
    }

    String m1(int a, int b) {
        StringBuilder bb = new StringBuilder();
        char p = a > b ? 'a' : 'b';
        char q = a > b ? 'b' : 'a';
        int max = a > b ? a : b;
        int count = 0, count2 = 0;

        for (int i = 0; i < a+b; i++) {
            if (count == max) {
                bb.append(q);
                continue;
            }
            if (count2 == 2) {
                bb.append(q);
                count2 = 0;
            }
            else {
                bb.append(p);
                count2++;
                count++;
            }
        }
        return bb.toString();
    }
}
