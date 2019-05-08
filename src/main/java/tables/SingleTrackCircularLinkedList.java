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
    }

    public Node<T> get (int index) {
        assertRange(index);
        Node<T> candidate = head;
        for (int i = 1; i <= index && null != candidate.next; i++) {
            candidate = candidate.next;
        }
        return candidate;
    }

    /**
     * 从指定位置开始移动 n 步获得节点
     * Notice: Thread unsafe
     *
     * @param start 指定的开始位置
     * @param stepSize 移动步数
     * @return 节点
     */
    public Node<T> getFromToIndex (int start, int stepSize) {
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

        return point;
    }
    
    public T remove (Node<T> removed) {
        T element = null;
        if (removed == head) {
            unlink(head);
            element = head.element;
        }
        else {
            Node<T> current = head.next;
            while (null != current) {
                if (current == removed) {
                    unlink(current);
                    element = current.element;
                    break;
                }
                current = current.next;
            }
        }
        return element;
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


    private class Node <T> {
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

