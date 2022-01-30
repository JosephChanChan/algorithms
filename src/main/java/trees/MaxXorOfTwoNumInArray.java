package trees;

/**
 * lc 421 medium
 *
 * Analysis:
 *  Trie树的应用。
 * 根据题目要求找到一对数字，如：p&q，使得 p ^ q 最大。
 * 假设现在有最大数字 25 它的二进制就是 0000 ... 11001
 * 其它数字都会小于25，所以其他数字的二进制有效字符的长度都小于5位，举例：5 二进制 0000 ... 00101
 * 如果 25异或某个数字，它最大的结果无非就是 0000 ... 11111，即低位的5个二进制全部被置为1.
 * 所以就要使每一位异或结果为1，这样不就最大了？
 * 对于5的二进制 1 0 1 i指向是1，就要找在相同i位置上是0的数字异或，找得到那么结果的第i位置就是1，找不到就是0
 *             i
 * 所以建一棵Trie树，节点就是每个数字的二进制0 1
 * 从根到叶节点的路径就是每一个数字的二进制表示。
 * 遍历每个数字，从根到叶节点，分别去找第k层和它异或能得到1的节点，继续向下。
 *
 * 文字描述太抽象，可以看lc题解。
 *
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2021-02-05 21:34
 */
public class MaxXorOfTwoNumInArray {

    TrieNode root = null;
    String[] a ;

    public int findMaximumXOR(int[] nums) {
        a = new String[nums.length];
        root = new TrieNode(-1);
        for (int i = 0; i < a.length; i++) {
            a[i] = leftPadding(Integer.toBinaryString(nums[i]));
            add(a[i]);
        }
        int max = 0;
        for (String i : a) {
            int xor = 0;
            TrieNode node = root;
            char[] c = i.toCharArray();
            for (int k = 0; k < 32; k++) {
                xor = xor << 1;
                int opp = c[k] == '0' ? 1 : 0;
                if (null != node.childes[opp]) {
                    // 当前第k层，找得到能使p第k位异或结果为1的q，记录当前异或结果第k位为1
                    // k+1层会将当前k推高1位
                    xor = (xor | 1);
                    node = node.childes[opp];
                }
                else {
                    node = node.childes[opp == 1 ? 0 : 1];
                }
            }
            max = Math.max(max, xor);
        }
        return max;
    }

    void add(String num) {
        TrieNode node = root;
        char[] c = num.toCharArray();
        for (int i = 0; i < 32; i++) {
            if (null == node.childes[c[i]-'0']) {
                node.childes[c[i]-'0'] = new TrieNode(c[i]-'0');
            }
            node = node.childes[c[i]-'0'];
        }
    }

    String leftPadding(String num) {
        char[] c = new char[32];
        for (int i = 0; i < 32; i++) c[i] = '0';
        for (int k = num.length()-1, i = 31; k >= 0; i--, k--) {
            c[i] = num.charAt(k);
        }
        return new String(c);
    }

    class TrieNode {
        int bit ;
        TrieNode[] childes ;
        public TrieNode(int bit) {
            this.bit = bit;
            this.childes = new TrieNode[2];
        }
    }
}
