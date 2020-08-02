package graphs;

/**
 * 广度优先搜索
 * 广搜整体思想是，从图中任意一个节点出发，访问到该节点后（可以对其做操作），搜索该节点链接的临节点，
 * 将临界点放入队列中，这视为一个循环操作完成。
 * 下一步从队列中取出节点，访问 -> 搜索临节点 -> 放入队列。
 * 循环至队列为空，至此图中所有节点被访问到，算法结束。
 *
 * Created by Joseph on 2017/6/10.
 */
public class BreathFirstSearch {

    private static final int MATRIX_MAX = 7;

    private static int[] queue = new int[MATRIX_MAX];
    private static int[] visited = new int[MATRIX_MAX];
    private static int[][] matrix = {
            {0, 1, 1, 0, 0, 1, 1},
            {1, 0, 0, 0, 0, 0, 0},
            {1, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 1, 0},
            {0, 0, 0, 1, 0, 1, 1},
            {1, 0, 0, 1, 1, 0, 0},
            {1, 0, 0, 0, 1, 0, 0}
    };

    public static void main(String[] args) {
        bfsSearch(0);
    }

    private static void bfsSearch(int start) {
        int head = 0, tail = 0;
        queue[tail++] = start;
        while (head != tail) {
            int t = queue[head++];
            System.out.println(t);
            visited[t] = 1;
            // 查找该节点的临节点
            for (int i = 0; i < MATRIX_MAX; i++) {
                if (matrix[t][i] == 1) {
                    // 如果没被访问
                    if (visited[i] == 0) {
                        // 入队
                        queue[tail++] = i;
                        visited[i] = 1;
                    }
                }
            }
        }
    }
}
