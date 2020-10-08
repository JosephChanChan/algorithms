import java.util.*;

/**
 * 剑指Offer 9 easy
 *
 * Analysis:
 * 因为队列是先进先出结构，栈底元素是队列头，如果单用一个栈无法完成操作。
 * 两个栈s1, s2 压入元素到s1，当要pop元素时，只能将s1的元素pop出到s2，否则无法拿到队列头。
 * 此时s1为空，s2的元素是s1的逆序，即队列头在栈顶，符合先进先出顺序。
 * 再压入元素直接进s1，此时s1的元素必定后于s2的元素入队，因为s2元素来自s1。
 *
 * 时间复杂度：push操作O(1) pop操作O(n)
 * 空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2020-10-08 20:07
 */
public class MockQueueUsingStack {

    private Stack<Integer> s1 = new Stack<>(), s2 = new Stack<>();

    public void appendTail(int value) {
        s1.push(value);
    }

    public int deleteHead() {
        if (s1.empty() && s2.empty()) return -1;
        if (!s2.empty()) return s2.pop();
        while (!s1.empty()) {
            s2.push(s1.pop());
        }
        return s2.pop();
    }
}
