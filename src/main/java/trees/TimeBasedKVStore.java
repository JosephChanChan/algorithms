package trees;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * lc 981 medium
 *
 * Analysis:
 *  根据题目特点，要求拿一个key小于等于当前给定时间戳的value，那决定了这个数据结构要以2个维度存值。
 * 第一个维度存key，第二个维度存时间戳。key存hash，时间戳每次要找非严格递增的上一个时间戳，那么用树来组织
 * 数据是比较好的。
 *
 * @author Joseph
 * @since 2021-08-20 22:32
 */
public class TimeBasedKVStore {

    Map<String, Map<Integer, String>> map = new HashMap<>();

    public void set(String key, String value, int timestamp) {
        Map<Integer, String> seg ;
        if (null == (seg = map.get(key))) {
            seg = new TreeMap<>();
            seg.put(timestamp, value);
            map.put(key, seg);
        }
        else {
            seg.put(timestamp, value);
        }
    }

    public String get(String key, int timestamp) {
        Map<Integer, String> seg ;
        if (null == (seg = map.get(key))) {
            return "";
        }
        Map.Entry<Integer, String> e = ((TreeMap) seg).floorEntry(timestamp);
        if (null == e) return "";
        return e.getValue();
    }
}
