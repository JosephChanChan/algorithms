
import java.util.*;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class MainTest {



    static int vis = 1000;
    static int[] ans ;
    // 右下左上
    static int[][] d = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public static void main(String[] args) {
        // Scanner input=new Scanner(System.in);
        // String str=input.next();
        System.out.println("hello world");
        MainTest m = new MainTest();
        int[] p = m.calc(new int[][]{{1,2,3}, {4,5,6}, {7,8,9}});
        for (int i = 0; i < p.length; i++) {
            System.out.println(p[i]);
        }
    }

    int[] calc(int[][] a) {
        int n = a.length;
        int m = a[0].length;
        ans = new int[n*m];
        System.out.println("calc begin");

        int done = 0, dirIndex = 0;
        int x = 0, y = 0;
        ans[0] = a[0][0];
        a[0][0] = 1000;
        done++;

        while (done < ans.length) {
            int[] dir = d[dirIndex];
            // 向一个方向遍历，直到遇到边界或者已访问过的格子
            while (true) {
                int i = y + dir[1];
                int j = x + dir[0];
                System.out.println("i="+i+" j="+j);
                // 到达边界或已访问过的格子
                if ((i < 0 || i >= n) || (j < 0 || j >= m) || a[i][j] == 1000) {
                    break;
                }
                ans[done++] = a[i][j];
                a[y][x] = 1000;
                // 更新坐标
                y = i;
                x = j;
            }
            // 更新方向
            dirIndex = dirIndex + 1 == 4 ? 0 : dirIndex + 1;
        }
        return ans;
    }




}
