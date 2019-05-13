package tables;

import java.util.List;

/**
 * 单向循环链表，一直遍历到尾部，超出尾部则循环到头部继续遍历。
 *
 * @author: Joseph
 * @datetime: 2019/4/20 16:11
 */
public class SingleTrackCircularLinkedList <T> {

    private volatile int count = 0;

    private Node<T> head, tail;

    public SingleTrackCircularLinkedList () {
    }

    public SingleTrackCircularLinkedList (List<T> list) {
    }

    public void add (T t) {
        if (null == head) {
            head = new Node<>(null, t, null);
            tail = head;
        }
        else {
            Node<T> newNode = new Node<>(tail, t, null);
            linkToLast(newNode);
        }
        count++;
    }

    public T get (int index) {
        assertRange(index);
        Node<T> candidate = head;
        for (int i = 1; i <= index && null != candidate.next; i++) {
            candidate = candidate.next;
        }
        return candidate.element;
    }

    /**
     * 从指定位置开始移动 n 步获得节点。
     * 如果到链表尾部，会循环到头部继续遍历，直至移动完毕。
     * Notice: Thread unsafe
     *
     * @param start 指定的开始位置，注意计数时不包括此位置
     * @param stepSize 移动步数
     * @return 节点数据
     */
    public T getFromToIndex (int start, int stepSize) {
        assertRange(start);

        Node<T> point = head;
        while (--start >= 0) {
            // 此类应用在线程安全的环境下，
            // 如下面的代码在多线程环境中由于删除操作，可能导致空指针异常
            point = point.next;
        }

        while (stepSize > 0) {
            // 此时处于尾部，循环回到头部继续
            if (null == point.next) {
                point = head;
                stepSize--;
                continue;
            }
            point = point.next;
            stepSize--;
        }

        return point.element;
    }

    /**
     * 删除链表中首次遇到的符合给定类型的参数节点。与 {@link java.util.LinkedList#remove} 类似。
     *
     * @param removed 被删除元素
     * @return 被删除元素
     */
    public T remove (T removed) {
        T element = null;
        if (removed == head.element) {
            unlink(head);
            element = head.element;
        }
        else {
            Node<T> current = head.next;
            while (null != current) {
                if (current.element == removed) {
                    unlink(current);
                    element = current.element;
                    break;
                }
                current = current.next;
            }
        }
        return element;
    }

    public T remove (int removeIndex) {
        T element = null;

        return element;
    }

    public static void main(String[] args) {
        SingleTrackCircularLinkedList<Integer> linkedList = new SingleTrackCircularLinkedList<>();
        linkedList.add(5);
        linkedList.add(4);
        linkedList.add(3);
        linkedList.add(2);
        linkedList.add(1);
        Integer integer = linkedList.get(0);
    }




    /* ------------------------------------------------- internal method ------------------------------------------------*/

    private void assertRange (int index) {
        if (index < 0 || index > (count - 1)) {
            throw new RuntimeException("the index exceed the range of list !");
        }
    }

    private void linkToLast (Node<T> newNode) {
        Node<T> previous = newNode.previous;
        previous.next = newNode;
        tail = newNode;
    }

    private void unlink (Node<T> node) {
        Node<T> previous = node.previous;
        previous.next = node.next;
        node.next.previous = previous;
        node.previous = null;
        node.next = null;
        node.element = null;
    }


    private static class Node <T> {
        Node<T> next;
        Node<T> previous;
        T element;

        Node (Node<T> previous, T t, Node<T> next){
            this.element = t;
            this.previous = previous;
            this.next = next;
        }
    }

}

