package trees;

/**
 * 剑指Offer 40 easy
 *
 * TopK问题，经典。
 *
 * 时间复杂度：TopK堆 O(n*logk)，位数组 O(n)
 * 空间复杂度：TopK堆 O(k) 位数组 O(max(a[i]))
 *
 * @author Joseph
 * @since 2020-12-19 21:42
 */
public class TopMinimumK {

    int[] heap = null;
    int count = 0;

    public int[] getLeastNumbers(int[] arr, int k) {
        if (arr.length == 0 || k == 0) return new int[0];
        if (arr.length == k) return arr;

        return usingHeap(arr, k);
    }

    private int[] usingHeap(int[] arr, int k) {
        /*
            维持一个k大小的最大堆。
            遍历arr数组插入堆中。如果a[i]小于堆顶最大元素，淘汰堆顶，换入a[i]。
            再从堆顶下滤调整堆序。
            遍历完arr数组后，最大堆中保存的自然是最小的k个元素。
        */
        heap = new int[k+1];
        buildHeap(arr, k);
        for (int i = k; i < arr.length; i++) {
            insert(arr[i]);
        }
        int[] ans = new int[k];
        for (int i = 1; i <= count; i++) {
            ans[i-1] = heap[i];
        }
        return ans;
    }

    private void insert(int e) {
        if (e < heap[1]) {
            heap[1] = e;
            percolateDown(1);
        }
    }

    private void buildHeap(int[] arr, int k) {
        /*
            从arr选出k个放入heap。
            从最后一个非叶节点 count/2 开始下滤调整堆
        */
        for (int i = 0; i < k; i++) {
            count++;
            heap[i+1] = arr[i];
        }
        for (int hole = count >> 1; hole > 0; hole--) {
            percolateDown(hole);
        }
    }

    private void percolateDown(int hole) {
        /*
            从指定的位置处下滤。
            下滤到什么位置停止？只要hole的左右子节点还在堆有效范围内就应该比较。
            只要下滤的元素大于等于左右子节点，此时的hole就满足堆序。
            否则根据堆序性质，目前是最大堆，选出左右子节点较大的一个升入hole
        */
        int e = heap[hole], child ;
        for ( ; (hole << 1) <= count; hole = child) {
            child = hole << 1;
            // 试探是否有右节点并选出左右节点中较大的
            if (child < count && heap[child] < heap[child+1]) {
                child++;
            }
            if (e >= heap[child]) break;
            heap[hole] = heap[child];
        }
        heap[hole] = e;
    }

    private int[] bitArray(int[] a, int k) {
        // 得益于a[i]的范围，最大10000可用位数组思想。时间O(n)
        int[] ans = new int[k];
        int[] container = new int[10001];
        for (int i = 0; i < a.length; i++) {
            container[a[i]]++;
        }
        for (int i = 0, j = 0; i < container.length; i++) {
            if (container[i] > 0) {
                int c = container[i];
                for (int t = 0; t < c && j < k; t++) {
                    ans[j++] = i;
                }
            }
            if (j == k) break;
        }
        return ans;
    }

}
