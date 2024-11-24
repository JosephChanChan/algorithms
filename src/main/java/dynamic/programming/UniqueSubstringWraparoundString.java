package dynamic.programming;

/**
 * lc 467 medium
 *
 * time: O(n)
 * space: O(1)
 *
 * @author Joseph
 * @since 2023/8/29
 */
public class UniqueSubstringWraparoundString {

    /*
        其实这题不算传统的DP，没有状态转移方程。
        本质就是计算s中每个字符结尾的最长递增子串长度
        每个字符Si利用了前面Si-1字符的当前最长递增子串长度，来计算自己的目前长度，有点DP思想，利用了前面子问题的解
    */

    public int findSubstringInWraproundString(String s) {
        int n = s.length();
        int[] f = new int[26];
        char[] c = s.toCharArray();
        f[c[0]-'a']++;
        int len = 1;
        for (int i = 1; i < n; i++) {
            int v = c[i] - c[i-1] + 26;
            if (v % 26 == 1) {
                // Si和Si-1相连
                // 这里不能直接取f(i-1)的最长递增子串，因为此时的f(i-1)可能不是当前位置的Si的，而是前面一个Si的
                //f[c[i]-'a'] = Math.max(f[c[i]-'a'], f[c[i-1]-'a']+1);
                len++;
            }
            else {
                len = 1;
            }
            f[c[i]-'a'] = Math.max(f[c[i]-'a'], len);
        }
        int ans = 0;
        for (int i = 0; i < f.length; i++) {
            ans += f[i];
        }
        return ans;
    }
}
