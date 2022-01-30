/**
 * lc 158 hard
 *
 * Analysis:
 * 读文件只能调read4，read4最多一次会返回4个字符，如果本次n<4
 * 剩余字符怎么办？下次read4已经不会再返回了
 * 肯定要存储起来。并且按顺序存储。
 * 队列是合适的数据结构
 *
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2021-04-04 10:27
 */
public class ReadNCharsGivenRead4Multi {

    int p = 0, limit = -1;
    char[] que = new char[4];

    /**
     * @param buf Destination buffer
     * @param n   Number of characters to read
     * @return    The number of actual characters read
     */
    public int read(char[] buf, int n) {
        int i = 0;
        // 还需要读？
        for ( ; i < n; ) {
            // 队列有缓存？
            if (p < limit) {
                // 还需要读 且 还有得读 ？
                while (i < n && p < limit) {
                    buf[i++] = que[p++];
                }
                if (p == limit) {
                    p = 0;
                    limit = -1;
                }
            }
            else {
                if ((limit = read4(que)) == 0) return i;
            }
        }
        return i;
    }

    // 假方法
    int read4(char[] que) {
        return -1;
    }
}
