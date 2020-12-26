package trees;

import java.util.*;

/**
 * 剑指Offer 41 hard
 *
 * Analysis:
 * 中位数定义就是在一个有序序列中，中间的数，即大于等于一半的数和小于等于一半的数。
 * 设p1 p2，p1大于等于较小的一半的数，p2小于等于较大一半的数。
 * 则p1和p2是序列中的中位数，如果序列长度是奇数则p1和p2指向相同的数。
 *
 * 如果维护两个数组，a1是较小一半数的数组，a2是较大一半数的数组，p1是a1的尾，p2是a2的头。
 * 很明显为了使得p1 p2始终指向中位数要满足两个约束：
 *  1.两个数组元素数量要相同或差<=1
 *  2.a1所有元素始终小于等于a2
 *
 * 为了满足条件1，可以交替把元素添加到a1和a2，这样使得两个数组元素数量差始终<=1
 * 为了满足条件2，设k为被添加元素，k要加入a1，但是k>=p2，就需要先将k加进a2，再从a2中移走一个最小元素放入a1。
 * 这样 a2元素数量不变，a1加了一个元素。
 * k要加入a2时同理。
 *
 * 那么最后一个问题，k加入a1/a2数组时，时间是O(n)的。利用这样的两个数组可以O(1)时间得到中位数。
 * 插入时间还可以优化，自然想到O(logN)的时间。可以提供插入O(logN)的数据结构有AVL树、红黑树等。
 * 这里用两个堆代替两个数组。所以一个最大堆和最小堆。
 *
 *  时间复杂度：添加 O(logN) findMid O(1)
 *  空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2020-12-26 22:44
 */
public class MidNumInStream {

    int count = 0;
    Queue<Integer> max = new PriorityQueue<>((o1, o2) -> o2 - o1);
    Queue<Integer> min = new PriorityQueue<>();

    public void addNum(int num) {
        /*
            为了使两个堆尽可能包含相同数量元素。
            偶数加max，奇数加min。
            max堆所有元素小于min。
            加max堆时，num大于等于min堆顶时，先加入min堆，再从min堆delMin获得最小元加入max
            加min堆时类似
        */
        int k = num;
        if ((count & 1) == 0) {
            if (min.size() > 0 && min.peek() <= num) {
                min.add(num);
                k = min.poll();
            }
            max.add(k);
        }
        else {
            if (max.size() > 0 && max.peek() >= num) {
                max.add(num);
                k = max.poll();
            }
            min.add(k);
        }
        count++;
    }

    public double findMedian() {
        /*
            当两个堆元素相同多时，max堆顶和min堆顶代表的是两个中位数
            当堆不平衡时，由于按照奇偶数分别加，两个堆元素数最多差1
            max堆元素数 > min堆，max堆顶是中位数，相反min堆顶
        */
        if (count == 0) return 0;

        if ((count & 1) == 0) {
            int p1 = max.peek();
            int p2 = min.peek();
            return (p1 + p2) / 2.0d;
        }
        else if (max.size() > min.size()) {
            return max.peek();
        }
        else {
            return min.peek();
        }
    }
}
