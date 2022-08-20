package graphs;

import java.util.*;

/**
 * lc 433 medium
 *
 * Analysis:
 * 时间复杂度：O(bank.length^2) 首先每个节点只会被访问一次，其次每个节点还要计算对于所有节点能够访问到的地方
 * 空间复杂度：O(bank.length)
 *
 * @author Joseph
 * @since 2022/7/24
 */
public class MinGeneticMutation {

    /**
         图bfs
         题目规定每一次变化后的值只能在bank中存在，所以如果起点能到终点，其每一步变化必然在bank中存在一条路径。
         且每一步变化都是变化一个字符边长为1的路径，只要把每个单词看做节点，从起点bfs找一条路bfs到终点即可
         PS: 所有边长为1的图，最短路用bfs求

         对于搜索空间很大的时候，并且明确知道起点和终点可以双向bfs优化搜索空间
        双向bfs每次从队列少的一头开始搜索，促使2端尽量在中间相遇。使得搜索空间变少
     */

    public int minMutation(String start, String end, String[] bank) {
        return doubleBfs(start, end, bank);
    }

    Map<String, Integer> d = new HashMap<>();
    LinkedList<String> q = new LinkedList<>();
    LinkedList<String> p = new LinkedList<>();
    Set<String> qvis = new HashSet<>();
    Set<String> pvis = new HashSet<>();


    public int doubleBfs(String start, String end, String[] bank) {
        if (!Arrays.asList(bank).contains(end)) {
            return -1;
        }

        q.add(start);
        p.add(end);
        qvis.add(start);
        pvis.add(end);
        d.put(start, 0);
        d.put(end, 0);

        while (!q.isEmpty() && !p.isEmpty()) {
            if (q.size() > p.size()) {
                LinkedList<String> shorter = p;
                p = q;
                q = shorter;
                Set<String> vis = pvis;
                pvis = qvis;
                qvis = vis;
            }
            // 每次从更短的队列搜索，q总是更短的
            int c = q.size();
            for (int i = 0; i < c; i++) {
                String term = q.removeFirst();
                // 此单词能去的边长1的未访问过的邻节点
                for (String word : bank) {
                    if (!qvis.contains(word) && canGo(term, word)) {
                        // word在对面搜索空间中已被访问过，代表双向原点都可达此点，有交集路径
                        if (pvis.contains(word)) {
                            // 找到交点
                            //System.out.println(term +" d="+d.get(term)+" "+word+" d="+d.get(word));
                            return d.get(term) + d.get(word) + 1;
                        }
                        qvis.add(word);
                        q.add(word);
                        d.put(word, d.get(term) + 1);
                        //System.out.println(term+" to "+word+" d="+d.get(term)+" "+d.get(word));
                    }
                }
            }
        }
        return -1;
    }


    Set<String> bs = new HashSet<>();
    LinkedList<String> q2 = new LinkedList<>();
    int[] f ;

    int bfs(String start, String end, String[] bank) {
        for (String term : bank) {
            bs.add(term);
        }
        // 首先看看end是否在bank中
        if (!bs.contains(end)) {
            return -1;
        }
        int d = -1;
        f = new int[bank.length];
        q2.add(start);

        while (!q2.isEmpty()) {
            int c = q2.size();
            d++;
            for (int i = 0; i < c; i++) {
                String term = q2.removeFirst();
                if (Objects.equals(term, end)) {
                    return d;
                }
                // 看看这个单词能去到哪些未访问的单词
                for (int j = 0; j < bank.length; j++) {
                    if (f[j] == 0 && canGo(term, bank[j])) {
                        q2.add(bank[j]);
                        f[j]++;
                    }
                }
            }
        }
        return -1;
    }

    boolean canGo(String a, String b) {
        int diff = 0;
        char[] p = a.toCharArray();
        char[] q = b.toCharArray();
        for (int i = 0; i < 8; i++) {
            if (p[i] != q[i] && ++diff > 1) {
                return false;
            }
        }
        return true;
    }
}
