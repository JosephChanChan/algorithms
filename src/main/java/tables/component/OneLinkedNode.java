package tables.component;

/**
 * @author Joseph
 * @since 2020-03-28 14:34
 *
 * 公用的单向链表节点
 */
public class OneLinkedNode<T> extends Node<T> {


    public OneLinkedNode(T data) {
        super(data);
    }

    public OneLinkedNode(T data, OneLinkedNode<T> next) {
        super(data, next);
    }


}
