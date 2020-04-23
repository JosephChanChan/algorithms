package tables.component;

/**
 * @author Joseph
 * @since 2020-03-28 14:34
 *
 * 公用的单向链表节点
 */
public class OneLinkedNode extends ListNode {


    public OneLinkedNode(long data) {
        super(data);
    }

    public OneLinkedNode(long data, OneLinkedNode next) {
        super(data, next);
    }


}
