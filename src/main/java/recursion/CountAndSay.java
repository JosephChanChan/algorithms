package recursion;

/**
 * lc 38 medium
 *
 * Analysis:
 *  dfs计算每层的数列，统计连续的字符的数量
 *
 * 不知道n-1到n时字符串增长的函数关系，很难计算时空间复杂度。
 * 最坏情况下，n-1每个是不同的字符，则n是2倍的n-1字符长度描述，但是最坏情况不太可能出现。
 * 时间复杂度：小于O(2^n)
 * 空间复杂度：小于O(2^n)
 *
 * @author Joseph
 * @since 2021-07-04 16:16
 */
public class CountAndSay {

    int n ;
    String ans = null;

    public String countAndSay(int n) {
        this.n = n;
        dfs(new String("1").toCharArray(), 1);
        return ans;
    }

    void dfs(char[] c, int k) {
        if (k == n) {
            ans = new String(c);
            return;
        }
        StringBuilder b = new StringBuilder();
        for (int i = 0; i < c.length; ) {
            int[] v = take(c, i);
            b.append(v[0]).append(v[1]);
            i = v[2];
        }
        dfs(b.toString().toCharArray(), k+1);
    }

    int[] take(char[] c, int j) {
        int[] a = new int[3];
        int count = 1, digit = c[j++]-'0';
        while (j < c.length && c[j]-'0' == digit) {
            j++;
            count++;
        }
        a[0] = count;
        a[1] = digit;
        a[2] = j;
        return a;
    }
}
