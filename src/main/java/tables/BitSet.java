package tables;

/**
 * 位图算法是一种逻辑存在的数据结构，其特殊的特点可以用来处理大数据量的场景，
 * 例如对大数据的数字查找、数字去重。Java中boolean貌似最小占一个byte，最小的数据类型又是byte。
 * 所以可以用byte[]模拟位图数据结构。
 *
 * @author Joseph
 * @since 2020-01-12 17:15
 */
public class BitSet {

    private byte[] bitSet ;



    /**
     * 设置一个可以容纳 1 ~ num 容量的位图。
     *
     * @param num 数字
     */
    public BitSet(int num) {
        // 数组长度最大限制在 Int.max
        bitSet = new byte[(num >> 3) + 1];
    }

    /**
     * 将数字k放进位图中。
     * 常数时间。
     *
     * @param k 数字
     */
    public void put(int k) {
        int index = getIndex(k);
        checkRange(k, index);

        bitSet[index] |= (1 << getPosition(k));
    }

    /**
     * 删除数字k。
     * 常数时间。
     *
     * @param k 数字
     */
    public void remove(int k) {
        int index = getIndex(k);
        checkRange(k, index);

        bitSet[index] &= ~(1 << getPosition(k));
    }

    /**
     * 位图中是否存在数字k。
     * 常数时间。
     *
     * @param k 数字
     * @return 0/1
     */
    public boolean contains(int k) {
        int index = getIndex(k);
        if (index > bitSet.length) return false;

        return (bitSet[index] & (1 << getPosition(k))) > 0;
    }



    /* ----------------------------------- internal method ----------------------------------- */

    /** 计算 k 在第几个byte */
    private int getIndex(int k) {
        return k >> 3;
    }

    /** 计算 k 在byte中的位置，k & 7 <-> k % 8 */
    private int getPosition(int k) {
        return k & 0x7;
    }

    private void checkRange(int k, int index) {
        if (index > bitSet.length) {
            throw new IllegalArgumentException("The number k are exceed the bound of bitmap!");
        }
    }
}
