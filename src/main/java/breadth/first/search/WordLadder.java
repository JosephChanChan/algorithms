package breadth.first.search;

import java.util.*;

/**
 * lc 127
 *
 * Analysis:
 * 抽象的将每个词看作是点，这个词通过一步距离变换可达的词看做是它的邻居，则经过BFS计算经过多少邻居可达endWord就是答案。
 * 难点在计算每个词的连通词，暴力计算只能勉强AC。
 *
 * 时间复杂度：O(length * n)
 * 空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2020-08-30 20:21
 */
public class WordLadder {

    private int L ;

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        /*
            1.计算每个词的neighbors
            2.bfs搜层级
        */
        L = endWord.length();
        Map<String, List<String>> d = calcNeighbors(beginWord, wordList);
        return bfs(beginWord, endWord, d);
    }

    private int bfs(String begin, String end, Map<String, List<String>> d) {
        Queue<String> queue = new LinkedList<>();
        Set<String> vis = new HashSet<>();

        int ladder = 0;
        vis.add(begin);
        queue.add(begin);

        while (!queue.isEmpty()) {
            ladder++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String term = queue.poll();
                if (term.equals(end)) return ladder;

                // seeking neighbors add to queue
                for (int j = 0; j < L; j++) {
                    char[] a = term.toCharArray();
                    a[j] = '*';
                    List<String> neighbors = d.get(new String(a));
                    for (String n : neighbors) {
                        if (!vis.contains(n)) {
                            vis.add(n);
                            queue.add(n);
                        }
                    }
                }
            }
        }
        return 0;
    }

    // 官方题解的计算连通词，O(length * n)
    private Map<String, List<String>> calcNeighbors(String begin, List<String> wordList) {
        Map<String, List<String>> d = new HashMap<>(wordList.size() + 1);
        wordList.add(begin);
        for (String term : wordList) {
            for (int i = 0; i < L; i++) {
                char[] a = term.toCharArray();
                a[i] = '*';
                String t = new String(a);
                List<String> list = d.getOrDefault(t, new ArrayList<>());
                list.add(term);
                d.put(t, list);
            }
        }
        return d;
    }

    // 很慢的方法，主要花时间在计算每个词的连通词上 AC > 1s, faster than 12%
    /*private int bfs(String begin, String end, Map<String, List<String>> d) {
        Queue<String> queue = new LinkedList<>();
        Set<String> vis = new HashSet<>();

        int ladder = 0;
        queue.add(begin);
        vis.add(begin);

        while (!queue.isEmpty()) {
            ladder++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String term = queue.poll();
                if (term.equals(end)) return ladder;

                List<String> neighbors = d.get(term);
                for (String neighbor : neighbors) {
                    if (!vis.contains(neighbor)) {
                        vis.add(neighbor);
                        queue.add(neighbor);
                    }
                }
            }
        }
        return 0;
    }
    // 遍历每个词判断差距，O(length * n^2)
    private Map<String, List<String>> calcNeighbors(String begin, List<String> wordList) {
        Map<String, List<String>> dictionary = new HashMap<>(wordList.size() + 1);
        wordList.add(begin);

        for (String term : wordList) {
            char[] a = term.toCharArray();
            List<String> ns = new ArrayList<>();
            for (String neighbor : wordList) {
                int count = 0;
                if (term.equals(neighbor)) continue;

                char[] b = neighbor.toCharArray();
                for (int i = 0; i < a.length; i++) {
                    if (a[i] != b[i]) count++;
                    if (count > 1) break;
                }
                if (count <= 1) ns.add(neighbor);
            }
            dictionary.put(term, ns);
        }
        return dictionary;
    }*/
}
