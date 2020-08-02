package graphs;

/**
 * Dijkstra是荷兰计算机科学家 艾兹格·迪科斯彻发明的。
 * 专门用于解决 单源最短路径的算法。可解图中任意顶点到另一顶点的最短路径。
 * 在贪心专题的 FleeMaze中就是用该算法解决的，
 *
 * @author Joseph
 * @since 2020-08-01 16:42
 */
public class Dijkstra {

    /*
        维护visited数组代表某顶点的访问情况，distance数组代表从原点到此顶点的最短路径，
        如果要追溯一条原点到目标定点的最短路径还要再维护一个previous数组代表此点的来源点。
        算法步骤：
            1.设定起点S，加入visited
            2.在图中找到S连通的未访问过的顶点集合P[]
             2.1 计算P[]中每个顶点的最短路径，
                如果新的路径权值<旧的路径权值则更新，distance[P[i]] = distance[S] + S到P[i]的权值
                否则不更新
            3.从所有未访问过的顶点集合中选择出离原点距离最近的顶点设为 Next
            4.设 S = Next，继续新一轮计算，直到访问过所有顶点

        网友证明：
            Dijkstra算法给出了从S到G的各个顶点的最短通路长度。
            我们假设Ｇ中的每个顶点Ｖ都被赋予了一个标记L(V),它要么是一个数，要么是 ∞。假设P是G的顶点的集合，P包含S，满足：
            1）如果V属于P，则L（V）是从S到V的最短通路的长度，并且存在这样的从S到V的最短通路：通路上的顶点都在P中
            2）如果V不属于P，则L(V)是从S到V的满足下面限制的最短通路的长度：V是通路中唯一一个不属于P的顶点。
            我们可以用归纳法证明Dijkstra算法中的P符合上述定义的集合：
            1）当P中元素个数为1时，P={S},显然满足。
            2）假设P中元素个数为K时，P满足上述定义，
            先找出不在P中且带有最小标记的顶点U，标记为L(U), 可以证明从S到U的最短通路中除U外不包含不属于P的元素。
            设立命题：从 S 到 U 的最短通路中除 U 外还有其他不属于P的元素。
            因为若存在除 U 外其他顶点，则最短通路为 S P1 P2...Pn Q1 Q2...Qn U (P1,P2..Pn属于P,Q1,Q2,...Qn不属于P),
            则由性质2）最短通路长度为 L(Q1) + PATH(Q1,U) > L(U)，这是一个矛盾的点，我们已经假设了 S p1 p2 pn q1 qn u 是最短通路了，
            但是根据推论，这条通路还大于 L(U) 因为我们设定 L(U) 就是最短的。所以推论是不成立的，命题也就不成立。
            只有 Q1...Qn 属于P，才满足之前的设定。从而从S到U的最短通路长度由L(U)给出.
            现把U加入P中构成P' ,显然P'满足性质1)。
            取V不属于P',显然V也不属于P,那么从S到V的最短通路且满足除V外所有顶点都在P'中的通路有两种可能，1）包含 U，2）不包含U。
            对 1）S P1 P2...Pn U V = L(U)+W(U,V)
              2）S P1 P2..Pn V = L(V)
            显然二者中的最小给出了从S到V的最短通路且满足除V外所有顶点都在P'中的长度。
            从而 P' 含 K+1 个元素且满足 1), 2)。
            又归纳，命题得证！
     */

    private static int[][] GRAPHS = {
            {0, 1, 12, 0, 0, 0},//          1
            {1, 0, 0, 3, 0, 0},//           2
            {12, 0, 0, 4, 5, 0},//          3
            {0, 3, 4, 0, 13, 15},//         4
            {0, 0, 5, 13, 0, 4},//          5
            {0, 0, 0, 15, 4, 0},//          6
    };

    public static void main(String[] args) {
        Dijkstra test = new Dijkstra();
        boolean[] visited = new boolean[GRAPHS.length];
        int[] distance = new int[GRAPHS.length];
        test.doDijkstra(visited, distance, 0);
        System.out.println(distance[5]);
    }

    private void doDijkstra(boolean[] visited, int[] distance, int s) {
        // 初始化每个顶点的距离
        for (int i = 0; i < distance.length; i++) distance[i] = Integer.MAX_VALUE;

        // 原点s出发，初始化
        visited[s] = true;
        distance[s] = 0;

        int visitedCount = 1, peak = visited.length, next = -1;
        while (visitedCount < visited.length) {
            // 计算S连通的未访问过的每个顶点 P[]
            for (int i = 0; i < peak; i++) {
                if (GRAPHS[s][i] > 0 && !visited[i]) {
                    int curDis = distance[s] + GRAPHS[s][i];
                    if (curDis < distance[i]) distance[i] = curDis;
                }
            }
            // 从未访问过的顶点中选出离原点最近的顶点
            int minimum = Integer.MAX_VALUE;
            for (int i = 0; i < peak; i++) {
                if (!visited[i] && distance[i] < minimum) {
                    next = i;
                    minimum = distance[i];
                }
            }
            s = next;
            visitedCount++;
            visited[s] = true;
        }
    }
}
