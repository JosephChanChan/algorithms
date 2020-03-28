package tables.component;

/**
 * @author Joseph
 * @since 2020-03-28 14:38
 */
public abstract class Node<T> {

    private T data;

    private Node<T> next;



    /* Constructor */

    public Node() {}

    public Node(T data) {
        this.data = data;
    }

    public Node(T data, Node<T> next) {
        this.data = data;
        this.next = next;
    }


    @Override
    public int hashCode() {
        if (null == data)
            return super.hashCode();
        return data.hashCode() ^ (data.hashCode() >>> 16) | this.hashCode();
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(Object obj) {
        if (null == obj) return false;
        if (!(obj instanceof Node)) return false;

        Node<T> other = (Node<T>) obj;
        if (this == other)
            return true;
        if (data == other.getData() ||
            null != data && null != other.getData() &&
            data.equals(other.getData()))
            return true;

        return super.equals(obj);
    }



    /* Setter And Getter */

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }

}
