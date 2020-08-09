import java.util.Scanner;

/**
 * 网易2020校招笔试
 *
 * Question Description:
 *  给定长度n的数组，将数组内每个元素a[i]可拆分成若干个正整数，这些整数的和等于a[i]。也可以不拆。
 *  求这个数组拆分后可以得到的最多的素数个数。
 *  输入：
 *  3
 *  1 1 5
 *  输出：
 *  2
 *  解释：只有5才能被拆，可拆分成：2、2、1，其中两个质数
 *
 *  数据范围：1<=n<=100000，1<=a[i]<=1e9
 *
 * Analysis:
 * 看到题人傻了，突然忘了什么叫"素数"？，一个劲往怎么求质数上想，但是忘了定义的东西只能GG。
 * 后面看题解，这是道送分题。
 * 一个数要么是偶数/奇数，最小的质数是2，2直接除a[i]分成最多的2，就行了！
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2020-08-08 21:03
 */
public class PrimeNumCountInArray {

    static long primeCount = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        // ai很大，int会溢出
        int i ;
        for (i = 0; i < n; i++) {
            primeCount += scanner.nextInt() >> 1;
        }
        System.out.println(primeCount);
    }

}
