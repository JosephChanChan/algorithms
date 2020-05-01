package trees;

import java.util.Scanner;

/**
 * @author Joseph
 * @since 2020-04-26 21:13
 *
 * 腾讯实习笔试
 *
 * Question Description:
 *  给定一颗无限深度的完全二叉树，第一层节点编号为1，第二层节点编号为2，3，第三层节点编号为4，5，6，7。以此类推。
 * 现在给定一个 x, k，询问该x节点的第k层的祖先节点的编号是多少？
 *
 * 输入：
 * 4
 * 10 1
 * 10 2
 * 10 3
 * 10 4
 *
 * 输出：
 * 1
 * 2
 * 5
 * -1
 *
 * Analysis:
 *  时间复杂度：
 *  空间复杂度：
 */
public class KthAncestorInFullBinTree {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextInt()) {
            int c = scanner.nextInt();
            for (int i = 0; i < c; i++) {
                String line = scanner.nextLine();
                if (line.equals("")) {
                    i--;
                    continue;
                }
                String[] command = line.split(" ");
                long x = Long.parseLong(command[0]);
                long k = Long.parseLong(command[1]);
                long ancestor = calc(x, k);
                System.out.println(ancestor);
            }
        }

    }

    private static long calc(long x, long k) {
        // 计算x所处的深度
        int depth = depth(x);
        // 判断d(x) < k
        if (depth <= k) {
            return -1;
        }
        // 从d(x)到k 每次做 x/2
        for (int d = depth; d > k; d--) {
            x = x >> 1;
        }
        return x;
    }


    private static int depth(long x) {
        if (x < 2) return 1;

        int d = 2;
        long v = 2;
        for ( ; ; d++) {
            v = (v << 1);
            if (v-1 >= x) {
                break;
            }
        }
        return d;
    }
}
