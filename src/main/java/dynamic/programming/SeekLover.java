package dynamic.programming;

/**
 * @author Joseph
 * @since 2019/8/10 21:27
 *
 * Question Description:
 *  在一个小王国中，有8个村子。各个村子之间有道路想通。你的恋人住在这8个村子中的某一个，她每过1个月便顺着道路去另一个村子。
 *  选择哪个村子是随机的，预测不了。例如这个月在G村，那么下个月就会在 C、F、H 中的一个。
 *  目前可靠的信息只有：1年前（12月前），恋人在G村，求出当前月恋人住A村的概率。
 *              ------------------- A--------------------
 *              |                   |                   |
 *              |                   |                   |
 *              |         ----------E----------         |
 *              |         |                    |        |
 *              |         |                    |        |
 *              B---------F                    H--------D
 *              |         |                    |        |
 *              |         |                    |        |
 *              |         ----------G----------         |
 *              |                   |                   |
 *              |                   |                   |
 *              |-------------------C--------------------
 *  Analysis:
 *   动态规划解决的问题分为2类：优化问题（求某个函数的最大、最小值）、组合问题（求某种可行方案的数量或概率）。
 *   这个问题属于组合问题，求出某个情况的概率。
 *   子问题很明显就是 n个月前在某个点的概率。抽象一下为连走 n 步在某个点的概率
 *   设立 f(step, peek) 为第 step 步在 peek 点的概率。
 *   当前 step 步的下一步有3个可能的方向，均是等概率事件，那么 step + 1 步后所在的 peek 点
 *   就是当前 f(step, peek) * 1/3 的概率。所以得到状态转移方程：
 *      f(step, peek) = [f(step - 1, nearPeek1) + f(step - 1, nearPeek2) + f(step - 1, nearPeek3)] * 1/3
 *   边界:
 *      f(step, peek) = 0, step <= 0
 *   时间复杂度：O(step * peek)
 *   空间复杂度：目前 O(step * peek) 可优化成 O(2peek)，不过对应的时间复杂度要增加到 O(step * 2peek)
 */
public class SeekLover {

    // 将顶点编排：0=A 1=B 2=C 3=D 4=E 5=F 6=G 7=H
    // 当然可以用枚举映射，不过这里是算法题，就简单写一下好了。
    static double[][] peekChance = new double[13][8];

    static int[][] peekRelated = {
            {4, 1, 3}, // A点相邻点 ... 以此类推到 H点
            {0, 5, 2},
            {1, 6, 3},
            {0, 7, 2},
            {0, 5, 7},
            {4, 1, 6},
            {2, 5, 7},
            {4, 3, 6}
    };

    public static void main(String[] args) {
        // 起始点：第0步在G点概率为 1
        peekChance[0][6] = 1;

        for (int step = 1; step <= 12; step++) {
            for (int peek = 0; peek < 8; peek++) {
                double chance = calculatePeekChance(step, peek);
                peekChance[step][peek] = chance;
                System.out.println("当前第 " + step + " 步，在顶点 " + mappingPeek(peek) + " 的概率为: " + chance);
            }
        }

        // 答案揭晓，12个月后在A村的概率为 0...
    }

    private static double calculatePeekChance(int step, int peek) {
        int[] related = peekRelated[peek];
        int nearPeek1 = related[0];
        int nearPeek2 = related[1];
        int nearPeek3 = related[2];

        return (
                peekChance[step - 1][nearPeek1] +
                peekChance[step - 1][nearPeek2] +
                peekChance[step - 1][nearPeek3]
        ) * (1d/3d);
    }

    private static String mappingPeek(int peek) {
        // 将顶点编排：0=A 1=B 2=C 3=D 4=E 5=F 6=G 7=H
        switch (peek) {
            case 0: return "A";
            case 1: return "B";
            case 2: return "C";
            case 3: return "D";
            case 4: return "E";
            case 5: return "F";
            case 6: return "G";
            case 7: return "H";
            default: return "";
        }
    }


}
