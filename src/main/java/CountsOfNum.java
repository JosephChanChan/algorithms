package main.java;

import java.io.*;

/**
 * Created by Joseph on 2017/7/10.
 * 给定一个十进制正整数N，写下从1开始，到N的所有正数，计算出其中出现所有1的个数。
 例如：n = 12，包含了5个1。1,10,12共包含3个1，11包含2个1，总共5个1。
 以上题目可转成 1~N 中出现X数字的个数 例如 1~9998中 出现5的个数

 Input
 输入N(1 <= N <= 10^9)
 Output
 输出包含1的个数

 规律:
 从 1 至 10，在它们的个位数中，任意的 X 都出现了 1 次。
 从 1 至 100，在它们的十位数中，任意的 X 都出现了 10 次。
 从 1 至 1000，在它们的千位数中，任意的 X 都出现了 100 次。
 依此类推，从 1 至 10i，在它们的左数第二位（右数第 i 位）中，任意的 X 都出现了 10i−1 次。

 算法:
 取第 i 位左边（高位）的数字，乘以 10i−1，得到基础值 a。
 取第 i 位数字，计算修正值：
 如果大于 X，则结果为 a+10i−1。
 如果小于 X，则结果为 a。
 如果等 X，则取第 i 位右边（低位）数字，设为 b，最后结果为 a+b+1。

 相应的代码非常简单，效率也非常高，时间复杂度只有 O(log10n)。
 */
public class CountsOfNum {

    public static void main(String[] args){
        int count=0;
        long n;
        String num;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in),1<<16);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out), 1 << 16);
            num = reader.readLine();
            n = Long.parseLong(num);
            long x;
            for(int i=1; n>0; i++){
                x = n%10;
                n = n/10;
                //计算 10i-1 次方
                int calc = calc(i-1);
                int base = (int)n * calc;
                //计算第i位
                if(x > 1){
                    base = base+calc;
                }else if(x == 1){
                    String ts = num.substring((num.length() - i)+1);
                    long ii = Long.parseLong(ts)+1;
                    base += ii;
                }

                count+=base;
            }

            writer.write(count+"\r\n");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int calc(int i){
        int val=10;
        if(i==1) return val;
        else if(i==0) return 1;
        else if(i<0) return 0;
        while (i>1){
            val *= val;
            i--;
        }
        return val;
    }
}