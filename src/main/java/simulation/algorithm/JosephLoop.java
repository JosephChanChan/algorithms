package simulation.algorithm;

import tables.SingleTrackCircularLinkedList;

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

    static int personCount, startIndex, killIndex;
    static int[] loop ;

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        personCount = scanner.nextInt();
        startIndex = scanner.nextInt() - 1;
        killIndex = scanner.nextInt();

        loop = new int[personCount];
        for (int i = 1; i <= personCount; i++) {
            /*
                [1] [2] [3] ... [10]
                 0   1   2        9
             */
            loop[i - 1] = i;
        }

        // 一共要淘汰 N-1 个人
        for (int k = 0; k < personCount - 1; k++) {
            // 根据规则报数，从开始报数的那个人也算1位，
            int eliminatedIndex = beginRoll();
            System.out.println("The eliminated element is " + loop[eliminatedIndex] +" in round " + (k + 1));
            loop[eliminatedIndex] = 0;
            /*System.out.println("The next element index is " + startIndex);*/
        }

        System.out.println("Survivor is " + loop[startIndex]);
    }

    private static int beginRoll () {
        int point = startIndex, timeToOut ;
        // 从 start 移步 k 位到下一个被淘汰的人的位置返回下标
        for (int i = killIndex; i > 0; ) {
            // 被淘汰的人会被置为 0，大于 0 代表此位置有人
            if (loop[point] > 0) {
                i--;
            }
            if (i > 0) {
                // 如果当前指针已经是环的尾部了，再往后走就需要回到环的开头
                point = turn2HeadIfNecessary(point);
            }
        }
        timeToOut = point;

        // 找到此轮被淘汰的人后，需要找到下一个开始的人
        point = turn2HeadIfNecessary(point);
        while (true) {
            if (loop[point] > 0) {
                startIndex = point;
                break;
            }
            point = turn2HeadIfNecessary(point);
        }
        return timeToOut;
    }

    private static int turn2HeadIfNecessary(int point) {
        return  ++point == loop.length ? 0 : point;
    }


}
