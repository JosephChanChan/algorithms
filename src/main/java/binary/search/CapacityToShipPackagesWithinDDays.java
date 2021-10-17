package binary.search;

/**
 * lc 1011 medium
 *
 * Analysis:
 *  二分答案+前缀和。
 * 猜一个答案w，然后快速验证是否存在一种方案，把n个元素划分成d段，最大的那段区间和<=w
 * 二分答案通常check函数是比较难想的。
 * 在这里显然划分的时候任意一段区间和需要 <= w。
 * 根据前缀和的单调性有二分性，前缀和中必然有 sum(0~i)<=w，sum(i~n)>w 这种二分性质。
 * 则设起始位置i从0开始，二分搜i~j的一个区间和<=w，将i更新为j继续搜下一个划分点。
 * 将d段区间划分完毕后，看最后一段区间和是否<=w
 *
 * 最后还有一点优化，check2函数一开始我想的复杂了，看了别人题解写了check函数。
 * 只要看猜的w承重运完货物所需的分段数是否<=d就行
 *
 * 时间复杂度：O(logSum * N)
 * 空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2021-08-31 22:34
 */
public class CapacityToShipPackagesWithinDDays {

    public int shipWithinDays(int[] weights, int days) {
        int n = weights.length, max = Integer.MIN_VALUE, sum = 0;
        int[] a = weights;
        for (int i = 0; i < n; i++) {
            sum += a[i];
            max = Math.max(max, a[i]);
        }

        if (days == 1) return sum;
        if (days == n) return max;

        int l = max, r = sum, w ;
        while (l + 1 < r) {
            w = ((r - l) >> 1) + l;
            if (check(w, days-1, a)) {
                r = w;
            }
            else {
                l = w;
            }
        }
        int ans = check(l, days-1, a) ? l : r;
        return ans;
    }

    // 10ms
    boolean check(int w, int d, int[] a) {
        // 承重w需要多少段运完a
        int sum = 0, t = 0, i = 0;
        // 还有的运？
        while (i < a.length) {
            if (sum + a[i] <= w) {
                sum += a[i++];
                continue;
            }
            t++;
            sum = 0;
        }
        return t <= d;
    }

    // 20ms
    boolean check2(int w, int d, int[] s) {
        int i = 0, j ;
        for (int k = 0; k < d && i < s.length; k++) {
            int l = i, r = s.length-1, m ;
            while (l + 1 < r) {
                m = (l + r) >> 1;
                // sum(i~m)
                int sum = calc(i, m, s);
                if (sum <= w) {
                    l = m;
                }
                else {
                    r = m;
                }
            }
            j = (calc(i, r, s) <= w ? r : l) + 1;
            //System.out.println("第"+k+"段区间="+i+"~"+(j-1)+" 区间和="+calc(i, j-1, s)+" w="+w);
            i = j;
        }
        // sum(最后一段) <= w
        //System.out.println("最后一段区间="+i+"~"+(s.length-1)+" 区间和="+calc(i, s.length-1, s)+" w="+w);
        return calc(i, s.length-1, s) <= w;
    }

    int calc(int i, int j, int[] s) {
        if (i > j) return 0;
        return s[j] - (i > 0 ? s[i-1] : 0);
    }

}
