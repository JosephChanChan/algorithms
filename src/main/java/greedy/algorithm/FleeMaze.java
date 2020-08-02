package greedy.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeSet;

/**
 * Question Description:
 *  你来到一个迷宫前。该迷宫由若干个房间组成，每个房间都有一些黄金，第一次进入这个房间，你就可以得到这些黄金。
 * 还有若干双向道路连结这些房间，你沿着这些道路从一个房间走到另外一个房间需要一些时间。游戏规定了你的起点和终点房间，
 * 你首要目标是从起点尽快到达终点，在满足首要目标的前提下，使得你拿到黄金总和尽可能大。
 * 现在问题来了，给定房间、道路、黄金、起点和终点等全部信息，你能计算在尽快离开迷宫的前提下，你能拿到最多的黄金是多少么？
 *
 * Analysis:
 *  Dijkstra算法，对于单源图（指定某个顶点出发到另一个顶点）的最短路径算法。
 *
 * created by Joseph
 * at 2018/9/3 18:08
 */
public class FleeMaze {

    static int peakCount = 0, roadCount = 0, start = 0, end = 0, lessWeight = 0, maxGold = 0;
    static int[] peakGold,                  // 每个顶点存储的黄金数
                 minimumRoadWeight ;        // 原点到每个顶点的最短连通路径长
    static int[][] peakArray ;              // 顶点邻接矩阵

    static Set<Integer> visitedPeak = new TreeSet<>();                      // 到访过的顶点
    static PriorityQueue<RoadToPeak> priorityQueue = new PriorityQueue();

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in),1 << 16);

        try {
            String temp = reader.readLine();
            String[] params = temp.split(" ");
            peakCount = Integer.parseInt(params[0]);
            roadCount = Integer.parseInt(params[1]);
            start = Integer.parseInt(params[2]) + 1;
            end = Integer.parseInt(params[3]) + 1;

            temp = reader.readLine();
            params = temp.split(" ");
            peakGold = new int[peakCount + 1];
            for (int i = 0; i < peakCount; i++){
                peakGold[i + 1] = Integer.parseInt(params[i]);
            }

            // 跳过第0位，让顶点从1开始编号
            peakArray = new int[peakCount + 1][peakCount + 1];
            minimumRoadWeight = new int[peakCount + 1];

            int peakA, peakB, weight ;
            for (int j = 0; j < roadCount; j++){
                temp = reader.readLine();
                params = temp.split(" ");

                peakA = Integer.parseInt(params[0]) + 1;// 跳过第0编号
                peakB = Integer.parseInt(params[1]) + 1;// 跳过第0编号
                weight = Integer.parseInt(params[2]);

                // A、B顶点连通，边权值是 weight
                peakArray[peakA][peakB] = weight;
                peakArray[peakB][peakA] = weight;
            }

            dijkstraAlgorithm();

            System.out.println(minimumRoadWeight[end] + " " + maxGold);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void dijkstraAlgorithm(){
        searchRoad(start, 0);

        int currentPeak = start;
        visitedPeak.add(currentPeak);
        maxGold += peakGold[currentPeak];
        while (visitedPeak.size() < peakCount){
            RoadToPeak minimumRoad = priorityQueue.poll();
            int destination = minimumRoad.getDestination();
            if (visitedPeak.contains(destination)){
                continue;
            }
            visitedPeak.add(destination);
            minimumRoadWeight[destination] = minimumRoad.getWeight();
            searchRoad(minimumRoad.getDestination(), minimumRoadWeight[destination]);
            currentPeak = destination;
            maxGold += peakGold[currentPeak];
        }
    }

    /**
     * 从指定顶点搜索未到访的连通顶点，将边加入优先队列
     *
     * @param original 指定顶点
     * @param previousWeight 此前积累的路径权值
     */
    private static void searchRoad(int original, int previousWeight){
        RoadToPeak roadToPeak ;
        for (int i = 1; i <= peakCount; i++){
            if (
                    peakArray[original][i] > 0 &&
                    !visitedPeak.contains(i)
               )
            {
                roadToPeak = new RoadToPeak();
                roadToPeak.setWeight(peakArray[original][i] + previousWeight);
                roadToPeak.setDestination(i);
                priorityQueue.add(roadToPeak);
            }
        }
    }

    static class RoadToPeak implements Comparable {

        int weight;         // 权值
        int destination;    // 边连通的目标顶点

        @Override
        public int compareTo(Object o) {
            int weight = ((RoadToPeak) o).getWeight();
            if (weight > this.weight){
                return -1;
            }
            else if (weight < this.weight){
                return 1;
            }
            else {
                return 0;
            }
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public int getDestination() {
            return destination;
        }

        public void setDestination(int destination) {
            this.destination = destination;
        }
    }
}
