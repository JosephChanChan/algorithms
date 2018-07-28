package dynamic.programming;

import java.io.*;

/**
 *  最大公共子序列
 *  序列A｛1 3 5 4 2 6 8 7｝
 *  序列B｛1 4 8 6 7 5｝
 *
 *  1 4 8 7
 *  1 4 6 7
 *
 *  动态规划算法:
 *      求 Ax和By的最大公共子序列等价于 求 An和Bm的，n<x m<y 子问题
 *      设 L(x,y) 为Ax和By的最大公共子序列。 最优子问题
 *      如果 Ax = By 则L(x,y)的最后一项 t 必然等于 Ax By。 Ax删掉一项，By也删掉一项它们的最大公共子序列变为 L(x-1,y-1)
 *          所以 L(x,y) = L(x-1,y-1) + t
 *      如果 Ax != By  则L(x,y)的最后一项 t 必然不等于 Ax 或 By。
 *          如果 t!=Ax 则L(x,y) = L(x-1,y)
 *          如果 t!=By 则L(x,y) = L(x,y-1)
 *          所以 L(x,y) = max{L(x-1,y),L(x,y-1)}
 *
 * Created by Joseph on 2017/7/14.
 */


public class MaxCommSubSequence {

    public static void main(String[] args){
        char[] a = null;
        char[] b = null;
        Node[][] nodes = null;

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in),1<<16);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out),1<<16);

        try {
            String s1 = reader.readLine();
            String s2 = reader.readLine();
            a = s1.toCharArray();
            b = s2.toCharArray();

            nodes = new Node[a.length+1][b.length+1];
            Node node = null;
            for(int x=0; x<=a.length; x++){
                for(int y=0; y<=b.length; y++){
                    node = new Node();
                    nodes[x][y] = node;
                    if(x==0 || y==0){
                        nodes[x][y].setValue(0);
                        continue;
                    }
                    if(a[x-1]==b[y-1]){
                        //L(x,y) = L(x-1,y-1) + a[x]
                        node.setValue(nodes[x-1][y-1].getValue()+1);
                        node.setX(a[x-1]);
                        node.setY(b[y-1]);
                        node.setPrev(nodes[x-1][y-1]);
                    }else {
                        // A[x] != B[y]  L(x,y) = max{L(x-1,y),L(x,y-1)}
                        node.setX(a[x-1]);
                        node.setY(b[y-1]);
                        if(nodes[x-1][y].getValue() >= nodes[x][y-1].getValue()){
                            node.setPrev(nodes[x-1][y]);
                            node.setValue(nodes[x-1][y].getValue());
                        }else{
                            node.setPrev(nodes[x][y-1]);
                            node.setValue(nodes[x][y-1].getValue());
                        }
                    }

//                    System.out.println(node.getX()+" "+node.getY()+" "+node.getValue()+" "+node.getPrev());
                }
            }

            Node terminal = nodes[a.length][b.length];
            StringBuilder builder = new StringBuilder();
            while (null != terminal){
                if(terminal.getX()==terminal.getY() && terminal.getValue()>0){
//                    System.out.println("长度= "+terminal.getValue()+" 元素="+terminal.getX());
                    builder.append(terminal.getX());
                }
                terminal = terminal.getPrev();
            }

            writer.write(builder.reverse().toString());
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    static class Node{
        private int value;
        private char x;
        private char y;
        private Node prev;

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public char getX() {
            return x;
        }

        public void setX(char x) {
            this.x = x;
        }

        public char getY() {
            return y;
        }

        public void setY(char y) {
            this.y = y;
        }

        public Node getPrev() {
            return prev;
        }

        public void setPrev(Node prev) {
            this.prev = prev;
        }
    }

}

