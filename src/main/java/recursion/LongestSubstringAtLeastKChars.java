package recursion;

/**
 * lc 395 medium
 *
 * Analysis：
 * 时间复杂度：O(n* alphabet)
 * 空间复杂度：O(n* alphabet) alphabet是字符集字符数
 *
 * @author Joseph
 * @since 2023/1/12
 */
public class LongestSubstringAtLeastKChars {

    public int longestSubstring(String s, int k) {
        return sliding(s, k);
    }

    /*
        分治，怎么分？怎么治？
        先从最优解思考，最长的符合条件的子串肯定不包括出现次数<k的字符
        分：以窗口内出现次数<k的字符为分割点，直到窗口<1或窗口所有字符出现次数>=k则是有效窗口
        治：分下去的每个小窗口返回长度，计算最大的返回
        每一次分的时候这个分割点，有没可能在下一层的递归中遇到？不会。
        因为一段子区间最多有26个字符，就算每个字符递归一次，也就是26，所以递归深度最多26。
        而窗口长度最长n，所以时间还是O(n)
     */
    int sliding(String s, int k) {
        char[] a = s.toCharArray();
        int n = a.length;
        if (k == 1) {
            return n;
        }
        return dAndC(0, n-1, k, a);
    }

    int dAndC(int l, int r, int k, char[] a) {
        //System.out.println("i="+l+" j="+r);
        if (l >= r) {
            return 0;
        }
        // 统计本窗口内每个字符出现次数
        int[] hash = new int[26];
        for (int i = l; i <= r; i++) {
            hash[a[i]-'a']++;
        }
        int i = l, j = i, max = 0;
        while (j <= r) {
            if (hash[a[j]-'a'] > 0 && hash[a[j]-'a'] < k) {
                int len = dAndC(i, j-1, k, a);
                max = Math.max(len, max);
                i = j+1;
            }
            j++;
        }
        if (i == l) {
            return j-i;
        }
        int len = dAndC(i, j-1, k, a);
        max = Math.max(len, max);
        return max;
    }

    int calcLength(int i, int j, int k, char[] a) {
        int[] hash = new int[26];
        for (int p = i; p <= j-1; p++) {
            hash[a[p]-'a']++;
        }
        for (int p = 0; p < 26; p++) {
            if (hash[p] > 0 && hash[p] < k) {
                return 0;
            }
        }
        return j-i;
    }

    /*
        时间O(n^2)
     */
    int plainCalc(String s, int k) {
        int[] hash = new int[26];
        int n = s.length();
        if (k == 1) {
            return n;
        }

        int max = 0;
        char[] a = s.toCharArray();
        for (int i = 0; i < n; i++) {
            hash[a[i]-'a']++;
            for (int j = i+1; j < n; j++) {
                hash[a[j]-'a']++;
                if (check(k, hash)) {
                    max = Math.max(max, j-i+1);
                }
            }
            hash = new int[26];
        }
        return max;
    }

    boolean check(int k, int[] hash) {
        for (int i = 0; i < hash.length; i++) {
            if (hash[i] > 0 && hash[i] < k) {
                return false;
            }
        }
        return true;
    }
}
