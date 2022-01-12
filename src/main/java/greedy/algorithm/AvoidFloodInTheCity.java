package greedy.algorithm;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeSet;

/**
 * lc 1488 medium
 *
 * Analysis:
 *  这题的本质是同一个湖有两次下雨，就会洪水，除非中间可以抽干一次。
 * 所以假如一个湖它在第i天下雨，然后在第j天又下雨，i<j。
 * 那么只能期望在i ~ j之间有一个可用的晴天用来抽干这个湖的水，否则必然洪水。
 * 所以可以遍历的时候记录一个湖上一次下雨的天数i，当我遍历到这个湖又下雨时第j天，查一下发现这个湖存在i，
 * 那么就要看i~j之间是否有可用的晴天k。
 * 所以线性查找i~j之间是否存在k，用O(n)时间，用红黑树二分是O(log n)
 *
 * 这题的模型是什么？贪心？更多是思维题吧
 *
 * 时间复杂度：O(n*log n)
 * 空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2021-10-30 16:31
 */
public class AvoidFloodInTheCity {

    TreeSet<Integer> sunny = new TreeSet<>();
    // 湖 -> 上一次下雨的天数
    Map<Integer, Integer> lastRain = new HashMap<>();

    public int[] avoidFlood(int[] rains) {
        int n = rains.length;
        int[] a = rains;
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            if (a[i] == 0) {
                sunny.add(i);
            }
            else {
                ans[i] = -1;
                int j = i;
                Integer last = lastRain.computeIfAbsent(a[i], key -> j);
                if (last != i) {
                    // looking sunny day between last~i
                    Integer k = sunny.ceiling(last);
                    if (null == k || k > i) {
                        return new int[0];
                    }
                    sunny.remove(k);
                    lastRain.put(a[i], i);
                    ans[k] = a[i];
                }
            }
        }
        // 将剩余晴天随便选一个湖抽干，题目要求
        if (sunny.size() > 0) {
            for (Integer integer : sunny) {
                ans[integer] = 1;
            }
        }
        return ans;
    }
}
