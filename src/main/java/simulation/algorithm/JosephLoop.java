package simulation.algorithm;

import java.util.*;

/**
 * 剑指Offer 62 easy
 *
 * Analysis:
 *  数学分析题。比较难
 *
 * 有n个数，长度n的序列，m%n是第一个被淘汰的数的下标
 * 1 2 3 4 5 6  7  8 ...... n
 *             m%n
 *
 * 删除m%n的数后剩下长度 n-1的序列，并假设剩下n-1个数，继续从1报数直到第m个数删除，循环直至剩下最后一个数
 * 这个数所在原来 n-1 个数序列中的下标是x，在这里假设最后一个数是2，并且它的下标是x。
 * 注意这里的x是指从元素8开始一直数到元素2的长度。
 * 8 ...... n 1 2 3 4 5 6
 *              x
 *
 * 假设现在就有这么一个函数 f(n-1, m) 代表n-1个数中从1开始报数到m淘汰，循环直至剩最后一个数，得到最后一个数在原n-1长度序列中的下标。
 * 以上面的例子 f(n-1, m)=x
 * 现在知道了一开始长度为n的序列淘汰了m%n之后，变为n-1长的序列，并且假设最后一个数下标是x，
 * 那么最后一个数在长度n序列中下标是多少？
 *
 * 那么自然从m%n开始再走长度x步，就到了最后一个数对吧，所以是
 * f(n, m) = f(n-1, m) + m%n
 *         = m%n + x
 * 因为x是长度，并且m%n再走x步可能超过n的长度，超过后又要回到序列开头继续数，所以mod n
 * f(n, m) = (m%n + x)%n
 *         = (m+x)%n
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
