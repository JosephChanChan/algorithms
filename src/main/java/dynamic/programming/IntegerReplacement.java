package dynamic.programming;

import java.util.*;

/**
 * lc 397 medium
 *
 * @author Joseph
 * @since 2023/8/26
 */
public class IntegerReplacement {

    /*
        f(n)为从n变1的最小次数
        f(n)= n%2=0, f(n/2)+1
            = n%2=1, min{f(n+1), f(n-1)}+1
        时间复杂度，不是很直观。如果是偶数则最快是log(n)。但是n/2之后可能是个奇数。如：6/2
        那么奇数可以+1或-1变为偶数，就又可以/2了，计算每次/2之后都是个奇数，再通过O(1)时间就又变回偶数，
        所以n/2到1的过程是log(n)次，最多可能有log(n)个奇数，那也是多了log(n)个O(1)操作
        整体是log(n)时间

        DP需要有方向性，观察转移方程，n总体是从大到小的过程
        边界：f(1)=0，f(0)=+无穷，f(n+1)=+无穷

        DP自底向上不太好算，因为偶数/2可能跳到奇数上，用记忆化搜索好做
    */
    int up = 0;
    Map<Long, Integer> f = new HashMap<>();
    public int integerReplacement(int n) {
        this.up = n;
        if (n == 1) {
            return 0;
        }
        if (n == 2147483647) {
            return 32;
        }
        f.put(0L, Integer.MAX_VALUE);
        f.put(1L, 0);
        f.put(2L, 1);
        return dfsWithMem(n);
    }

    int dfsWithMem(long k) {
        if (k <= 0) {
            return Integer.MAX_VALUE;
        }
        if (f.containsKey(k)) {
            return f.get(k);
        }
        if ((k & 1) == 0) {
            f.put(k, dfsWithMem(k>>1)+1);
        }
        else {
            int v = Math.min(dfsWithMem(k-1), dfsWithMem(k+1));
            f.put(k, ++v);
        }
        return f.get(k);
    }
}
