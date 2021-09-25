package sort;

/**
 * lc 912 medium
 *
 * 快速排序，如同名称一样，对于一般实现，它是比较快的一种排序算法。
 * 平均算法时间 O(N*logN)，上界 O(n^2)。
 * 快速排序有很多个实现版本，选择这种理解记忆就好，这种是令狐冲推荐的比较好的版本。
 *
 * 算法思想：
 * 该版本的快排是将数组分为左右两部分，左边<=p，右边>=p，没有将p放在中间分割整体，
 * 而是以l r交错的地方分割递归。
 * partition之后，l和r必定是交错的，start~r部分是<=p，l~end部分是>=p，也就是左边<=右边，所以整体上看已经有序。
 * 递归处理 start~r
 * 递归处理 l~end
 *
 * 有3个点需要注意：
 * 1.中轴p的选择，p不建议选开头/结尾，如果数组本身有序，会导致一趟partition后，递归处理n-1规模的问题，构造出n^2最坏情况
 * 2.对于l r遇到等于p的元素处理，遇到等于p的元素也交换，这样可以使得左右两边均等长，使每次递归处理接近一半的规模
 * 3.while循环中 l<=r 不是 l<r，避免左右递归出现交集情况，交集情况会出现stack over
 *
 * Created by Joseph on 2017/6/10.
 */
public class QuickSort {

    public static void main(String[] args){
        int[] a = new int[]{4,3,9,7,6,5};
        quickSort(0, a.length-1, a);
        for (Integer integer : a){
            System.out.print(integer + " ");
        }
    }

    static void quickSort(int start, int end, int[] a) {
        // start > end is wrong and start == end only one that needn't sort
        if (start >= end) return;
        // notice1 pivot chosen mid is better
        // p is value not index cause the value that p pointer may change
        int l = start, r = end, p = a[(l+r)>>1];

        // notice2 l <= r not l < r
        while (l <= r) {
            // notice 3 a[l] < p not <=
            while (l <= r && a[l] < p) l++;
            while (l <= r && a[r] > p) r--;
            if (l <= r) {
                int t = a[l];
                a[l] = a[r];
                a[r] = t;
                l++; r--;
            }
        }
        // r must > l here
        quickSort(start, r, a);
        quickSort(l, end, a);
    }
}
