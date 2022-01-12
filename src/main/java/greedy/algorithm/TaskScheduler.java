package greedy.algorithm;

import java.util.*;

/**
 * lc 621 medium
 *
 * Analysis:
 *  模拟过程，没执行完ai任务存一个此任务下次可执行时间t
 * 每个时间点遍历所有待执行任务，O(1)时间判断此任务此时间是否可执行
 * 决策是优先执行剩余任务次数多的，相等情况下，选择被执行次数少的
 *
 * 决策的背后思想是，尽可能使任务剩余执行次数平均，减少CPU等待时间。
 * 因为如果A剩余执行次数10，B剩余执行次数1，B执行完后，剩下A执行一次CPU必须等待，使得任务尽量平均时
 * CPU可切换任务执行。
 *
 * 不同的任务数是k
 *
 * 时间复杂度：O(max{task}*n*k*log(k))
 * 空间复杂度：O(k)
 *
 * @author Joseph
 * @since 2021-03-25 13:23
 */
public class TaskScheduler {

    public int leastInterval(char[] tasks, int n) {
        if (n == 0) return tasks.length;

        int k = tasks.length;
        int[] t = new int[26];

        Queue<Task> q = new PriorityQueue<>(k, (o1, o2) -> {
            if (o1.available == o2.available) {
                return o1.executed - o2.executed;
            }
            return o2.available - o1.available;
        });

        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < k; i++) {
            map.put(tasks[i], map.getOrDefault(tasks[i], 0)+1);
        }
        for (Map.Entry<Character, Integer> e : map.entrySet()) {
            q.add(new Task(e.getKey(), e.getValue()));
        }

        int times = 0;

        List<Task> list = new ArrayList<>(k);
        while (!q.isEmpty()) {
            times++;
            while (!q.isEmpty()) {
                Task a = q.poll();
                if (times > t[a.c-'A']) {
                    a.available--;
                    a.executed++;
                    if (a.available != 0) {
                        q.add(a);
                    }
                    t[a.c-'A'] = times+n;
                    break;
                }
                list.add(a);
            }
            if (list.size() > 0) {
                for (Task e : list) q.add(e);
                list.clear();
            }
        }
        return times;
    }

    class Task {
        char c ;
        int available;
        int executed;
        public Task(char c, int i) {
            this.c = c;
            this.available = i;
            this.executed = 0;
        }
        public int hashCode() {
            return 31 * available * executed * c;
        }
        public boolean equals(Object o) {
            Task t = (Task) o;
            return t.c == this.c;
        }
    }
}
