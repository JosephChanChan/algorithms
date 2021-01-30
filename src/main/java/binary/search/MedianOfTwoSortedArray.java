package binary.search;

/**
 * lc 4 hard
 *
 * Analysis:
 *  在A&B数组中找中位数，偶数个数就是n/2 n/2+1，奇数个数就是n/2+1
 * n/2就意味着A&B数组中有n/2个数小于等于它，这里设定比较巧妙，包含了等于中位数的数，就意味着中位数自己也被算进去
 * 例如：1 2 3 4 5，中位数是3，这个数组中就有1 2 3小于等于它。之所以要包含等于的关系，是元素可能重复
 * 如 1 3 3 4 5，小于3的数只有1，对后面的编码不利。
 * 总体思想就是二分答案范围。设两个数组共有n个数，最小值min和最大值max，数组元素必定在[min~max]间。
 * n个数的中位数，假设n是奇数，则中位数k=n/2+1，又由于k必定在[min~max]间，只要二分[min~max]找到一个数mid
 * mid在两个数组大于等于的数个数为p。如果p > k，则mid太大，缩小二分范围[min~mid]。
 * 如果p < k，则mid太小，缩小二分范围[mid~max]。如果p == k，如果mid在A&B中存在则mid就是答案，但是mid不一定在数组中存在。
 * 那就继续缩小二分范围[min~mid]，始终向k靠近就行了。
 * 例如：A [1,2,3,4,5] B [7,9,13] k=n/2+1=5，二分过程如下：
 * [1~13] mid=6，mid在A&B中大于等于5个数，k的设定也是大于等于5个数，但6不在数组中。
 * [1~6] mid=3 < 5
 * [3~6] mid=4 < 5
 * [4~6] mid=5 == 5，代表mid >= 真正的中位数，向左寻找
 * [4~5] break
 * 通过以上不断缩小二分范围，还是可以向真正的k靠近
 *
 * 时间复杂度：O(range * (log(A.length) + log(B.length)))
 * 空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2021-01-30 00:50
 */
public class MedianOfTwoSortedArray {

    int[] A, B ;
    int n ;

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        A = nums1; B = nums2;
        n = A.length + B.length;

        if (n % 2 != 0) {
            return findMedian(n/2 + 1);
        }
        return (findMedian(n/2) + findMedian(n/2 + 1)) / 2.0d;
    }

    private int findMedian(int k) {
        if (A.length == 0) return B[k-1];
        if (B.length == 0) return A[k-1];

        // k的定义：在两个数组中，大于等于k个数的数，在这里就是大于等于一半的数那不就是中位数吗
        int min = Math.min(A[0], B[0]), max = Math.max(A[A.length-1], B[B.length-1]), mid ;
        while (min + 1 < max) {
            mid = (min + max) >> 1;
            // 看mid在A&B中大于等于多少个数
            int p = countSmallerAndEq(A, mid) + countSmallerAndEq(B, mid);
            if (p > k) {
                max = mid;
            }
            else if (p < k) {
                min = mid;
            }
            else {
                max = mid;
            }
        }
        if (countSmallerAndEq(A, min) + countSmallerAndEq(B, min) >= k) return min;
        return max;
    }
    private int countSmallerAndEq(int[] a, int num) {
        // 找到大于num的那个数的下标就是num大于等于的数的个数
        int l = 0, r = a.length-1, m ;
        while (l + 1 < r) {
            m = (l + r) >> 1;
            if (a[m] < num) {
                l = m;
            }
            else if (a[m] > num) {
                r = m;
            }
            else {
                // 向右找大于num的数
                l = m;
            }
        }
        if (a[l] > num) return l;
        if (a[r] > num) return r;
        // num大于数组所有元素
        return a.length;
    }

}
