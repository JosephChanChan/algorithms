package dynamic.programming;

/**
 * lint code 395 medium
 *
 * Analysis:
 * f(i)为剩下i枚硬币时，作为先手能获取的最大价值
 * 如果取一枚硬币，那么对手剩下i-1枚硬币可取。
 * 如果取两枚硬币，那么对手剩下i-2枚硬币可取。
 *
 * 对手会采取对i-1 i-2枚硬币时的最优策略，即当面对i-1 i-2枚硬币时会取 max{f(i-1), f(i-2)}
 * 那么剩下i-2 i-3 i-4枚硬币给当前先手取
 *
 * 例如A取1枚硬币，剩下i-1枚，B肯定取最优策略的，可能取1枚或2枚，剩下i-2 i-3给A取
 * 那么A只能取到f(i-2) f(i-3)中比较少的一种
 *
 * f(i)=max{a[n-i]+min{f(i-2), f(i-3)}, a[n-i]+a[n-i+1]+min{f(i-3),f(i-4)}}
 *
 * 边界：
 * f(0)=0
 * f(1)=a[n]
 * f(2)=a[n-1]+a[n]
 *
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2021-03-31 16:52
 */
public class CoinInALine2 {

    public boolean firstWillWin(int[] values) {
        int n = values.length;
        if (n == 0) return false;
        if (n == 1 || n == 2) return true;

        int[] f = new int[n+1];
        f[0] = 0;
        f[1] = values[n-1];
        f[2] = values[n-2] + values[n-1];

        for (int i = 3; i <= n; i++) {
            f[i] = values[n-i] + Math.min(f[i-2], f[i-3]);
            f[i] = Math.max(f[i], values[n-i]+values[n-i+1]+Math.min(f[i-3], i > 3 ? f[i-4] : 0));
        }
        // 作为先手面对n个硬币时获取的价值能比后手面对剩下n-1/n-2个硬币时获取的价值更大则必胜
        return f[n] > f[n-1] || f[n] > f[n-2];
    }

}
