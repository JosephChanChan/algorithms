package tables;

/**
 * lc 792 medium
 *
 * Analysis:
 * 时间复杂度：O(words.length * avg(word.length))
 * 空间复杂度：O(words.length)
 *
 * @author Joseph
 * @since 2022/9/8
 */
public class NumOfMatchingSubsequence {

    int ans = 0;
    char[] c = null;


    /**
         hash分桶法，对于word的首字母是期望在s中被匹配到的，那么将每个word首字母分桶放入
         遍历s时对每个Si，找hash桶中是否有期望被Si匹配到的word，然后再把匹配到Si的word首字母更新放入到新的桶中
     */
    public int numMatchingSubseq(String s, String[] words) {
        return 0;
    }


    /**
     * 朴素解法，逐个遍历判断word是否s的子序列
     * 时间 O(s * sum(words.length))，勉强能过
     */
    public int numMatchingSubseq2(String s, String[] words) {
        c = s.toCharArray();

        for (String word : words) {
            if (isSubSeq(word)) {
                ans++;
            }
        }
        return ans;
    }
    boolean isSubSeq(String word) {
        char[] p = word.toCharArray();
        int i = 0, j = 0;
        while (j < p.length && i < c.length) {
            if (c[i] == p[j]) {
                j++;
                if (j == p.length) {
                    return true;
                }
            }
            i++;
        }
        return false;
    }


}
