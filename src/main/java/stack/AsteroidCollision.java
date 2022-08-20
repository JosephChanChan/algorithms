package stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * lc 735 medium
 *
 * @author Joseph
 * @since 2022/7/14
 */
public class AsteroidCollision {


    /*
        栈+模拟
        本质上碰撞一定是左右方向冲突的点，并且同方向的点不会撞
        那可以用栈保存同方向的点，和相反方向的点碰撞，撞碎了就弹出栈顶

        时间复杂度：O(n)
        空间复杂度：O(n)
     */
    boolean[] f2 ;
    Stack<Integer> s = new Stack<>();

    public int[] asteroidCollision2(int[] asteroids) {
        int n = asteroids.length;
        f2 = new boolean[n];

        int k = 0;
        for (int i = 0; i < n; i++) {
            // 栈保存向右的点，栈顶一直和向左的点碰撞
            // 1.栈顶被撞碎，弹出下一个栈元素继续碰撞
            // 2.栈顶撞碎向左的点，继续撞
            if (asteroids[i] > 0) {
                s.push(i);
                continue;
            }
            if (s.isEmpty()) {
                continue;
            }
            int p = Math.abs(asteroids[s.peek()]);
            int q = Math.abs(asteroids[i]);
            if (p == q) {
                f2[s.pop()] = true;
                f2[i] = true;
                k += 2;
            }
            else if (p > q) {
                f2[i] = true;
                k += 1;
            }
            else {
                f2[s.pop()] = true;
                k += 1;
                i--;
            }
        }
        int[] ans = new int[n-k];
        for (int i = 0, j = 0; i < n; i++) {
            if (!f2[i]) ans[j++] = asteroids[i];
        }
        return ans;
    }

    /*
        dfs+模拟
        270/275 TLE
     */
    boolean[] f ;

    public int[] asteroidCollision(int[] asteroids) {
        List<Integer> ans = new ArrayList<>();
        f = new boolean[asteroids.length];
        for (int i = 0; i < asteroids.length; i++) {
            dfs(i, asteroids);
        }
        for (int i = 0; i < asteroids.length; i++) {
            if (!f[i]) {
                ans.add(asteroids[i]);
            }
        }
        int[] a = new int[ans.size()];
        for (int i = 0; i < a.length; i++) {
            a[i] = ans.get(i);
        }
        return a;
    }

    // 模拟第i个点运动，直到碰撞 1.自己被撞碎递归撞碎它的 2.撞碎其它继续模拟运动 3.同方向的递归
    void dfs(int i, int[] a) {
        if (f[i]) {
            return;
        }
        int j = a[i] > 0 ? i+1 : i-1;
        int deta = a[i] > 0 ? 1 : -1;
        for ( ; j >= 0 && j < a.length; ) {
            if (f[j]) {
                j += deta;
                continue;
            }
            // j点存在，看看j是否同方向
            if ((a[i] > 0 && a[j] > 0) || (a[i] < 0 && a[j] < 0)) {
                dfs(j, a);
                if (f[i]) return;
                //System.out.println("i="+i+" j="+j);
                j += deta;
                //System.out.println("after j="+j);
            }
            else {
                // 计算i和j相撞结果
                int p = Math.abs(a[i]);
                int q = Math.abs(a[j]);
                if (p == q) {
                    f[i] = true;
                    f[j] = true;
                    //System.out.println("p==q i="+i+" j="+j);
                    return;
                }
                if (p > q) {
                    f[j] = true;
                    //System.out.println("p>q j="+j);
                    j += deta;
                }
                if (p < q) {
                    f[i] = true;
                    //System.out.println("p<q i="+i);
                    // i被j撞碎，模拟j运动，j运动完后就应该结束
                    dfs(j, a);
                    return;
                }
            }
        }
    }
}
