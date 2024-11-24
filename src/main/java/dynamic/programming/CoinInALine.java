package dynamic.programming;

/**
 * lint code 394 medium
 *
 * Analysis:
 *  f(i)为剩下i枚硬币可取时，作为先手是否必胜
 * f(i)=!f(i-1)||!f(i-2)
 * 边界：
 * f(0)=false
 * f(1)=f(2)=true
 *
 * 时间复杂度：O(n)
 * 空间复杂度：O(n) 可优化 O(1)
 *
 * @author Joseph
 * @since 2021-03-31 16:04
 */
public class CoinInALine {

    public boolean firstWillWin(int n) {
        if (n == 0) return false;
        if (n == 1 || n == 2) return true;

        boolean[] f = new boolean[n+1];
        f[0] = false;
        f[1] = f[2] = true;

        for (int i = 3; i <= n; i++) {
            f[i] = !f[i-1] || !f[i-2];
        }
        return f[n];
    }
}
