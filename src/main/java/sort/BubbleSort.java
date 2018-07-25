package sort;

import java.util.Random;

/**
 * Created by Joseph on 2017/6/28.
 */
public class BubbleSort {

    private static int[] arr = {7,5,1,6,4,9,3,2,8};
    private static int[] tests = new int[100];

    public static void main(String[] args){
        Random random = new Random();
        for(int k=0; k<100; k++){
            tests[k] = random.nextInt(100);
        }

        int temp;
        for(int i=0; i<tests.length-1; i++){
            for(int j=0; j<tests.length-1-i; j++){
                if(tests[j]>tests[j+1]){
                    temp = tests[j];
                    tests[j] = tests[j+1];
                    tests[j+1] = temp;
                }
            }
        }

        int flag = CheckSortedArr.checkIntAsc(tests);

        if(flag != -1) {
            System.out.println("排序有误!");
        }
        else {
            System.out.println("排序正确");
        }

        for(int i : tests){
            System.out.print(i+" ");
        }
    }
}
