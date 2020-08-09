package tables;

import java.util.Scanner;

/**
 * 网易2020校招笔试
 *
 * 原本以为阿里的笔试最难，没想到网易的也很难，100分钟 4道算法。
 * 只AC了这一道，其它三道基本没有思路。
 *
 * Question Description:
 *  给定长度为m的序列T，求一个长度为n且字典序最小的排列，并且要求序列T为所求排列的子序列。
 *  题目保证这样的序列一定存在。
 *  假设所求序列为S，则T是S子序列，且T是S删除任意元素后得到的。
 *  输入：
 *  5 3
 *  2 1 5
 *  输出：
 *  2 1 3 4 5
 *  输入：
 *  5 2
 *  输出：
 *  1 3 4 2 5
 *
 * Analysis:
 *  一开始看题没看懂，理解了一下才懂。要求排列字典序最小，并且不能改动原序列的元素顺序。
 *  那么将元素逐个依字典序插入原序列中就行了。
 *  但是也不是那么简单的，数据范围 1 <= n <= 10^5，如果每次插入元素时将后面元素后移，时间复杂度就到O(n^2)了。
 *  所以选择了用链表实现，插入O(1)，但是代码就没有数组那么简单了。
 *  如果用数组当时可能只能ac一部分。
 *
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2020-08-08 14:58
 */
public class MinimumPermutation {

    static int n, m ;
    static Node head = null;
    static boolean[] a ;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        m = scanner.nextInt();
        a = new boolean[n+1];// 1~n

        // 构造一个原始的双向链表
        int i ;
        Node t = head;
        for (i = 0; i < m; i++) {
            Node node = new Node(scanner.nextInt());
            a[node.v] = true;
            if (null == t) {
                head = node;
                t = head;
                continue;
            }
            t.next = node;
            node.prev = t;
            t = node;
        }

        // 在a中按字典序取数，遍历链表插入，如果小于当前节点立即插入，否则继续遍历到后面
        // 有个细节，每次插入完元素后，下个元素还从当前位置插入，因为 i > i-1，所以当前位置前的元素必然 < i

        Node p = head;
        for (i = 1; i < a.length; i++) {
            if (a[i]) continue;

            int c = i;

            while (true) {
                if (c > p.v) {
                    if (null == p.next) {
                        Node q = new Node(c);
                        p.next = q;
                        q.prev = p;
                        p = q;
                        break;
                    }
                    p = p.next;
                }
                else {
                    Node q = new Node(c);
                    Node oldPrev = p.prev;
                    q.next = p;
                    p.prev = q;
                    if (null != oldPrev) {
                        q.prev = oldPrev;
                        oldPrev.next = q;
                    }
                    else {
                        head = q;
                    }
                    p = q;
                    break;
                }
            }
        }

        // 将构造好的字典序链表输出
        Node node = head;
        while (null != node) {
            if (null == node.next) {
                System.out.print(node.v);
                break;
            }
            System.out.print(node.v + " ");
            node = node.next;
        }
    }

    static class Node {
        Node prev;
        Node next;
        int v;
        public Node(int v) {
            this.v = v;
        }
    }

}
