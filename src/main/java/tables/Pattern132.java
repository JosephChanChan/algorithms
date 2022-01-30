package tables;

import java.util.Map;
import java.util.Stack;
import java.util.TreeMap;

/**
 * lc 456 medium
 *
 * Analysis:
 *  这题显然用单调栈最合适，会简单很多。132的模式恰好符合单调栈解决问题的模式。
 * 好吧，试了一发不能AC，单纯的”单调栈“只能帮助找到j左右两边<最近>的边界元素，但本题还有一个限制是右边界>左边界。
 * 遇到 [3,5,0,3,2] 这种case就报错了。
 *
 * 朴素解法，枚举ijk，时间O(n^3)
 * 优化一点，枚举j，在j的左边找min{i}<j，在j的右边找max{k}<j，因为从左往右枚举j，可边枚举j边更新min，在O(1)时间得min{i}。
 * 但max{k}只能遍历，所以总时间O(n^2)，数据范围是10^5会超时。
 * 用AVL树找到max{k}，在O(N*logN)时间得到max{k}。
 * 总时间O(N*logN)
 *
 * 递减栈，从右往左遍历，设每次从栈弹出的元素为k，则由于递减栈特性此时栈顶必然存在一个j，j>k
 * 遍历每个元素当做候选i，看i<k? 则找到符合132模式的ijk
 * 总时间O(n)
 *
 * 时间复杂度：单调栈 O(n)  平衡树+枚举J O(N*logN)
 * 空间复杂度：单调栈 O(n)  平衡树+枚举J O(N)
 *
 * @author Joseph
 * @since 2021-09-21 15:30
 */
public class Pattern132 {

    public boolean find132pattern(int[] nums) {
        return decreaseStack(nums);
    }

    boolean enumerateJ(int[] a) {
        int n = a.length;
        /*
             枚举j,找到j左边最小的i，j右边小于j的最大的k
             平衡树维护j，先全部元素存入树中，从左往右枚举j时，删除树中的j
         */
        TreeMap<Integer, Integer> tree = new TreeMap<>();
        for (int i = 1; i < n; i++) {
            tree.put(a[i], tree.getOrDefault(a[i], 0)+1);
        }

        int minI = a[0];
        for (int j = 1; j < n; j++) {
            int v = tree.get(a[j])-1;
            tree.put(a[j], v);
            if (v == 0) {
                tree.remove(a[j]);
            }
            Map.Entry<Integer, Integer> k = tree.lowerEntry(a[j]);
            if (null != k && k.getKey() > minI) {
                return true;
            }
            minI = Math.min(minI, a[j]);
        }
        return false;
    }

    boolean decreaseStack(int[] nums) {
        int[] a = nums;
        int n = a.length;

        // 递减栈
        Stack<Integer> s = new Stack<>();

        int k = Integer.MIN_VALUE;
        for (int i = n-1; i >= 0; i--) {
            if (a[i] < k) {
                return true;
            }
            // 保持递减栈的非严格递减，当k有值时则栈顶元素一定存在j比k大
            // 又因为从右往左遍历，可保证顺序上i<j<k
            while (!s.isEmpty() && a[s.peek()] < a[i]) {
                k = Math.max(k, a[s.pop()]);
            }
            s.push(i);
        }
        return false;
    }
}
