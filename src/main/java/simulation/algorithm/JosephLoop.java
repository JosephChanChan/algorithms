package simulation.algorithm;

import java.util.*;

/**
 * 剑指Offer 62 easy
 *
 * Analysis:
 *  数学分析题。比较难
 *
 * 有n个数，长度n的序列，m%n是第一个被淘汰的数的下标
 * 1  2  3  4  5  6  7   8  9 ...... n
 *      m%n
 * 在长度n的序列中淘汰m%n后，假设在长度n-1序列中最后一个数的下标为x
 * 4  5  6  7   8  9 ...... n ...... 1  2
 * 0  1  2  3   4  5 ...x.. n
 * 现有f(n,m)函数可知在长度n的序列中报数淘汰m，最后剩下的数在长度n的序列中的下标
 * f(n-1,m)=x
 * 求最后一个数在n长的序列中的下标，可知从原序列m%n位置再走x步就到了x的位置
 * f(n,m)=(f(n-1,m)+m%n) % n
 *       =(x+m%n)%n
 *       =(x+m)%n
 *
 * 式子的简化用到定理：
 * 1. (a+b)%c = a%c + b%c
 * 2. (a%c)%c = a%c
 * 所以：(m%n + x)%n => [(m%n)%n] + x%n => m%n + x%n => (m+x)%n
 *
 * 边界：f(1, m)=0
 *
 * 时间复杂度：数学法 O(n)， 模拟 O(nm)
 * 空间复杂度：数学法 O(1)， 模拟 O(n)
 *
 * @author Joseph
 * @since 2021-01-21
 */
public class JosephLoop {


    public int lastRemaining(int n, int m) {
        return math(n, m);
    }

    private int math(int n, int m) {
        int x = 0;
        // 从已知的f(1, m)反推回长度n的序列，要计算n-1个序列
        for (int i = 0, len = 2; i < n-1; i++, len++) {
            x = (m + x) % len;
        }
        return x;
    }

    int dfs(int n, int m) {
        if (n == 1) return 0;

        return (m + dfs(n-1, m)) % n;
    }

    // TLE
    private int simulation(int n, int m) {
        /*
            链表模拟loop，从头弹出，从尾入队
        */
        Queue<Integer> que = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            que.add(i);
        }

        int count = 0, p = 1, e ;
        while (count < n-1) {
            e = que.poll();
            if (p++ == m) {
                count++;
                p = 1;
            }
            else {
                que.add(e);
            }
        }
        return que.poll();
    }


}
