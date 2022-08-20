package prefix.sum.hash;

/**
 * lc 1310 medium
 *
 * Analysis:
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2022/3/18
 */
public class XORQuerySubarray {

    public int[] xorQueries(int[] arr, int[][] queries) {
        /**
             异或前缀和
             一个数异或自己会归0，例如 0011 ^ 0011 = 0000
             a^b^c=d，求b^c的结果，d^a=b^c，因为b^c后本身二进制中有一些位是0，一些位是1
             d是因为b^c后再^a，使得原本b^c中的二进制位发生了变化，0变1，1变0.
             假设第k位，原本是0，异或后是1，则a中第k位必定是1，现在d的第k位也是1，再异或a的第k位，
             就变回0。
             第k位是1同理。
         */
        int n = arr.length;

        int[] f = new int[n];
        f[0] = arr[0];
        for (int i = 1; i < n; i++) {
            f[i] = f[i-1] ^ arr[i];
        }
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int l = queries[i][0];
            int r = queries[i][1];
            if (l == r) {
                ans[i] = arr[l];
            }
            else if (l == 0) {
                ans[i] = f[r];
            }
            else {
                // xor(i,j) = f(j)^f(i-1)
                ans[i] = f[r] ^ f[l-1];
            }
        }
        return ans;
    }
}
