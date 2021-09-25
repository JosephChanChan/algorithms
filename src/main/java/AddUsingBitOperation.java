/**
 * 剑指Offer 65 easy
 *
 * Analysis:
 *  二进制的位运算分3步走：1.计算a+b的无进位结果。2.计算a+b的进位结果。3.将无进位结果+进位结果
 * 无进位结果加法可以使用按位异或，进位结果加法可以用按位与。
 * 3+5=8
 *
 *     0000...0011
 * ^   0000...0101
 * -----------------
 *     0000...0110
 *
 *     0000...0011
 * &   0000...0101
 * -----------------
 *     0000...0001 <<< 1
 * -----------------
 *     0000...0010 进位结果>0 代表有进位
 *
 *     0000...0110
 * ^   0000...0010
 * -----------------
 *     0000...0100
 *
 *     0000...0110
 * &   0000...0010
 * -----------------
 *     0000...0010 <<< 1
 *
 *     0000...0100
 * ^   0000...0100
 * -----------------
 *     0000...0000
 *
 *     0000...0100
 * &   0000...0100
 * -----------------
 *     0000...0100 <<< 1
 *
 *     0000...0000
 * ^   0000...1000
 * -----------------
 *     0000...1000 => 8
 *
 *     0000...0000
 * &   0000...1000
 * -----------------
 *     0000...0000
 * 进位结果==0，已无进位，结束运算。
 * 这种加法对于负数+正数也有效，在草稿纸上模拟一下就知道了。
 * 负数在计算机中以补码形式存在。
 *
 * 时间复杂度：O(1)，循环取决于进位，进位最多31位，不随a/b变化，所以是固定常数
 * 空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2021-01-23 15:20
 */
public class AddUsingBitOperation {

    public int add(int a, int b) {
        if (a == 0 && b == 0) return 0;
        if (a == 0) return b;
        if (b == 0) return a;

        int base, progress ;
        while (true) {
            base = (a ^ b);
            progress = (a & b) << 1;
            if (progress == 0) break;
            a = base;
            b = progress;
        }
        return base;
    }
}