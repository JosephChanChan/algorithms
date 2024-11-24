package sweep.line;

import java.util.*;

/**
 * lc 1942 medium
 *
 * Analysis:
 * 时间复杂度：O(nlog(n))
 * 空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2022/9/16
 */
public class NumOfSmallestUnoccupiedChair {

    /**
         扫描线+堆
         扫描线模拟每个人到达和离开的占据椅子的过程，堆维护最小可用的编号
         一开始只想到扫描线去模拟，但是对于最小可用编号没有好的办法去维护，这个时候应该要想到用最小堆维护
         看了题目类型才想到用堆
     */
    int[][] events ;
    // 保存每个人对应的椅子
    Map<Integer, Integer> m = new HashMap<>();
    PriorityQueue<Integer> q ;

    public int smallestChair(int[][] times, int targetFriend) {
        events = new int[times.length << 1][3];
        for (int i = 0; i < times.length; i++) {
            events[i<<1] = new int[]{times[i][0], 1, i};
            events[(i<<1)+1] = new int[]{times[i][1], 2, i};
        }
        Arrays.sort(events, (o1, o2) -> {
            if (o1[0] == o2[0]) {
                // 如果事件时间相同，先让离开时间发生
                return o2[1] - o1[1];
            }
            return o1[0] - o2[0];
        });
        initializeNum(times.length);
        for (int i = 0; i < events.length; i++) {
            int[] event = events[i];
            //System.out.println("event="+event[0]+" "+event[1]);
            int num = event[2];
            if (event[1] == 1) {
                int min = q.poll();
                //System.out.println(num+" "+min);
                m.put(num, min);
                if (num == targetFriend) {
                    return min;
                }
            }
            else {
                //System.out.println(num);
                int aval = m.remove(num);
                q.add(aval);
            }
        }
        return -1;
    }

    void initializeNum(int n) {
        q = new PriorityQueue<>(n);
        for (int i = 0; i < n; i++) {
            q.add(i);
        }
    }
}
