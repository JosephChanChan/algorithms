package sort;

/**
 * 归并排序，对于一般的实现（未经优化），是与快速排序媲美的排序算法。
 *
 * 算法思想：
 *  采用分治递归的思想，将大问题转化小问题，直至小问题不需要计算即可解决。
 *  还有一个重要点是，归并排序一个重要步骤将 2 个有序的数组合并成一个有序的数组。
 *  将以上 2 点结合，将大数组二等分 递归分治... 直至每个小数组不需继续递归（即每个小数组只有一个元素）
 *  将左右 2 部分有序的小数组合并成一个有序的稍大的数组。当递归栈依次结束时，每次都有小数组被合并成有序的大数组。
 *  算法结束时，数组将有序。
 *
 * 时间复杂度：O(N logN)
 * 空间复杂度：O(N)
 *
 * created by Joseph
 * at 2018/8/31 0:26
 */
public class MergeSort {

    static int[] array = {16, 5, 47, 8, 35, 56, 31, 27, 46, 3, 28, 6, 13};

    public static void main(String[] args) {
        array = mergeSort(array);

        int result = CheckSortedArr.checkIntAsc(array);
        System.out.println(result);
    }

    public static int[] mergeSort(int[] beSorted){
        return mergeSort(beSorted, 0, beSorted.length - 1);
    }

    /**
     * 分治递归算法，将大数组不断二等分分割小数组，直至小数组有序的，然后合并左右 2 部分子数组。
     *
     * @param beSorted  原数组
     * @param left      开始位
     * @param right     结束位
     */
    private static int[] mergeSort(int[] beSorted, int left, int right){
        // means the sub array has elements to be sorted
        if (left < right){
            int center = (left + right) >> 1;
            // continue partition sub array until the array is sorted
            int[] leftSorted = mergeSort(beSorted, left, center);
            int[] rightSorted = mergeSort(beSorted, center + 1, right);
            // both sub array is sorted, just merge them
            return merge(leftSorted, rightSorted);
        }
        return new int[]{beSorted[left]};
    }

    /**
     * 左指针会和右指针比较，较小者存入临时数组中，然后指针继续移动下一个。
     * 直到算法将所有元素合并完。
     *
     * @param leftSorted    左侧有序数组
     * @param rightSorted   右侧有序数组
     * @return int[]        新的有序数组
     */
    private static int[] merge(int[] leftSorted, int[] rightSorted){
        int leftPos = 0, rightPos = 0, pos = 0;
        int[] sorted = new int[leftSorted.length + rightSorted.length];

        while (leftPos < leftSorted.length &&
                rightPos < rightSorted.length) {
            if (leftSorted[leftPos] > rightSorted[rightPos]) {
                sorted[pos++] = rightSorted[rightPos++];
            }
            else {
                sorted[pos++] = leftSorted[leftPos++];
            }
        }

        if (leftPos == leftSorted.length) {
            while (rightPos < rightSorted.length) {
                sorted[pos++] = rightSorted[rightPos++];
            }
        }
        else {
            while (leftPos < leftSorted.length) {
                sorted[pos++] = leftSorted[leftPos++];
            }
        }

        return sorted;
    }
}
