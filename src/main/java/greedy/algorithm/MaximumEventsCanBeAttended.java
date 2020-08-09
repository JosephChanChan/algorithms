package greedy.algorithm;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * leetcode 1353 medium
 *
 * Question Description:
 *  参见 lc 1353
 *
 * Analysis:
 * 时间复杂度：O(n*logN)
 * 空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2020-08-08 11:59
 */
public class MaximumEventsCanBeAttended {

    int count = 0;
    private boolean[] attended ;

    public static void main(String[] args) {
        int[][] events = {
                {1, 2},
                {2, 3},
                {3, 4},
        };
        MaximumEventsCanBeAttended test = new MaximumEventsCanBeAttended();
        test.maxEvents(events);
    }

    public int maxEvents(int[][] events) {
        return greedy2(events);
    }

    /** leetcode题解，O(N * logN) */
    private int greedy2(int[][] events) {
        // 将会议按开始时间升序排序
        Arrays.sort(events, (o1, o2) -> o1[0] - o2[0]);

        int eventIdx = 0, n = events.length, count = 0, day = 1;
        PriorityQueue<Integer> queue = new PriorityQueue<>(n);
        /*
            1.扫描每天开始的会议加入队列
            2.队列中按结束时间升序排序
            3.淘汰队列中已结束的会议
            4.取出最快结束的会议参加
         */
        while (eventIdx < n || !queue.isEmpty()) {
            // 扫描当天可以参加的会议
            for ( ; eventIdx < n; eventIdx++) {
                if (events[eventIdx][0] == day) queue.add(events[eventIdx][1]);
                else break;
            }
            // 排除结束时间小于当天的会议
            while (!queue.isEmpty() && queue.peek() < day) {
                queue.poll();
            }
            if (queue.isEmpty()) break;
            // 剩下的会议全部是在当天或之前就已经开始，并且还未结束的会议
            // 从中选出一个快结束的会议参加，其它的没那么快结束的可以后面参加
            day++;
            count++;
            queue.poll();
        }
        return count;
    }

    /** Time limit out, pass 44 case total 45 case */
    private int greedy(int[][] events) {
        if (events.length == 0) return 0;
        if (events.length == 1) return 1;
        /*
            1.将结束时间升序排序             O(nlogn)
            2.选择当前最快结束的活动 i         O(1)
            3.从i开始时间选择一天有空的参加       O(n * time)
        */
        int i, n = events.length ;
        Queue<Event> queue = new PriorityQueue<>(n, (o1, o2) -> o1.end - o2.end);
        for (i = 0; i < n; i++) {
            Event event = new Event();
            event.start = events[i][0];
            event.end = events[i][1];
            queue.add(event);
        }

        attended = new boolean[100000 + 1];

        while (!queue.isEmpty()) {
            Event event = queue.poll();
            int j ;
            for (j = event.start; j <= event.end; j++) {
                if (!attended[j]) {
                    attended[j] = true;
                    count++;
                    break;
                }
            }
        }
        return count;
    }

    class Event {
        int start;
        int end;
    }


}
