package graphs;

import java.util.*;

/**
 * lc 752 medium
 *
 * Analysis:
 * 时间复杂度：O(d^9 * d)，其中d是节点的位数，比如这里是4位，乘d是因为代码中要枚举节点的每个位置的变化
 *  bfs时间和搜索的空间大小有关，这里从0000开始搜索，每个节点位置有4个邻节点，第一层有4^1，二层有4^2 以此类推 最深有9层 4^9个节点
 * 空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2022/7/25
 */
public class OpenTheLock {

    /**
         bfs
         把0000看作图的原点，每次经过1个Ai变化，走到新的节点，如果新节点是deadends则节点后的分支都剪枝。
         一直搜索看是否有一条路径到达target。
         其实搜索空间是4^k增长，很大。双向bfs可以减少空间搜索
     */

    LinkedList<String> q = new LinkedList<>();
    LinkedList<String> p = new LinkedList<>();
    Set<String> qVis = new HashSet<>();
    Set<String> pVis = new HashSet<>();
    Set<String> pruned = new HashSet<>();
    Map<String, Integer> d = new HashMap<>();

    public int openLock(String[] deadends, String target) {
        pruned.addAll(Arrays.asList(deadends));

        String start = "0000".intern();
        if (pruned.contains(start)) {
            return -1;
        }
        if (Objects.equals(target, start)) {
            return 0;
        }

        q.add(start);
        p.add(target);
        qVis.add(start);
        pVis.add(target);

        d.put(start, 0);
        d.put(target, 0);

        while (!q.isEmpty() && !p.isEmpty()) {
            if (q.size() > p.size()) {
                LinkedList<String> smaller = p;
                p = q;
                q = smaller;

                Set<String> vis = pVis;
                pVis = qVis;
                qVis = vis;
            }

            int count = q.size();
            for (int i = 0; i < count; i++) {
                String node = q.removeFirst();
                int far = d.get(node);
                // 搜索节点能到达的邻节点
                for (int j = 0; j < node.length(); j++) {
                    // 对于每个Aj有2个变化方向，Aj-1 Aj+1
                    String n1 = move(node, j, -1);
                    String n2 = move(node, j, 1);

                    // 如果邻节点在对方搜索空间已经访问过，则有交点
                    if (pVis.contains(n1)) {
                        return far + d.get(n1) + 1;
                    }
                    if (pVis.contains(n2)) {
                        return far + d.get(n2) + 1;
                    }

                    if (!pruned.contains(n1) && !qVis.contains(n1)) {
                        q.add(n1);
                        qVis.add(n1);
                        d.put(n1, far+1);
                    }
                    if (!pruned.contains(n2) && !qVis.contains(n2)) {
                        q.add(n2);
                        qVis.add(n2);
                        d.put(n2, far+1);
                    }
                }
            }
        }
        return -1;
    }

    String move(String node, int j, int way) {
        //System.out.println("move c="+node+" j="+j+" way="+way);
        char[] copy = node.toCharArray();
        if (copy[j] == '0') {
            copy[j] = way > 0 ? ++copy[j] : '9';
        }
        else if (copy[j] == '9') {
            copy[j] = way > 0 ? '0' : --copy[j];
        }
        else {
            copy[j] = way > 0 ? ++copy[j] : --copy[j];
        }
        //System.out.println("move res="+new String(copy));
        return new String(copy);
    }
}
