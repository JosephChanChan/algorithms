import depth.first.search.FactorCombinations;

import java.util.HashMap;
import java.util.Map;

/**
 * lc 166 medium
 *
 * Analysis:
 * 原本以为简单的模拟题，实际上挺麻烦。
 * 难点在于边界case比较多，尤其循环小数如何判断？
 *
 * 时间复杂度：O(log(b,a)) 以分母为底，分子为真数的对数
 * 空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2021-04-24 15:23
 */
public class FractionToDecimal {


    public String fractionToDecimal(int numerator, int denominator) {
        /*
             模拟除法。
             分子<分母，给商补0，分子*10
             分子>分母，直接除，商保存下来。余数*10作为新的商
             分子=分母返回1

             循环小数判定，如果余数在前面出现过
             记录小数部分余数出现的位置，一旦余数开始重复代表是循环小数
         */
        if (numerator == denominator) return "1";
        if (numerator == 0) return "0";

        Map<Long, Integer> hash = new HashMap();
        StringBuilder ans = new StringBuilder();
        long a = numerator, b = denominator;
        if (a < 0) a = -a;
        if (b < 0) b = -b;

        if ((numerator < 0 && denominator > 0) || (denominator < 0 && numerator > 0)) {
            ans.append("-");
        }

        ans.append(a/b);
        a = a%b;
        if (a == 0) return ans.toString();

        // 除不尽，有小数
        ans.append(".");
        a *= 10;

        while (a > 0) {
            // 余数和商一一对应起来
            ans.append(a/b);
            a = a%b;

            if (hash.containsKey(a)) {
                ans.insert(hash.get(a), "(");
                ans.append(")");
                break;
            }
            if (a < b) {
                hash.put(a, ans.length());
                a *= 10;
            }
        }
        return ans.toString();
    }
}
