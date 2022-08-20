package graphs;

import java.util.*;

/**
 * @author Joseph
 * @since 2022/7/23
 */
public class RebuildSequence {

    /**
         其实nums序列可以看成是一个有向无环图，图中每个节点是nums[i]
         边是nums[i] -> nums[j], i < j
         nums序列如果是最短的且唯一，seq[i]又是nums的子序列
         那么所有seq[i]序列也可以构成一个有向无环图，且这个图应该有一条路径，从一个起点到一个终点恰好构成nums序列
         不用担心有另外的路径可以构成nums序列，因为题目保证每一个seq[i]是惟一的
     */

    Map<Integer, List<Integer>> g = new HashMap<>();

    public boolean sequenceReconstruction(int[] nums, int[][] sequences) {
        buildGraph(sequences);
        if (!g.containsKey(nums[0])) {
            System.out.println("起点="+nums[0]+" 不存在");
            return false;
        }
        // 看看seq[i]建的有向无环图可不可以走完nums
        return dfs(1, nums, g.get(nums[0]));
    }

    boolean dfs(int peek, int[] a, List<Integer> peeks) {
        if (peek == a.length) {
            return true;
        }
        boolean found = false;
        for (int i = 0; i < peeks.size(); i++) {
            if (a[peek] == peeks.get(i)) {
                found = true;
                break;
            }
        }
        if (!found) {
            //System.out.println("节点="+a[peek]+" 无法到达");
            return false;
        }
        return dfs(peek+1, a, g.get(a[peek]));
    }

    void buildGraph(int[][] seqs) {
        for (int i = 0; i < seqs.length; i++) {
            int[] seq = seqs[i];
            for (int j = 0; j < seq.length; j++) {
                List<Integer> nodes = g.getOrDefault(seq[j], new LinkedList<>());
                if (j < seq.length-1) {
                    nodes.add(seq[j+1]);
                }
                g.put(seq[j], nodes);
            }
        }
    }
}
