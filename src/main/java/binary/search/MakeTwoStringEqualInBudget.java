package binary.search;

/**
 * lc 1208 medium
 *
 * Analysis:
 *  二分答案+前缀和
 * 设用maxCost能将s子串转化为t子串最大长度为k。
 * 小于等于k的长度s子串都能转为t子串，大于k的长度则不能，具有二分性。
 * 那么如果有一个函数能判断，给定cost和长度k，判断将s子串转为t子串的长度k的p<=cost
 *
 * 时间复杂度：O(N*logN)
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
}
