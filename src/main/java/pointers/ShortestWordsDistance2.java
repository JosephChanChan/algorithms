package pointers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * lc 244 medium
 *
 * Analysis:
 * 时间复杂度：O(max(k, l)) k和l是两个字符串的位置数组长度
 * 空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2022-01-27 16:33
 */
public class ShortestWordsDistance2 {

    String[] a ;
    Map<String, List<Integer>> address = new HashMap<>();
    Map<Key, Integer> m = new HashMap<>();

    public ShortestWordsDistance2(String[] wordsDict) {
        this.a = wordsDict;
        int i = 0;
        for (String s : a) {
            address.computeIfAbsent(s, (k) -> new ArrayList<>()).add(i);
            i++;
        }
    }

    public int shortest(String word1, String word2) {
        /*
            这题比较有意思的点在于不像243单纯考察算法思维，
            还考察工程上的缓存思维。
            如果直接照搬243的题解，对于单一的找w1&w2最短距离时间是O(n)，没得再优化了
            但是函数会被调用很多次，对于相同的入参每次都花O(n)时间去计算重复的参数？
            2个优化的地方：
            1.计算完w1 w2的最短距离后缓存结果
            2.构造函数预处理，记录每个字符串的出现位置，出现位置是少数的，直接比较w1 w2的最短位置
            预处理时间均摊后会比较少。
         */
        Key k = new Key(word1, word2);
        if (m.containsKey(k)) {
            return m.get(k);
        }

        int min = a.length;
        List<Integer> p = address.get(word1);
        List<Integer> q = address.get(word2);
        for (Integer i : p) {
            for (Integer j : q) {
                min = Math.min(min, Math.abs(i-j));
            }
        }

        m.put(k, min);
        return min;
    }

    class Key {
        public String w1;
        public String w2;
        public Key(String w1, String w2) {
            this.w1 = w1;
            this.w2 = w2;
        }
        public int hashCode() {
            return w1.hashCode() + w2.hashCode();
        }
        public boolean equals(Key k2) {
            if (w1.equals(k2.w1)) {
                return w2.equals(k2.w2);
            }
            return false;
        }
    }
}
