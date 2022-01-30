package string;

import java.util.HashMap;
import java.util.Map;

/**
 * lc 288 medium
 *
 * Analysis:
 * 文件系统首先想到dfs遍历，但是给的参数是字符串不具备树形结构，要自己转
 * 换个思路，看有其它的特征可以表示层级吗
 * 显然\t可以计算层级。
 * 并且抽象成树的话，每一个文件就是树中的叶节点。
 * 要做的就是遍历叶子节点。
 * 换行符就是分支，从上到下遍历，就像从右到左遍历树的每一个节点。
 *
 * 时间复杂度：O(n)
 * 空间复杂度：O(depth of path)
 *
 * @author Joseph
 * @since 2021-04-04 20:51
 */
public class LongestAbsoluteFilePath {

    public int lengthLongestPath(String input) {
        Map<Integer, Integer> level = new HashMap<>();
        String[] paths = input.split("\n");

        int i = 0;
        if (paths[0].equals("dir")) {
            i++;
            level.put(0, 3);
        }

        int max = 0;
        for ( ; i < paths.length; i++) {
            // 每一个路径应该都是以\t开头
            String path = paths[i];
            int c = countTab(path);
            level.put(c, path.length()-c);
            if (path.contains(".")) {
                int len = -1;
                for (int l = c; l >= 0; l--) {
                    // +1 代表层级间的/ 字符，也是参与计数的
                    len += level.get(l) + 1;
                }
                max = Math.max(max, len);
            }
        }
        return max;
    }

    int countTab(String path) {
        char[] c = path.toCharArray();
        int p = 0;
        for (int i = 0; i < c.length; i++) {
            if (c[i] == '\t') {
                p++;
            }
        }
        return p;
    }

}
