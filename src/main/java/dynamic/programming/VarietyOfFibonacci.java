package dynamic.programming;

import java.util.*;

/**
 * @author Joseph Chan
 * @since 2020/03/27
 *
 * 2019阿里暑期实习笔试
 *
 * Question Description:
 *  小明是一个数学家，他喜欢用数字给事物命名编号，他给自己编号为1，同时在2019年小明开办了一个农场，准备开始养母猪，
 * 他专门给农场的母猪用以下数列2，3，4，5，7，9，12，16，21，28，37，49，65，86，114，151...进行命名。
 * 假设农场的母猪永远不会死，小母猪出生后3年后成熟，成熟后从第三年开始每年只会生一只小母猪。
 * 第一年农场，有一只刚刚出生的小母猪和一只成熟的母猪(本年不再生小猪，下一年开始生小猪)，并给他们编号为2和3。
 * 请问，第m只母猪编号为多少？其是哪一年出生的？小明还准备了1份礼物，专门颁给农场第1到m只的母猪颁奖，
 * 颁奖规则如下:选出第1到m只的母猪翻转编号(114编号翻转为411)为第k大的母猪进行颁奖，请问是第几只猪获奖？提示: f(n)=f(n-2)+f(n-3)
 *
 * Analysis:
 *  按照考试要求，标准是30分钟AC哦
 *  读题都读了好久... 编号数列和猪的出生数列是不一样的，相信这点都能坑一部分人了。
 *  猪的出生数列 p(n)=p(n-1)+p(n-3) 与 f(n)=f(n-2)+f(n-3) 很混淆，p(n)和f(n)都是斐波那契数列变种，
 *  搞清楚这点后就是对3个小问题求解，第m只猪的编号直接求f(m)数列，它的出生年份，求从2019以来累计的猪的数量 > m 的那一年就是出生年。
 *  第3问，没好的办法，只能对 1~m 只猪的编号反转，排序求第k大。
 */
public class VarietyOfFibonacci {

    private Map<Integer, Integer> numList = new HashMap<>();
    private Map<Integer, Integer> pigList = new HashMap<>();
    private PriorityQueue<Pig> priorityQueue = new PriorityQueue<>(((o1, o2) -> o2.getReverseNum() > o1.getReverseNum() ? 1 : -1));

    public static void main(String[] args) {
        VarietyOfFibonacci varietyOfFibonacci = new VarietyOfFibonacci();
        int i = varietyOfFibonacci.calcNum(20);
        System.out.println(i);
        int year = varietyOfFibonacci.calcYear(20);
        System.out.println(year);
        int prize = varietyOfFibonacci.calcPrize(3);
        System.out.println(prize);
    }

    private int calcNum(int m) {
        numList.put(1, 2);// 第N头猪   猪规则编号
        numList.put(2, 3);
        numList.put(3, 4);
        if (m < 4) return numList.get(m);
        int num = 0;
        for (int i = 4; i <= m; i++) {
            num = numList.get(i - 2) + numList.get(i - 3);
            numList.put(i, num);
        }
        return num;
    }

    private int calcYear(int m) {
        /*
            f(n) 为第n年的母猪总数
            f(n) = f(n-1) + f(n-3)
         */
        pigList.put(2019, 2);
        pigList.put(2020, 3);
        pigList.put(2021, 4);
        pigList.put(2022, 6);
        int count = 0, year = 2023;
        for (Map.Entry<Integer, Integer> entry : pigList.entrySet()) {
            count += entry.getValue();
            if (count >= m) return entry.getKey();
        }
        while (count < m) {
            int temp = pigList.get(year - 1) + pigList.get(year - 3);
            pigList.put(year++, temp);
            count += temp;
        }
        return year;
    }

    private int calcPrize(int k) {
        for (Map.Entry<Integer, Integer> entry : numList.entrySet()) {
            StringBuilder builder = new StringBuilder(String.valueOf(entry.getValue()));
            priorityQueue.add(new Pig(entry.getValue(), Integer.parseInt(builder.reverse().toString()), entry.getKey()));
        }
        int index = 0;
        for (int i = 1; i <= k; i++) {
            Pig pig = priorityQueue.poll();
            index = pig.getIndex();
        }
        return index;
    }

    private class Pig {
        private Integer num;
        private Integer reverseNum;
        private Integer index;

        public Pig(int num, int reverseNum, int index) {
            this.num = num;
            this.reverseNum = reverseNum;
            this.index = index;
        }

        public Integer getNum() {
            return num;
        }

        public void setNum(Integer num) {
            this.num = num;
        }

        public Integer getReverseNum() {
            return reverseNum;
        }

        public void setReverseNum(Integer reverseNum) {
            this.reverseNum = reverseNum;
        }

        public Integer getIndex() {
            return index;
        }

        public void setIndex(Integer index) {
            this.index = index;
        }
    }
}
