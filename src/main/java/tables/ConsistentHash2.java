package tables;

import java.util.*;

/**
 * lintcode 520 medium
 *
 * Analysis:
 *  一致性哈希算法模板
 *
 * @author Joseph
 * @since 2022-01-31 23:27
 */
public class ConsistentHash2 {

    int n, k ;
    Random random = new Random();
    // n*k个虚节点的坐标
    TreeMap<Integer, Integer> nodes = new TreeMap<>();

    /*
     * @param machine_id: An integer
     * @return: a list of shard ids
     */
    public List<Integer> addMachine(int machine_id) {
        return buildVirtualNodes(machine_id);
    }

    List<Integer> buildVirtualNodes(int mid) {
        List<Integer> v = new ArrayList<>(k);
        int bound = (1 << 31) - 1, i ;
        for (int j = 0; j < k; ) {
            while (!nodes.containsKey((i = random.nextInt(bound) % n))) {
                v.add(i);
                nodes.put(i, mid);
                j++;
                break;
            }
        }
        return v;
    }

    /*
     * @param hashcode: An integer
     * @return: A machine id
     */
    public int getMachineIdByHashCode(int hashcode) {
        int solt = hashcode % n;
        // 大于solt的最小的一个node
        Map.Entry<Integer, Integer> e = nodes.ceilingEntry(solt);
        if (null == e) {
            e = nodes.firstEntry();
        }
        return e.getValue();
    }
}
