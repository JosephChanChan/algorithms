package recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Joseph
 * @since 2019-09-27 15:50
 *
 * lc 779 medium
 *
 * Question Description:
 * On the first row, we write a 0. Now in every subsequent row,
 * we look at the previous row and replace each occurrence of 0 with 01,
 * and each occurrence of 1 with 10.
 * Given row N and index K, return the K-th indexed symbol in row N.
 * (The values of K are 1-indexed.) (1 indexed).
 * Examples:
 * Input: N = 1, K = 1
 * Output: 0
 * Input: N = 2, K = 1
 * Output: 0
 * Input: N = 2, K = 2
 * Output: 1
 * Input: N = 4, K = 5
 * Output: 1
 * Explanation:
 * row 1: 0
 * row 2: 01
 * row 3: 0110
 * row 4: 01101001
 * Note:
 * N will be an integer in the range [1, 30].
 * K will be an integer in the range [1, 2^(N-1)].
 *
 * Analysis:
 *                                  0
 *                             /       \
 *                          0             1
 *                     /      \         /     \
 *                  0           1       1       0
 *                /    \       / \     /  \    /    \
 *               0      1     1   0   1    0  0      1
 *               按照规则会生成这么一颗二叉树，仔细观察会发现，每层节点若从1开始算起，
 *               则k是偶数时，一定是右节点，并且与父节点相反。
 *               反之一定是左节点，且与父节点相同。
 *               得到了此规律后，若题目给的n是10，则我们可以一直递归到边界，
 *               再从边界按规律一直递推回n就得到了k的值。
 *               那么时间复杂度多少？n最大是30，每一层递归的计算时间是常数，所以整个算法时间就只是递归的深度。
 *               对一颗满二叉树来说深度就是 logN ！这就是数学推理的威力！
 *               对比我的算法，虽然也发现了一些规律并通过1、2这种特殊标志代表了64位的0、1，
 *               使得数列长度缩短，并且每层递归计算只计算上层递归的后一半，但还是太耗时间。
 *               我的算法时间复杂度：logN * (2^n)/64/2
 *
 */
public class KthSymbol {


    public static void main(String[] args) {
        /*
            30
            473538164
         */
        int n = 30, k = 473538164;
        KthSymbol kthSymbol = new KthSymbol();
        int i = kthSymbol.kthGrammar(n, k);
        System.out.println(i);
    }

    // lc 上大神的 logN 算法
    public int kthGrammar(int n, int k) {
        if (n == 1) return 0;
        if (k % 2 == 0) {
            return kthGrammar(n - 1, k >> 1) == 0 ? 1 : 0;
        }
        else {
            return kthGrammar(n - 1, (k + 1) >> 1);
        }
    }


    /* --------------------------------- my algorithm (time limit exceed) ----------------------------------- */

    private List<Byte> byteArray = new ArrayList<>(8388608);

    public int myKthGrammar(int n, int k) {
        if (n < 7) {
            Byte[] bytes = null;
            switch (n) {
                case 1:
                    bytes = new Byte[]{0};
                    break;
                case 2:
                    bytes = new Byte[]{0, 1};
                    break;
                case 3:
                    bytes = new Byte[]{0, 1, 1, 0};
                    break;
                case 4:
                    bytes = new Byte[]{0, 1, 1, 0, 1, 0, 0, 1};
                    break;
                case 5:
                    bytes = new Byte[]{0, 1, 1, 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 1, 0};
                    break;
                case 6:
                    bytes = new Byte[]{
                            0, 1, 1, 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 1, 0,
                            1, 0, 0, 1, 0, 1, 1, 0, 0, 1, 1, 0, 1, 0, 0, 1
                    };
            }
            byteArray = Arrays.asList(bytes);
            return byteArray.get(k - 1);
        }

        kthGrammar(n);
        return getKthSymbol(k);
    }

    private void kthGrammar(int n) {
        // row为6时，数列长度为32
        if (n == 7) {
            byteArray.add((byte) 1);
            return;
        }
        kthGrammar(n - 1);
        if (byteArray.size() == 1) {
            byteArray.add((byte) 2);
            return;
        }

        // 本层递归应该拿上层递归计算的结果的后半部分计算
        int begin = byteArray.size() >> 1;
        int end = byteArray.size();
        for (int k = begin; k < end; k++) {
            Byte bb = byteArray.get(k);
            if (bb == 1) {
                byteArray.add((byte) 1);
                byteArray.add((byte) 2);
            }
            else {
                byteArray.add((byte) 2);
                byteArray.add((byte) 1);
            }
        }
    }

    private int getKthSymbol(int k) {
        int index = 1;
        int extent = index * 64;

        for (; extent < k; ) {
            index++;
            extent = index * 64;
        }

        Byte symbol = byteArray.get(index - 1);
        byte[] chars;
        if (symbol == 1) {
            chars = new byte[]{
                    0, 1, 1, 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 1, 0,
                    1, 0, 0, 1, 0, 1, 1, 0, 0, 1, 1, 0, 1, 0, 0, 1,
                    1, 0, 0, 1, 0, 1, 1, 0, 0, 1, 1, 0, 1, 0, 0, 1,
                    0, 1, 1, 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 1, 0
            };
        }
        else {
            chars = new byte[]{
                    1, 0, 0, 1, 0, 1, 1, 0, 0, 1, 1, 0, 1, 0, 0, 1,
                    0, 1, 1, 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 1, 0,
                    0, 1, 1, 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 1, 0,
                    1, 0, 0, 1, 0, 1, 1, 0, 0, 1, 1, 0, 1, 0, 0, 1

            };
        }

        int i = 64 - (extent - k) - 1;
        return chars[i];
    }
}
