import java.util.*;

/**
 * lc 1487 medium
 *
 * Analysis:
 * 思维逻辑题
 *
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2022/9/8
 */
public class MakingFileNameUnique {

    String mark = "(";
    String mark2 = ")";
    Map<String, Integer> m = new HashMap<>();

    public String[] getFolderNames(String[] names) {
        if (names.length == 1) {
            return names;
        }
        int n = names.length;
        String[] ans = new String[n];
        for (int i = 0; i < n; i++) {
            String name = names[i];
            if (m.containsKey(name)) {
                int k = m.get(name);
                String maybe = name + mark + k + mark2;
                while (m.containsKey(maybe)) {
                    k++;
                    maybe = name + mark + k + mark2;
                }
                ans[i] = maybe;
                m.put(maybe, 1);
                // 这里必须更新name出现的次数，否则下次同样的name出现还得从1开始尝试，最坏时间O(n^2)
                m.put(name, k);
            }
            else {
                ans[i] = name;
                m.put(name, 1);
            }
        }
        return ans;
    }
}
