package depth.first.search;

import java.util.ArrayList;
import java.util.List;

/**
 * lc 282 hard
 *
 * Analysis:
 * 数字需要枚举，k个字符组成数字后的符号也需要枚举
 * 假设现在枚举了一个数字k，每个k都有+k -k *k 3种符号
 * *k 需要改变运算顺序，需要保存上一步状态枚举的数字 f
 * 维护 v和f
 * 对于 +k v=v+k f= +k
 * -k同理
 * *k v=(v-f)+f*k, f=f*k
 *
 * 时间复杂度：O(3^n) 对每个数字都有 +/-/* 3种符号，最长有n个数字枚举
 * 空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2021-04-08 17:54
 */
public class ExpressionAddOperators {

    String num;
    int t;
    List<String> ans = new ArrayList();

    public List<String> addOperators(String num, int target) {
        if (num.length() == 0) return ans;
        this.num = num;
        this.t = target;

        dfs(0, 0, 0, new StringBuilder());

        return ans;
    }

    void dfs(long v, long f, int k, StringBuilder b) {
        if (k == num.length()) {
            if (v == t) {
                ans.add(b.toString());
            }
            return;
        }

        int l = b.length();

        for (int i = k; i < num.length(); i++) {
            // 有前导0的数字 跳过
            if (num.charAt(k) == '0' && k < i) continue;

            long dig = dig(k, i+1);

            // +k 第一个数字只能是 +号
            if (k > 0) b.append("+");
            b.append(dig);
            dfs(v+dig, dig, i+1, b);
            b.delete(l, b.length());

            // -k
            if (k > 0) {
                b.append("-").append(dig);
                dfs(v-dig, -dig, i+1, b);
                b.delete(l, b.length());
            }
            // *k
            if (k > 0) {
                b.append("*").append(dig);
                dfs(v-f + f*dig, f*dig, i+1, b);
                b.delete(l, b.length());
            }
        }
    }

    long dig(int i, int j) {
        String a = num.substring(i, j);
        return Long.parseLong(a);
    }
}
