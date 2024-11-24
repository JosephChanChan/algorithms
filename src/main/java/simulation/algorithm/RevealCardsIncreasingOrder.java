package simulation.algorithm;

import java.util.*;

/**
 * lc 950 medium
 *
 * Analysis：
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2022/9/19
 */
public class RevealCardsIncreasingOrder {

    /**
        分析题+模拟
        按照这个规则可以发现，一个长度为7的数组，其按照递增显示的顺序，下标应该这么排列
        0<2<4<6<3<1<5，也就是说这个递增的序列的下标元素的大小关系是这样的，下标0元素<下标2元素< ... < 下标5元素
        既然推不出给定长度n的序列，生成这么一个下标序列公式，但是可以通过队列模拟
     */

    Queue<Integer> q = new LinkedList<>();

    public int[] deckRevealedIncreasing(int[] deck) {
        int n = deck.length;
        if (n == 1) {
            return deck;
        }
        for (int i = 0; i < n; i++) {
            q.add(i);
        }
        int[] indexs = new int[n];
        int i = 0, j = 0;
        while (q.size() > 0) {
            if ((i++ & 1) == 0) {
                indexs[j++] = q.poll();
            }
            else {
                q.add(q.poll());
            }
        }
        int[] ans = new int[n];
        Arrays.sort(deck);
        for (i = 0; i < n; i++) {
            int index = indexs[i];
            ans[index] = deck[i];
        }
        return ans;
    }
}
