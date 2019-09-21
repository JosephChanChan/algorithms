package recursion;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author Joseph
 * @since 2019-09-18 23:35
 *
 * 水题，leetcode 509 easy
 *
 * Question Description:
 *  递归带备忘录的算法，解决斐波那契问题。
 *  f(n) = f(n-1) + f(n-2)。边界：f(0) = 0, f(1) = 1
 *
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 */
public class FibonacciSequence2 {


    public static void main(String[] args) {
        FibonacciSequence2 fibonacciSequence2 = new FibonacciSequence2();
        int val = fibonacciSequence2.fib(6);
        System.out.println(val);
    }

    private int fib(int num) {
        // 这里用 hash 可以加快查找速度，在leetcode上的体现是 hash 0ms, tree 1ms，差距不大
        // 时间复杂度上就是 线性和对数的关系
        Map<Integer, Integer> memorandum = new TreeMap<>();
        return doCalc(num, memorandum);
    }

    private int doCalc(int num, Map<Integer, Integer> memorandum) {
        if (num == 0) return 0;
        if (num == 1) return 1;
        if (null != memorandum.get(num)) {
            return memorandum.get(num);
        }
        int val = doCalc(num - 1, memorandum) + doCalc(num - 2, memorandum);
        memorandum.put(num, val);
        return val;
    }


}
