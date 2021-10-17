package simulation.algorithm;

import java.util.*;

/**
 * lc 1834 medium
 *
 * Analysis:
 * 考逻辑分析的模拟题。
 * 维护一个时间点t，将任务按照开始时间先后顺序排列。
 * 队列初始为空，代表CPU空闲没任务可做。
 * 将时间t推进到下一个任务开始时间。
 * 将开始时间<=t的都入队，因为这些任务已经开始。
 * 从队列取出执行时间最少的任务开始执行。
 * 这个模拟过程就是将t推进到任务执行完毕的时间t1。
 *
 * 时间复杂度：O(N*logN)
 * 空间复杂度：O(N)
 *
 * @author Joseph
 * @since 2021-04-18 12:21
 */
public class SingleCPU {

    public int[] getOrder(int[][] tasks) {
        int n = tasks.length;
        int[] ans = new int[n];

        int[][] f = new int[n][3];
        for (int i = 0; i < n; i++) {
            f[i][0] = tasks[i][0];
            f[i][1] = tasks[i][1];
            f[i][2] = i;
        }

        Arrays.sort(f, (o1, o2) -> o1[0]-o2[0]);

        // 血泪的教训。这里之前用Integer[] 用了 == 符号判断 o1[1] == o2[1]
        // 结果第15case一直错，过不了。以为逻辑错了，一行一行地对题解代码，都没找到原因
        // 最后发现是 == 符号问题！！！
        Queue<int[]> q = new PriorityQueue<>((o1, o2) -> {
            if (o1[1] == o2[1]) return o1[2]-o2[2];
            return o1[1] - o2[1];
        });

        long curTime = 0;
        for (int i = 0, next = i; i < n; i++) {
            // 队列为空，当前CPU空闲没有可处理任务。
            if (q.isEmpty()) {
                // 尝试将时间推进到下一个任务的开始时间。有可能当前时间已经超过下一个任务开始时间，就不用改变
                curTime = Math.max(curTime, f[next][0]);
            }
            // 将所有开始时间 <= 当前时间的任务入队
            while (next < n && f[next][0] <= curTime) {
                q.add(new int[]{f[next][0], f[next][1], f[next][2]});
                next++;
            }
            int[] e = q.poll();
            ans[i] = e[2];
            // 时间推进到做完这个任务的时间
            curTime += e[1];
        }
        return ans;
    }
}
