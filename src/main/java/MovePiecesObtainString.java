import java.util.*;

/**
 * lc 2337 medium
 *
 * Analysis:
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2022/9/16
 */
public class MovePiecesObtainString {

    /**
         思维题
         因为L只能向左移动，R只能向右移动，且L和R相撞不能穿越
         所以去掉了_后，剩余的s和t字符串的L和R排列应该要一样
         那么考虑s和t字符串去掉_后的字符数组A：
         1. s和t的Ai字符都应该一样，要么是L或R
         2. Ai字符一样情况下，Ai=L，则在t中这个L的下标应该要<= s中的L下标
         3. 同理Ai=R时，t中R的下标要>= s中R下标
     */

    int leftCode = 'L'-'A';
    List<int[]> A = new ArrayList<>();
    List<int[]> B = new ArrayList<>();

    public boolean canChange(String start, String target) {
        char[] s = start.toCharArray();
        char[] t = target.toCharArray();
        for (int i = 0; i < start.length(); i++) {
            parse(s[i], i, A);
            parse(t[i], i, B);
        }
        if (A.size() != B.size()) {
            return false;
        }
        for (int i = 0; i < A.size(); i++) {
            int[] p = A.get(i);
            int[] q = B.get(i);
            if (p[0] != q[0]) {
                return false;
            }
            if (p[1] == q[1]) {
                continue;
            }
            if (q[0] == leftCode && q[1] > p[1]) {
                return false;
            }
            if (q[0] != leftCode && q[1] < p[1]) {
                return false;
            }
        }
        return true;
    }

    void parse(char c, int i, List<int[]> list) {
        if (c == '_') {
            return;
        }
        list.add(new int[]{c-'A', i});
    }
}
