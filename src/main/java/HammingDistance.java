/**
 * lc 461 easy
 *
 * Analysis:
 *  异或得到两个数之间的二进制不同的位，这些不同的位是1，扫一遍看看有多少个1
 *
 * 时间复杂度：O(1)
 * 空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2021-03-23 16:24
 */
public class HammingDistance {

    public int hammingDistance(int x, int y) {
        int a = x ^ y;
        int i = 1, c = 0;
        for (int j = 0; j < 32; j++) {
            if ((a & i) > 0) c++;
            i = i << 1;
        }
        return c;
    }
}
