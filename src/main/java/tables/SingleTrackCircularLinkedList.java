package tables;

import java.util.List;

/**
 * 单向循环链表，一直遍历到尾部，超出尾部则循环到头部继续遍历。
 *
 * @author: Joseph
 * @datetime: 2019/4/20 16:11
 */
public class SingleTrackCircularLinkedList <T> {

    int count = 0;
    Node<T> head, tail;

    public SingleTrackCircularLinkedList () {
    }

    public SingleTrackCircularLinkedList (List<T> list) {

    }

    public void add (T t) {
        if (null == head) {
            head = new Node<>(null, t, null);
            tail = new Node<>(null, t, null);
        }
        else {
            Node<T> newNode = new Node<>(tail, t, null);
            linkToLast(newNode);
        }
    }

    private void linkToLast (Node<T> newNode) {
        Node<T> previous = newNode.previous;
        previous.next = newNode;
        tail = newNode;
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

