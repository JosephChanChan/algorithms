package dynamic.programming;

import java.util.*;

/**
 * 顺丰2020校招笔试
 *
 * Question Description:
 *  有n个任务，每个任务有开始时间l，结束时间r，收益w，每个任务只能做一次且做的任务间不能冲突。
 * 小明比较贪心，想选择一些任务来做并且获得最大的收益，最多能收益多少？
 *  输入：
 *  第一行任务个数，下面n-1行3个数，第一个是l，第二个是r，第三个是w
 *  3
 *  1 3 5
 *  2 7 3
 *  5 10 1
 *  输出：
 *  6
 *
 * Analysis:
 * DP，提交只AC了28%，题目提示 1<=n<=1e5，1<=wi<=1e9，即最后的总收益可能会超过int，怀疑是溢出导致部分AC。
 * 时间复杂度：O(n^2)
 * 空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2020-08-20 21:04
 */
public class BountyHunter {

    static int n ;
    static Task[] tasks ;
    static int[] f ;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        tasks = new Task[n+1];
        f = new int[n+1];

        tasks[0] = new Task();

        for (int i = 1; i <= n; i++) {
            Task task = new Task();
            task.l = scanner.nextInt();
            task.r = scanner.nextInt();
            task.profit = scanner.nextInt();
            tasks[i] = task;
        }

        // 将任务按结束时间升序
        Arrays.sort(tasks, ((o1, o2) -> o1.r - o2.r));

        /*
            f(i)以i任务结尾能获得的最大利益
            f(i) = max{f(i-1) && r[i-1] < l[i]} + w[i]
         */
        f[0] = 0;

        int totalMax = 0;
        for (int i = 1; i <= n; i++) {
            // 枚举i-1，
            int l = tasks[i].l;
            int wi = tasks[i].profit;
            int max = 0;
            for (int j = 1; j < i; j++) {
                int r = tasks[j].r;
                if (l > r && f[j] > max) {
                    max = f[j];
                }
            }
            f[i] = max + wi;
            if (f[i] > totalMax) {
                totalMax = f[i];
            }
        }
        System.out.println(totalMax);
    }

    static class Task {
        int l;
        int r;
        int profit;
    }
}
