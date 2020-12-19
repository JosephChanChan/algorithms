package trees;

import sort.CheckSortedArr;

import java.util.Random;

/**
 * 最小堆模板。
 * 需要搞明白堆这种数据结构的一般实现, 堆序性质, 建立堆的过程,
 * 插入, 删除, 下虑算法, 上滤算法, 以及堆的一般应用, 堆排序算法等...
 * 对于二叉堆一般实现, 上滤和下滤算法是最重要的...
 *
 * @author Joseph
 * @since 2020-12-19
 */
public class BinaryHeap {

    /** 堆实际存储元素的数组 */
    private Integer[] heap;

    /** 堆中当前元素数量 */
    private int count = 0;

    /** 默认堆数组初始化量 */
    private final int MINIMUM_CAPACITY = 32;
    private final int DEFAULT_CAPACITY = 128;

    public BinaryHeap(){
        heap = new Integer[DEFAULT_CAPACITY];
    }

    public BinaryHeap(int[] array) {
        if ( array.length <= 0 ){
            throw new IllegalArgumentException(" The array must have datum !");
        }
        buildHeap(array);
    }

    /**
     * 先构建一个无序堆。
     * 从最后一个父节点开始下滤调整每个小堆，直到根节点下滤完毕则整个堆是有序的。
     * 工业界已证明N个元素下滤平均时间O(n)
     *
     * @param array 原数组
     */
    private void buildHeap(int[] array) {
        heap = new Integer[Math.max(MINIMUM_CAPACITY, array.length)];
        for (int i = 0; i < array.length; i++, count++) {
            heap[i+1] = array[i];
        }
        for (int hole = count >> 1; hole > 0; hole--) {
            percolateDown(hole);
        }
    }

    /**
     * 在堆中寻找合适位置插入给定的元素。
     * 如果插入前堆已经到了数组临界值，将会先进行扩容。
     * 扩容长度是原数组的 2 倍长。扩容操作花费 O(N) 时间。
     * 插入操作花费 O(logN) 时间。
     *
     * @param nodeValue the element be insert
     */
    public void insert(int nodeValue) {
        if (count >= heap.length-1) {
            expandHeap();
        }
        percolateUp(nodeValue);
    }

    /**
     * 删除堆的最小元，并返回最小元。
     * 用堆的最后一个元素覆盖根处，从根处下滤调整堆。
     *
     * @return minimum element of heap
     */
    public int deleteMin() {
        int min = heap[1];
        // 堆的最后元覆盖根
        heap[1] = heap[count--];
        // 删除堆的最后元素
        heap[count+1] = null;
        // 从根处下滤调整堆序
        percolateDown(1);
        return min;
    }

    /**
     * 返回堆的元素数量
     *
     * @return the size of heap
     */
    public int heapSize(){
        return count;
    }

    /**
     * 上滤.
     * 在下一个可用位置处建立空穴,
     * 若元素可直接插入空穴则插入完成, 否则向上寻找可插入的空穴.
     * 上滤一直到根处做最后一次比较。所以将e放到根的上部结束循环，或hole上升到根处位置结束也可以。
     */
    private void percolateUp(int e) {
        heap[0] = e;
        int hole = ++count;
        for ( ; e < heap[hole >> 1]; hole = hole >> 1) {
            heap[hole] = heap[hole >> 1];
        }
        heap[hole] = e;
    }

    /**
     * 下滤.
     * 删除根处最小元素, 此时根处建立空穴, 需要将队列最后一个元素重新安排一个位置插入.
     * 若可以插入空穴则下滤完成, 否则需要将空穴向下移动寻找合适的位置.
     *
     * @param hole 空穴
     */
    private void percolateDown(int hole) {
        int e = heap[hole], child ;
        for (; (hole << 1) <= count; hole = child) {
            child = hole << 1;
            if (child < count && heap[child] > heap[child+1]) {
                child++;
            }
            if (e <= heap[child]) break;
            heap[hole] = heap[child];
        }
        heap[hole] = e;
    }

    /**
     * 对二叉堆进行扩容.
     * 目前处理就只是将数组扩大至原来 2 倍
     */
    private void expandHeap(){
        Integer[] newHeap = new Integer[heap.length << 1];
        if (count >= 0) {
            System.arraycopy(heap, 1, newHeap, 1, count);
        }
        // 扩容完毕后, 让旧数组随风飘扬
        heap = newHeap;
    }




    public static void main(String[] args){

        int[] test = new int[10];
        Random random = new Random();
        for ( int k = 0; k < test.length; k++ ){
            int t = random.nextInt(100);
            test[k] = t;
        }

        BinaryHeap binaryHeap = new BinaryHeap(test);

        System.out.println("原始序列 : ");
        for (int i = 0; i < test.length; i++){
            System.out.print(test[i] + " ");
        }
        System.out.println();
        System.out.println("堆排序 : ");
        Integer[] sortedArray = new Integer[test.length];
        for (int i = 0; i < sortedArray.length; i++){
            sortedArray[i] = binaryHeap.deleteMin();
            System.out.print(sortedArray[i] + " ");
        }
        System.out.println();
        System.out.println("检测数组正序 : ");
        System.out.println(CheckSortedArr.checkAsc(sortedArray));

        System.out.println("原始队列长度: " + binaryHeap.heap.length);
        System.out.println("测试扩容, 在填满队列后继续插入k个元素 ");

        // 差值
        int diff = binaryHeap.heap.length - binaryHeap.count;
        diff += 10;
        while ( diff-- > 0 ){
            int r = random.nextInt(100);
            binaryHeap.insert(r);
        }

        System.out.println("扩容后堆排序序列 : ");
        sortedArray = new Integer[binaryHeap.count];
        int i = 0;
        while (binaryHeap.count > 0){
            sortedArray[i] = binaryHeap.deleteMin();
            System.out.print(sortedArray[i++] + " ");
        }
        System.out.println("检测数组正序 : ");
        System.out.println(CheckSortedArr.checkAsc(sortedArray));
    }

}
