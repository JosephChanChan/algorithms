package stack;

import java.util.Stack;

/**
 * lc 1003 medium
 *
 * Analysis:
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2022/7/15
 */
public class CheckWordsValidAfterReplaced {

    /**
         栈
         s是由`abc`组成，s串无论怎么样都是有限的排列
         abc -> a abc bc
         abc -> ab abc c
         abc -> abc abc
         把后续插入的abc排掉后剩余的Sleft + Sright仍然需要构成一个`abc`组成的串
         如果是一个有效的s串，只需要把k个`abc`子串排除，应该就剩一个空串
         那么按照上面有限的排列，从左到右把字符依次入栈，组成一个abc时就弹出，
         到最后栈中应该是空的
     */

    Stack<Integer> st = new Stack<>();

    public boolean isValid(String s) {
        char[] c = s.toCharArray();
        int n = c.length;
        if (n < 3 || (n % 3) != 0) {
            return false;
        }
        st.push(0);
        for (int i = 1; i < n; i++) {
            if (c[i] == 'c' && st.size() >= 2) {
                char c1 = c[st.pop()];
                char c2 = c[st.pop()];
                if (c2 != 'a' || c1 != 'b') {
                    return false;
                }
                continue;
            }
            st.push(i);
        }
        //System.out.println(st.size());
        return st.size() == 0;
    }
}
