package dynamic.programming;

/**
 * lc 376 medium
 *
 * Analysis:
 * time: O(n^2)
 * space: O(n)
 *
 * @author Joseph
 * @since 2023/7/30
 */
public class WiggleSubsequence {

    public static void main(String[] args) {
        WiggleSubsequence m = new WiggleSubsequence();
        m.wiggleMaxLength(new int[]{1, 7, 4, 9, 2, 5});
    }

    public int wiggleMaxLength(int[] nums) {
        /*
             f(n,0)为以An结尾的前n个元素中最长摆动序列长度
             f(n,1)为以An结尾的前n个元素中最长摆动序列的最后一个差
             f(0,0)=0, f(0,1)=0
             f(1,0)=1, f(1,1)=A1
             f(2,0)=2, f(2,1)=A2-A1

             f(n,0)=1, 如果An和前面任何一个Aj都不能构成摆动序列，0<j<n
             f(n,0)=max{f(j,0)+1 | An和Aj形成摆动序列， 0<j<n}
             f(n,1)=An-Aj
         */
        int n = nums.length;
        if (n == 1) {
            return 1;
        }
        if (n == 2 && nums[1] == nums[0]) {
            return 1;
        }
        if (n == 2 && nums[1] != nums[0]) {
            return 2;
        }

        int[][] f = new int[n+1][2];
        f[0][0]=0; f[0][1]=0;
        f[1][0]=1; f[1][1]=nums[0];

        int ans = 0;
        for (int i = 2; i <= n; i++) {
            f[i][0] = 1;
            f[i][1] = nums[i-1];
            // 寻找前面Aj能构成摆动序列的序列
            for (int j = 1; j < i; j++) {
                if (nums[i-1] != nums[j-1]) {
                    int diff = nums[i-1]-nums[j-1];
                    // 如果前面只有1个数，并且Ai!=Aj所以长度是2
                    if (j == 1 || (diff > 0 && f[j][1] < 0) || (diff < 0 && f[j][1] > 0)) {
                        if (f[i][0] < f[j][0] + 1) {
                            f[i][0] = f[j][0] + 1;
                            f[i][1] = nums[i-1]-nums[j-1];
                        }
                    }
                }
            }
            ans = Math.max(ans, f[i][0]);
        }
        return ans;
    }

}
