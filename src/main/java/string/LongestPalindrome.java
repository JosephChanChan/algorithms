package string;

/**
 * lc 5 medium
 *
 * Analysis:
 *  中心扩散法。分析每一个回文串，cabac这种奇数回文串，cbaabc这种偶数回文串。
 * 奇数回文串从每一个字符s[i]为中心向左右两边同时扩散记录最长的回文串。
 * 偶数回文串从每两个字符(s[i],s[i+1])开始同时向两边扩散记录最长回文串。
 *
 *  动态规划。AxxxxA这种字符串，我们知道s[i]==s[j]，如果知道s[i+1]~s[j-1]是否回文串就可以判断s[i]~s[j]是否回文串。
 * 设 f(i,j)为s[i]~s[j]是否回文串
 * 上一个阶段为f(i+1,j-1)，状态有两个true or false
 * 转移方程为 f(i,j) = f(i+1,j-1) && s[i]==s[j]
 * 边界：
 * f(i,i)=true
 * f(i,j)=false, i>j
 * f(i,i+1)=true, s[i]==s[i+1]
 *
 * 时间复杂度：O(n^2)
 * 空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2021-01-30 01:32
 */
public class LongestPalindrome {

    char[] c ;

    public String longestPalindrome(String s) {
        c = s.toCharArray();
        if (c.length == 1) return s;
        if (c.length == 2) return c[0] == c[1] ? s : String.valueOf(c[0]);

        int max = 0, left = 0, right = 0;
        for (int i = 1; i < c.length; i++) {
            int l = i-1, r = i+1;
            while (l >= 0 && r < c.length) {
                if (c[l] == c[r]) {
                    if (max < r-l+1) {
                        max = r-l+1;
                        left = l; right = r;
                    }
                    l--; r++;
                }
                else {
                    break;
                }
            }
        }
        for (int l = 0, r = 1; r < c.length; l++, r++) {
            int p = l, q = r;
            while (p >= 0 && q < c.length) {
                if (c[p] == c[q]) {
                    if (max < q-p+1) {
                        max = q-p+1;
                        left = p; right = q;
                    }
                    p--; q++;
                }
                else {
                    break;
                }
            }
        }
        return s.substring(left, right+1);
    }
}
