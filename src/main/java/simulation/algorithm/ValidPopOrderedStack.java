package simulation.algorithm;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 剑指Offer 31 medium
 *
 * Analysis:
 *  初始化按出栈序列，计算入栈元素并记录入栈边界。
 *  遍历出栈元素，若出栈元素：
 *    1.在入栈边界外，则(边界~此元素)中间元素全部入栈
 *    2.在入栈边界内，此元素匹配辅助栈顶元素，不匹配则出栈顺序有误
 *
 *  边界参数：
 *    pushed队列为空
 *
 *  时间复杂度：O(n)
 *  空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2020-11-22 17:16
 */
public class ValidPopOrderedStack {

    Deque<Integer> stack ;

    int border ;
    int[] orderMapping = new int[1000];

    public boolean validateStackSequences(int[] pushed, int[] popped) {
        if (pushed.length == 0 && popped.length == 0) return true;

        stack = new ArrayDeque<>(pushed.length);
        int first = popped[0];
        boolean touched = false;
        for (int i = 0; i < pushed.length; ++i) {
            orderMapping[pushed[i]] = i;
            if (!touched && first == pushed[i]) {
                border = i;
                touched = true;
            }
            if (!touched) stack.add(pushed[i]);
        }

        // 计算出栈序列
        boolean valid = true;
        for (int i = 1; i < popped.length && valid; i++) {
            int idx = orderMapping[popped[i]];
            if (idx > border) {
                for (int j = border + 1; j < idx; j++) {
                    stack.add(pushed[j]);
                }
                border = idx;
            }
            else {
                if (popped[i] != stack.pollLast()) {
                    valid = false;
                }
            }
        }
        return valid;
    }
}
