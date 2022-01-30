import java.util.Arrays;

/**
 * lc 31 medium
 *
 * Analysis:
 * 逻辑分析题
 *  先说算法过程：
 * 1、从低位到高位找第一个非降序的数设为i
 * 2、i+1~n都是降序且都大于ai，从i+1~n中找到一个大于ai的最小的数aj
 * 3、交换ai aj，此时i+1~n仍然是降序因为换了个更小的ai过来
 * 4、将i+1~n按升序排列
 *
 * 以上算法思想推导过程：
 * 将原序列中左边较小的数和右边较大的数交换，较小的数要尽量靠右即尽量在低位，较大的数要尽量小。
 * 难的地方在于怎么确定较小的那个数？
 * 考虑原排列A，A中从低位到高位的每一位k，如果能将某位k换成更大的一个数则新的排列B就会比A大
 * 思考从低位到高位的某一位k，k能不能用k+1~n即低位的某个比k大的数来代替，这样B就会比A大
 * k还必须尽量靠右即尽量在低位，这样B会在大于A的情况下尽可能小
 * 从低位到高位逐位遍历，把每一位当成k，找k+1~n是否有更大的数代替k，这样找的第一个k就是最靠右且被替换后使得B比A更大
 * 这个k其实就是低位到高位的第一个非降序的数
 * 剩下的就好理解了，从k+1~n选出最小的比k大的数，
 * k被换成更大的数后要使得k+1~n的排列尽可能小，所以按升序排列
 *
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2021-02-27 13:31
 */
public class NextPermutation {

    public void nextPermutation(int[] nums) {
        int n = nums.length;
        int i ;
        for (i = n-1; i > 0; i--) {
            if (nums[i-1] < nums[i]) break;
        }
        if (i == 0) {
            Arrays.sort(nums); return;
        }
        // i-1是尽可能靠右的较小的数
        int j ;
        for (j = n-1; j >= i; j--) {
            if (nums[j] > nums[i-1]) {
                int t = nums[i-1];
                nums[i-1] = nums[j];
                nums[j] = t;
                break;
            }
        }
        // i~n是降序，双指针交换首尾
        for (j = n-1; i < j; i++, j--) {
            int t = nums[i];
            nums[i] = nums[j];
            nums[j] = t;
        }
    }
}
