package prefix.sum.hash;

/**
 * lc 686 medium
 *
 * Analysis:
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2022/2/12 11:27 PM
 */
public class RepeatedStringMatch {

    int c = 1, prime = 13331;

    public int repeatedStringMatch(String a, String b) {
        /**
            字符串子串的匹配，可以考虑字符串哈希前缀
            只有a长度>=b长度才可能匹配成功，所以如果a<b，就把a复制几次使它长度>=b
            这是匹配成功次数的下界，最多需要复制多少次？
            b如果是a的子串，则b的开头肯定在a中存在，所以b的开头不会超过a的长度，
            假设b的开头刚好和a最后一个字符匹配，最多也就是b的长度+a的长度
            所以a最多需要复制 a长度+b长度，也就是超过b长度后再多复制一次就行，
            然后就在其中用字符串哈希逐个匹配b
         */
        int n = a.length();
        int origN = n;
        int m = b.length();

        a = repeated(a, m);
        n = a.length();

        int ans = 0;
        for (int i = 0; i < m; i++) {
            ans = ans * prime + b.charAt(i);
        }

        int[] h = new int[n+1];
        int[] p = new int[n+1];
        p[0] = 1;
        for (int i = 1; i <= n; i++) {
            h[i] = h[i-1] * prime + a.charAt(i-1);
            p[i] = p[i-1] * prime;
        }

        for (int i = 1; i <= n-(m-1); i++) {
            int j = i + m - 1;
            int match = h[j] - h[i-1] * p[j-i+1];
            if (match == ans) {
                return (j-1) / origN + 1;
            }
        }
        return -1;
    }

    String repeated(String s, int m) {
        StringBuilder b = new StringBuilder(s);
        while (b.length() < m) {
            b.append(s);
            c++;
        }
        // 这里多复制一次是巧妙
        b.append(s);
        c++;
        return b.toString();
    }
}
