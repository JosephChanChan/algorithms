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
 *
 * Analysis:
 *  因为对动态规划的不熟练，这题还是写了3~4个钟... 在笔试中早就挂了，还是太菜
 *  时间复杂度：O(n*m) n和m分别为2个字符串的长度
 *  空间复杂度：O(n*m)
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
        String temp = reader.readLine();
        String[] str = temp.split(",");
        return doCalc(str[0], str[1]);
    }

    private int doCalc(String s, String t) {
        /*
            f[i][j] s前i位和t前j位中以s[i]和t[j]结尾的最长公共子串
            即s前i位和t前j位中可能存在一个最长公共子串，这个子串的结尾是s[i]=t[j]。
            思考，前i-1位和前j-1位中可能存在一个公共子串 A1 A2 ... Ak-1，
            如果当前Si == Tj设为Ak，那么将前面的公共子串+Ak就可以得到更长的公共子串，但是Ak-1必须和Ak连着的，
            即Ak-1 = Si-1 = Tj-1，如果Si-1 != Tj-1 那么即使前面存在一个Ak-1的子串，也不能和Ak连着，所以f[i][j]=1。
            如果Si != Tj，因为状态的定义，所以f[i][j]=0
            所以得出转移方程：
                if Si == Tj && Si-1 == Tj-1
                    f[i][j] = f[i-1][j-1] + 1
                else if Si == Tj && Si-1 != Tj-1
                    f[i][j] = 1
                else
                    f[i][j] = 0

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
                if (i == 0 || j == 0) {
                    commonSubLength[i][j] = sArray[i] == tArray[j] ? 1 : 0;
                    continue;
                }
                if (sArray[i] == tArray[j] && sArray[i-1] == tArray[j-1]) {
                    commonSubLength[i][j] = getPre(i-1, j-1) + 1;
                }
                else if (sArray[i] == tArray[j] && sArray[i-1] != tArray[j-1]) {
                    commonSubLength[i][j] = 1;
                }
                else {
                    commonSubLength[i][j] = 0;
                }
                if (commonSubLength[i][j] > totalMax) {
                    totalMax = commonSubLength[i][j];
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
