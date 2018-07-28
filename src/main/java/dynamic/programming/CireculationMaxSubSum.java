package dynamic.programming;

import java.io.*;

/**
 * N个整数组成的循环序列a[1],a[2],a[3],…,a[n]，求该序列如a[i]+a[i+1]+…+a[j]的连续的子段和的最大值
 *  （循环序列是指n个数围成一个圈，因此需要考虑a[n-1],a[n],a[1],a[2]这样的序列）。
 *  当所给的整数均为负数时和为0。
 *  例如：-2,11,-4,13,-5,-2，和最大的子段为：11,-4,13。和为20。
 *
 *  分析一：
 *  和一般的最大字段和不同的是，这里是循环的 也就是头和尾是相连的额 ，那么最大的字段和可能位置有两种：
 *  一、和一般的最大字段和一样，取数组头尾之间的某段
 *  二、当取头尾相连的某段时，那么其余的那一段的和一定是负数 且和最小，如果把数组变号，
 *  那么其余的那段就是最大的子段和（绝对值最大），数组总和加上这个和就是头尾子段的最大和
 *  之后比较两种情况的最大值
 *
 *  Created by Joseph on 2017/10/12.
 */
public class CireculationMaxSubSum {

    static Node first = null;
    static Node last = null;

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in),1<<16);
        try {
            long sum = 0;
            int n = Integer.parseInt(reader.readLine());
            for(int i=0; i<n; i++){
                long t = Long.parseLong(reader.readLine());
                add(t);
                sum += t;
            }

            //求普通的最大字段和
            long genMaxSubSum = calcMaxSubSum(first);

            //求最小字段和
            long minSubSum = clacMinSubSum(first);

            long value = sum - minSubSum;
            if(value < genMaxSubSum){
                value = genMaxSubSum;
            }
            System.out.println(value);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static long calcMaxSubSum(Node item){
        long subSum = 0, f1 = 0;
        Node temp = item;
        while (null != temp){
            //f(i) = max{ f(i-1)+a[i], a[i] }
            long t = temp.getValue();
            if(f1 > 0){
                f1 = f1 + t;
            }else {
                f1 = t;
            }
            if(f1 > subSum){
                subSum = f1;
            }
            temp = temp.getNext();
        }
        return subSum;
    }

    private static long clacMinSubSum(Node item){
        long minSubSum = 0, f1 = 0;
        Node temp = item;
        while (null != temp){
            //f(i) = min{ f(i-1)+a[i], a[i] }
            long t = temp.getValue();
            if(f1 > 0){
                f1 = t;
            }else {
                f1 += t;
            }
            if(f1 < minSubSum){
                minSubSum = f1;
            }
            temp = temp.getNext();
        }
        return minSubSum;
    }

    private static void add(long t){
        Node node = new Node(t);
        if(null == first)
            first = node;
        if(null != last)
            last.setNext(node);
        last = node;
    }

    private static void reBuilQueue(){
        last.setNext(first);
        last = first;
        Node item = first.getNext();
        first.setNext(null);
        first = item;
    }

    private static class Node{
        private long value;
        private Node next;

        public Node(long v){
            this.value = v;
        }

        public Node(){}

        public long getValue() {
            return value;
        }

        public void setValue(long value) {
            this.value = value;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }
}