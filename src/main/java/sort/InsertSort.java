package main.java.sort;

import java.util.Random;

/**
 * 插入排序算法, 标准版, 以升序为例.
 *  算法思想:
 *      假设前 p 位都是已排好序的, 则遍历到的当前位置 j 的元素为 k. 向左遍历,
 *   寻找第一个比 k 小的元素的位置记为 x. 此时 x+1 便是 k 要插入的位置. 当遍历完数组,
 *   就完成了排序.
 *
 *  时间复杂度:
 *      分析嵌套循环的代码需要执行的次数. 可发现每次最坏情况下执行j次,
 *    对j求和 sigma(j,1,n) = n^2. 所以最坏时间复杂度是2的次方.
 *
 * Created by Joseph on 2017/6/14.
 */
public class InsertSort {

    static int[] arr = {5,4,6,1,3,8,7,9,2};
//    static int[] arr = new int[10];

    public static void main(String[] args){

//        Random random = new Random();
//        for(int a = 0; a < 10; a++){
//            arr[a] = random.nextInt(10);
//        }

        // i从1开始, 跳过第1个无谓排序
        for (int i = 1; i <= arr.length - 1; i++){
            int temp = arr[i], j = i - 1;
            // 向左寻找第1个小于temp的位置
            for ( ; j >= 0 && arr[j] > temp; j--){
                arr[j+1] = arr[j];
            }
            // 此时的j+1位置就是a[i]的合适位置
            arr[j+1] = temp;
        }

        int flag = CheckSortedArr.checkIntAsc(arr);

        if(flag != -1) {
            System.out.println("排序失败，关键位置:" + flag);

            CheckSortedArr.printIntArr(arr);
        }
        else
            System.out.println("排序成功");

    }
}
