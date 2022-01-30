package pointers;

/**
 * lc 243 easy
 *
 * Analysis:
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2022-01-27 15:47
 */
public class ShortestWordsDistance {

    public int shortestDistance(String[] wordsDict, String word1, String word2) {
        /*
            从左到右扫一遍记录最近的w1，w2位置，不断更新新发现的w1/w2的位置与原来另一个字符位置的距离
         */
        int n = wordsDict.length;
        int w1 = n, w2 = n, min = n;

        for (int i = 0; i < n; i++) {
            if (wordsDict[i].equals(word1)) {
                w1 = i;
                min = Math.min(min, Math.abs(w1 - w2));
            }
            if (wordsDict[i].equals(word2)) {
                w2 = i;
                min = Math.min(min, Math.abs(w1 - w2));
            }
        }
        //System.out.println(w1+" "+w2);
        return min;
    }
}
