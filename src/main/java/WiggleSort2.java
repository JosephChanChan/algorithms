import java.util.PriorityQueue;
import java.util.Queue;

/**
 * lc 324 medium
 *
 * Analysis:
 * 时间复杂度：O(n*log(n))
 * 空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2022/12/30
 */
public class WiggleSort2 {

    /*
        根据题目要求容易想到从大到小，从左到右，先放置奇数位置的下标
        然后剩余数字，从左到右，放置偶数下标，即
        min, max, min+1, max-1, min+3, max-2 ... max-(n/2)

        这题还有更快的O(n)时间做法：
        1.快速选择找到中位数mid
        2.三数排序，将数组分为3部分，a1 a2 a3 ... b1 b2 b3 ... c1 c2 c3，
        a部分 < mid，b部分 = mid，mid < c部分
        3.从左到右，从大到小，先放置奇数位置，再放置偶数位置。
     */

    Queue<Integer> q = new PriorityQueue<>((o1 , o2) -> o2-o1);

    public void wiggleSort(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            q.add(nums[i]);
        }
        for (int i = 1; i < n; i += 2) {
            nums[i] = q.poll();
        }
        for (int i = 0; i < n; i += 2) {
            nums[i] = q.poll();
        }
    }
}
