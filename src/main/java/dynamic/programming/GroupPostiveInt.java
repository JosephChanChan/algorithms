package dynamic.programming;

import java.io.*;

/**
 * 将一堆正整数分为2组，要求2组的和相差最小。
 *  例如：1 2 3 4 5，将1 2 4分为1组，3 5分为1组，两组和相差1，是所有方案中相差最少的。 整数个数n<=100，所有整数的和<=10000
 *  这个问题是0-1背包问题的变形，认识到一个问题，要求2组的和相差最小，即要求一组中的 和 = 总和 * 1/2
 *  那么另一组的和也是总和的二分之一。问题就可以转化成，在n个正数中挑选m个数划为一组，和不超过总和且尽量大。
 *  很明显，典型的0-1背包问题，在满足资源限制的条件下，最大化价值。
 *
 *  状态：
 *  设 f(i,j) 为前i个数中选出的数，和不超过j的最大和。f(i,j) <= j
 *  资源就是j，j<=总和 * 1/2
 *  既然是从前i个数中选取一些数组成一组，使和最接近于j，那么对于第i个数，取不取？
 *  如果不取则 f(i,j) = f(i-1,j)
 *  取则  f(i,j) = f(i-1,j-ai)+ai
 *  要求和不超过j的最大和，f(i,j) = max{ f(i-1,j) , f(i-1,j-ai)+ai }
 *  边界：
 *  n=0，f(0,j)=0
 *  j=0，f(n,0)=0
 *  n>=1，j<ai，f(i,j) = f(i-1,j)
 *  n>=1，j>=ai，f(i,j) = f(i-1,j-ai)+ai
 *  状态转移方程：
 *  f(i,j) = max{ f(i-1,j) , f(i-1,j-ai)+ai }
 *
 *  Created by Joseph on 2017/8/19.
 */
public class GroupPostiveInt {

    static int[] a = null;
    static Node[][] val = null;
    public static void main(String[] args){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in),1<<16);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out), 1 << 16);
        int n=0,m=0,sum=0,k=0,y=0;
        try {
            n = Integer.parseInt(reader.readLine());
            a = new int[n];
            for(int i=0; i<n; i++){
                a[i] = Integer.parseInt(reader.readLine());
                sum += a[i];
            }

            k = sum >> 1;
            val = new Node[n][k];

            Node node = null;
            Node track = null;
            for(int i=0; i<n; i++){
                for(int j=1; j<=k; j++){
                    node = new Node();
                    if(j<a[i]){
                        //n>=1，j<ai，f(i,j) = f(i-1,j)
                        node.setVal(getVi(i-1,j));
                        node.setSelfJoin(false);
                    }else{
                        //n>=1，j>=ai，f(i,j) = max{ f(i-1,j) , f(i-1,j-ai)+ai }
                        if(getVi(i-1,j) >= getVi(i-1,j-a[i])+a[i]){
                            node.setVal(getVi(i-1,j));
                            node.setSelfJoin(false);
                            node.setPrevious(getNode(i-1,j));
                        }else{
                            node.setVal(getVi(i-1,j-a[i])+a[i]);
                            node.setSelfJoin(true);
                            node.setSelf(a[i]);
                            node.setPrevious(getNode(i-1,j-a[i]));
                            track = node;
                        }
                    }
                    val[i][j-1] = node;
                }
            }

//            for(int i=0; i<n; i++){
//                for(int j=0; j<k; j++){
//                    System.out.print(val[i][j].getVal()+" ");
//                }
//                System.out.println();
//            }

//            while (null != track){
//                if(track.isSelfJoin())
//                    System.out.print(track.getSelf()+" ");
//                track = track.getPrevious();
//            }
//            System.out.println();

            writer.write((sum-(val[n-1][k-1].getVal()))-val[n-1][k-1].getVal()+"\r\n");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int getVi(int i,int j){
        if(i<0 || j<1){
            return 0;
        }
        return val[i][j-1].getVal();
    }

    private static Node getNode(int i,int j){
        if(i<0 || j<1){
            return null;
        }
        return val[i][j-1];
    }

    private static class Node{
        private Node previous;
        private boolean selfJoin;
        private int self;
        private int val;

        public Node getPrevious() {
            return previous;
        }

        public void setPrevious(Node previous) {
            this.previous = previous;
        }

        public boolean isSelfJoin() {
            return selfJoin;
        }

        public void setSelfJoin(boolean selfJoin) {
            this.selfJoin = selfJoin;
        }

        public int getVal() {
            return val;
        }

        public void setVal(int val) {
            this.val = val;
        }

        public int getSelf() {
            return self;
        }

        public void setSelf(int self) {
            this.self = self;
        }
    }
}