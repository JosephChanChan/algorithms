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
 * 算法描述：
 * 1、设定集合P[]，优先队列Q，距离数组D[]。对原图G各个顶点编号 1 ~ n。W(P1,P2) 是P1到P2的边权值。
 * 2、选择原点 S 出发，将 S 所有边加入 Q。
 * 3、从 Q 中取出权值最小的边，该边假设为 m，m 连通顶点 T，若 T 不属于 P 则选择T访问，否则舍弃 m，继续从Q中取出下一条边。
 * 4、已经选择出 T，得到了 S 到 T的最短距离，设置 D[T] = D[S] + W(S,T)，
 * 排除连通点已经在 P 中的边，将 T 的剩下的边的权值加上 D[T](因为是从原点到T的最短路径长，再从T到各个边的顶点，自然要加上此前的路径)。
 * 然后加入Q。
 * 5、将 T 设置为 S，循环第3步，直到 P 的顶点等于原图 G 所有顶点。
 *
 * 证明：
 * Dijkstra算法给出了从S到G的各个顶点的最短通路长度。
 * 我们假设Ｇ中的每个顶点Ｖ都被赋予了一个标记L(V),它要么是一个数，要么是 ∞。假设P是G的顶点的集合，P包含S，满足：
 * 1）如果V属于P，则L（V）是从S到V的最短通路的长度，并且存在这样的从S到V的最短通路：通路上的顶点都在P中
 * 2）如果V不属于P，则L(V)是从S到V的满足下面限制的最短通路的长度：V是通路中唯一一个不属于P的顶点。
 *
 * 我们可以用归纳法证明Dijkstra算法中的P符合上述定义的集合：
 * 1）当P中元素个数为1时，P={S},显然满足。
 * 2）假设P中元素个数为K时，P满足上述定义，
 *    先找出不在P中且带有最小标记的顶点U，标记为L(U), 可以证明从S到U的最短通路中除U外不包含不属于P的元素。
 * 设立命题：从 S 到 U 的最短通路中除 U 外还有其他不属于P的元素。
 * 因为若存在除 U 外其他顶点，则最短通路为 S P1 P2...Pn Q1 Q2...Qn U (P1,P2..Pn属于P,Q1,Q2,...Qn不属于P),
 * 则由性质2）最短通路长度为 L(Q1) + PATH(Q1,U) > L(U)，这是一个矛盾的点，我们已经假设了 S p1 p2 pn q1 qn u 是最短通路了，
 * 但是根据推论，这条通路还大于 L(U) 因为我们设定 L(U) 就是最短的。所以推论是不成立的，命题也就不成立。
 * 只有 Q1...Qn 属于P，才满足之前的设定。从而从S到U的最短通路长度由L(U)给出.
 * 现把U加入P中构成P' ,显然P'满足性质1)。
 * 取V不属于P',显然V也不属于P,那么从S到V的最短通路且满足除V外所有顶点都在P'中的通路有两种可能，1）包含 U，2）不包含U。
 * 对 1）S P1 P2...Pn U V = L(U)+W(U,V)
 *    2）S P1 P2..Pn V = L(V)
 * 显然二者中的最小给出了从S到V的最短通路且满足除V外所有顶点都在P'中的长度。
 * 从而 P' 含 K+1 个元素且满足 1), 2)。
 * 又归纳，命题得证！
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
