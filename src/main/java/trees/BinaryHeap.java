package trees;

import sort.CheckSortedArr;

import java.util.Random;

/**
 * 二叉堆(优先队列)
 * 需要搞明白堆这种数据结构的一般实现, 堆序性质, 建立堆的过程,
 * 插入, 删除, 下虑算法, 上滤算法, 以及堆的一般应用, 堆排序算法等...
 * 对于二叉堆一般实现, 上滤和下滤算法是最重要的...
 *
 * Created by Joseph at 2018/4/14 0014 17:09.
 */
public class BinaryHeap {

    /** 堆实际存储元素的数组 */
    private static Integer[] nodeArray;

    /** 堆中当前元素数量 */
    private static int currentSize = 0;

    /** 默认堆数组初始化量 */
    private static final int DEFAULT_CAPACITY = 100;

    public BinaryHeap(){
        nodeArray = new Integer[ (DEFAULT_CAPACITY << 1) + 1 ];
    }

    public BinaryHeap(Integer[] array){
        if ( array.length <= 0 ){
            throw new IllegalArgumentException(" The array must have datum !");
        }
        buildHeap(array);
    }

    private void buildHeap(Integer[] array){
        nodeArray = new Integer[ (array.length << 1) ];
        for ( int i : array ){
            insert(i);
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
    public void insert(int nodeValue){
        if ( currentSize >= nodeArray.length-1 ){
            expandHeap();
        }
        nodeArray[0] = nodeValue;
        int hole = percolateUp( ++currentSize );
        nodeArray[hole] = nodeValue;
    }

    /**
     * 删除堆的最小元，并返回最小元
     *
     * @return minimum element of heap
     */
    public Integer deleteMin(){
        int min = nodeArray[1];
        percolateDown(1);
        return min;
    }

    /**
     * 返回堆的元素数量
     *
     * @return the size of heap
     */
    public int heapSize(){
        return currentSize;
    }

    /**
     * 上滤.
     * 在下一个可用位置处建立空穴,
     * 若元素可直接插入空穴则插入完成, 否则向上寻找可插入的空穴.
     *
     * @param hole 空穴
     * @return 可插入的位置
     */
    private int percolateUp(int hole){
        int beInsert = nodeArray[0];
        while ( nodeArray[ hole >> 1 ] > beInsert ){
            nodeArray[hole] = nodeArray[hole >> 1];
            hole = hole >> 1;
        }
        return hole;
    }

    /**
     * 下滤.
     * 删除根处最小元素, 此时根处建立空穴, 需要将队列最后一个元素重新安排一个位置插入.
     * 若可以插入空穴则下滤完成, 否则需要将空穴向下移动寻找合适的位置.
     *
     * @param hole 空穴
     */
    private void percolateDown(int hole){
        int adjusted = nodeArray[currentSize];

        // the hole first time always be one

        int leftIndex = hole << 1;
        int rightIndex = leftIndex + 1;

        Integer leftVi = nodeArray[leftIndex];
        Integer rightVi = nodeArray[rightIndex];

        /*
            if adjusted > leftVi or adjusted > rightVi
            the loop will continue until that find a appropriate hole
         */
        while ( null != leftVi && leftVi < adjusted ||
                null != rightVi && rightVi < adjusted ){
            if (null == rightVi){
                nodeArray[hole] = nodeArray[leftIndex];
                hole = leftIndex;
            }
            else {
                int minMore = leftVi > rightVi ? rightIndex : leftIndex;

                nodeArray[hole] = nodeArray[minMore];
                hole = minMore;
            }

            // calculate the left and right index
            leftIndex = hole << 1;
            rightIndex = leftIndex + 1;
            if ( leftIndex < nodeArray.length ){
                leftVi = nodeArray[leftIndex];
            }
            else {
                leftVi = null;
            }
            if ( rightIndex < nodeArray.length ){
                rightVi = nodeArray[rightIndex];
            }
            else {
                rightVi = null;
            }
        }

        nodeArray[hole] = adjusted;
        nodeArray[currentSize--] = null;
    }

    /**
     * 对二叉堆进行扩容.
     * 目前处理就只是将数组扩大至原来 2 倍
     */
    private void expandHeap(){
        System.out.println(" 队列开始扩容!! ");
        Integer[] newPriorityQueue = new Integer[nodeArray.length << 1];
        for (int i = 1; i < nodeArray.length; i++){
            if (null == nodeArray[i]){
                break;
            }
            newPriorityQueue[i] = nodeArray[i];
        }
        // 扩容完毕后, 让旧数组随风飘扬
        nodeArray = newPriorityQueue;
    }

    /**
     * 根据堆序性质检查堆是否正确.
     * 算法思想:
     * 从根出发检查队列中所有父节点的左右节点是否大于等于父节点.
     * 当队列中的每一个父节点被检查完后则认为该队列是正确的.
     * 算法很简单, 但是操作数组很麻烦.
     *
     * @param heap 优先队列
     * @return 0/1
     */
    private static boolean checkHeap(Integer[] heap){
        /*
            利用堆序性质:
            1. 对于队列中任意 i 节点, 其左右节点分别为 2i, 2i+1
            2. 对于队列中任意 i 节点的父节点为 i/2
            3. 对于树中任意节点其都小于等于该节点的所有孩子节点
         */
        Integer rootIndex = 1,
                leftIndex = rootIndex << 1,
                rightIndex = leftIndex + 1,

                rootVi = heap[rootIndex], leftVi = heap[leftIndex], rightVi = heap[rightIndex];

        while ( null != rootVi  &&
                null != leftVi  || null != rightVi ){


            if ( null != leftVi && rootVi > leftVi ){
                return false;
            }

            if ( null != rightVi && rootVi > rightVi ){
                return false;
            }

            // print value
            System.out.println(String.format(" root vi = %s, left vi = %s, right vi = %s", rootVi, leftVi, rightVi));

            // calculate the index
            if ( ++rootIndex < heap.length ){
                rootVi = heap[rootIndex];
            }
            else {
                rootVi = null;
            }

            leftIndex = rootIndex << 1;
            if ( leftIndex < heap.length ){
                leftVi = heap[leftIndex];
            }
            else {
                leftVi = null;
            }

            rightIndex = leftIndex + 1;
            if ( rightIndex < heap.length ){
                rightVi = heap[rightIndex];
            }
            else {
                rightVi = null;
            }
        }

        return true;
    }

    public static void main(String[] args){

        Integer[] test = new Integer[12];
        Random random = new Random();
        for ( int k = 0; k < test.length; k++ ){
            int t = random.nextInt(100);
            test[k] = t;
        }

        BinaryHeap binaryHeap = new BinaryHeap(test);

//        for ( Integer k : nodeArray){
//            System.out.print( k +" " );
//        }
//
//        System.out.println();

//        for ( int j = 0; j < currentSize; j++){
//            int i = binaryHeap.deleteMin();
//            System.out.print(i + " ");
//        }

//        Integer[] test = new Integer[]{0,13,14,16,19,21,19,68,65,26,32};
//        boolean falg = checkHeap(nodeArray);
//        System.out.println(falg);

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

        System.out.println("原始队列长度: " + nodeArray.length);
        System.out.println("测试扩容, 在填满队列后继续插入10个元素 ");

        // 差值
        int diff = nodeArray.length - currentSize;
        diff += 10;
        while ( diff-- > 0 ){
            int r = random.nextInt(100);
            binaryHeap.insert(r);
        }

        System.out.println("扩容后序列 : ");
        sortedArray = new Integer[currentSize];
        int i = 0;
        while (currentSize > 0){
            sortedArray[i] = binaryHeap.deleteMin();
            System.out.print(sortedArray[i++] + " ");
        }
        System.out.println("检测数组正序 : ");
        System.out.println(CheckSortedArr.checkAsc(sortedArray));
    }

}
