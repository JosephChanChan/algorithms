package breadth.first.search;

import java.util.*;

/**
 * leetcode 207 medium
 *
 * Analysis:
 *  拓扑排序
 *
 * 时间复杂度：O(n+m)
 * 空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2020-08-31 23:55
 */
public class CourseSchedule {

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        /*
            拓扑排序：
                将有向无环连通图排成一个线性表使得每个点满足图中的先修条件
                有向边的起点是终点的先修条件

            1.统计每个点的入度
            2.将边集转成邻接表
            3.入度0的点全部入队，遍历该点所有邻居的入度-1，入度减至0的入队
        */
        if (prerequisites.length == 0) return true;

        Map<Integer, Integer> d = countIndegree(numCourses, prerequisites);
        Map<Integer, List<Integer>> al = convert2AdjacentList(prerequisites);

        Queue<Integer> q = new LinkedList<>();
        List<Integer> order = new ArrayList<>();

        for (int i = 0; i < numCourses; i++) {
            if (d.get(i) == 0) q.add(i);
        }

        while(!q.isEmpty()) {
            int node = q.poll();
            order.add(node);

            List<Integer> nextCourses = al.get(node);
            if (null == nextCourses) continue;
            for (Integer course : nextCourses) {
                int indegree = d.get(course) - 1;
                d.put(course, indegree);
                if (indegree == 0) {
                    q.add(course);
                }
            }
        }
        return order.size() == numCourses;
    }

    private Map<Integer, List<Integer>> convert2AdjacentList(int[][] pre) {
        Map<Integer, List<Integer>> al = new HashMap<>();
        for (int i = 0; i < pre.length; i++) {
            List<Integer> list = al.getOrDefault(pre[i][1], new ArrayList<>());
            list.add(pre[i][0]);
            al.put(pre[i][1], list);
        }
        return al;
    }

    private Map<Integer, Integer> countIndegree(int numCourses, int[][] pre) {
        Map<Integer, Integer> indegree = new HashMap<>();
        for (int i = 0; i < pre.length; i++) {
            int c = indegree.getOrDefault(pre[i][0], 0);
            indegree.put(pre[i][0], ++c);
        }
        for (int i = 0; i < numCourses; i++) {
            if (!indegree.containsKey(i)) indegree.put(i, 0);
        }
        return indegree;
    }

}
