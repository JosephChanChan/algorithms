package tables;

import java.util.Deque;
import java.util.LinkedList;

/**
 * lc 71 medium
 *
 * Analysis:
 * 每个目录入队，遇.跳过，遇 .. 删除队尾目录
 *
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2021-07-04 11:19
 */
public class SimplifyPath {

    Deque<String> que = new LinkedList<>();

    public String simplifyPath(String path) {
        if (path.length() <= 1) return path;

        while (path.length() > 0) {
            if (path.length() == 1 && path.equals("/")) break;

            int j = take(path);
            String sub = path.substring(0, j);
            sub = sub.replaceAll("/+", "");

            if (sub.equals("..")) {
                if (!que.isEmpty()) que.removeLast();
            }
            else if (sub.length() > 0 && !sub.equals(".")) {
                que.addLast(sub);
            }
            path = path.substring(j);
        }
        StringBuilder b = new StringBuilder("/");
        while (!que.isEmpty()) {
            //System.out.println(que.peekFirst());
            b.append(que.removeFirst()).append("/");
        }
        if (b.length() > 1) b.delete(b.length()-1, b.length());
        return b.toString();
    }

    int take(String path) {
        // 每个目录以 / 开始，多个//合并为1个/，3个.以上是目录
        int j = 0, chars = 0;
        while (j < path.length()) {
            if (path.charAt(j) == '/') {
                if (chars > 0) return j;
                j++; continue;
            }
            j++;
            chars++;
        }
        return j;
    }
}
