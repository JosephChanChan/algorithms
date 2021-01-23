/**
 * 剑指Offer 64 medium
 *
 * Analysis:
 *  有点无聊的题目
 *
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2021-01-21 23:40
 */
public class AccumulatedN {

    public int sumNums(int n) {
        boolean done = n > 0 && (n += sumNums(n-1)) > 0;
        return n;
    }
}
