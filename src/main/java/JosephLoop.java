import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Problem Description:
 * 约瑟夫环（约瑟夫问题）是一个数学的应用问题：已知n个人（以编号1，2，3...n分别表示）围坐在一张圆桌周围。从编号为k的人开始报数，
 * 数到m的那个人出列；他的下一个人又从1开始报数，数到m的那个人又出列；依此规律重复下去，直到圆桌只剩下最后一人，给出这个人的原始编号。
 *
 * Analysis:
 * 很自然的想到模拟算法，规则给得很清楚，共有 n 个人，从第 1 个开始，报数到 M，淘汰，然后下一个人重新从 1 开始报数，如此循环。
 * 也就是 k + m - 1 个人会被淘汰。一共要循环 n 轮。
 *
 * created by Joseph
 * at 2019/4/17 19:46
 */
public class JosephLoop {

    static int n, k, m;

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        n = scanner.nextInt();
        k = scanner.nextInt() - 1;
        m = scanner.nextInt();



    }





}
