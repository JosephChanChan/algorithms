package backtracking.algorithm;

/**
 * 全排列问题。
 * 某次面试时的笔试题.
 * 给定某个序列 S[Si Si+1 Si+2 ... Sn] 输出其序列的每一种排列.
 * 本算法采用递归实现. 所以会出现重复排列情况.
 *
 *
 * Created by Joseph on 2017/6/18.
 */
public class FullPermutation {

    static int[] arr = {1,2,3};
    static int count = 0;

    public static void main(String[] args){
        permutation(arr, 0, arr.length);
        System.out.println(count);
    }

    private static void permutation(int[] arr, int i, int k){
        if (i == k - 1){
            for (int l = 0; l < arr.length; l++){
                System.out.print(arr[l]+" ");
            }
            System.out.println();
            count++;
        }
        else {
            for (int h = i; h < k; h++){
                // 固定某个开头递归求解在第h位以该开头的字母的全排列
                if(i != h) {
                    swap(arr, i, h);
                }
                // 递归求后续的全排列
                permutation(arr,i + 1, k);
                // 递归完毕后，回溯这次交换
                if(i != h){
                    swap(arr, i, h);
                }
            }
        }
    }

    private static void swap(int[] arr,int i,int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
