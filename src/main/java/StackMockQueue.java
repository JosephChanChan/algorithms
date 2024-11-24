import java.util.*;

/**
 * @author Joseph
 * @since 2020-04-26 20:41
 *
 * Question Description:
 *  腾讯实习笔试
 *  用两个栈实现一个队列，要求提供 "add" 加入队尾，"peek" 查看队首元素但是不删除，"poll" 删除队首元素。
 *
 *  输入：
 *  n组数据，每组数据：
 *  第一行数据为n代表接下来会有n行命令，add X peek poll 等
 *
 *  6
 *  add 1
 *  add 2
 *  add 3
 *  peek
 *  poll
 *  peek
 *
 *  输出：
 *  遇到peek命令输出队首元素
 *
 *  1
 *  3
 *
 * Analysis:
 *  超时只能过75%数据，GG
 */
public class StackMockQueue {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextInt()) {
            Stack<String> stack1 = new Stack<>();
            Stack<String> stack2 = new Stack<>();

            int n = scanner.nextInt();
            for (int i = 0; i < n; i++) {
                String line = scanner.nextLine();
                String[] command = line.split(" ");
                if (line.equals("")) {
                    i--;
                    continue;
                }
                if (command.length > 1) {
                    // add
                    stack1.push(command[1]);
                }
                else {
                    String order = command[0];
                    switch (order) {
                        case "peek":
                            peek(stack1, stack2);
                            break;
                        case "poll":
                            poll(stack1, stack2);
                    }
                }
            }
        }

    }

    private static void peek(Stack<String> stack1, Stack<String> stack2) {
        if (stack1.size() == 0) {
            return;
        }
        while (!stack1.isEmpty()) {
            stack2.push(stack1.pop());
        }
        System.out.println(stack2.peek());
        while (!stack2.isEmpty()) {
            stack1.push(stack2.pop());
        }
    }

    private static void poll(Stack<String> stack1, Stack<String> stack2) {
        if (stack1.size() == 0) {
            return;
        }
        while (!stack1.isEmpty()) {
            stack2.push(stack1.pop());
        }
        stack2.pop();
        while (!stack2.isEmpty()) {
            stack1.push(stack2.pop());
        }
    }
}
