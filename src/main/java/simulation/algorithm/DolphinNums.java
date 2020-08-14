package simulation.algorithm;

import java.util.Scanner;

/**
 * 携程2020笔试
 *
 * Question Description:
 *  携程海洋馆中有 n 只萌萌的小海豚，初始均为 0 岁，每只小海豚的寿命是 m 岁，且这些小海豚会在 birthYear[i]
 *  这些年份生产出一位宝宝海豚（1 <= birthYear[i] <= m），每位宝宝海豚刚出生为 0 岁。
 *  问 x 年时，携程海洋馆有多少只小海豚？
 * 输入
 * n（初始海豚数）
 * m（海豚寿命）
 * 海豚生宝宝的年份数量(假设为p)
 * 海豚生宝宝的年份1
 * ...
 * 海豚生宝宝的年份p
 * x（几年后）
 * 输出
 * x年后，共有多少只小海豚
 * 样例输入
 * 5 5 2 2 4 5
 * 样例输出
 * 1
 * 20
 *
 * Analysis:
 *  理解题意后，关键维护两个数组，每年出生数和每年海豚数，当第i年可根据birthYear推出n年前出生的海豚今年可以生育。
 *  遍历birthYear[]得到今年出生数，今年海豚数=去年海豚数+今年出生数-今年死亡数，模拟x年后情况
 *
 * 时间复杂度：O(x*p)
 * 空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2020-08-14 18:57
 */
public class DolphinNums {

    static int n, m, p, x ;
    static int[] birthYear, totalNum, birthNum ;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        m = scanner.nextInt();
        p = scanner.nextInt();
        birthYear = new int[p];

        int i ;
        for (i = 0; i < p; i++) {
            birthYear[i] = scanner.nextInt();
        }

        x = scanner.nextInt();
        // 每年海豚数
        totalNum = new int[x + 1];
        // 每年生出的出生数
        birthNum = new int[x + 1];

        // 初始化第0年
        totalNum[0] = n;
        birthNum[0] = n;

        for (i = 1; i <= x; i++) {
            // 立足i年，往前推birthYear，当前i年应生海豚数
            for (int j = 0; j < birthYear.length; j++) {
                int birth = i - birthYear[j];
                if (birth < 0) continue;
                if (birthNum[birth] > 0) {
                    // 当年出生的海豚今年可以生
                    birthNum[i] += birthNum[birth];
                }
            }
            // total = 前年海豚数+今年出生数-今年死亡
            // 今年死亡= m+1年前的应该死亡
            totalNum[i] = totalNum[i-1] + birthNum[i] - ((i - m - 1) < 0 ? 0 : birthNum[i-m-1]);
        }
        System.out.println(totalNum[x]);
    }
}
