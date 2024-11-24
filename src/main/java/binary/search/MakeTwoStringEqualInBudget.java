package binary.search;

import java.util.Map;
import java.util.TreeMap;

/**
 * lc 1208 medium
 *
 * Analysis:
 *  二分答案+前缀和
 * 设用maxCost能将s子串转化为t子串最大长度为k。
 * 小于等于k的长度s子串都能转为t子串，大于k的长度则不能，具有二分性。
 * 那么如果有一个函数能判断，给定cost和长度k，判断将s子串转为t子串的长度k的p<=cost
 *
 *  哈希前缀和(用的是TreeMap)
 * s(i)为a和b 0~i子串的差值前缀和
 * 对于一个区间，如果s(i)<=cost，则0~i可以全部转化，
 * 如果s(i)>cost，s(i)-cost=d，则看前面是否有s(j)>=d，舍弃min{s(j)>=d}，即前面有一段区间字符差大于等于d，舍弃它
 * j+1~i的区间的字符差就可以全部转化
 * 记录最大的区间
 *
 * 时间复杂度：前缀和二分 O(N*logN)，哈希前缀和 O(n*log(n))
 * 空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2021-09-05 16:45
 */
public class MakeTwoStringEqualInBudget {

    public int equalSubstring(String s, String t, int maxCost) {
        int n = s.length();

        int[] d = new int[n];
        for (int i = 0; i < n; i++) {
            int a = (s.charAt(i) - 'a') - (t.charAt(i) - 'a');
            if (i == 0) {
                d[i] = Math.abs(a);
                continue;
            }
            d[i] = d[i-1] + Math.abs(a);
        }

        int l = 0, r = n, m ;
        while (l + 1 < r) {
            m = (l + r) >> 1;
            if (check(m, d, maxCost)) {
                l = m;
            }
            else {
                r = m;
            }
        }
        if (check(r, d, maxCost)) return r;
        if (check(l, d, maxCost)) return l;
        return 0;
    }

    boolean check(int k, int[] d, int cost) {
        if (k == 0) return true;

        for (int i = 0; i <= d.length-k; i++) {
            int j = i+k-1;
            int p = calc(i, j, d);
            if (p <= cost) return true;
        }
        return false;
    }

    int calc(int i, int j, int[] d) {
        return d[j] - (i > 0 ? d[i-1] : 0);
    }

    public int hashPrefix(String s, String t, int maxCost) {
        // 哈希前缀和
        int n = s.length();
        char[] a = s.toCharArray();
        char[] b = t.toCharArray();

        int[] f = new int[n];
        f[0] = Math.abs(a[0]-b[0]);

        for (int i = 1; i < n; i++) {
            f[i] = f[i-1] + Math.abs(a[i]-b[i]);
        }

        int ans = 0;
        // 字符差前缀和 -> 右边界
        TreeMap<Integer, Integer> m = new TreeMap<>();
        for (int i = 0; i < n; i++) {
            if (f[i] <= maxCost) {
                ans = Math.max(ans, i+1);
            }
            else {
                int d = f[i] - maxCost;
                // 还差d，找前面的字符差前缀和大于等于d的最靠右的一个，舍弃
                Map.Entry<Integer, Integer> e = m.ceilingEntry(d);
                if (null != e) {
                    ans = Math.max(ans, i - e.getValue());
                }
            }
            // 前面已有相同字符差前缀和的子区间，只要更靠左的
            if (!m.containsKey(f[i])) {
                m.put(f[i], i);
            }
        }
        return ans;
    }
}
