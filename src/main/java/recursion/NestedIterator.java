package recursion;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * lc 341 medium
 *
 * Analysis:
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2023/1/10
 */
public class NestedIterator {

    Queue<Integer> q = new LinkedList<>();

    public NestedIterator(List<NestedInteger> nestedList) {
        if (null != nestedList && nestedList.size() > 0) {
            for (NestedInteger item : nestedList) {
                if (item.isInteger()) {
                    q.add(item.getInteger());
                    continue;
                }
                flat(item);
            }
        }
    }

    void flat(NestedInteger ni) {
        for (NestedInteger item : ni.getList()) {
            if (item.isInteger()) {
                q.add(item.getInteger());
                continue;
            }
            flat(item);
        }
    }

    public Integer next() {
        return q.remove();
    }

    public boolean hasNext() {
        return q.size() > 0;
    }

    public interface NestedInteger {
      // @return true if this NestedInteger holds a single integer, rather than a nested list.
      public boolean isInteger();

      // @return the single integer that this NestedInteger holds, if it holds a single integer
      // Return null if this NestedInteger holds a nested list
      public Integer getInteger();

      // @return the nested list that this NestedInteger holds, if it holds a nested list
      // Return empty list if this NestedInteger holds a single integer
      public List<NestedInteger> getList();
    }
}
