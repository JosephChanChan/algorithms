package graphs;

import java.util.*;

/**
 * lc medium 1462
 *
 * @author Joseph
 * @since 2022/9/17
 */
public class CourseSchedule4 {

    /**
         朴素的做法，将关系构造成拓扑排序图，对每个query执行bfs搜索
         时间 O(numCourses * queries)
         bfs勉强可过

         尝试了双向bfs，发现不行，因为这是个有向图，双向bfs要求无向图，即从两个点都可互相访问，有向图只能从一个点到另一个点单向访问

         这题用dfs+dp的思路会做的更优，对每个搜索路径的节点记录本次搜索的结果
         bfs无法dp的原因是在访问时，不是一条单向的纵深路径，无法记录搜索结果
     */


    public List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
        return bfs(numCourses, prerequisites, queries);
    }

    List<Boolean> bfs(int numCourses, int[][] prerequisites, int[][] queries) {
        Map<Integer, List<Integer>> g = new HashMap<>();

        for (int i = 0; i < numCourses; i++) {
            List<Integer> q = new LinkedList<>();
            g.put(i, q);
        }
        for (int i = 0; i < prerequisites.length; i++) {
            int[] rel = prerequisites[i];
            int a = rel[0];
            int b = rel[1];
            // a是b的前继
            List<Integer> q = g.get(a);
            q.add(b);
        }
        List<Boolean> ans = new LinkedList<>();
        for (int i = 0; i < queries.length; i++) {
            int[] query = queries[i];
            ans.add(isPreNode(query, g));
        }
        return ans;
    }

    boolean isPreNode(int[] query, Map<Integer, List<Integer>> g) {
        int start = query[0];
        Queue<Integer> q = new LinkedList<>();
        Set<Integer> vis = new HashSet<>();
        q.add(start);
        vis.add(start);
        while (!q.isEmpty()) {
            int node = q.poll();
            List<Integer> nodes = g.get(node);
            for (Integer nei : nodes) {
                if (nei == query[1]) {
                    return true;
                }
                if (!vis.contains(nei)) {
                    q.add(nei);
                    vis.add(nei);
                }
            }
        }
        return false;
    }
}
