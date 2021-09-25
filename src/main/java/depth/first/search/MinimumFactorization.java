package depth.first.search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * lc 625 medium
 *
 * Analysis:
 * 为了使构成a的因式整数尽可能小，有两方面：1.整数的位数应尽可能少，a=27 3*3*3和3*9 肯定是后者
 * 所以如果a可以被9整除，就不应该枚举3，所以对于每一个a都从最大的9开始枚举a的因子。
 *
 * 2. a=27 3*9或9*3，肯定是3*9
 * 那么dfs搜索得到一组解后，排序返回即可
 *
 * 时间复杂度：O(logN)
 * 空间复杂度：O(logN)
 *
 * @author Joseph
 * @since 2021-04-08 16:00
 */
public class MinimumFactorization {

    boolean end = false;
    Stack<Integer> b = new Stack();

    public int smallestFactorization(int a) {
        /*
             假设答案为b，则b[i]各数位的积是a，要求b最小
             a>1，b不会包含1，因为包含1，各数位相乘的结果不变，去掉1之后b会更小。
             看看b还有什么性质？
             因为要求b最小，所以b从低位到高位（个位到万位...）应该是非严格递增的，即越小的数在越低位
             并且b要最小，b的数位要尽可能少。自然想到因子越大，那需要的因子数量就越少。
             所以从高位开始枚举大的因子，往低位枚举，低位的因子<=高位
         */
        if (a <= 1) return 1;

        dfs(a, 9);
        if (end) return convert();
        return 0;
    }

    void dfs(int a, int f) {
        if (a == 1) {
            end = true;
            return;
        }
        for (int k = f; k > 1; k--) {
            if (a % k == 0) {
                b.push(k);
                dfs(a/k, k);
                if (end) return;
                b.pop();
            }
        }
    }

    int convert() {
        long v = b.pop();
        while (!b.isEmpty()) {
            v = v*10+b.pop();
        }
        return v > Integer.MAX_VALUE ? 0 : (int) v;
    }
}
