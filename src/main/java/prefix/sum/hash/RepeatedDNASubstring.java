package prefix.sum.hash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * lc 187 medium
 *
 * Analysis:
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2022/2/6 11:02 PM
 */
public class RepeatedDNASubstring {

    /*
        解法1，哈希前缀和
        时间复杂度：O(n)
        空间复杂度：O(n)
        h[]哈希前缀和数组
        p[]次方数组
        h(i~j)哈希值，重点公式h(j)-h(i-1)*p(j-(i-1))
        因为h(j)哈希值前缀和包括了h(i-1)哈希值前缀和，且h(i-1)哈希值被乘了p(j-(i-1))次p
        将0~i-1子串的哈希值乘上p(j-i+1)次方，再减h(j)即可抹去h(i-1)子串的哈希前缀和
     */
    int prime = 13331;

    List<String> ans = new ArrayList<>();
    Map<Integer, Integer> hash = new HashMap<>();

    public List<String> findRepeatedDnaSequences(String s) {
        int n = s.length();

        if (n < 10) {
            return ans;
        }

        char[] c = s.toCharArray();
        int[] h = new int[n+1];
        int[] p = new int[n+1];
        p[0] = 1;

        for (int i = 1; i <= n; i++) {
            p[i] = p[i-1] * prime;
            h[i] = h[i-1] * prime + c[i-1];
        }

        for (int i = 1; i <= n-9; i++) {
            int j = i+9;
            // i~j子串的哈希值
            int code = h[j] - h[i-1] * p[j-i+1];
            hash.put(code, hash.getOrDefault(code, 0) + 1);
            if (hash.get(code) == 2) {
                ans.add(s.substring(i-1, j));
            }
        }

        return ans;
    }

    /*
        解法2，朴素哈希字符串
        时间复杂度：O(n*c)，c是窗口大小
        空间复杂度：O(n)
     */
    List<String> ans2 = new ArrayList<>();
    Map<String, Integer> hash2 = new HashMap<>();

    public List<String> findRepeatedDnaSequences2(String s) {
        int n = s.length();

        if (n < 10) {
            return ans2;
        }

        for (int i = 0; i < n-9; i++) {
            String sub = s.substring(i, i+10);
            hash2.put(sub, hash.getOrDefault(sub, 0) + 1);
        }

        for (Map.Entry<String, Integer> e : hash2.entrySet()) {
            if (e.getValue() > 1) {
                ans2.add(e.getKey());
            }
        }
        return ans2;
    }
}
