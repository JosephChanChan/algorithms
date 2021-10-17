package pointers;

import java.util.Arrays;

/**
 * lc 923 medium
 *
 * Analysis:
 *  3sum的变形，原本3sum要求去重。现在如 a+b+c=t，
 * 如果b,c不同，则b向右统计有多少个b，c向左移动统计多少个c。
 * b*c得到一共有多少bc可以组合一起。
 * 如果b==c，代表b~c全是相同的数字，假设有q个数，那么就相当于算C(q,2)种组合
 * 根据组合公式，C(n,m)=n!/m!(n-m)! 直接套公式计算是不行的，因为n可能很大，题目范围n有3000个数，
 * 从3000中任选2，就要计算3000的阶乘会溢出。
 * C(q,2)=q!/2!(q-2)! 这个q可能很大，比如有个数据就给了3000个0，然后target=0，
 * q就是2999，2999!会溢出。
 * 其实先对q!和(q-2)!约分！公式简化后得 C(q,2)=q(q-1)/2
 *
 * 时间复杂度：O(n^2)
 * 空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2020-10-03 16:14
 */
public class ThreeSumWithMultiplicity {

    int m = (int) 1e9 + 7;

    public int threeSumMulti(int[] A, int target) {
        int n = A.length;
        if (n < 3) return 0;

        int ans = 0;

        Arrays.sort(A);

        for (int i = 0; i < n; i++) {
            int a = A[i];
            int j = i + 1, k = n - 1;
            while (j < k) {
                int b = A[j];
                int c = A[k];
                int sum = a + b + c;
                if (sum == target) {
                    if (b == c) {
                        int q = k-j+1;
                        ans = (ans + q*(q-1)/2) % m;
                        break;
                    }
                    else {
                        int bCount = 1, cCount = 1;
                        while (j+1 < k && A[j+1] == b) {
                            bCount++;
                            j++;
                        }
                        while (j < k-1 && A[k-1] == c) {
                            cCount++;
                            k--;
                        }
                        ans = (ans + bCount * cCount) % m;
                        j++; k--;
                    }
                }
                else if (sum > target) {
                    while (j < k && c == A[k]) k--;
                }
                else {
                    while (j < k && b == A[j]) j++;
                }
            }
        }
        return ans;
    }

    // TLE，pass 38/70 case
    public int threeSumMulti2(int[] A, int target) {
        /*
            3sum的变形，原本3sum要求去重。现在如 a+b+c=t，则可以统计有多少个b可以组合c。
            但是假如枚举了a+b+c=t，不能再枚举b+a+c=t，也即题目要求的i<j<k
        */
        int n = A.length;
        if (n < 3) return 0;

        int ans = 0;

        Arrays.sort(A);

        for (int i = 0; i < n; i++) {
            int a = A[i];
            int j = i + 1, k = n - 1;
            while (j < k) {
                int b = A[j];
                int c = A[k];
                int sum = a + b + c;
                if (sum == target) {
                    // 这里太慢了，计算每个bc的组合，最坏需要O(n^2)，再套上外层在枚举a，总体就是O(n^3)
                    while (j < k && A[j] == b) {
                        int p = k;
                        while (j < p && A[p] == c) {
                            ans = ans+1 % m;
                            p--;
                        }
                        j++;
                    }
                    while (j < k && A[k] == c) k--;
                }
                else if (sum > target) {
                    while (j < k && c == A[k]) k--;
                }
                else {
                    while (j < k && b == A[j]) j++;
                }
            }
        }
        return ans;
    }
}
