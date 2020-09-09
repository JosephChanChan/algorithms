package breadth.first.search;

import java.util.*;

/**
 * leetcode 126 hard
 *
 * Analysis:
 * 时间复杂度：
 * 空间复杂度：
 *
 * @author Joseph
 * @since 2020-09-05 23:29
 */
public class WordLadder2 {


    int max = Integer.MAX_VALUE;
    List<List<String>> ans = new ArrayList<>();
    Map<String, List<String>> al = new HashMap<>();

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        wordList.add(beginWord);
        Map<String, List<String>> vagues = vagueList(wordList);
        buildAdjacentList(vagues, wordList);

        Map<String, Integer> d = new HashMap<>();
        d.put(beginWord, 0);

        Queue<Node> q = new LinkedList<>();
        List<String> l = new ArrayList<>();
        l.add(beginWord);
        q.offer(new Node(beginWord, l));

        while (!q.isEmpty()) {
            Node n = q.poll();
            int cost = d.get(n.node);
            List<String> path = n.path;
            List<String> negibhors = al.get(n.node);
            for (String s : negibhors) {
                // 计算 d[]
                int c = d.getOrDefault(s, max);
                if (cost + 1 <= c) {
                    d.put(s, cost + 1);
                    List<String> nextPath = new ArrayList<>(path);
                    nextPath.add(s);
                    if (s.equals(endWord)) {
                        ans.add(new ArrayList<>(nextPath));
                    }
                    else {
                        q.add(new Node(s, nextPath));
                    }
                }
            }
        }
        return ans;
    }

    class Node {
        String node;
        List<String> path;

        public Node(String node, List<String> path) {
            this.node = node;
            this.path = path;
        }
    }

    private void buildAdjacentList(Map<String, List<String>> v, List<String> vertexes) {
        for (String vertex : vertexes) {
            List<String> list = new ArrayList<>();
            Set<String> added = new HashSet<>();

            char[] a = vertex.toCharArray();
            for (int i = 0; i < a.length; i++) {
                char c = a[i];
                a[i] = '*';
                List<String> neighbors = v.get(new String(a));
                for (String n : neighbors) {
                    if (n.equals(vertex) || added.contains(n)) continue;
                    added.add(n);
                    list.add(n);
                }
                a[i] = c;
            }
            al.put(vertex, list);
        }
    }

    private Map<String, List<String>> vagueList(List<String> list) {
        Map<String, List<String>> v = new HashMap<>();
        for (String s : list) {
            char[] a = s.toCharArray();
            for (int i = 0; i < a.length; i++) {
                char c = a[i];
                a[i] = '*';
                String k = new String(a);
                List<String> l = v.getOrDefault(k, new ArrayList<>());
                l.add(s);
                v.put(k, l);
                a[i] = c;
            }
        }
        return v;
    }
}
