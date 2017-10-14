package main.java.sort;

/**
 * Created by Joseph on 2017/6/28.
 */
public class CheckSortedArr {

    public static boolean checkAsc(int[] arr){
        int k = arr[0];
        for(int i=1; i<arr.length; i++){
            if(k > arr[i]) {
                return false;
            }else{
                k = arr[i];
            }
        }
        return true;
    }
}
