package prefix.sum.hash;


/**
 * lc 1442 medium
 *
 * Analysis:
 * 时间复杂度：O(n^2)
 * 空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2022/3/19
 */
public class IJKMakeTwoSubarrayXorEquals {

    public int countTriplets(int[] arr) {
        return plain2(arr);
    }

    int plain2(int[] arr) {
        /**
             降低时间复杂度，从枚举i,j,k变为枚举i,k
             x(i,k)为异或i~k区间的结果
             f(i)为0~i区间异或的结果，即异或前缀和
             原本是找x(i,j-1)=a，x(j,k)=b，求让a=b的区间，其实a=b意味着a^b=0
             即x(i,k)=0，那么只需要枚举i和k就行。
             但是区间i~k有多少个j可以满足划分出来的a,b相等呢？
             ai ^ ai+1 ^ ... ^ aj-1 ^ aj ^ ... ^ ak
             i < j <= k
             设x(i,k)=0，可推出x(i,j-1)^x(j,k)=0，即x(i,j-1)=x(j,k)
             若有任意x(i,j-1) != x(j,k)，即x(i,j-1)^x(j,k) != 0
             与原本设定矛盾，所以如果x(i,k)=0，则i~k区间内任取一个点j，i<j
             作为中间点划分出2段区间，这2段区间的异或结果都是相同的
             所以区间i~k有 k-i个点j可以划分出来2段区间使得a,b相等
         */
        int n = arr.length;
        int[] f = new int[n];
        f[0] = arr[0];
        int ans = 0;
        for (int i = 1; i < n; i++) {
            f[i] = f[i-1] ^ arr[i];
        }
        for (int i = 0; i < n; i++) {
            for (int k = i+1; k < n; k++) {
                // xor(i,k) = xor(k) ^ xor(i-1)
                if (i == 0 && f[k] == 0) {
                    ans += k;
                }
                else if (i > 0) {
                    if ((f[k] ^ f[i-1]) == 0) {
                        ans += k-i;
                    }
                }
            }
        }
        return ans;
    }

    int plain(int[] arr) {
        /**
            看数据量不大，枚举i,j,k，O(n^3)可过？
         */
        int n = arr.length;
        int[] f = new int[n];
        f[0] = arr[0];
        for (int i = 1; i < n; i++) {
            f[i] = f[i-1]^arr[i];
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                for (int k = j; k < n; k++) {
                    int a = i == 0 ? f[j-1] : f[j-1] ^ f[i-1];
                    int b = j == k ? arr[j] : f[k] ^ f[j-1];
                    if (a == b) {
                        ans++;
                        //System.out.println(i+" "+j+" "+k);
                    }
                }
            }
        }
        return ans;
    }
}
