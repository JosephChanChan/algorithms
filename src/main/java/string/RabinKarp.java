package string;

/**
 * lc 28 easy
 *
 * Question Description:
 *  参见 lc 28
 *
 * Analysis:
 *  本题可以有暴力搜索，O(n^2)，借这题来学习下 Rabin-Karp 算法。
 *  媲美KMP的字符串搜索算法，时间复杂度与KMP同级别，实现和思想上要较为简单。
 *
 * 思想是利用hashCode加速每次对比字符串，字符串相同hashCode一定相同，每次计算hashCode只用常数时间。
 * 最后对比时花费O(m)时间，总结起来要计算n次hashCode，对比常数次字符串（取决于hash冲突）
 * 代码中的mod是把hashCode限制在0~mod-1范围内，防止溢出。
 *
 * 时间复杂度：O(n+m)
 * 空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2020-08-12 23:23
 */
public class RabinKarp {

    public static void main(String[] args) {
        String s = "BBC ABCDAB ABCDABCDABDE";
        String t = "ABCDABD";
        RabinKarp test = new RabinKarp();
        System.out.println(test.doRabinKarp(s, t));
    }

    private int doRabinKarp(String s, String t) {
        // calc 31^n-1
        int power = 1, mod = 1000000;
        for (int i = 0; i < t.length() - 1; i++) {
            power = power * 31 % mod;
        }

        // calc t hash code
        // using formula s[0]*31^n-1 + s[1]*31^n-2 + ... + s[n]
        int tHash = 0;
        for (int i = 0; i < t.length(); i++) {
            tHash = (tHash * 31 + t.charAt(i)) % mod;
        }

        // compare using hash
        int sHash = 0, head = 0;
        char[] sArray = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            // (a*31 + b) * 31 + c
            sHash = (sHash * 31 + sArray[i]) % mod;
            if (sHash == tHash) {
                if (s.substring(head, head + t.length()).equals(t)) {
                    return head;
                }
            }
            if (i < t.length() - 1) continue;
            // (a*31^2 + b*31 + c)-a*31^2
            sHash = sHash - sArray[head++] * power % mod;
            if (sHash < 0) sHash += mod;
        }
        return -1;
    }
}
