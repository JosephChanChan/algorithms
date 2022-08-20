package tables.component;

/**
 * @author Joseph
 * @since 2020-03-28 14:38
 */
public class ListNode {

    public long data;

    public ListNode next;



    /* Constructor */
    public ListNode() {}

    public ListNode(long data) {
        this.data = data;
    }

    public ListNode(int data) {
        this.data = data;
    }

    public ListNode(long data, ListNode next) {
        this.data = data;
        this.next = next;
    }

    @Override
    public int hashCode() {
        if (data == 0)
            return super.hashCode();
        return (int) (data << 5 + 1) | super.hashCode();
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(Object obj) {
        if (null == obj) return false;
        if (!(obj instanceof ListNode)) return false;

        ListNode other = (ListNode) obj;
        if (this == other)
            return true;
        if (data == other.data)
            return true;

        return super.equals(obj);
    }



    /* Setter And Getter */

    public long getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public ListNode getNext() {
        return next;
    }

    public void setNext(ListNode next) {
        this.next = next;
    }
}
