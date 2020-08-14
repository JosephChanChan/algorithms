package simulation.algorithm;

import java.util.Scanner;

/**
 * 大疆2019笔试
 *
 * Question Description:
 *  小A是一名DIY爱好者，经常制作一些有趣的东西。
 * 今天，小A突然想要来做这样一个东西。小A现在有两块同样大小为n×m，有n×m块大小为1×1小电路板拼成的矩形电路板，假设叫做电路板A和电路板B。
 * 电路板A上每个小电路板都是一个开关，电路板B上每个小电路板上都是一盏电灯泡。A与B之间存在如下关系：对于B上的某盏灯Bij的开关控制，有A上第i行与第j列所有开关并联控制。即：
 *  B(i,j) = A(i,1)|A(i,2)| ... |A(i,m)|A(1,j)|A(2,j) ... |A(n,j)
 * 现给一矩阵，表示B上灯泡的明暗状态，问是否存在一种A的开关状态，能够满足给出的B上的灯泡开关情况，如果有输出YES，没有输出NO。
 * 输入描述：
 * 第一行，n和m表示电路板的长和宽，（1<=n，m<=1000）
 * 接下来n行，每行有m个由空格隔开的数字，0或1。0表示灯泡暗，1表示灯泡亮
 * 输出描述：
 * 输出一行，如果存在至少一种A的开关设置方式，能够使得B上灯泡明亮和给出的状态矩阵相同，则输出YES；不存在则输出NO。
 * 示例：
 * 样例1：
 * 输入：
 * 2 3
 * 1 1 1
 * 1 1 1
 * 输出：
 * YES
 * 样例2：
 * 输入：
 * 2 3
 * 1 1 0
 * 0 1 1
 * 输出：
 * NO
 *
 * Analysis:
 *  仔细理解题意和观察case2可以发现，若B(i,j)为亮着的，则A中第i行或第j列必然有一个开关会开，根据题意可推出若第i行有开关为亮，则B中第i行所有都亮，
 * 同理A第j列有一个开，B第j列全亮，继续可推出若B(i,j)为亮，则要么i行全亮，要么j列全亮，或i，j全亮。
 * 遍历B中所有亮着的灯泡，判断所在行列是否分别同时有灯泡关着的
 *
 * 时间复杂度：O(n*m)
 * 空间复杂度：O(n*m)
 *
 * @author Joseph
 * @since 2020-08-14 19:09
 */
public class BulbOnOrOff {

    static int n, m ;
    static boolean[][] matrix ;
    static boolean[] rowOff ;
    static boolean[] columnOff ;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        m = scanner.nextInt();
        matrix = new boolean[n][m];
        // 1~n row each row if has bulb off
        rowOff = new boolean[n];
        columnOff = new boolean[m];

        int i, j ;
        for (i = 0; i < n; i++) {
            for (j = 0; j < m; j++) {
                int trigger = scanner.nextInt();
                matrix[i][j] = trigger == 1;
                // 遍历i，j看每一行每列是否有灯泡关的
                if (!matrix[i][j]) {
                    rowOff[i] = true;
                    columnOff[j] = true;
                }
            }
        }

        calc(matrix, rowOff, columnOff);
    }

    private static void calc(boolean[][] matrix, boolean[] rowOff, boolean[] columnOff) {
        // 遍历i,j所有亮的灯泡，判断行列是否分别有关着的灯泡
        int i, j ;
        for (i = 0; i < n; i++) {
            for (j = 0; j < m; j++) {
                if (matrix[i][j]) {
                    if (rowOff[i] && columnOff[j]) {
                        System.out.println("NO");
                        return;
                    }
                }
            }
        }
        System.out.println("YES");
    }

}
