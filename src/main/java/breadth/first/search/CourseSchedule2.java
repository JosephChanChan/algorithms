package breadth.first.search;

import java.util.*;

/**
 * lc 210 medium
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
public class CourseSchedule2 {

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> al = new HashMap<>();
        Queue<Integer> q = new LinkedList<>();
        int[] order = new int[numCourses];

        Map<Integer, Integer> d = indegreeAndAdjacentList(numCourses, prerequisites, q, al);
        if (q.isEmpty()) return new int[0];

        int i = 0;
        while (!q.isEmpty()) {
            int course = q.poll();
            order[i++] = course;

            List<Integer> next = al.get(course);
            if (null == next) continue;
            for (Integer nextCourse : next) {
                int indegree = d.get(nextCourse);
                if (--indegree == 0) {
                    q.add(nextCourse);
                }
                d.put(nextCourse, indegree);
            }
        }
        return i != numCourses ? new int[0] : order;
    }

    private Map<Integer, Integer> indegreeAndAdjacentList(int num, int[][] pre, Queue<Integer> q, Map<Integer, List<Integer>> al) {
        Map<Integer, Integer> d = new HashMap<>();
        for (int i = 0; i < pre.length; i++) {
            // 计算入度
            int c = d.getOrDefault(pre[i][0], 0);
            d.put(pre[i][0], ++c);
            // 转邻接表
            List<Integer> list = al.getOrDefault(pre[i][1], new ArrayList<>());
            list.add(pre[i][0]);
            al.put(pre[i][1], list);
        }
        for (int i = 0; i < num; i++) {
            if (!d.containsKey(i)) q.add(i);
        }
        return d;
    }

}
