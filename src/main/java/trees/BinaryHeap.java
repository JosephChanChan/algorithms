package main.java.trees;

import java.util.Random;

/**
 * 二叉堆(优先队列)
 * 需要搞明白堆这种数据结构的一般实现, 堆序性质, 建立堆的过程,
 * 插入, 删除, 下虑算法, 上滤算法, 以及堆的一般应用, 堆排序算法等...
 *
 * Created by Joseph at 2018/4/14 0014 17:09.
 */
public class BinaryHeap {

    /** 堆实际存储元素的数组 */
    private static int[] nodeArray;

    /** 堆中当前元素数量 */
    private static int currentSize = 0;

    /** 默认堆数组初始化量 */
    private static final int DEFAULT_CAPACITY = 100;

    public BinaryHeap(){
        nodeArray = new int[ (DEFAULT_CAPACITY << 1) + 1 ];
    }

    public BinaryHeap(int[] array){
        if ( array.length <= 0 ){
            throw new IllegalArgumentException(" The array must have datum !");
        }
        buildHeap(array);
    }

    private void buildHeap(int[] array){
        nodeArray = new int[ (array.length << 1) + 1 ];
        for ( int i : array ){
            insert(i);
        }
    }

    public void insert(int node){
        if ( currentSize >= nodeArray.length-1 ){
            expandHeap();
        }
        nodeArray[0] = node;
        int hole = percolateUp( ++currentSize );
        nodeArray[hole] = node;
    }

    public int deleteMin(){
        int min = nodeArray[1];
        percolateDown(1);
        return min;
    }

    /**
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

    private void percolateDown(int hole){
        int adjusted = nodeArray[currentSize];

        while ( (hole << 1) + 1 < nodeArray.length &&
                ( nodeArray[hole << 1] < adjusted || nodeArray[(hole << 1) + 1] < adjusted ) ){

            if ( nodeArray[hole << 1] > nodeArray[(hole << 1) + 1] ){
                nodeArray[hole] = nodeArray[(hole << 1) + 1];
                hole = (hole << 1) + 1;
            }
            else {
                nodeArray[hole] = nodeArray[hole << 1];
                hole = hole << 1;
            }
        }

        nodeArray[hole] = adjusted;
        nodeArray[currentSize--] = 0;
    }

    private void expandHeap(){

    }

    public static void main(String[] args){

        int[] test = new int[12];
        Random random = new Random();
        for ( int k = 0; k < test.length; k++ ){
            int t = random.nextInt(100);
            test[k] = t;
        }

        BinaryHeap binaryHeap = new BinaryHeap(test);

        for ( int k : nodeArray){
            System.out.print( k +" " );
        }

        System.out.println();

        for ( int j = 0; j < currentSize; j++){
            int i = binaryHeap.deleteMin();
            System.out.print(i + " ");
        }
    }

    /** 根据堆序性质检查堆是否正确 */
    private static boolean checkHeap(int[] heap){

    }

}
