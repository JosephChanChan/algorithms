package tables;

import java.util.Stack;

/**
 * lc 394 medium
 *
 * Analysis:
 *  数字存s1
 * 左括号和字母存s2
 * 字母拼接在s2最近的字符串
 * 对于嵌套的 k1[...k2[...]]
 * 要先从内部k2展开，拼在k1后继续展开
 * 那么可以把k2展开后压入s2中，下次展开时把k2和k1的字符弹出来拼在一起展开
 *
 * 时间复杂度：O(s)
 * 空间复杂度：O(s)
 *
 * @author Joseph
 * @since 2021-03-20 22:41
 */
public class DecodeString {

    Stack<Integer> s1 = new Stack<>();
    Stack<String> s2 = new Stack<>();

    public String decodeString(String s) {
        if (null == s || s.length() == 0) return null;

        char[] c = s.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] >= '0' && c[i] <= '9') {
                String n = new String();
                while (c[i] >= '0' && c[i] <= '9') n = n.concat(String.valueOf(c[i++]));
                i--;
                s1.push(Integer.parseInt(n));
            }
            else if (c[i] == ']') {
                String t = get();
                s2.push(dev(t, s1.pop()));
            }
            else {
                s2.push(String.valueOf(c[i]));
            }
        }
        Stack<String> ans = new Stack<>();
        while (!s2.isEmpty()) ans.push(s2.pop());
        StringBuilder b = new StringBuilder();
        while (!ans.isEmpty()) b.append(ans.pop());
        return b.toString();
    }

    String get() {
        Stack<String> t = new Stack<>();
        StringBuilder b = new StringBuilder();
        while (!s2.peek().equals("[")) t.push(s2.pop());
        s2.pop();
        while (!t.isEmpty()) b.append(t.pop());
        return b.toString();
    }

    String dev(String s, int k) {
        String t = new String();
        for (int i = 0; i < k; i++) t = t.concat(s);
        return t;
    }
}
