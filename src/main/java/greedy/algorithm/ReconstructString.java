package greedy.algorithm;

import java.util.*;

/**
 * lc 767 medium
 *
 * Analysis:
 * 时间复杂度：O(n*log(n))
 * 空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2022/9/22
 */
public class ReconstructString {

    /**
         首先分析下不可能重新排列的情况
         如果出现次数最多的字符为m，字符串长度为偶数则由n/2个偶数位和n/2个奇数位
         若m出现次数 > n/2，则把偶数位装满后，还有m要装且必定会与偶数位的m相邻
         若字符串长度为奇数，下标从0开始，则有(n+1)/2个偶数位，同样的若m出现次数 > (n+1)/2则会有相邻
         所以若m出现次数 <= n/2，则整个字符串可按照某种规则重新排列

         贪心思想，每次取2个出现次数最多的不同的字符，把它们排列一起
     */

    int n ;
    int[] h ;
    Queue<Character> q = new PriorityQueue<>((o1, o2) -> {
        if (h[o1-'a'] == h[o2-'a']) {
            return (int)o1 - (int) o2;
        }
        return h[o2-'a'] - h[o1-'a'];
    });

    public String reorganizeString(String s) {
        if (s.length() == 1) {
            return s;
        }
        char[] c = s.toCharArray();
        this.n = c.length;
        this.h = new int[26];
        int max = 0;
        // 统计每个字符出现次数
        for (int i = 0; i < n; i++) {
            h[c[i]-'a']++;
            max = Math.max(max, h[c[i]-'a']);
        }
        // 若出现最多次数的字符超过了一半的位置，则必定相邻
        if (max > (n+1)/2) {
            return "";
        }
        // 字符按照出现次数降序排序，次数相同按照字典序排序
        for (int i = 0; i < 26; i++) {
            if (h[i] > 0) {
                q.add(Character.valueOf((char) (i + 'a')));
            }
        }
        int k = 0;
        char[] ans = new char[n];
        while (q.size() > 1) {
            char a = q.poll();
            char b = q.poll();
            ans[k++] = a;
            ans[k++] = b;
            if (--h[a-'a'] > 0) {
                q.add(new Character(a));
            }
            if (--h[b-'a'] > 0) {
                q.add(new Character(b));
            }
        }
        if (q.size() > 0) {
            char a = q.poll();
            while (h[a-'a'] > 0) {
                ans[k++] = a;
                h[a-'a']--;
            }
        }
        return new String(ans);
    }
}
