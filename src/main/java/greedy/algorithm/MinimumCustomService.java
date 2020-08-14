package greedy.algorithm;

import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * 携程2020校招
 *
 * Question Description:
 * 携程呼叫中心7×24小时帮助旅客解决在途中的各种问题，为了尽可能提升服务质量，公司希望客服人数可以满足所有旅客的来电，不用排队等待人工客服。
 * 现在提供客服中心所有的通话记录时间，你能算出最少需要多少名客服吗？
 * 输入一个n表示要输入的通话记录个数，接下来输入n行，每行为逗号相隔的两个整数，两个数字分别代表呼入时间和挂断时间的时间戳。
 * 举例：10,30，表示[10,30)，代表第10秒呼入，第30秒已经挂断，即第30秒可以接入新的来电； 每一行都是一条通话记录，通话记录已经按呼入时间由小到大排序；
 * 输出一个整数；代表最少需要多少客服，可以满足所有旅客来电不用等待。
 * 样例输入
 * 6
 * 0,30
 * 0,50
 * 10,20
 * 15,30
 * 20,50
 * 20,65
 * 输出：
 * 5
 *
 * Analysis:
 *  时间复杂度：O(n*logn)
 *  空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2020-08-14 21:52
 */
public class MinimumCustomService {

    static int n ;
    static PhoneRecord[] phones ;


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        // 开始时间升序
        phones = new PhoneRecord[n];

        PriorityQueue<PhoneRecord> queue = new PriorityQueue<>(((o1, o2) -> o1.end - o2.end));

        int i;
        for (i = 0; i < n; i++) {
            PhoneRecord record = new PhoneRecord();
            record.i = i;
            record.start = scanner.nextInt();
            record.end = scanner.nextInt();
            phones[i] = record;
        }

        /*
            这题就是之前做过的"活动安排"，求重叠的最大线段数
         */
        int max = 1;
        queue.add(phones[0]);
        for (i = 1; i < n; i++) {
            PhoneRecord record = phones[i];
            PhoneRecord minimumEnd = queue.peek();
            if (record.start < minimumEnd.end) {
                max++;
            }
            else {
                queue.poll();
            }
            queue.add(record);
        }
        System.out.println(max);
    }

    static class PhoneRecord {
        int i;
        int start;
        int end;
    }

}
