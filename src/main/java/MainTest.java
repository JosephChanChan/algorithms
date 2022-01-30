import java.util.HashMap;
import java.util.Map;

/**
 * @author Joseph
 * @since 2021-07-05 16:21
 */
public class MainTest {

    public static void main(String[] args) {
        MainTest m = new MainTest();
        Node o1 = new Node(1);
        Node o2 = new Node(2);
        Node o3 = new Node(3);
        Node o4 = new Node(4);
        Node o5 = new Node(5);
        Node o6 = new Node(6);
        Node o7 = new Node(7);
        o1.next = o2;
        o2.next = o3;
        o3.next = o4;
        o4.next = o5;
        o5.next = o6;
        o6.next = o7;

        Node o8 = new Node(8);
        o8.next = o3;

        final Node node = m.check(o1, o8);
        System.out.println(node.value);
    }

    Node check(Node a, Node b) {
        int n = length(a);
        int m = length(b);

        if (n == 0 || m == 0) return null;

        int k = Math.abs(n-m);
        Node max, min ;
        if (n >= m) {
            max = a;
            min = b;
        }
        else {
            max = b;
            min = a;
        }

        // 先对比max的k节点
        while (k-- > 0) {
            if (max == min) return max;
            max = max.next;
        }

        while (null != max && null != min) {
            if (max == min) return max;
            max = max.next;
            min = min.next;
        }
        return null;
    }

    int length(Node a) {
        int count = 0;
        Node n = a;
        while (null != n) {
            count++;
            n = n.next;
        }
        return count;
    }

    static class Node {
        int value;

        Node next;
        public Node(int v) {
            this.value = v;
        }
    }







}
