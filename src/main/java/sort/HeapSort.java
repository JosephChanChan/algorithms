package sort;

import com.sun.media.jfxmediaimpl.HostUtils;

/**
 * 堆排序模板
 * 演示升序排序
 *
 * 时间复杂度：O(nlogn)
 * 空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2020-12-19 22:11
 */
public class HeapSort {

    static int[] heap ;

    public static void main(String[] args) {
        HeapSort test = new HeapSort();

        int errorIdx = 0;
        boolean allCorrect = true;
        for (int i = 0; i < 1000000; i++) {
            int[] a = CheckSortedArr.buildArray(100);
            test.heapSort(a);
            if (-1 != (errorIdx = CheckSortedArr.checkIntAsc(heap))) {
                allCorrect = false;
                System.out.println("errorIdx="+errorIdx);
                CheckSortedArr.printIntArr(heap);
                break;
            }
        }
        if (allCorrect) {
            System.out.println("running 1000000 test case, all is correct");
        }
    }

    private void heapSort(int[] a) {
        heap = a;
        if (a.length <= 1) return;
        buildHeap();

        /*
            把堆顶和堆最后元素依次交换，每交换一次缩小堆
         */
        for (int i = heap.length-1; i > 0; i--) {
            int max = heap[0];
            heap[0] = heap[i];
            heap[i] = max;
            percolateDown(0, i);
        }
    }

    private void buildHeap() {
        int count = heap.length;
        for (int i = (count >> 1) - 1; i >= 0; i--) {
            percolateDown(i, count);
        }
    }

    private void percolateDown(int hole, int count) {
        int e = heap[hole], child ;
        for ( ; (hole << 1) + 1 < count; hole = child) {
            child = (hole << 1) + 1;
            if (child < count-1 && heap[child] < heap[child+1]) {
                child++;
            }
            if (e >= heap[child]) break;
            heap[hole] = heap[child];
        }
        heap[hole] = e;
    }
}
