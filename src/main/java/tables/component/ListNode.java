package tables.component;

/**
 * @author Joseph
 * @since 2020-03-28 14:38
 */
public abstract class ListNode {

    public int data;

    public ListNode next;



    /* Constructor */
    public ListNode() {}

    public ListNode(int data) {
        this.data = data;
    }

    public ListNode(int data, ListNode next) {
        this.data = data;
        this.next = next;
    }

    @Override
    public int hashCode() {
        if (data == 0)
            return super.hashCode();
        return (data << 5 + 1) | super.hashCode();
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

    public int getData() {
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
