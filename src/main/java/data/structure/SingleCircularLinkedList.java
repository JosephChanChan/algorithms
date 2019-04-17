package data.structure;

/**
 * 单向循环链表
 *
 * created by Joseph
 * at 2019/4/17 22:50
 */
public class SingleCircularLinkedList <T> {

    int count = 0;
    Node<T> head, tail;

    public void add(T t){
        if (null == head){
            head = new Node<>(t);
        }
        else {
            tail = new Node<>(t);
        }
    }

    public Node get(int index){
        Node cur = head;
        for (int i = 0; i < index; i++){
            cur = cur.next;
        }
        return cur;
    }

    public void remove(int k){
        if (k > count){
            calcIndex(0, k - count);
        }
    }

    private Node calcIndex(int start, int end){
        int i = start;
        Node index = head;
        for ( ; i <= end; i++){
            index = head.next;
        }
        return index;
    }

    private class Node <T> {
        Node next;
        T element;

        Node(T t){
            this.element = t;
        }
    }
}
