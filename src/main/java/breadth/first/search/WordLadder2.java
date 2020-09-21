package breadth.first.search;

import java.util.*;

/**
 * leetcode 126 hard
 *
 * Analysis:
 *  bfs1是leetcode官方题解，走一遍bfs，记录下从起点到每个点的最近距离，如果p离起点有2条长度不一的路径，
 * 则从起点到p肯定会先从较短的路径走到p（因为bfs是层级搜索），此时记录下p的距离，当从另一条较长距离的路径到p时发现p已经有距离了，
 * 代表之前有一条更短的路径到过p，所以当前Path没必要继续向下搜索。
 *
 *  bfs+dfs的做法更加好理解，先从终点反向遍历到每个点并记录它们都终点的距离。
 *  再从起点开始dfs，每次选择离终点更近的点搜索。
 *
 * 时间复杂度：O(n*length*neighbors)
 * 空间复杂度：O(n*neighbors)
 *
 * @author Joseph
 * @since 2020-09-05 23:29
 */
public class WordLadder2 {


    boolean flag = false;
    String begin, end ;
    int max = Integer.MAX_VALUE;
    List<List<String>> ans = new ArrayList<>();
    Map<String, List<String>> al = new HashMap<>();
    Map<String, Integer> dFromE = new HashMap<>();

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        begin = beginWord;
        end = endWord;

        if (begin.equals(end)) {
            List<String> list = new ArrayList<>();
            list.add(end);
            ans.add(list);
            return ans;
        }

        wordList.add(beginWord);
        Map<String, List<String>> vagues = vagueList(wordList);
        if (!flag) return ans;
        buildAdjacentList(vagues, wordList);

        normalBfs(end);
        Integer stair = dFromE.get(begin);
        if (null == stair) return ans;

        List<String> path = new ArrayList<>();
        path.add(begin);
        dfs(begin, stair - 1, path);
        return ans;
    }

    private void dfs(String p, int stair, List<String> path) {
        if (p.equals(end)) {
            ans.add(new ArrayList<>(path));
            return;
        }

        List<String> neighbors = al.get(p);
        for (String n : neighbors) {
            if (stair == dFromE.get(n)) {
                path.add(n);
                dfs(n, stair-1, path);
                path.remove(path.size()-1);
            }
        }
    }

    private void normalBfs(String end) {
        Queue<String> q = new LinkedList<>();
        Set<String> v = new HashSet<>();
        q.add(end);
        v.add(end);

        int stair = -1;
        while (!q.isEmpty()) {
            stair++;
            int size = q.size();
            for (int i = 0; i < size; i++) {
                String p = q.poll();
                dFromE.put(p, stair);
                List<String> neighbors = al.get(p);
                for (String n : neighbors) {
                    if (!v.contains(n)) {
                        v.add(n);
                        q.add(n);
                    }
                }
            }
        }
    }

    private void bfs1(String beginWord, String endWord) {
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
            if (s.equals(end)) flag = true;
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
