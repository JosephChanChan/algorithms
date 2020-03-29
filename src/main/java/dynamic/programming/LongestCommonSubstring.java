package dynamic.programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Joseph
 * @since 2020-03-29 21:14
 *
 * 阿里校招笔试某题，牛客网：最大公共子串
 *
 * Question Description:
 *  给定两个字符串，请编写代码，输出最长公共子串（Longest Common Substring），是指两个字符串中的最长的公共子串，要求子串一定是连续。
 * 输入描述:
 * 文本格式，2个非空字符串（字母数字组成），2个字符串以","英文逗号分割，字符串长度均小于等于100。
 * 输出描述:
 * 整形，为匹配到的最长子串长度
 * 示例1
 * 输入
 * 复制
 * bab,caba
 * 输出
 * 复制
 * 2
 */
public class LongestCommonSubstring {

    int[][] commonSubLength;

    public static void main(String[] args) throws IOException {
        LongestCommonSubstring longestCommonSubstring = new LongestCommonSubstring();
        int i = longestCommonSubstring.dpCalc();
        System.out.println(i);
    }

    public int dpCalc() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in),1<<16);
        String s = reader.readLine();
        String t = reader.readLine();
        return doCalc(s, t);
    }

    private int doCalc(String s, String t) {
        /*
            f[i][j] s中前i位和t中前j位最长的公共子串
            if s[i]=t[j] && s[i-1]==t[j-1]
                f[i][j]=f[i-1][j-1]+1
            else
                f[i][j] = max{f[i-1][j-1], f[i-1][j], f[i][j-1]}
            边界  f[i][j]=0, i=j<0
         */
        char[] sArray = s.toCharArray();
        char[] tArray = t.toCharArray();
        if (sArray.length == 0 || tArray.length == 0) {
            return 0;
        }
        commonSubLength = new int[sArray.length][tArray.length];
        commonSubLength[0][0] = sArray[0] == tArray[0] ? 1 : 0;

        int totalMax = 0;
        for (int i = 0; i < sArray.length; i++) {
            for (int j = 0; j < tArray.length; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                int max = Math.max(getPre(i - 1, j - 1), getPre(i - 1, j));
                max = Math.max(max, getPre(i, j - 1));
                if (sArray[i] == tArray[j]) {
                    max += 1;
                }
                commonSubLength[i][j] = max;
                if (max > totalMax) {
                    totalMax = max;
                }
            }
        }
        return totalMax;
    }

    private int getPre(int i, int j) {
        if (i < 0 || j < 0) {
            return 0;
        }
        return commonSubLength[i][j];
    }


}
