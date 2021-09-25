package depth.first.search;

import java.util.ArrayList;
import java.util.List;

/**
 * lc 51/52 hard
 *
 * Analysis:
 *
 * 时间复杂度：
 *  该算法形式上采用了递归+回溯实现，本质还是对棋盘上每一个位置枚举，
 *  但加上剪枝，时间会比暴力穷举好很多。
 * 空间复杂度：O(1)
 *
 * Created by Administrator on 2017/4/14 0014.
 */
public class N_Queen {

    List<List<String>> ans = new ArrayList();

    public List<List<String>> solveNQueens(int n) {
        if (n == 1) {
            List<String> l = new ArrayList();
            l.add("Q");
            ans.add(l);
            return ans;
        }
        dfs(0, n, new ArrayList());
        return ans;
    }

    void dfs(int k, int n, List<String> mem) {
        if (k == n) {
            ans.add(new ArrayList(mem));
            return;
        }

        StringBuilder b = genString(n);
        for (int j = 0; j < n; j++) {
            if (check(k, j, n, mem)) {
                b = b.replace(j, j+1, "Q");
                mem.add(b.toString());
                dfs(k+1, n, mem);
                mem.remove(mem.size()-1);
                b = b.replace(j, j+1, ".");
            }
        }
    }

    boolean check(int i, int j, int n, List<String> mem) {
        for (int k = 0; k < i; k++) {
            if (mem.get(k).charAt(j) == 'Q') return false;
        }
        int x = j-1, y = i-1;
        while (x >= 0 && y >= 0) {
            if (mem.get(y).charAt(x) == 'Q') return false;
            y--; x--;
        }
        x = j+1; y = i-1;
        while (x < n && y >= 0) {
            if (mem.get(y).charAt(x) == 'Q') return false;
            y--; x++;
        }
        return true;
    }

    StringBuilder genString(int n) {
        StringBuilder b = new StringBuilder();
        for (int i = 0; i < n; i++) b.append(".");
        return b;
    }

}

