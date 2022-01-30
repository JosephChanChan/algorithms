package trees;

import java.util.*;

/**
 * lc 295 hard && 剑指Offer 41 hard
 *
 * Analysis:
 * 中位数定义就是在一个有序序列中，中间的数，即大于等于一半的数和小于等于一半的数。
 * 设p1 p2，p1大于等于较小的一半的数，p2小于等于较大一半的数。
 * 则p1和p2是序列中的中位数，如果序列长度是奇数则p1和p2指向相同的数。
 *
 * 数组前半部分在最大堆，后半部分在最小堆。
 * {max} <= {min}
 * 最大堆和最小堆数量差 <= 1
 * 所以插入后要进行调整
 *
 * 时间复杂度：添加 O(logN) findMid O(1)
 * 空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2020-12-26 22:44
 */
public class MidNumInStream {

    Queue<Integer> min = new PriorityQueue<>();
    Queue<Integer> max = new PriorityQueue<>((o1, o2) -> o2-o1);

    /** initialize your data structure here. */
    public MidNumInStream() {
    }

    public void addNum(int num) {
        if (max.isEmpty() && min.isEmpty()) {
            max.add(num); return;
        }
        if (min.isEmpty() || num <= max.peek()) {
            max.add(num);
        }
        else {
            min.add(num);
        }
        if (Math.abs(max.size()-min.size()) > 1) {
            if (max.size() > min.size()) min.add(max.poll());
            else max.add(min.poll());
        }
    }

    public double findMedian() {
        if (((min.size()+max.size()) & 1) == 1) {
            return min.size() > max.size() ? min.peek()/1.0d : max.peek()/1.0d;
        }
        return (min.peek()+max.peek())/2.0d;
    }
}
