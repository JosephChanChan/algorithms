package graphs;

import java.util.*;

/**
 * lc 2039 medium
 *
 * Analysis:
 * 时间复杂度：O(n)
 * 空间复杂度：O(n^2)
 *
 * @author Joseph
 * @since 2022/7/28
 */
public class TheTimeNetworkIdle {

    /**
         网络中最后一个节点收到最后一个数据包的时间决定空闲时间
         如果所有节点的patience都是相同的，那么最后一个收到数据包的节点肯定是路径最长的，但是有patience变量就不同了
         一个数据包从发送到收到的时间是节点到0点的路径和，设为t
         第一个数据包收到时间显然为t，那么最后一个数据包的发送时间必然 <= t-1，可以根据patience算出最后一个数据包发送时间
         ((t-1)/patience) * patience 是最后一个数据包发送时间，再根据路径和算出这个节点的最后一个数据包收到时间
         取所有节点最后一个数据包收到时间 max+1就是空闲时间

         从0点bfs搜索每个节点，然后算出从0点到它们的距离，再算出该节点的最后数据包收到时间
     */

    Set<Integer> vis = new HashSet<>();
    LinkedList<Integer> q = new LinkedList<>();
    Map<Integer, LinkedList<Integer>> g = new HashMap<>();

    public int networkBecomesIdle(int[][] edges, int[] patience) {
        for (int i = 0; i < edges.length; i++) {
            LinkedList<Integer> l1 = g.getOrDefault(edges[i][0], new LinkedList<>());
            l1.add(edges[i][1]);
            g.put(edges[i][0], l1);

            LinkedList<Integer> l2 = g.getOrDefault(edges[i][1], new LinkedList<>());
            l2.add(edges[i][0]);
            g.put(edges[i][1], l2);
        }

        q.add(0);
        vis.add(0);

        int ans = 0;
        int d = -1;
        while (!q.isEmpty()) {
            d++;
            int c = q.size();
            for (int i = 0; i < c; i++) {
                int node = q.removeFirst();

                // 扩展邻节点
                LinkedList<Integer> list = g.get(node);
                for (Integer p : list) {
                    if (!vis.contains(p)) {
                        vis.add(p);
                        q.add(p);
                    }
                }
                // 计算点的最后一个数据包收发时间
                if (node > 0) {
                    int firstPacketReceiveTime = (d << 1);
                    int lastPacketSendTime = ((firstPacketReceiveTime - 1) / patience[node]) * patience[node];
                    int lastPacketReceiveTime = lastPacketSendTime + firstPacketReceiveTime;
                    //System.out.println("node="+node+" firstPacketReceiveTime="+firstPacketReceiveTime+"  lastPacketSendTime="+lastPacketSendTime+" lastPacketReceiveTime="+lastPacketReceiveTime);
                    ans = Math.max(ans, lastPacketReceiveTime);
                }
            }
        }
        return ans + 1;
    }
}
