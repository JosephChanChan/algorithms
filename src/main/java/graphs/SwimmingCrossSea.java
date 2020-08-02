package graphs;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

/**
 * 2020阿里校招笔试，凉经
 * 之所以要写是因为这个问题可以用之前学过的一个算法解，但是考试中没想到，太可惜了
 *
 * Question Description:
 *  万恶的小明这次来游泳了。
 *
 *  有个n*m的矩阵，由字符'S'和'C'组成，S表示海洋，C表示陆地，从陆地到陆地要消耗体力3，从海洋到海洋要消耗体力2，
 *  从陆地到海洋或者从海洋到陆地都需要消耗体力5。
 *
 *  给定任意起点(i, j)和任意终点(x, y)，求小明从起点到终点要消耗最少多少体力？
 *
 * Analysis:
 *  算是最短路问题的一种，每个格子的消耗体力就是路径权值，从矩阵中寻找最短路径到终点。
 *  只不过这题表现形式不是图的邻接矩阵，用Dijkstra直接从矩阵的原点遍历4个方向直到所有顶点都被访问过。
 *  用优先队列存所有计算过的边，可以优化 "从所有未访问过的顶点集合中选择出离原点距离最近的顶点设为Next" 这一步，
 *  因为所有未被 visited 的顶点分为两类，没被触达的顶点它们的距离还未被更新自然是无穷大，
 *  与 visited 里顶点相邻的顶点肯定被计算过距离，并且它们都有至少一条边被加入了优先队列，所以直接从队列中取出累加权值最小的一条边
 *  这条边连接的自然是目前离原点最近的顶点。如果不用优先队列，那么找Next这一步就要用 O(n*m)时间去找
 *
 * @author Joseph
 * @since 2020-08-01 17:42
 */
public class SwimmingCrossSea {

    /*
        test case
        3 3
        SCS
        CSC
        CSC
        0 0 2 2
     */

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 输入 n*m 的矩阵
        int n, m ;
        String[] nums = scanner.nextLine().split(" ");
        n = Integer.parseInt(nums[0]);
        m = Integer.parseInt(nums[1]);

        char[][] matrix = new char[n][m];
        for (int i = 0; i < n; i++) {
            char[] c = scanner.nextLine().toCharArray();
            for (int j = 0; j < m; j++) {
                matrix[i][j] = c[j];
            }
        }

        // 输入起点和终点
        int i, j, x, y ;
        i = scanner.nextInt();
        j = scanner.nextInt();
        y = scanner.nextInt();
        x = scanner.nextInt();

        SwimmingCrossSea test = new SwimmingCrossSea();
        int answer = test.doDijkstra(matrix, i, j, x, y);
        System.out.println(answer);

    }

    private int doDijkstra(char[][] matrix, int i, int j, int x, int y) {
        int n = matrix.length, m = matrix[0].length, visitedCount ;
        int[][] distance = new int[n][m];
        boolean[][] visited = new boolean[n][m];
        // 按权值升序存边信息，每次计算完一个顶点后从这里取出一条边
        Queue<Road> queue = new PriorityQueue<>(n * m);

        // 初始化
        for (int p = 0; p < n; p++) {
            for (int q = 0; q < m; q++) {
                distance[p][q] = Integer.MAX_VALUE;
            }
        }

        distance[i][j] = 0;
        visited[i][j] = true;
        visitedCount = 1;

        while (visitedCount == 1 || !queue.isEmpty()) {
            // 每个顶点有4条路可以走，上下左右连通4个顶点
            if (i > 0 && !visited[i-1][j]) {
                calcPeak(matrix, i, j, j, i-1, distance);
                queue.add(addRoad(i, j, j, i-1, distance, matrix));
            }
            if (i < n - 1 && !visited[i+1][j]) {
                calcPeak(matrix, i, j, j, i+1, distance);
                queue.add(addRoad(i, j, j, i+1, distance, matrix));
            }
            if (j > 0 && !visited[i][j-1]) {
                calcPeak(matrix, i, j, j-1, i, distance);
                queue.add(addRoad(i, j, j-1, i, distance,  matrix));
            }
            if (j < m - 1 && !visited[i][j+1]) {
                calcPeak(matrix, i, j, j+1, i, distance);
                queue.add(addRoad(i, j, j+1, i, distance, matrix));
            }
            Road minimumRoad = getMinimumUnvisited(queue, visited);
            if (null == minimumRoad) break;
            i = minimumRoad.i;
            j = minimumRoad.j;
            visitedCount++;
            visited[i][j] = true;
        }
        return distance[y][x];
    }

    private Road getMinimumUnvisited(Queue<Road> queue, boolean[][] visited) {
        while (!queue.isEmpty()) {
            Road road = queue.poll();
            if (!visited[road.i][road.j]) return road;
        }
        return null;
    }

    private Road addRoad(int i, int j, int x, int y, int[][] distance, char[][] matrix) {
        Road road = new Road();
        road.i = y;
        road.j = x;
        road.weight = distance[i][j] + roadCost(i, j, x, y, matrix);
        return road;
    }

    private int calcPeak(char[][] matrix, int i, int j, int x, int y, int[][] distance) {
        int curDis = distance[i][j] + roadCost(i, j, x, y, matrix);
        if (curDis < distance[y][x]) distance[y][x] = curDis;
        return curDis;
    }

    private int roadCost(int i, int j, int x, int y, char[][] matrix) {
        return matrix[i][j] == matrix[y][x] ? matrix[i][j] == 'S' ? 2 : 3 : 5;
    }

    class Road implements Comparable {
        int weight;
        // i, j 是该边连通的顶点
        int i;
        int j;

        @Override
        public int compareTo(Object o) {
            return ((Road) o).weight < this.weight ? 1 : -1;
        }
    }
}
