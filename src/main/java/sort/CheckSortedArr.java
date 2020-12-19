package sort;

import java.util.Random;

/**
 * Created by Joseph on 2017/6/28.
 */
public class CheckSortedArr {

    /**
     * 检查 T 数组是否升序,
     * 若是则返回-1,
     * 否则将返回数组第一个出现问题的下标
     *
     * @param arr 待检查数组
     * @return -1 or problem index
     */
    public static <T extends Comparable<? super T>> int checkAsc(T[] arr){
        T k = arr[0];

        for(int i = 1; i < arr.length; i++){
            T ai = arr[i];
            if(k.compareTo(ai) == 1) {
                return --i;
            }
            else {
                k = arr[i];
            }
        }
        return -1;
    }

    /**
     * 检查 int 数组是否升序,
     * 若是则返回-1,
     * 否则将返回数组第一个出现问题的下标
     *
     * @param arr 待检查数组
     * @return -1 or problem index
     */
    public static int checkIntAsc(int[] arr){
        int k = arr[0];

        for(int i = 1; i < arr.length; i++){
            if(k > arr[i]) {
                return --i;
            }
            else {
                k = arr[i];
            }
        }
        return -1;
    }

    public static void printIntArr(int[] arr){
        for (int i = 0; i < arr.length; i++){
            System.out.printf(arr[i] + " ");
        }
    }

    public static int[] buildArray(int n) {
        int[] a = new int[n];
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            a[i] = random.nextInt(n);
        }
        return a;
    }
}
