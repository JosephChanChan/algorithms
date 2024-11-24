package dynamic.programming;

/**
 * lc 338 medium
 *
 * Analysis：
 *  规律 + dp题
 * 时间复杂度：officialDP O(n) myDP O(16n) bitCalc O(31n)
 * 空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2021-03-20 16:46
 */
public class CountingBits {

    public int[] countBits(int num) {
        return officialDP(num);
    }

    int[] officialDP(int num) {
        int n = num;
        int[] f = new int[n+1];
        f[0] = 0;

        /*
            i是奇数，i-1是偶数最低位是0，i只要在最低位加一便可
            i是偶数，想想i怎么计算得来的？i/2 * 2，也就是说i/2的二进制全部左移1位便可。
            那知道i/2的二进制1的个数不就行了？
            f(i)=f(i-1)+1, i%2>0
            f(i)=f(i/2), i%2==0
         */
        for (int i = 1; i <= n; i++) {
            if (i % 2 > 0) {
                f[i] = f[i-1] + 1;
            } else {
                f[i] = f[i>>1];
            }
        }
        return f;
    }

    // 30min
    int[] myDp(int num) {
        int n = num;
        /*
            dp+规律题
            f(i)为i的二进制1的个数
            知道i-1的二进制个数后，对于i的计算
            如果i-1最低位是0，i只不过在i-1最低位加上1
            f(i)=f(i-1)+1
            对于i-1最低位是1，情况就复杂了，从最低位开始进一，该位是1进一后变0相应的1的个数减少。
            直到遇到0停止。
            这是半dp解法，因为有一半的偶数最低位是1，可以走f(i)=f(i-1)+1
            剩下的还是要走31的循环。
        */
        int[] f = new int[n+1];
        f[0] = 0;

        for (int i = 1; i <= n; i++) {
            if (((i-1) & 1) == 0) {
                f[i] = f[i-1]+1;
            }
            else {
                int p = 1, q = i-1, k = f[i-1];
                for (int j = 0; j < 31; j++) {
                    if ((q & p) == 0) break;
                    k--;
                    p = p << 1;
                }
                f[i] = ++k;
            }
        }
        return f;
    }

    // 15min
    int[] bitCalc(int num) {
        int n = num;
        int[] ans = new int[n+1];
        ans[0] = 0;

        for (int i = 1; i <= n; i++) {
            int p = 1, q = 0;
            for (int j = 0; j < 31; j++) {
                if ((i & p) > 0) q++;
                p = p << 1;
            }
            ans[i] = q;
        }
        return ans;
    }
}
