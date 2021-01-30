package pointers;

/**
 * lc 76 hard
 *
 * Analysis:
 *  从暴力法O(n^2)开始，i&j指针分别是窗口左右边界。j不断右移，直到i~j构成的窗口内包含所有有效字符。
 * 当前窗口就是有效窗口，更新窗口长度后，优化点就是j指针是否需要回溯？
 * abcdefgk 假设t = cek
 * i      j
 * j单独右移没价值，因为i~j是有效窗口，i~j+1也是有效窗口但是长度更长了，题目要求最短。
 * i单独右移有价值，因为i+1~j可能仍是有效窗口并且长度更短了，就比如上面的例子。
 * 如果j回溯变成i+1~i+1...j，因为之前的i~j-1不是有效窗口，所以i+1~ i+1 ... j-1都不会是有效窗口，j回溯没价值。
 * 因为i&j都只会单调递增，所以时间就变成了O(n)
 *
 * 剩下的就是优化判断i~j窗口是否有效窗口，如果常数太大，会卡在最后一个case超时
 *
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2021-01-26 21:32
 */
public class MinSuperString {

    int count = 0, min = Integer.MAX_VALUE;
    int[] sHash = new int[123];
    int[] tHash = new int[123];

    // 4ms AC faster than 88%
    public String minWindow(String s, String t) {
        if (t.length() == 1 && s.contains(t)) return t;

        initHash(t);
        int l = -1, r = -1;
        int should, coming ;
        for (int i = 0, j = i; j < s.length(); ) {
            if (tHash[s.charAt(j)] > 0) {
                should = tHash[s.charAt(j)];
                coming = sHash[s.charAt(j)]++;
                // 当前字符出现次数<应该出现次数，代表出现了新的有效字符，大于应该出现次数是冗余的次数没有价值
                if (coming < should) count++;
                while (count == t.length()) {
                    if (j-i+1 < min) {
                        min = j-i+1;
                        l = i; r = j;
                    }
                    // i滑出窗口
                    if ((should = tHash[s.charAt(i)]) > 0) {
                        // 如果有效字符出现次数<应该出现次数，代表现在窗口内有效字符已经不足了，不再是有效窗口
                        if (should > --sHash[s.charAt(i)]) count--;
                    }
                    i++;
                }
            }
            j++;
        }
        return min == Integer.MAX_VALUE ? "" : s.substring(l, r+1);
    }

    private void initHash(String t) {
        for (int i = 0; i < t.length(); i++) {
            tHash[t.charAt(i)]++;
        }
    }
}
