/**
 * lc 157 easy
 *
 * Analysis:
 * 模拟系统缓存的题目，有点意思。注意处理下从read4读回来的数据写入buf时的边界问题就行。
 * 搞清楚循环终止条件是 1.还有得读，2.还需要读
 *
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2021-04-04 00:24
 */
public class ReadNCharsGivenRead4 {

    public int read(char[] buf, int n) {
        char[] c = new char[4];
        int i = 0;
        for ( ; i < n; ) {
            int len = read4(c);
            if (len == 0) return i;
            // j < len 还有得读
            // i < n 还需要读
            for (int j = 0; j < len && i < n; j++, i++) {
                buf[i] = c[j];
            }
        }
        return i;
    }

    // 假方法
    int read4(char[] bur) {
        return 0;
    }
}
