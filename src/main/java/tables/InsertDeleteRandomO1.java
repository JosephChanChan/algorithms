package tables;

import java.util.*;

/**
 * lc 380 medium
 *
 * Analysis:
 * 时间复杂度：平均O(1)
 * 空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2023/1/10
 */
public class InsertDeleteRandomO1 {

    /*
        满足2个条件：插入时间O(1)，能等概率随机查找
        插入O(1)，该数据结构得满足哈希性质
        等概率随机，要满足类似数组性质
        哈希表+双向数组
        双向数组大部分时候add或remove都是O(1)，只有在扩容时是O(n)，平均下来是O(1)

        上面的思路有个问题，我们要使用数组因为依赖数组下标实现等概率访问。map存储了每个val的数组下标，
        但是删除了元素后，它后面的元素会被移动，map中的下标可能就不准了。

        所以总结：用数组，可实现O(1)时间随机，但是删除时存在下标被改变的问题。
        用链表，可实现O(1)时间删除，但是随机要O(n)时间

        这题tricky在于删除时，只能从尾部或头部元素删除，把头部或尾部元素值覆盖被删除元素的值
        题目给了条件：元素值唯一，有时候元素值唯一也是一个解题条件
     */

    Random random = new Random();
    List<int[]> list = new ArrayList<>();
    // val -> list.idx
    Map<Integer, Integer> map = new HashMap<>();


    public InsertDeleteRandomO1() {
    }

    public boolean insert(int val) {
        if (map.containsKey(val)) {
            return false;
        }
        list.add(new int[]{val});
        map.put(val, list.size()-1);
        return true;
    }

    public boolean remove(int val) {
        if (!map.containsKey(val)) {
            return false;
        }
        int idx = map.get(val);
        if (idx == list.size()-1) {
            list.remove(list.size()-1);
        }
        else {
            int tail = list.get(list.size()-1)[0];
            list.get(idx)[0] = tail;
            list.remove(list.size()-1);
            // 更新尾部元素下标
            map.put(tail, idx);
        }
        map.remove(val);
        return true;
    }

    public int getRandom() {
        int idx = random.nextInt(list.size());
        return list.get(idx)[0];
    }
}
