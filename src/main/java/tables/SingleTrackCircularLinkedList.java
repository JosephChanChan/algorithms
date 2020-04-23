package tables;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 单向循环链表，一直遍历到尾部，超出尾部则循环到头部继续遍历。
 *
 * @author Joseph
 * @since  2019/4/20 16:11
 */
public class SingleTrackCircularLinkedList <T> {

    private AtomicInteger count = new AtomicInteger(0);

    // volatile 不能保证对 count 写操作时是原子性的，在并发环境中仍可能造成线程安全问题。
    /*private volatile int count = 0;*/

    private Node<T> head, tail;

    public SingleTrackCircularLinkedList () {
    }

    public SingleTrackCircularLinkedList (List<T> list) {
    }

    /**
     * 把元素添加进链表中，尾插法的实现。
     *
     * @param t 元素
     */
    public void add (T t) {
        assertNonNull(t);
        if (null == head) {
            head = new Node<>(null, t, null);
            tail = head;
        }
        else {
            Node<T> newNode = new Node<>(tail, t, null);
            linkToLast(newNode);
        }
        count.incrementAndGet();
    }

    /**
     * 通过下标获取链表中的元素。
     *
     * @param index 下标
     * @return 元素
     */
    public T get (int index) {
        return getNode(index).element;
    }

    /**
     * 在链表中搜索第一个匹配上的目标元素，并且返回目标元素在当前链表中的位置。
     * 需注意如果链表中不存在目标元素则会返回 -1。
     *
     * @param target 目标元素
     * @return 第一个匹配上的目标元素的位置或 -1.
     */
    public int getIndex (T target) {
        assertNonNull(target);
        int index = -1, point = 0;
        Node<T> item = head;
        while (null != item) {
            if (
                    target == item.element ||
                    target.equals(item.element)
               )
            {
                index = point;
                break;
            }
            item = item.next;
            point++;
        }
        return index;
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
     * 根据给定的元素值搜索链表中第一个匹配上的节点，
     * 然后再移动到此节点的下一个节点（有可能会循环到头部），返回下一个节点的下标值。
     *
     * @param current 当前元素值
     * @return 当前元素值的下一个节点下标，可能会循环到头部。
     */
    public int getNextIndex (T current) {
        Node<T> currentNode = getNode(current);
        Node<T> next = tail == currentNode ? head : currentNode.next;
        return getIndex(next);
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

    /*public T remove (int removeIndex) {
        T element = null;

        return element;
    }*/

    /*public static void main(String[] args) {
        SingleTrackCircularLinkedList<Integer> linkedList = new SingleTrackCircularLinkedList<>();
        linkedList.add(5);
        linkedList.add(4);
        linkedList.add(3);
        linkedList.add(2);
        linkedList.add(1);
        Integer integer = linkedList.get(0);
    }*/




    /* ------------------------------------------------- internal method ------------------------------------------------*/

    private void assertRange (int index) {
        if (index < 0 || index > (count.intValue() - 1)) {
            throw new RuntimeException("the index exceed the range of list !");
        }
    }

    private void assertNonNull (T target) {
        if (null == target) {
            throw new RuntimeException("Target Element must not be null !");
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
        count.decrementAndGet();
    }

    private Node<T> getNode (int index) {
        assertRange(index);
        Node<T> candidate = head;
        for (int i = 1; i <= index && null != candidate.next; i++) {
            candidate = candidate.next;
        }
        return candidate;
    }

    private Node<T> getNode (T targetElement) {
        assertNonNull(targetElement);
        Node<T> currentNode = head;
        T currentElement ;
        while (null != currentNode) {
            currentElement = currentNode.element;
            if (
                    currentElement == targetElement ||
                    currentElement.equals(targetElement)
               )
            {
                return currentNode;
            }
            currentNode = currentNode.next;
        }
        return null;
    }

    private int getIndex (Node<T> targetNode) {
        int index = 0;
        Node<T> currentNode = head;
        while (null != currentNode) {
            if (currentNode == targetNode) {
                return index;
            }
            ++index;
            currentNode = currentNode.next;
        }
        return -1;
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

