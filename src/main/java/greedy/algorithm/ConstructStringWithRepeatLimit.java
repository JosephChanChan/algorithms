package greedy.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * lc 2182 medium
 *
 * Analysis：
 * 时间复杂度：O(s.length * log(s.length))
 * 空间复杂度：O(s.length)
 *
 * @author Joseph
 * @since 2022/9/28
 */
public class ConstructStringWithRepeatLimit {

    /**
         贪心，每次从s中取字典序最大的字符
         如果取出的字符超过限制，则再从队列取，如果队列为空则结束
         取完后把限制字符再次入队
     */

    Queue<int[]> q = new PriorityQueue<>((o1, o2) -> o2[0]-o1[0]);

    public String repeatLimitedString(String s, int repeatLimit) {
        char[] c = s.toCharArray();
        int[] h = new int[26];
        for (int i = 0; i < c.length; i++) {
            h[c[i]-'a']++;
        }
        for (int i = 0; i < 26; i++) {
            if (h[i] > 0) {
                q.add(new int[]{i, h[i]});
            }
        }
        int repeated = 0;
        List<Integer> ans = new ArrayList<>();
        while (q.size() > 0) {
            int[] val = q.poll();
            int topVal = -1;
            if (ans.size() > 0) {
                topVal = ans.get(ans.size()-1);
            }
            if (repeated >= repeatLimit && val[0] == topVal) {
                if (q.size() == 0) {
                    //System.out.println("val="+val +" temp="+temp.size());
                    break;
                }
                int[] minor = q.poll();
                ans.add(minor[0]);
                if (--minor[1] > 0) {
                    q.add(minor);
                }
                repeated = 1;
            }
            else if (val[0] == topVal) {
                ans.add(val[0]);
                repeated++;
                val[1]--;
            }
            else {
                ans.add(val[0]);
                repeated = 1;
                val[1]--;
            }
            if (val[1] > 0) {
                q.add(val);
            }
        }
        char[] cc = new char[ans.size()];
        for (int i = 0; i < cc.length; i++) {
            cc[i] = (char) (ans.get(i)+'a');
        }
        return new String(cc);
    }
}
