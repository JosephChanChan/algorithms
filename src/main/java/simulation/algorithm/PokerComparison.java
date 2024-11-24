package simulation.algorithm;

import java.util.Scanner;

/**
 * 华为机试 HJ 88
 *
 * Analysis:
 *  主要思路是权重法+特殊值映射法。将不好判断的特殊值例如10映射成单字母，这样所有牌都是单字母好处理。
 * 将所有牌映射一个数字权重就好比较大小。所以将特殊牌字母映射数字。
 *
 *  时间复杂度：O(1)
 *  空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2022/4/30
 */
public class PokerComparison {

    /*
        扑克牌游戏大家应该都比较熟悉了，一副牌由54张组成，含3~A、2各4张，小王1张，大王1张。牌面从小到大用如下字符和字符串表示（其中，小写joker表示小王，大写JOKER表示大王）：
        3 4 5 6 7 8 9 10 J Q K A 2 joker JOKER
        输入两手牌，两手牌之间用"-"连接，每手牌的每张牌以空格分隔，"-"两边没有空格，如：4 4 4 4-joker JOKER。
        请比较两手牌大小，输出较大的牌，如果不存在比较关系则输出ERROR。
        基本规则：
        （1）输入每手牌可能是个子、对子、顺子（连续5张）、三个、炸弹（四个）和对王中的一种，不存在其他情况，由输入保证两手牌都是合法的，顺子已经从小到大排列；
        （2）除了炸弹和对王可以和所有牌比较之外，其他类型的牌只能跟相同类型的存在比较关系（如，对子跟对子比较，三个跟三个比较），不考虑拆牌情况（如：将对子拆分成个子）；
        （3）大小规则跟大家平时了解的常见规则相同，个子、对子、三个比较牌面大小；顺子比较最小牌大小；炸弹大于前面所有的牌，炸弹之间比较牌面大小；对王是最大的牌；

        （4）输入的两手牌不会出现相等的情况。

        输入：
        4 4 4 4-joker JOKER
        输出：
        joker JOKER
     */

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        String[] sa = s.split("-");
        String a = sa[0];
        String b = sa[1];

        if ((a.contains("joker") && a.length() == 11) || (b.contains("joker") && b.length() == 11)) {
            System.out.println("joker JOKER");
            return;
        }

        a = a.replaceAll("10", "T");
        b = b.replaceAll("10", "T");

        int p = type(a);
        int q = type(b);

        if (p != q && (p != 5 && q != 5)) {
            System.out.println("ERROR");
            return;
        }

        if (p == 5) {
            a = a.replaceAll("T", "10");
            System.out.println(a);
            return;
        }
        if (q == 5) {
            b = b.replaceAll("T", "10");
            System.out.println(b);
            return;
        }

        String ans = val(a) > val(b) ? a : b;
        ans = ans.replaceAll("T", "10");

        System.out.println(ans);
    }

    static int val(String a) {
        if (a.equals("joker")) {
            return 99;
        }
        if (a.equals("JOKER")) {
            return 100;
        }
        char c = a.charAt(0);
        if (c == 'T') {
            return 10;
        }
        if (c == 'J') {
            return 11;
        }
        if (c == 'Q') {
            return 12;
        }
        if (c == 'K') {
            return 13;
        }
        if (c == 'A') {
            return 14;
        }
        if (c == '2') {
            return 15;
        }
        return Integer.parseInt(c +"");
    }

    static int type(String a) {
        /*
            单 1
            双 2
            顺 3
            三个 4
            炸弹 5
            王炸 6
        */
        if (a.length() == 1 || a.length() == 2 || a.equals("joker") || a.equals("JOKER")) {
            return 1;
        }
        if (a.length() == 3) {
            return 2;
        }
        if (a.length() == 9) {
            return 3;
        }
        if (a.length() == 5) {
            return 4;
        }
        if (a.length() == 7) {
            return 5;
        }
        throw new RuntimeException("no type for="+a);
    }
}
