package main.java;

/**
 * Created by Joseph on 2017/6/15.
 */
public class FibonacciSequence {

    /* 1 1 2 3 5 8 13 21 34 55 89 144*/

    public static void main(String[] args){
        int pre=1,pre2=1,sum=0;
        for(int i=0; i<5; i++){
            sum = pre + pre2;
            pre = pre2;
            pre2 = sum;
        }
        System.out.println(sum);
    }
}
