package dynamic.programming;

import java.util.Scanner;

/**
 * 牛客华为机试HJ71
 *
 * Analysis:
 *  时间复杂度：O(nm)
 *  空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2022/4/30
 */
public class StringRegularizationMatching {

    /*
        问题描述：在计算机中，通配符一种特殊语法，广泛应用于文件搜索、数据库、正则表达式等领域。现要求各位实现字符串通配符的算法。
        要求：
        实现如下2个通配符：
        *：匹配0个或以上的字符（注：能被*和?匹配的字符仅由英文字母和数字0到9组成，下同）
        ？：匹配1个字符

        注意：匹配时不区分大小写。

        输入：
        通配符表达式；
        一组字符串。
        输出：
        返回不区分大小写的匹配结果，匹配成功输出true，匹配失败输出false
        数据范围：字符串长度：1\le s\le 100\1≤s≤100
        进阶：时间复杂度：O(n^2)\O(n) ，空间复杂度：O(n)\O(n)
        输入描述：
        先输入一个带有通配符的字符串，再输入一个需要匹配的字符串

        输出描述：
        返回不区分大小写的匹配结果，匹配成功输出true，匹配失败输出false

        te?t*.*
        txt12.xls
        false

        h*?*a
        h#a
        false
     */

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String B = sc.nextLine();
        String A = sc.nextLine();

        char[] a = new char[A.length()+1];
        char[] b = new char[B.length()+1];

        int n = a.length;
        int m = b.length;

        for (int i = 0; i < n-1; i++) {
            a[i+1] = A.charAt(i);
        }
        for (int i = 0; i < m-1; i++) {
            b[i+1] = B.charAt(i);
        }

        boolean[][] f = new boolean[n][m];
        f[0][0] = true;

        /*
            f(i,j)
            Ai == Bj or Bj==?
                f(i,j)=f(i-1,j-1)
            Ai != Bj && Bj==*
                f(i,j)=f(i,j-1) or f(i-1,j)

            边界：
                f(0,0)=true
                f(i,0)=false, I>0
                f(0,j)按方程计算
        */
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0 && j == 0) continue;
                if (j == 0) {
                    f[i][j] = false;
                    continue;
                }
                if (i == 0 && b[j] == '*') {
                    f[i][j] = f[i][j-1];
                    continue;
                }
                if (equ(a[i], b[j]) || (validC(a[i]) && b[j] == '?')) {
                    f[i][j] = f[i-1][j-1];
                }
                else if (b[j] == '*') {
                    f[i][j] = f[i-1][j] || f[i][j-1];
                }
            }
        }
        System.out.println(f[n-1][m-1]);
    }

    static boolean validC(char c) {
        return (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z') || (c >= '0' && c <= '9');
    }

    static boolean equ(char a, char b) {
        return Character.toUpperCase(a) == Character.toUpperCase(b);
    }
}
