package math;

/**
 * lc 1759 medium
 *
 * Analysis:
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2022/12/26
 */
public class CountHomogeneousSubstring {

    long ans = 0, mod = (long) (1e9 + 7);

    public int countHomogenous(String s) {
        char[] c = s.toCharArray();
        int i = 0, j = i;
        for ( ; j < c.length-1; j++) {
            if (c[j] == c[j+1]) {
                continue;
            }
            long k = count(i, j);
            i = j+1;
            //System.out.println("i="+i+" j="+j+" k="+k);
            ans = (ans + k) % mod;
        }
        ans = (ans + count(i, j)) % mod;
        return (int) ans;
    }

    long count(int i, int j) {
        if (i == j) {
            return 1;
        }
        // 等差数列公式
        long n = j-i+1;
        return (1 + n) * n / 2;
    }
}
