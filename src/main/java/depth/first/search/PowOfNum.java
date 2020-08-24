package depth.first.search;

/**
 * @author Joseph
 * @since 2019-09-22 20:26
 *
 * leetcode 50 medium
 *
 * Question Description:
 *  Implement pow(x, n), which calculates x raised to the power n (x^n).
 * Example 1:
 * Input: 2.00000, 10
 * Output: 1024.00000
 * Example 2:
 * Input: 2.10000, 3
 * Output: 9.26100
 * Example 3:
 * Input: 2.00000, -2
 * Output: 0.25000
 * Explanation: 2-2 = 1/22 = 1/4 = 0.25
 * Note:
 * -100.0 < x < 100.0
 * n is a 32-bit signed integer, within the range [−231, 231 − 1]
 *
 * Analysis:
 *  题目本意很简单，但是给的n很大，按照普通思路写了递归(就算是尾递归还是爆栈)和遍历，一个爆栈一个超时...
 *  猜测用O(n)是过不去的，得用更低时间复杂度的算法，那就只能是O(logN)了，但是怎么都想不到降低时间复杂度的方法。
 * 卡了一晚上，上网看了题解，了解到用递归+分治，自己推算了下，的确时间复杂度降低到了logN级别，顿时感慨真tm聪明...
 * 因为每次将问题划分1半，只计算一半数字得到结果直接平方得到某个局部的完整幂运算的结果。巧妙的利用了n个x相乘，
 * 分治2组后发现2组的计算结果都是一样的，那么只用计算其中一组结果再平方就可以了，继续递归下去就能成功将问题规模每次都降低2倍
 *
 * 时间复杂度：O(logN)
 * 空间复杂度：O(1)
 */
public class PowOfNum {


    public static void main(String[] args) {
        PowOfNum powOfNum = new PowOfNum();
        double v = powOfNum.myPow(1, -2147483648);
        System.out.println(v);
    }

    private double myPow(double x, int n) {
        if (x == 0) return 0;
        if (n == 0) return 1;

        // 2^-31 Math.abs()会超过 int的max_value，返回原数，这里做下处理
        boolean minimum = false;
        if (n == Integer.MIN_VALUE) {
            n++;
            minimum = true;
        }
        double v = doRecursionDivide(x, Math.abs(n));
        if (minimum) {
            v *= x;
        }
        if (n < 0) {
            v = 1 / v;
        }
        return v;
    }

    // divide-conquer
    private double doRecursionDivide(double x, long pow) {
        if (pow == 1) return x;

        long tempPow = pow;
        if (pow % 2 != 0) {
            tempPow = pow - 1;
        }
        double half = doRecursionDivide(x, tempPow >> 1);
        half *= half;
        if (tempPow != pow) {
            half *= x;
        }
        return half;
    }

    // loop
    private double recursionCalc(double x, int pow) {
        double cur = x;
        int temp = Math.abs(pow);
        for (int i = 1; i < temp; i++) {
            cur *= x;
        }
        return cur;
    }

    // recursion
    private double calc(double x, int pow) {
        if (pow == 1) return x;
        return calc(x, --pow) * x;
    }

    // tail recursion
    private double calc(double cur, double x, int pow, int count) {
        if (Math.abs(pow) == count) return cur;
        cur *= x;
        return calc(cur, x, pow, ++count);
    }


}
