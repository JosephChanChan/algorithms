package sort;

import java.util.Random;

/**
 * 快速排序，如同名称一样，对于一般实现，它是比较快的一种排序算法。
 * 平均算法时间 O(N logN)，上界 O(n^2)。
 *
 * 算法思想：
 *  和归并排序相似，都是采用递归分而治之的算法。
 *  选出一个基准数（一般选择数组头元素），用变量记录下来。
 *  2个指针分别指向左开头与右结尾，右指针向左遍历，只留下大于等于基准数的元素，直到遇到一个小于基准数的元素停止。
 *  接下来左指针向右遍历，只留下小于等于基准数的元素，直到遇到一个大于基准数的元素停止。
 *  将右指针与左指针的元素互换，重复此过程直到 左指针 = 右指针。此时将基准数覆盖至此位置。算法完成一轮排序。
 *  此时的数组将会是这样的情况，基准数左边全部小于等于它，右边则全部大于等于它。
 *  以基准数为中间界，递归左部分和右部分，重复以上步骤。递归结束后数组将有序。
 *
 *  PS：本实例的过程与上述稍做了点改动，在右指针与左指针遍历部分，但是整体思想与上述一致。
 *
 * Created by Joseph on 2017/6/10.
 */
public class QuickSort {

    static int[] arr = {5,8,3,7,1,6,4,9,2};

    public static void main(String[] args){
        /*Random random = new Random();
        for(int j=0; j<100; j++){
            arr[j] = random.nextInt(100);
        }

        doQuickSort(arr,0,arr.length-1);

        int flag = CheckSortedArr.checkAsc(arr);

        if(flag != -1) {
            System.out.println("排序有误!");
        }
        else {
            System.out.println("排序正确");
        }

        for(int i : arr){
            System.out.print(i+" ");
        }*/

        /*doQuickSortDesc(arr,0, arr.length-1);*/
        doQuickSort(arr, 0, arr.length - 1);

        /*int flag = CheckSortedArr.checkIntAsc(arr);
        if (flag != -1){
            System.out.println("数组排序有误! "+flag);
        }
        else {
            System.out.println("数组排序成功!");
        }*/

        for (Integer integer : arr){
            System.out.print(integer + " ");
        }
    }

    /** 针对基本数据类型 int 服务 */
    public static void doQuickSort(int[] arr, int left, int right){
        if(left >= right) return;

        int base = arr[left];

        int i = quickSort(arr, base, left, right);

        doQuickSort(arr, left,i - 1);
        doQuickSort(arr, i + 1, right);
    }
    private static int quickSort(int[] arr, int base, int left, int right){
        while (left < right){
            while (left < right && arr[right] >= base){
                right--;
            }
            arr[left] = arr[right];
            while (left < right && arr[left] <= base){
                left++;
            }
            arr[right] = arr[left];
        }
        arr[left] = base;
        return left;
    }

    /** 针对基本数据类型 int 服务. 降序排序 */
    public static void doQuickSortDesc(int[] arr, int left, int right){
        if(left >= right){
            return;
        }

        int base = arr[left];

        int i = quickSortDesc(arr, base, left, right);

        doQuickSortDesc(arr,left,i-1);
        doQuickSortDesc(arr,i+1,right);
    }
    private static int quickSortDesc(int[] arr, int base, int left, int right){
        while (left < right){
            while (left < right && arr[right] <= base){
                right--;
            }
            arr[left] = arr[right];
            while (left < right && arr[left] >= base){
                left++;
            }
            arr[right] = arr[left];
        }
        arr[left] = base;
        return left;
    }

    /** 针对实现 Comparable 可排序接口的泛型服务 */
    public static <T extends Comparable<? super T>> void doQuickSort(T[] arr,int left,int right){
        if(left >= right){
            return;
        }

        T base = arr[left];

        int i = quickSort(arr, base, left, right);

        doQuickSort(arr,left,i-1);
        doQuickSort(arr,i+1,right);
    }
    private static <T extends Comparable<? super T>> int quickSort(T[] arr, T base, int left, int right){
        while (left < right){
            while (left < right && arr[right].compareTo(base) >= 0){
                right--;
            }
            arr[left] = arr[right];
            while (left < right && arr[left].compareTo(base) <= 0){
                left++;
            }
            arr[right] = arr[left];
        }
        arr[left] = base;
        return left;
    }
}
