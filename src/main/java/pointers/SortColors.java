package pointers;

/**
 * leetcode 75 medium
 *
 * Analysis:
 *  onePass是最优解，也是本题的follow up要求的一次遍历。
 *  twoPass是比较容易想到的解，两次遍历。
 *
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2020-10-02 16:38
 */
public class SortColors {

    public void sortColors(int[] nums) {
        onePass(nums);
    }

    private void onePass(int[] nums) {
        /*
            l初始在左端，r初始在右端，l位置放0，r位置放2。
            i初始在左端，左到右计算，遇1跳过，0放l，2放r。
            思想就是l,r在左右不断逼向中间，i处理每个数。
            [0,l)全是0，[l,i)是i遍历过的应是1，(r,n]全是2
        */
        if (nums.length <= 1) return;

        int l = 0, r = nums.length - 1, i = 0;

        while (i <= r) {
            if (nums[i] == 1) {
                i++;
            }
            else if (nums[i] == 2) {
                int t = nums[r];
                nums[r--] = nums[i];
                nums[i] = t;
            }
            else {
                int t = nums[l];
                nums[l++] = nums[i];
                nums[i++] = t;
            }
        }
    }

    private void twoPass(int[] nums) {
        /*
            两阶段，第一阶段把所有2移到右边，第二阶段在第一阶段基础上把所有1移到右边。
            phase1:
                l->0, r->length-1，r开始找不是2的位置停留，l开始找是2的。
                l和r互换，r继续向左找非2，l向右找2，直到相遇，此时r右边全是2
            phase2:
                l拉回0开始，r停留在phase1结束时的位置。r向左找非1的停留，l向右找1的互换
        */
        if (nums.length <= 1) return;

        int l = 0, r = nums.length - 1;
        while (l < r) {
            while (l < r && 2 == nums[r]) r--;
            while (l < r && 2 != nums[l]) l++;
            int t = nums[r];
            nums[r] = nums[l];
            nums[l] = t;
        }

        l = 0; r = nums[r] == 2 ? r-1: r;
        while (l < r) {
            while (l < r && 1 == nums[r]) r--;
            while (l < r && 1 != nums[l]) l++;
            int t = nums[r];
            nums[r] = nums[l];
            nums[l] = t;
        }
    }
}
