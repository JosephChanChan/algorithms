package pointers;

/**
 * 剑指Offer 58 medium & lc 151
 *
 * Analysis:
 *  双指针，l&r，分别指向一个单词，单词以空格为分割界。
 *  一开始用char[]裸操作指针，复制原字符到新char[]中，人都给搞傻了，要处理各种边界case。
 *  用了StringBuilder代码简化了很多，有库函数太幸福了
 *
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2021-01-17 21:32
 */
public class ReverseWords {

    public String reverseWords(String s) {
        if (null == s) return null;

        s = s.trim();
        if (s.length() <= 1) return s;

        int r = s.length()-1, l = r;
        StringBuilder b = new StringBuilder();
        while (l >= 0) {
            while (l >= 0 && s.charAt(l) != ' ') l--;
            b.append(s.substring(l+1, r+1)).append(" ");
            while (l > 0 && s.charAt(l) == ' ') l--;
            r = l;
        }
        return b.deleteCharAt(b.length()-1).toString();
    }
}
